package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Chess;
import model.Draw;
import model.InvalidMoveException;
import model.Move;
import model.Pawn;
import model.Piece;
import model.Piece.Player;
import model.Promotion;
import model.Record;
import model.Square;
import model.Win;
import view.ChessPieceType;
import view.IChessViewer;

public abstract class ViewController  {
	private Piece chosen;
	protected Chess chess;
	private DrawManager drawManager;

	public static final String ERROR_MESSAGE = "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\n"
			+ "you can omit the \"P\" at the begining for a pawn." + "for casting, enter \"O-O\" or \"O-O-O\"\n"
			+ "for examples, \"e2-e4\", \"Nb2-c3\" \n" + "If you need further help, type \"help\"";

	public static final String HELP_MESSAGE = "Enter commands:\n" + "enter 'undo' to undo the previous round;\n"
			+ "enter 'restart' to start a new game over;\n'" + "enter 'print' to print all the records;\n"
			+ "enter 'resign' to give up;\n" + "enter 'draw' to request for draw;\n"
			+ "enter complete or abbreviated algebraic chess notation to make a move;\n"
			+ "enter 'rules for ....' to get help about the rules of chess.\n"
			+ "    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";

	public static final Map<String, String> rules = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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

	public ViewController() {
		chess = new Chess();
		drawManager = new DrawManager();
		chosen = null;
	}

	private void restart() {
		chess = new Chess();
		chosen = null;
		IChessViewer white = chooesView(true);
		IChessViewer black = chooesView(false);
		restartView(white);
		if (black != white)
			restartView(black);
	}

	private void restartView(IChessViewer view) {
		view.deHighLightWholeBoard();
		view.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
		view.printOut("Start a new game!");
	}

	private void repaintAll(IChessViewer view) {
		for (Square sq : chess.getBoard()) {
			if (sq.isOccupied()) {
				view.upDatePiece(sq.getX(), sq.getY(), ChessPieceType.from(sq.getPiece().getType()),
						sq.getPiece().getWhiteOrBlack() == Player.WHITE);
			} else {
				view.clearLabel(sq.getX(), sq.getY());
			}
		}
		view.repaint();
	}

	/**
	 * This method is called if the user enter a command to undo his move. It
	 * will undo two moves.
	 * 
	 */
	private void undo(Chess chess, IChessViewer view) {
		if (!chess.undoLastMove())
			view.printOut("It is already the start of Game");
		else
			view.printOut("Undo the Previous Move!");
	}

	/**
	 * return the requested rules text.
	 * 
	 * @param command
	 * @param whiteOrBlack
	 * @return
	 */
	private void showRules(String command, IChessViewer view, Map<String, String> rules) {
		if (rules.containsKey(command))
			view.printOut(rules.get(command));
		view.printOut("You can get rules for castling, pawn, king, queen, rook, bishop, knight, En Passant, promotion");
	}

	/**
	 * print out the records of the game in starndart chess recording language
	 * 
	 * @param whiteOrBlack
	 * 
	 * @return records
	 */
	private void printRecords(IChessViewer view, Chess chess) {
		Record records = chess.getRecords();
		if (records.isEmpty()) {
			view.printOut("Game hasn't started yet.");
			return;
		}
		view.printOut(records.printDoc());
	}

	public void resign(IChessViewer view, Chess chess) {
		Draw canClaimDraw = chess.canClaimDraw();
		if (canClaimDraw != null) {
			view.printOut("Actually, you can go with a draw!");
			chess.endGame(canClaimDraw);
			return;
		}
		if (chess.getWhoseTurn() == Player.WHITE) {
			chess.endGame(Win.WHITERESIGN);
		} else {
			chess.endGame(Win.BLACKESIGN);
		}
	}

	private void askForDraw(boolean whiteOrBlack) {
		Draw canClaimDraw = chess.canClaimDraw();
		if (canClaimDraw == null) {
			IChessViewer request = chooesView(whiteOrBlack);
			IChessViewer response = chooesView(!whiteOrBlack);
			if (drawManager.canAskFordraw(whiteOrBlack)) {
				while (true) {
					response.printOut(side(whiteOrBlack) + " ask for draw, do you agreed?");
					String command = response.getResponse("Do you agree draw?");
					if (command.isEmpty())
						continue;
					if (command.toLowerCase().startsWith("yes")) {
						chess.endGame(Draw.AGREEMENT);
						return;
					} else if (command.toLowerCase().startsWith("no")) {
						drawManager.setRightToRequestDraw(whiteOrBlack);
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

	protected void updateChessBoard() {
		IChessViewer white = chooesView(true);
		IChessViewer black = chooesView(false);
		repaintAll(white);
		if (black != white)
			repaintAll(black);
	}

	/**
	 * This method will be called, if the user types a command to make a move.
	 * 
	 * Interpret the command, and find out if it is legal to do make this move.
	 * If it is, make this move.
	 * 
	 * @param moveCommand
	 *            the input command
	 * @return
	 */
	public boolean makeMove(IChessViewer view, String moveCommand) {
		Move move = null;
		try {
			move = chess.interpreteMoveCommand(moveCommand);
			chess.makeMove(move);
			updateGuiAfterMove(move);
			return true;
		} catch (InvalidMoveException e) {
			switch (e.type) {
			case InvalidMoveException.invalidFormat:
				view.printOut("The command is not in a valid format.");
				break;
			case InvalidMoveException.ambiguousMove:
				view.printOut("Fail to guess move: There is ambiguity, multiple possible moves.");
				break;
			case InvalidMoveException.castleNotAllowed:
				view.printOut("You cannot do castling, please check the rules for castling.");
				break;
			case InvalidMoveException.impossibleMove:
				view.printOut("This is not a possible move.");
				break;
			case InvalidMoveException.incorrectPiece:
				view.printOut("The chessman in the start Position is not correct! "
						+ "\n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
				break;
			case InvalidMoveException.pieceNotPresent:
				view.printOut("There is no piece at the start position.");
				break;
			case InvalidMoveException.promotionTo:
				view.printOut("You should specify what piece you want to promote to");
				break;
			default:
				throw new RuntimeException(e);
			}
			return false;
		}
	}

	public void handleCommand(String input, boolean whiteOrBlack) {
		IChessViewer view = chooesView(whiteOrBlack);
		if (input.length() == 0)
			return;
		if (input.equals("print")) {
			printRecords(view, chess);
		} else if (input.equals("help")) {
			view.printOut(HELP_MESSAGE);
		} else if (input.startsWith("rules for ")) {
			showRules(input.substring(10), view, rules);
		} else if (input.equals("quit")) {
			// TODO:
		} else if (input.equals("restart")) {
			restart();
			updateChessBoard();
		} else if (chess.hasEnd()) {
			view.printOut(chess.lastMoveDiscript());
		} else {
			chosen = null;
			view.deHighLightWholeBoard();
			if (input.equals("resign")) {
				resign(view, chess);
			} else if (input.equals("draw")) {
				askForDraw(whiteOrBlack);
			} else if (input.equals("undo")) {
				undo(chess, view);
			} else if (!makeMove(view, input)) {
				// makeMove return false, so this move is not allowed.
				view.printOut(ERROR_MESSAGE);
			}
			updateChessBoard();
		}
	}

	public void click(int file, int rank, boolean whiteOrBlack) {
		IChessViewer clickedView = chooesView(whiteOrBlack);
		if (chess.hasEnd()) {
			clickedView.printOut("Game is already over! Type restart to start a new game");
			return;
		}
		if (clickedView != chooesView(chess.getWhoseTurn() == Player.WHITE)) {
			clickedView.printOut("Please wait for your opponnet to finish");
			return;
		}
		Square spot = chess.spotAt(file, rank);
		if (chosen != null) {
			Move move = chosen.getMove(spot);
			if (move == null) {
				clickedView.cleanTemp();
			} else {
				if (move instanceof Promotion) {
					Promotion promotion = (Promotion) move;
					String promoteTo = clickedView.getResponse("What do you want to promote to?");
					promotion.setPromoteTo(Piece.getType(promoteTo.charAt(0)));
				}
				chess.makeMove(move);
				updateGuiAfterMove(move);
			}
			chosen = null;
			clickedView.deHighLightWholeBoard();
		} else {
			if (spot.occupiedBy(chess.getWhoseTurn())) {
				chosen = spot.getPiece();
				ArrayList<Square> reachable = chosen.getReachableSquares();
				reachable.add(spot);
				for (Square sqr : reachable) {
					clickedView.highLight(sqr.getX(), sqr.getY());
				}

				if (spot.getPiece().isType(Pawn.class))
					clickedView.printTemp(spot.toString());
				else
					clickedView.printTemp(spot.getPiece().getType() + spot.toString());
			}
		}
	}

	public abstract IChessViewer chooesView(boolean whiteOrBlack);

	/**
	 * print messages and update GUI when this move just get accomplished
	 * 
	 * @param move
	 */
	protected abstract void updateGuiAfterMove(Move move);

	protected static String side(boolean whoseTurn) {
		return whoseTurn ? "White" : "Black";
	}

}
