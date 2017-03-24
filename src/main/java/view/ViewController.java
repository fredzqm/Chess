package view;

import java.util.ArrayList;
import java.util.Map;

import model.Bishop;
import model.Chess;
import model.Knight;
import model.Piece;
import model.Queen;
import model.Record;
import model.Rook;
import model.Square;
import model.Piece.Player;

public abstract class ViewController {
	protected String side(boolean whoseTurn) {
		return whoseTurn ? "White" : "Black";
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
}
