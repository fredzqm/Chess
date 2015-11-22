/**
 * 
 * @author zhangq2
 *
 */
public class Rook extends Piece {

	/**
	 * constructs a Rook with initial square
	 * 
	 * @param type
	 * @param wb
	 * @param Position
	 */
	public Rook(char type, boolean wb, Square Position) {
		super(type, wb, Position);
		name = "Rook";
		value = 5;
	}

	@Override
	public boolean legalPosition(Square end) {
		return legalPosition(spot, end, chess);
	}

	protected static boolean legalPosition(Square start, Square end, Chess chess) {
		if (start.equals(end))
			return false;

		if (end.X() == start.X()) {
			int k = (end.Y() - start.Y()) / (Math.abs(end.Y() - start.Y()));
			for (int i = start.Y() + k; i != end.Y(); i += k) {
				if (chess.spotAt(start.X(), i).occupied())
					return false;
			}
			return true;
		} else if (end.Y() == start.Y()) {
			int k = (end.X() - start.X()) / (Math.abs(end.X() - start.X()));
			for (int i = start.X() + k; i != end.X(); i += k) {
				if (chess.spotAt(i, start.Y()).occupied())
					return false;
			}
			return true;
		}
		return false;
	}

}
