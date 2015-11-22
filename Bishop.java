
/**
 * 
 * @author zhangq2
 *
 */
public class Bishop extends Piece {
	/**
	 * constructs a Bishop with initial square
	 * @param type
	 * @param wb
	 * @param Position
	 */
	public Bishop(char type, boolean wb, Square Position) {
		super(type, wb, Position);
		name  = "Bishop" ;
		value = 4 ;
	}

	@Override
	public boolean legalPosition(Square end) {
		return legalPosition(spot, end, chess);
	}
	protected static boolean legalPosition(Square start , Square end, Chess chess){
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
}
