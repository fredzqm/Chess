package model;

import java.lang.reflect.InvocationTargetException;

import model.Piece.Player;

public class Promotion extends Move {
	private Piece promotedTo;

	/**
	 * creates a record for pawn promotion
	 * 
	 * @param moved
	 *            the pawn that promotes
	 * @param start
	 *            the start square of this move
	 * @param taken
	 *            the piece that in taken when the pawn promotes, null if there
	 *            is not one.
	 * @param end
	 *            the square the pawn promotes
	 * @param time
	 *            which round the pawn promotes
	 */
	public Promotion(Piece moved, Square start, Piece taken, Square end, int round) {
		super(moved, start, taken, end);
		this.promotedTo = null;
	}

	public String getDoc() {
		checkPromotedTo();
		return super.getDoc() + "(" + promotedTo.getType() + ")";
	}

	public String getPrintOut() {
		checkPromotedTo();
		return getDoc() + " Successfully promote to " + promotedTo.getName() + "!";
	}

	public String getDescript() {
		checkPromotedTo();
		String s = "";
		if (super.playerColor == Player.WHITE)
			s += "White ";
		else
			s += "Black ";
		s += "Pawn promotes to ";
		s += promotedTo.getName() + "!!";

		return s;
	}

	public boolean notQuiet() {
		return true;
	}

	public void undo(Chess chess) {
		checkPromotedTo();
		chess.takeOffBoard(promotedTo);
		chess.putBackToBoard(movedPiece, startPosition);
		if (capturedPiece != null)
			chess.putBackToBoard(capturedPiece, lastPosition);
	}

	public void performMove(Chess chess) {
		checkPromotedTo();
		if (capturedPiece != null)
			chess.takeOffBoard(capturedPiece);
		chess.takeOffBoard(movedPiece);
		chess.putBackToBoard(promotedTo, lastPosition);
	}

	public void setPromoteTo(Class<? extends Piece> promotToClass) {
		try {
			promotedTo = promotToClass.getConstructor(Player.class, Square.class, Chess.class)
					.newInstance(this.playerColor, this.lastPosition, this.movedPiece.chess);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	private void checkPromotedTo() {
		if (promotedTo == null)
			throw new RuntimeException("promtedTo is not specified");
	}

}
