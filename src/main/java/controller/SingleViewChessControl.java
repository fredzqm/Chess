package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import model.Bishop;
import model.Chess;
import model.ChessGameException;
import model.Draw;
import model.EndGame;
import model.InvalidMoveException;
import model.Knight;
import model.Move;
import model.Pawn;
import model.Piece;
import model.Piece.Player;
import model.Queen;
import model.Record;
import model.Rook;
import model.Square;
import model.Win;
import view.ChessPieceType;
import view.ChessViewer;
import view.ChessViewerControl;
import view.SquareLabel;

/**
 * The chess controller opens a single chess view
 * 
 * @author zhang
 *
 */
public class SingleViewChessControl implements ChessViewerControl {

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

	private ChessViewer view;
	private Chess chess;
	private Piece chosen;
	private Request drawRequest;

	/**
	 * start my little chess game!!!!
	 * 
	 * @param args
	 *            ignored
	 */
	public SingleViewChessControl() {
		chess = new Chess();
		chosen = null;
		drawRequest = new Request();
		view = new ChessViewer(this, "The Great Chess Game", true);
		repaintAll();
	}

	private void restart() {
		chess = new Chess();
		chosen = null;
		drawRequest = new Request();

		view.deHighLightWholeBoard();
		view.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
		view.printOut("Start a new game!");
		repaintAll();
	}

	private String side(boolean whoseTurn) {
		return whoseTurn ? "White" : "Black";
	}

	/**
	 * return the requested rules text.
	 * 
	 * @param command
	 * @return
	 */
	private void showRules(String command) {
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
			view.printOut("It is already the start of Game");
		else
			view.printOut("Undo the Previous Move!");
	}

	/**
	 * print out the records of the game in starndart chess recording language
	 * 
	 * @return records
	 */
	private void printRecords() {
		Record records = chess.getRecords();
		if (records.isEmpty()) {
			view.printOut("Game hasn't started yet.");
			return;
		}
		view.printOut(records.printDoc());
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
		Move move = null;
		try {
			move = chess.getMove(s);
		} catch (InvalidMoveException e) {
			switch (e.type) {
			case invalidFormat:
				view.printOut("The command is not in a valid format.");
				break;
			case ambiguousMove:
				view.printOut("Fail to guess move: There is ambiguity, multiple possible moves.");
				break;
			case castleNotAllowed:
				view.printOut("You cannot do castling, please check the rules for castling.");
				break;
			case impossibleMove:
				view.printOut("This is not a possible move.");
				break;
			case incorrectPiece:
				view.printOut("The chessman in the start Position is not correct! "
						+ "\n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
				break;
			case pieceNotPresent:
				view.printOut("There is no piece at the start position.");
				break;
			}
		}

		if (move != null) {
			chess.makeMove(move);
			return true;
		}

		return false;
	}

	private SquareLabel squareToLabel(Square sqr, boolean whiteOrBlack) {
		return view.labelAt(sqr.getX(), sqr.getY());
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
		String c = command;
		if (c.length() == 0)
			return;
		if (c.equals("print")) {
			printRecords();
		} else if (c.equals("help")) {
			view.printOut(HELP_MESSAGE);
		} else if (c.startsWith("rules for ")) {
			showRules(c.substring(10));
		} else if (c.equals("quit")) {
			System.exit(0);
		} else if (c.equals("restart")) {
			restart();
		} else if (chess.hasEnd()) {
			view.printOut(chess.lastMoveDiscript());
		} else {
			chosen = null;
			view.deHighLightWholeBoard();
			if (c.equals("resign")) {
				drawRequest.resign();
			} else if (c.equals("draw")) {
				drawRequest.askForDraw();
			} else if (c.equals("undo")) {
				undo();
			} else if (!makeMove(c)) {
				// makeMove return false, so this move is not allowed.
				view.printOut(ERROR_MESSAGE);
			}
		}

		repaintAll();
	}

	private void repaintAll() {
		Collection<Square> board = chess.getAllSquares();
		for (Square sq : board) {
			updateSquare(sq);
		}

		view.repaint();
	}

	@Override
	public void click(SquareLabel label, boolean whiteOrBlack) {
		if (chess.hasEnd()) {
			view.printOut("Game is already over! Type restart to start a new game");
		} else {
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
					view.cleanTemp();
				chosen = null;
				view.deHighLightWholeBoard();
			} else {
				if (spot.occupiedBy(chess.getWhoseTurn())) {
					chosen = spot.getPiece();
					ArrayList<Square> reachable = chess.reachable(chosen);
					reachable.add(spot);
					ArrayList<SquareLabel> hightlight = squareToLabel(reachable, whiteOrBlack);
					view.highLightAll(hightlight);

					if (spot.getPiece().isType(Pawn.class))
						view.printTemp(spot.toString());
					else
						view.printTemp(spot.getPiece().getType() + spot.toString());

				}
			}
		}

		repaintAll();
	}

	public Piece choosePromotePiece(Player wb, Square end) {
		view.cleanTemp();
		while (true) {
			view.printOut("Please choose one kind of piece to promote to -- Q, N, R, B");
			repaintAll();
			String s = view.getResponse("What piece do you want your pawn to romotion to ?");
			if (!s.isEmpty()) {
				s = s.toUpperCase();
				char a = s.charAt(0);
				if (a == 'Q')
					return new Queen(wb, end, chess);
				else if (a == 'R')
					return new Rook(wb, end, chess);
				else if (a == 'B')
					return new Bishop(wb, end, chess);
				else if (a == 'N')
					return new Knight(wb, end, chess);
			}
		}
	}

	public void updateSquare(Square sq) {
		if (sq.isOccupied()) {
			view.labelAt(sq.getX(), sq.getY()).upDatePiece(ChessPieceType.from(sq.getPiece().getType()),
					sq.getPiece().getWhiteOrBlack() == Player.WHITE);
		} else {
			view.labelAt(sq.getX(), sq.getY()).clearLabel();
		}
	}

	public void endGame(EndGame end) {
		if (end == Win.BLACKCHECKMATE || end == Win.WHITECHECKMATE || end == Draw.STALEMENT) {
			view.cleanTemp();
			view.printOut(chess.lastMoveOutPrint());
		}

		view.setStatusLabelText(end.getDescript());
		view.printOut(end.getPrintOut());
	}

	private void updateGuiToMove(Move previousMove) {
		view.setStatusLabelText(chess.lastMoveDiscript());
		view.cleanTemp();
		view.printOut(chess.lastMoveOutPrint());
		view.printOut("Next move -- " + side(previousMove.getWhoseTurn() == Player.BLACK));
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
		 * 
		 * @return
		 */
		public void askForDraw() {
			Draw canClaimDraw = chess.canClaimDraw();
			boolean whiteOrBlack = chess.getWhoseTurn() == Player.WHITE;
			if (canClaimDraw == null) {
				if (canAskFordraw(whiteOrBlack)) {
					while (true) {
						view.printOut(side(whiteOrBlack) + " ask for draw, do you agreed?");
						String command = view.getResponse("Do you agree draw?");
						if (command.isEmpty())
							continue;
						if (command.toLowerCase().startsWith("yes")) {
							chess.endGame(Draw.AGREEMENT);
							return;
						} else if (command.toLowerCase().startsWith("no")) {
							setRightToRequestDraw(whiteOrBlack);
							view.printOut("Request declined");
							return;
						}
					}
				} else {
					view.printOut("You cannot request for draw again now.");
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
		 * @return
		 */
		public void resign() {
			Draw canClaimDraw = chess.canClaimDraw();
			if (canClaimDraw != null) {
				view.printOut("Actually, the game is alreayd an automatic draw!");
				chess.endGame(canClaimDraw);
				return;
			}
			if (chess.getWhoseTurn() == Player.WHITE) {
				chess.endGame(Win.WHITERESIGN);
			} else {
				chess.endGame(Win.BLACKESIGN);
			}
		}
	}

	public static void main(String[] args) {
		new SingleViewChessControl();
	}

}
