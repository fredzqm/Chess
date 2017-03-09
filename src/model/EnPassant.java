package model;
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
		super(moved, start, taken, end, round);
		pawnPos = taken.getSpot();
	}

	public String getDescript() {
		String s = "";
		if (wb)
			s += "White";
		else
			s += "Black";
		s += " Pawn";
		s += " moves to " + end.toString();
		s += " catches En passant pawn on " + pawnPos.toString();
		s += note.getDescriptEnd();
		return s;
	}

	public void undo(Chess chess) {
		moved.moveTo(start);
		if (taken != null) {
			chess.putBackToBoard(taken, pawnPos);
		}
	}
}
