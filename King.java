/**
 * 
 * @author zhangq2
 *
 */
public class King extends Piece {
	private final int VALUE = 100;

	/**
	 * constructs a King with initial square
	 * 
	 * @param type
	 * @param wb
	 * @param Position
	 */
	public King(boolean wb, Square Position) {
		super(wb, Position);
	}

	@Override
	public Move legalPosition(Square end) {
		if (spot.equals(end))
			return null;
		if (Math.abs(spot.X() - end.X()) > 1 || Math.abs(spot.Y() - end.Y()) > 1)
			return null;
		else {
			return new Move(this, spot, end.getPiece(), end, chess.getTime());
		}
	}

	@Override
	protected Move canMove(Square end) {
		if (end.occupied())
			return null;

		if (getX() == 5 && getY() == end.Y()) {
			if (end.X() == 3) {
				return chess.canCastling(this, true) ;
			} else if (end.X() == 7) {
				return chess.canCastling(this, false) ;
			}
		}
		
		if (chess.giveAwayKing(this, spot, null, end, wb))
			return null;
		return legalPosition(end) ;
	}

	public void makeMove(Square end, Piece taken) {
		if (taken == null) {
			if ((spot.X() - end.X()) > 1)
				castling(chess, true);
			else if (end.X() - spot.X() > 1)
				castling(chess, false);
			return;
		}
		if (taken != null)
			chess.takeOffBoard(taken);
		Square start = spot;
		moveTo(end);
		chess.addRecord(this, start, taken, end);
		chess.wrapMove();
	}

	/**
	 * carry on the castling of this king
	 * 
	 * @param rook
	 *            the rook involved in this castling
	 * @param chess
	 * @param longOrShort
	 *            long castling or short castling
	 * @return the output needed for box
	 */
	public void castling(Chess chess, boolean longOrShort) {
		Square kingStart = spot;
		Square kingEnd;
		Square rookStart;
		Square rookEnd;
		Piece rook;
		int y = getY();

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
		chess.addRecord(this, kingStart, kingEnd, (Rook) rook, rookStart, rookEnd);
		moveTo(kingEnd);
		rook.moveTo(rookEnd);
		// return "castling sucessful! " + // TODO: get those printed
		chess.wrapMove();
	}

	@Override
	public int getValue() {
		return VALUE;
	}

	@Override
	public char getType() {
		return 'K';
	}
}
