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
	 * @param wb
	 * @param Position
	 */
	public Knight(boolean wb, Square Position) {
		super( wb, Position);
	}

	@Override
	public boolean legalPosition(Square end) {
		if (spot.equals(end))
			return false;
		int a = Math.abs(spot.X() - end.X());
		int b = Math.abs(spot.Y() - end.Y());
		if (a + b == 3 && (a != 0 && b != 0))
			return true;
		else
			return false;
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
