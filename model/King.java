package model;
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
			return new Move(this, spot, end.getPiece(), end, chess.getRound());
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

	@Override
	public int getValue() {
		return VALUE;
	}

	@Override
	public char getType() {
		return 'K';
	}
}
