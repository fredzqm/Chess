package model;

/**
 * 
 * @author zhangq2
 *
 */
public class Bishop extends Piece {
	private final int VALUE = 4;
	private boolean bishopColor;

	/**
	 * constructs a Bishop with initial square
	 * 
	 * @param wb
	 * @param Position
	 * @param chess
	 */
	public Bishop(Player c, Square Position, Chess chess) {
		super(c, Position, chess);
		
		if((Position.getX() == 3 && Position.getY() == 1) ||
				(Position.getX() == 6 && Position.getY() == 8)) {
			bishopColor = false;
		} else {
			bishopColor = true;
		}
	}

	/**
	 * Determine whether this bishop is on the black or white squares.
	 * 
	 * @return True if it is on the white squares,
	 * 			false if it is on the black squares.
	 */
	public boolean getBishopType() {
		return this.bishopColor;
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

		if (Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY())) {
			int k = (end.getX() - start.getX()) / (Math.abs(end.getX() - start.getX()));
			int l = (end.getY() - start.getY()) / (Math.abs(end.getY() - start.getY()));
			int j = start.getY() + l;
			for (int i = start.getX() + k; i != end.getX(); i += k, j += l) {
				if (chess.spotAt(i, j).isOccupied())
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
