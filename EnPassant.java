
public class EnPassant extends Move {
	private Square pawnPos;

	public EnPassant(Piece moved, Square start, Piece taken, Square end, int round) {
		super(moved, start, taken, end, round);
		pawnPos = taken.getP();
	}

	/**
	 * 
	 * @return the words that will appear in the top label of the window
	 */
	public String getDescript() {
		String s = "";
		if (wb)
			s += "White";
		else
			s += "Black";
		s += " Pawn";
		s += " moves to " + end.toString();
		s += " catches En passant pawn on " + pawnPos.toString();
		if (checkOrNot)
			s += " Check!";

		if (endGame != null)
			return s + endGame.getDescript();

		return s;
	}

	public void undo(Chess chess) {
		moved.moveTo(start);
		if (taken != null) {
			chess.putBackToBoard(taken, pawnPos);
		}
	}
}
