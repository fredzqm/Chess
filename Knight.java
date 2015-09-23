/**
 * 
 * @author zhangq2
 *
 */
public class Knight extends Piece {
	/**
	 * constructs a Knight with initial square
	 * 
	 * @param type
	 * @param wb
	 * @param Position
	 */
	public Knight(char type, boolean wb, Square Position) {
		super(type, wb, Position);
		name = "Knight";
		value = 3;
	}

	@Override
	public boolean legalPosition(Square end, Chess chess) {
		if (spot.equals(end))
			return false;
		int a = Math.abs(spot.X() - end.X());
		int b = Math.abs(spot.Y() - end.Y());
		if (a + b == 3 && (a != 0 && b != 0))
			return true;
		else
			return false;
	}
}
