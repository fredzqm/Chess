package model;

/**
 * this class records the castling move
 * 
 * @author zhangq2
 *
 */
public class Castling extends Move {
	boolean longOrShort;
	private Rook rook;
	private Square rookStart;

	/**
	 * it creates a record of castling
	 * 
	 * @param king
	 *            the king involved
	 * @param kingStart
	 *            the start square of the king
	 * @param kingEnd
	 *            the end square of the king
	 * @param rook
	 *            the rook involved in this castling
	 * @param rookStart
	 *            the starting position of this rook
	 * @param time
	 *            the round number of this casting
	 */
	public Castling(King king, Square kingStart, Square kingEnd, Rook rook, Square rookStart, int round) {
		super(king, kingStart, null, kingEnd);
		this.rook = rook;
		if (rook.getX() < 4)
			this.longOrShort = true;
		else
			this.longOrShort = false;
		this.rookStart = rookStart;
	}

	public String getDoc() {
		String s;
		if (longOrShort)
			s = "O-O-O";
		else
			s = "O-O";
		s += note.getDocEnd();
		return s;
	}

	public String getPrintOut() {
		return getDoc() + " Successfully castling!";
	}

	public void undo(Chess c) {
		movedPiece.moveTo(startPosition);
		rook.moveTo(rookStart);
	}

	public boolean notQuiet() {
		return false;
	}

	public String getDescript() {
		String s = "";
		if (this.isWhite)
			s += "White ";
		else
			s += "Black ";
		if (longOrShort)
			s += "does long castling";
		else
			s += "does short castling";

		return s;
	}

	public void performMove(Chess chess) {
		Square kingEnd;
		Square rookEnd;
		Piece rook;
		int y = movedPiece.getY();
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
		movedPiece.moveTo(kingEnd);
		rook.moveTo(rookEnd);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Castling) {
			Castling x = (Castling) obj;
			return longOrShort == x.longOrShort && this.rook.equals(x.rook) && this.rookStart.equals(x.rookStart)
					&& super.equals(obj);
		}
		return false;
	}
}
