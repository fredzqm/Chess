
public class EnPassant extends Move {
	private Square pawnPos;
	
	public EnPassant(Piece moved, Square start, Piece taken, Square end, int time) {
		super(moved, start, taken, end, time);
		pawnPos = taken.getP();
	}
	
	
	/**
	 * 
	 * @return the words that will appear in the top label of the window
	 */
	public String getDescript() {
		if (endGame != null)
			return endGame;
		String s = "";
		if (wb)
			s += "White";
		else
			s += "Black";
		s += " Pawn";
		s += " moves to " + end.toString();
		s += " catches En passant pawn on "+ pawnPos.toString();
		if (checkOrNot)
			s += " Check!";
		return s;
	}
	
//	public void performMove(Chess chess) {
//	
//	}
	
}
