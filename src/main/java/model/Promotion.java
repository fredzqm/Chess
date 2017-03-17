package model;

import model.Piece.Color;

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
	public Promotion(Piece moved, Square start, Piece taken, Square end,
			int round) {
		super(moved, start, taken, end, round);
		this.promotedTo = null;
	}

	public String getDoc() {
		return super.getDoc() + "(" + promotedTo.getType() + ")";
	}

	public String getPrintOut() {
		return getDoc() + " Successfully promote to " + promotedTo.getName() + "!";
	}
	
	public void undo(Chess chess) {
		chess.takeOffBoard(promotedTo);
		chess.putBackToBoard(moved, start);
		if (taken != null) {
			chess.putBackToBoard(taken, end);
		}
	}

	public boolean notQuiet() {
		return true;
	}

	public String getDescript() {
		String s = "";
		if (super.color == Color.WHITE)
			s += "White ";
		else
			s += "Black ";
		s += "Pawn promotes to ";
		s += promotedTo.getName() + "!!";

		return s;
	}
	
	public void performMove(Chess chess) {
		if (taken != null)
			chess.takeOffBoard(taken);
		moved.moveTo(end);
		promotedTo = chess.promotion(super.color , end);
		chess.takeOffBoard(taken);
		chess.putBackToBoard(promotedTo, end);
	}
	
}
