/**
 * 
 * @author zhangq2
 *
 */
public class Rook extends Piece {
	private final int VALUE = 5;
	
	/**
	 * constructs a Rook with initial square
	 * 
	 * @param type
	 * @param wb
	 * @param Position
	 */
	public Rook( boolean wb, Square Position) {
		super(wb, Position);
	}

	@Override
	public Move legalPosition(Square end) {
		if (legalPosition(spot, end, chess))
			return new Move(this, spot, end.getPiece(), end, chess.getTime());
		return null;
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
	
	@Override
	public int getValue() {
		return VALUE;
	}
	
	@Override
	public char getType() {
		return 'R';
	}

}
