package model;

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
	 * @param c
	 * @param Position
	 */
	public Rook( Player c, Square Position) {
		super(c, Position);
	}

	@Override
	public Move legalPosition(Square end) {
		if (legalPosition(spot, end, chess))
			return new RegularMove(this, spot, end.getPiece(), end);
		return null;
	}

	protected static boolean legalPosition(Square start, Square end, Chess chess) {
		if (start.equals(end))
			return false;

		if (end.getX() == start.getX()) {
			int k = (end.getY() - start.getY()) / (Math.abs(end.getY() - start.getY()));
			for (int i = start.getY() + k; i != end.getY(); i += k) {
				if (chess.spotAt(start.getX(), i).isOccupied())
					return false;
			}
			return true;
		} else if (end.getY() == start.getY()) {
			int k = (end.getX() - start.getX()) / (Math.abs(end.getX() - start.getX()));
			for (int i = start.getX() + k; i != end.getX(); i += k) {
				if (chess.spotAt(i, start.getY()).isOccupied())
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
