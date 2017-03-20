package model;

import model.Piece.Player;

public class EnPassant extends Move {
	private Square pawnPos;

	/**
	 * constructs an En Passant record
	 * 
	 * @param moved
	 *            the pawn moved
	 * @param start
	 *            the start position of this pawn
	 * @param taken
	 *            the En Passant pawn that is captured
	 * @param end
	 *            the end position of moved pawn
	 * @param time
	 *            which round this move happens
	 */
	public EnPassant(Piece moved, Square start, Piece taken, Square end, int round) {
		super(moved, start, taken, end);
		pawnPos = taken.getSpot();
	}

	public String getDescript() {
		String s = "";
		if (this.playerColor == Player.WHITE)
			s += "White";
		else
			s += "Black";
		s += " Pawn";
		s += " moves to " + lastPosition.toString();
		s += " catches En passant pawn on " + pawnPos.toString();
		s += note.getDescriptEnd();
		return s;
	}

	public void undo(Chess chess) {
		movedPiece.moveTo(startPosition);
		if (capturedPiece != null) {
			chess.putBackToBoard(capturedPiece, pawnPos);
		}
	}
}
