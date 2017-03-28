package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.Chess;
import model.Draw;
import model.Piece;
import model.Piece.Player;
import model.Record;
import model.Square;
import model.Win;

public abstract class ViewController {
	protected Piece chosen;
	private boolean whiteCanDraw;
	private boolean blackCanDraw;
	protected Chess chess;
	public static final String ERROR_MESSAGE = "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\n"
			+ "you can omit the \"P\" at the begining for a pawn." + "for casting, enter \"O-O\" or \"O-O-O\"\n"
			+ "for examples, \"e2-e4\", \"Nb2-c3\" \n" + "If you need further help, type \"help\"";

	public static final String HELP_MESSAGE = "Enter commands:\n" + "enter 'undo' to undo the previous round;\n"
			+ "enter 'restart' to start a new game over;\n'" + "enter 'print' to print all the records;\n"
			+ "enter 'resign' to give up;\n" + "enter 'draw' to request for draw;\n"
			+ "enter complete or abbreviated algebraic chess notation to make a move;\n"
			+ "enter 'rules for ....' to get help about the rules of chess.\n"
			+ "    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";
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


	public ViewController() {
		this.whiteCanDraw = true;
		this.blackCanDraw = true;
		chess = new Chess();
		chosen = null;
	}

	protected String side(boolean whoseTurn) {
		return whoseTurn ? "White" : "Black";
	}

	protected void restartView(ChessViewer view) {
		chess = new Chess();
		chosen = null;

		view.deHighLightWholeBoard();
		view.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
		for (Square s : chess.getAllSquares())
			updateSquare(view, s);
		view.printOut("Start a new game!");
		repaintAll(view);
	}

	protected void updateSquare(ChessViewer view, Square sq) {
		if (sq.isOccupied()) {
			view.labelAt(sq.getX(), sq.getY()).upDatePiece(ChessPieceType.from(sq.getPiece().getType()),
					sq.getPiece().getWhiteOrBlack() == Player.WHITE);
		} else {
			view.labelAt(sq.getX(), sq.getY()).clearLabel();
		}
	}

	/**
	 * This method is called if the user enter a command to undo his move. It
	 * will undo two moves.
	 * 
	 */
	protected void undo(Chess chess, ChessViewer view) {
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
	protected void showRules(String command, ChessViewer view, Map<String, String> rules) {
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
	protected void printRecords(ChessViewer view, Chess chess) {
		Record records = chess.getRecords();
		if (records.isEmpty()) {
			view.printOut("Game hasn't started yet.");
			return;
		}
		view.printOut(records.printDoc());
	}

	protected SquareLabel squareToLabel(Square sqr, ChessViewer view) {
		return view.labelAt(sqr.getX(), sqr.getY());
	}

	protected ArrayList<SquareLabel> getAllViewLabels(ArrayList<Square> squares, ChessViewer view) {
		ArrayList<SquareLabel> list = new ArrayList<SquareLabel>();
		for (Square sqr : squares)
			list.add(squareToLabel(sqr, view));
		return list;
	}

	protected Square labelToSquare(SquareLabel sql, Chess chess) {
		return chess.spotAt(sql.X(), sql.Y());
	}

	public void resign(ChessViewer view, Chess chess) {
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

	protected boolean canAskFordraw(boolean whoseTurn) {
		if (whoseTurn)
			return whiteCanDraw;
		else
			return blackCanDraw;
	}

	protected void setRightToRequestDraw(boolean whoseTurn) {
		if (whoseTurn) {
			whiteCanDraw = false;
			blackCanDraw = true;
		} else {
			whiteCanDraw = true;
			blackCanDraw = false;
		}
	}

	public void repaintAll(ChessViewer view) {
		Collection<Square> board = chess.getAllSquares();
		for (Square sq : board) {
			updateSquare(view, sq);
		}

		view.repaint();
	}
	
	public void handleSingleCommand(ChessViewer view, String command, boolean whiteOrBlack) {
		String c = command;
		if (c.length() == 0)
			return;
		if (c.equals("print")) {
			printRecords(view, chess);
		} else if (c.equals("help")) {
			view.printOut(HELP_MESSAGE);
		} else if (c.startsWith("rules for ")) {
			showRules(c.substring(10), view, rules);
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
				resign(view, chess);
			} else if (c.equals("draw")) {
				askForDraw(whiteOrBlack);
			} else if (c.equals("undo")) {
				undo(chess, view);
			} else if (!makeMove(c)) {
				// makeMove return false, so this move is not allowed.
				view.printOut(ERROR_MESSAGE);
			}
		}

		repaintAll(view);
	}
	
	public abstract void askForDraw(boolean whiteOrBlack);
	public abstract boolean makeMove(String s);
	public abstract void restart();
	public abstract void click(SquareLabel squareLabel, boolean whiteOrBlack);
}
