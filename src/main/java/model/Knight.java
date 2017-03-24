package model;

/**
 * 
 * @author zhangq2
 *
 */
public class Knight extends Piece {
	private final int VALUE = 3;

	/**
	 * constructs a Knight with initial square
	 * 
	 * @param type
	 * @param c
	 * @param Position
	 * @param chess
	 */
	public Knight(Player c, Square Position, Chess chess) {
		super( c, Position, chess);
	}

	@Override
	public Move legalPosition(Square end) {
		if (spot.equals(end))
			return null;
		int a = Math.abs(spot.X() - end.X());
		int b = Math.abs(spot.Y() - end.Y());
		if (a + b == 3 && (a != 0 && b != 0))
			return new RegularMove(this, spot, end.getPiece(), end);
		else
			return null;
	}

	@Override
	public int getValue() {
		return VALUE;
	}

	@Override
	public char getType() {
		return 'N';
	}
}
