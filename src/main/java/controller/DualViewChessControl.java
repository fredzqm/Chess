package controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Bishop;
import model.Chess;
import model.ChessGameException;
import model.Draw;
import model.EndGame;
import model.Knight;
import model.Move;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Record;
import model.Rook;
import model.Square;
import model.Win;
import model.Piece.Color;
import view.ChessPieceType;
import view.ChessViewer;
import view.ChessViewerControl;
import view.SquareLabel;

/**
 * 
 * A chess controller that control two ChessView, each views the board separately
 * 
 * @author zhang
 *
 */
public class DualViewChessControl implements ChessViewerControl {

	/**
	 * printed when command line input cannot be recognized
	 */
	public static final String ERROR_MESSAGE = "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\n"
			+ "you can omit the \"P\" at the begining for a pawn." + "for casting, enter \"O-O\" or \"O-O-O\"\n"
			+ "for examples, \"e2-e4\", \"Nb2-c3\" \n" + "If you need further help, type \"help\"";

	/**
	 * printed when asked for help message
	 */
	public static final String HELP_MESSAGE = "Enter commands:\n" + "enter 'undo' to undo the previous round;\n"
			+ "enter 'restart' to start a new game over;\n'" + "enter 'print' to print all the records;\n"
			+ "enter 'resign' to give up;\n" + "enter 'draw' to request for draw;\n"
			+ "enter complete or abbreviated algebraic chess notation to make a move;\n"
			+ "enter 'rules for ....' to get help about the rules of chess.\n"
			+ "    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";

	/**
	 * rule message
	 */
	public static final HashMap<String, String> rules = new HashMap<String, String>() {
		{
			put("castling",
					"Only under those circumstances, you can castling\n"
							+ "1.Your king and the corresponding rook has never been moved.\n"
							+ "2.There is no chessman between your king and rook.\n"
							+ "3.The squres that your king goes over should not under attack by any pieces of the opponent.\n"
							+ "4.Your king cannot be in check either before and after the castling.");
			put("pawn",
					"The pawn may move forward to the unoccupied square immediately in front of it on the same file without capturing, "
							+ "or advance two squares along the same file without capturing on its first move;"
							+ "or capture an opponent's piece on a square diagonally in front of it on an adjacent file.\n"
							+ "En Passant and promotion are also special rules for pawn.");
			put("king", "The king moves one square in any direction. You always want to protect your king.\n"
					+ "Castling is also a special rule for king.");
			put("queen",
					"The queen combines the power of the rook and bishop and can move any number of squares along rank, file, or diagonal, without going over any pieces");
			put("rook", "The rook can move any number of squares along any rank or file without going over any pieces");
			put("bishop", "The bishop can move any number of squares diagonally, without going over any pieces.");
			put("knight",
					"The knight moves to any of the closest squares that are not on the same rank, file, or diagonal, thus the move forms an \"L\"-shape:");
			put("en passant", "En passant move:\n"
					+ "When a player moves a pawn 2 squares then on the very next move, the other player moves their pawn diagonally forward 1 square to the square that pawn moved through, capturing it in the process, the latter is said to be doing en passant. "
					+ "Note that the pawn does not move to the square of the pawn it captured in en passant.\n");
			put("promotion",
					"When a pawn reaches its eighth rank, it is immediately changed into the player's choice of a queen, knight, rook, or bishop of the same color.");
		}
	};

	private ChessViewer whiteView;
	private ChessViewer blackView;
	private Chess chess;
	private Piece chosen;
	private Request drawRequest;

	/**
	 * start my little chess game!!!!
	 * 
	 * @param args
	 *            ignored
	 */
	public DualViewChessControl() {
		chess = new Chess();
//		chess.addChessListener(this);
		chosen = null;
		drawRequest = new Request();

		whiteView = new ChessViewer(this, "The Great Chess Game white view" , true);
		blackView = new ChessViewer(this, "The Great Chess Game black view" , false);
		
		for (Square s : chess.getAllSquares())
			updateSquare(s);
		repaintAll();
	}

	private void restart() {
		chess = new Chess();
//		chess.addChessListener(this);
		chosen = null;
		drawRequest = new Request();

		whiteView.deHighLightWholeBoard();
		blackView.deHighLightWholeBoard();
		whiteView.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
		blackView.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
		for (Square s : chess.getAllSquares())
			updateSquare(s);
		whiteView.printOut("Start a new game!");
		blackView.printOut("Start a new game!");
	}

	private ChessViewer chooesView(boolean whiteOrBlack) {
		return whiteOrBlack ? whiteView : blackView;
	}

	private String side(boolean whoseTurn) {
		return whoseTurn ? "White" : "Black";
	}

	/**
	 * return the requested rules text.
	 * 
	 * @param command
	 * @param whiteOrBlack
	 * @return
	 */
	private void showRules(String command, boolean whiteOrBlack) {
		ChessViewer view = chooesView(whiteOrBlack);
		if (rules.containsKey(command))
			view.printOut(rules.get(command));
		view.printOut("You can get rules for castling, pawn, king, queen, rook, bishop, knight, En Passant, promotion");
	}

	/**
	 * This method is called if the user enter a command to undo his move. It
	 * will undo two moves.
	 * 
	 */
	private void undo() {
		if (!chess.undoLastMove())
			whiteView.printOut("It is already the start of Game");
		else
			whiteView.printOut("Undo the Previous Move!");
	}

	/**
	 * print out the records of the game in starndart chess recording language
	 * 
	 * @param whiteOrBlack
	 * 
	 * @return records
	 */
	private void printRecords(boolean whiteOrBlack) {
		Record records = chess.getRecords();
		if (records.isEmpty()) {
			chooesView(whiteOrBlack).printOut("Game hasn't started yet.");
			return;
		}
		chooesView(whiteOrBlack).printOut(records.printDoc());
	}

	/**
	 * This method will be called, if the user types a command to make a move.
	 * 
	 * Interpret the command, and find out if it is legal to do make this move.
	 * If it is, make this move.
	 * 
	 * @param s
	 *            the input command
	 * @return
	 */
	private boolean makeMove(String s) {
		if (s.startsWith("o")) {
			if (s.equals("o-o")) {
				if (!chess.castling(false))
					whiteView.printOut("You cannot do castling, please check the rules for castling.");
			} else if (s.equals("o-o-o")) {
				if (!chess.castling(true))
					whiteView.printOut("You cannot do castling, please check the rules for castling.");
			} else {
				whiteView.printOut("For short castling, enter \"O-O\" and for long castling, enter \"O-O-O\".");
			}
			return true;
		}

		Pattern p = Pattern.compile("([prnbqk])?([a-h])?([1-8])?.*?([a-h][1-8]).*");
		Matcher m = p.matcher(s);
		if (m.matches())

		{
			Class<? extends Piece> type = m.group(1) == null ? Pawn.class : Piece.getType(m.group(1).charAt(0));
			if (type == Piece.class) {
				whiteView.printOut(
						"Please enter valid initial of chessman -- R(Root), N(Knight), B(Bishop), Q(Queen), K(King). If you omit it, it is assumed as Pawn.");
				// return true;
			}
			Square start = null;
			if ((m.group(2) != null) && (m.group(3) != null)) {
				start = chess.getSquare(m.group(2) + m.group(3));
			}
			Square end = chess.getSquare(m.group(4));

			if (start != null) {
				Piece movedChessman = start.getPiece();
				if (movedChessman == null) {
					if (chess.getWhoseTurn() == Color.WHITE)
						whiteView.printOut("There should be a white chessman in the start Position!");
					else
						whiteView.printOut("There should be a black chessman in the start Position!");
				} else if (!(movedChessman.isType(type))) {
					whiteView.printOut(
							"The chessman in the start Position is not corret! \n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
				}
				Move move;
				if ((move = chess.performMove(movedChessman, end)) == null) {
					whiteView.printOut("Illegal move! Please check the rule of " + movedChessman.getName() + "!");
				} else {
					updateGuiToMove(move);
				}
				// return;
			} else {
				System.out.println(" " + type + " " + end);
				ArrayList<Piece> possible = chess.possibleMovers(type, end);
				System.out.println(possible);
				if (possible.size() == 0) {
					whiteView.printOut("Fail to guess move: No one can reach that spot.");
				} else if (possible.size() == 1) {
					Move move;
					if ((move = chess.performMove(possible.get(0), end)) == null) {
						throw new RuntimeException("OOOOO!");
					} else {
						updateGuiToMove(move);
					}
				} else {
					whiteView.printOut("Fail to guess move: There is ambiguity, multiple possible moves.");
				}

			}
			return true;
		}
		return false;

	}

	private SquareLabel squareToLabel(Square sqr, boolean whiteOrBlack) {
		return chooesView(whiteOrBlack).labelAt(sqr.X(), sqr.Y());
	}

	private ArrayList<SquareLabel> squareToLabel(ArrayList<Square> squares, boolean whiteOrBlack) {
		ArrayList<SquareLabel> list = new ArrayList<SquareLabel>();
		for (Square sqr : squares)
			list.add(squareToLabel(sqr, whiteOrBlack));
		return list;
	}

	private Square labelToSquare(SquareLabel sql) {
		return chess.spotAt(sql.X(), sql.Y());
	}

	@Override
	public void handleCommand(String command, boolean whiteOrBlack) {
		String c = command.toLowerCase();
		if (c.length() == 0)
			return;
		if (c.equals("print")) {
			printRecords(whiteOrBlack);
		} else if (c.equals("help")) {
			chooesView(whiteOrBlack).printOut(HELP_MESSAGE);
		} else if (c.startsWith("rules for ")) {
			showRules(c.substring(10), whiteOrBlack);
		} else if (c.equals("quit")) {
			System.exit(0);
		} else if (chess.hasEnd()) {
			if (c.equals("restart"))
				restart();
			else
				chooesView(whiteOrBlack).printOut(chess.lastMoveDiscript());
		} else {
			chosen = null;
			chooesView(whiteOrBlack).deHighLightWholeBoard();
			if (c.equals("resign")) {
				drawRequest.resign(whiteOrBlack);
			} else if (c.equals("draw")) {
				drawRequest.askForDraw(whiteOrBlack);
			} else if (whiteOrBlack != (chess.getWhoseTurn() == Color.WHITE)) {
				if (c.equals("undo"))
					undo();
				else
					chooesView(whiteOrBlack).printOut("Please wait for " + side(!whiteOrBlack) + " to finish");
			} else if (!makeMove(c)) {
				// makeMove return false, so this move is not allowed.
				chooesView(whiteOrBlack).printOut(ERROR_MESSAGE);
			}
		}
		
		repaintAll();
	}

	private void repaintAll() {
		whiteView.repaint();
		blackView.repaint();
	}

	@Override
	public void click(SquareLabel label, boolean whiteOrBlack) {
		ChessViewer clickedView = chooesView(whiteOrBlack);
		if (chess.hasEnd()) {
			clickedView.printOut("Game is already over! Type restart to start a new game");
		} else {
			if (whiteOrBlack != (chess.getWhoseTurn() == Color.WHITE)) {
				clickedView.printOut("Please wait for your opponnet to finish");
				return;
			}
			Square spot = labelToSquare(label);
			if (chosen != null) {
				if (label.isHighLight() && !spot.equals(chosen.getSpot())) {
					Move move;
					if ((move = chess.performMove(chosen, spot)) == null) {
						throw new ChessGameException(
								"Illegal move of " + chosen.getName() + " did not correctly caught from UI!");
					} else {
						updateGuiToMove(move);
					}
				} else
					clickedView.cleanTemp();
				chosen = null;
				clickedView.deHighLightWholeBoard();
			} else {
				if (spot.occupiedBy(chess.getWhoseTurn())) {
					chosen = spot.getPiece();
					ArrayList<Square> reachable = chess.reachable(chosen);
					reachable.add(spot);
					ArrayList<SquareLabel> hightlight = squareToLabel(reachable, whiteOrBlack);
					clickedView.highLightAll(hightlight);

					if (spot.getPiece().isType(Pawn.class))
						clickedView.printTemp(spot.toString());
					else
						clickedView.printTemp(spot.getPiece().getType() + spot.toString());

				}
			}
		}
		
		repaintAll();
	}

	public Piece choosePromotePiece(Color wb, Square end) {
		chooesView(wb == Color.WHITE).cleanTemp();
		while (true) {
			chooesView(wb== Color.WHITE).printOut("Please choose one kind of piece to promote to -- Q, N, R, B");
			repaintAll();
			String s = chooesView(wb == Color.WHITE).getResponse("What piece do you want your pawn to romotion to ?");
			if (!s.isEmpty()) {
				s = s.toUpperCase();
				char a = s.charAt(0);
				if (a == 'Q')
					return new Queen(wb, end);
				else if (a == 'R')
					return new Rook(wb, end);
				else if (a == 'B')
					return new Bishop(wb, end);
				else if (a == 'N')
					return new Knight(wb, end);
			}
		}
	}

	public void updateSquare(Square sq) {
		if (sq.occupied()) {
			whiteView.labelAt(sq.X(), sq.Y()).upDatePiece(ChessPieceType.from(sq.getPiece().getType()),
					sq.getPiece().getWhiteOrBlack() == Color.WHITE);
			blackView.labelAt(sq.X(), sq.Y()).upDatePiece(ChessPieceType.from(sq.getPiece().getType()),
					sq.getPiece().getWhiteOrBlack() == Color.WHITE);
		} else {
			whiteView.labelAt(sq.X(), sq.Y()).clearLabel();
			blackView.labelAt(sq.X(), sq.Y()).clearLabel();
		}
	}

	public void endGame(EndGame end) {
		if (end == Win.BLACKCHECKMATE || end == Win.WHITECHECKMATE || end == Draw.STALEMENT){
			whiteView.cleanTemp();
			blackView.cleanTemp();
			whiteView.printOut(chess.lastMoveOutPrint());
			blackView.printOut(chess.lastMoveOutPrint());
		}
		
		whiteView.setStatusLabelText(end.getDescript());
		whiteView.printOut(end.getPrintOut());
		blackView.setStatusLabelText(end.getDescript());
		blackView.printOut(end.getPrintOut());
		if (end.getResult() > 0)
			whiteView.printOut("Congratuations! you win!");
		else if (end.getResult() < 0)
			blackView.printOut("Congratuations! you win!");
	}

	private void updateGuiToMove(Move previousMove) {
		Collection<Square> board = chess.getAllSquares();
		for(Square sq : board) {
			updateSquare(sq);
		}
		
		ChessViewer pre = chooesView(previousMove.getWhoseTurn() == Color.WHITE);
		ChessViewer next = chooesView(previousMove.getWhoseTurn() == Color.BLACK);

		whiteView.setStatusLabelText(chess.lastMoveDiscript());
		blackView.setStatusLabelText(chess.lastMoveDiscript());
		pre.cleanTemp();
		whiteView.printOut(chess.lastMoveOutPrint());
		blackView.printOut(chess.lastMoveOutPrint());
		next.printOut("Please make your move.");
		pre.printOut("Wait for the " + side(previousMove.getWhoseTurn() == Color.BLACK) + " to make a move");
		
	}
	
	public void nextMove(Move previousMove) {
		
	}

	/**
	 * According to the chess law, no player can request for draw consecutively.
	 * if he has just made a request for draw, he cannot make another request
	 * for draw, untill his opponent makes a request for draw, and is declined.
	 * 
	 * The class is made to decide whether a player can request for draw.
	 */
	protected class Request {
		private boolean white;
		private boolean black;

		protected Request() {
			white = true;
			black = true;
		}

		private void setRightToRequestDraw(boolean whoseTurn) {
			if (whoseTurn) {
				white = false;
				black = true;
			} else {
				white = true;
				black = false;
			}
		}

		private boolean canAskFordraw(boolean whoseTurn) {
			if (whoseTurn)
				return white;
			else
				return black;
		}

		/**
		 * invoked when one player is asking for a draw.
		 * 
		 * Find out if the game satisfied automatic draw condition due to
		 * FIFTY_MOVE or REPETITION {@link Draw. End
		 * @param whiteOrBlack  the game and claim draw if
		 * those conditions are met. Otherwise, send a request for draw, and
		 * wait for the reply of opponent.
		 * 
		 * @return
		 */
		public void askForDraw(boolean whiteOrBlack) {
			Draw canClaimDraw = chess.canClaimDraw();
			if (canClaimDraw == null) {
				ChessViewer request = chooesView(whiteOrBlack);
				ChessViewer response = chooesView(!whiteOrBlack);
				if (canAskFordraw(whiteOrBlack)) {
					while (true) {
						response.printOut(side(whiteOrBlack) + " ask for draw, do you agreed?");
						String command = response.getResponse("Do you agree draw?");
						command = "no";
						if (command.isEmpty())
							continue;
						if (command.toLowerCase().startsWith("yes")) {
							chess.endGame(Draw.AGREEMENT);
							return;
						} else if (command.toLowerCase().startsWith("no")) {
							setRightToRequestDraw(whiteOrBlack);
							request.printOut("Request declined");
							return;
						}
					}
				} else {
					request.printOut("You cannot request for draw again now.");
				}
			} else {
				chess.endGame(canClaimDraw);
			}
		}

		/**
		 * Invoked if a player resigns. It will ends the game. However, if the
		 * game satisfied automatic draw condition, the game will be draw
		 * instead.
		 * 
		 * @param whiteOrBlack
		 * 
		 * @return
		 */
		public void resign(boolean whiteOrBlack) {
			Draw canClaimDraw = chess.canClaimDraw();
			if (canClaimDraw != null) {
				chooesView(whiteOrBlack).printOut("Actually, you can go with a draw!");
				chess.endGame(canClaimDraw);
				return;
			}
			if (whiteOrBlack) {
				chess.endGame(Win.WHITERESIGN);
			} else {
				chess.endGame(Win.BLACKESIGN);
			}
		}
	}
	
	public static void main(String[] args){
		new DualViewChessControl();
	}

}
