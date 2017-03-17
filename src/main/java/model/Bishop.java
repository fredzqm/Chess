package model;

/**
 * 
 * @author zhangq2
 *
 */
public class Bishop extends Piece {
	private final int VALUE = 4;

	/**
	 * constructs a Bishop with initial square
	 * 
	 * @param wb
	 * @param Position
	 */
	public Bishop(Player c, Square Position) {
		super(c, Position);
	}

	@Override
	public Move legalPosition(Square end) {
		if (legalPosition(spot, end, chess))
			return new Move(this, spot, end.getPiece(), end, chess.getRound());
		return null;
	}

	protected static boolean legalPosition(Square start, Square end, Chess chess) {
		if (start.equals(end))
			return false;

		if (Math.abs(start.X() - end.X()) == Math.abs(start.Y() - end.Y())) {
			int k = (end.X() - start.X()) / (Math.abs(end.X() - start.X()));
			int l = (end.Y() - start.Y()) / (Math.abs(end.Y() - start.Y()));
			int j = start.Y() + l;
			for (int i = start.X() + k; i != end.X(); i += k, j += l) {
				if (chess.spotAt(i, j).occupied())
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
		return 'B';
	}

}
