/**
 * 
 * @author zhangq2
 *
 */
public class Queen extends Piece {
	
	/**
	 * constructs a Queen with initial square
	 * @param type
	 * @param wb
	 * @param position
	 */
	public Queen(char type, boolean wb, Square position) {
		super(type, wb, position);
		name = "Queen";
		value= 9;
	}

	@Override
	public boolean legalPosition(Square end, Chess chess) {
		return Bishop.legalPosition(spot, end, chess) || Rook.legalPosition(spot, end, chess);
	}
}
