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
	public String getDiscript() {
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
	
}
