/**
 * this class records the castling move
 * @author zhangq2
 *
 */
public class Castling extends Move {
	boolean longOrShort;
	private Rook rook;
	private Square rookStart;
	
	/**
	 * it creates a record of castling
	 * @param king   the king involved
	 * @param kingStart  the start square of the king
	 * @param kingEnd  the end square of the king
	 * @param rook  the rook involved in this castling
	 * @param rookStart  the starting position of this rook
	 * @param time  the round number of this casting
	 * @param check  whether this castling make a check.
	 */
	public Castling(King king, Square kingStart, Square kingEnd, Rook rook,Square rookStart , int time) {
		super(king, kingStart, null, kingEnd, time);
		this.rook = rook;
		if (rook.getX()<4)
			this.longOrShort = true;
		else
			this.longOrShort = false;
		this.rookStart = rookStart;
	}
	
	/**
	 * it creates a record of castling
	 * @param king   the king involved
	 * @param kingStart  the start square of the king
	 * @param kingEnd  the end square of the king
	 * @param rook  the rook involved in this castling
	 * @param rookStart  the starting position of this rook
	 * @param time  the round number of this casting
	 * @param check  whether this castling make a check.
	 */
	public Castling(King king, Square kingStart, Square kingEnd, Rook rook,Square rookStart , int time, boolean check) {
		super(king, kingStart, null, kingEnd, time, check);
		this.rook = rook;
		if (rook.getX()<4)
			this.longOrShort = true;
		else
			this.longOrShort = false;
		this.rookStart = rookStart;
	}
	
	public String outPrint(){
		String s;
		if (longOrShort)
			s = "O-O-O";
		else
			s = "O-O";
		if (checkOrNot)
			s +="+";
		return s;
	}
	public void undo(Chess c){
		moved.moveTo(start);
		rook.moveTo(rookStart);
	}
	public boolean notQuiet(){
		return false;
	}
	public String getDescript() {
		String s = "";
		if (wb)
			s += "White ";
		else
			s += "Black ";
		if (longOrShort)
			s += "does long castling";
		else
			s += "does short castling";
		
		if (endGame != null)
			s += endGame;
		return s;
	}
	
	public void performMove(Chess chess) {
//		public void castling(Chess chess, boolean longOrShort) {
			Square kingStart = start;
			Square kingEnd;
			Square rookStart;
			Square rookEnd;
			Piece rook;
			int y = moved.getY();
			if (longOrShort) {
				kingEnd = chess.spotAt(3, y);
				rookStart = chess.spotAt(1, y);
				rookEnd = chess.spotAt(4, y);
				rook = chess.spotAt(1, y).getPiece();
			} else {
				kingEnd = chess.spotAt(7, y);
				rookStart = chess.spotAt(8, y);
				rookEnd = chess.spotAt(6, y);
				rook = chess.spotAt(8, y).getPiece();
			}
//			chess.addRecord(this, kingStart, kingEnd, (Rook) rook, rookStart, rookEnd);
			moved.moveTo(kingEnd);
			rook.moveTo(rookEnd);
			// return "castling sucessful! " + // TODO: get those printed
//			chess.wrapMove();
//		}
	}
}
