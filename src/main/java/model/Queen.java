package model;

/**
 * 
 * @author zhangq2
 *
 */
public class Queen extends Piece {
	private final int VALUE = 10;

	/**
	 * constructs a Queen with initial square
	 * 
	 * @param type
	 * @param wb
	 * @param position
	 * @param chess
	 */
	public Queen(boolean isWhite, Square position, Chess chess) {
		super(isWhite, position, chess);
	}
	
	@Override
	public Move legalPosition(Square end) {
		if (Bishop.legalPosition(spot, end, chess) || Rook.legalPosition(spot, end, chess))
			return new RegularMove(this, spot, end.getPiece(), end);
		return null;
	}

	@Override
	public int getValue() {
		return VALUE;
	}

	@Override
	public char getType() {
		return 'Q';
	}
}
