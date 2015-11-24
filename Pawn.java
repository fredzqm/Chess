
/**
 * Pawn in the chess
 * 
 * @author zhangq2
 *
 */
public class Pawn extends Piece {
	private final int VALUE = 1;

	/**
	 * constructs a Pawn with initial square
	 * 
	 * @param wb
	 * @param p
	 */
	public Pawn(boolean wb, Square p) {
		super(wb, p);
	}

	@Override
	public Move legalPosition(Square end) {
		if (legalPosition(spot, end, chess, getWb()))
			return new Move(this, spot, end.getPiece(), end, chess.getTime());
		return null;
	}

	public static boolean legalPosition(Square spot, Square end, Chess chess, boolean wb) {
		if (end.occupied() || spot == null)
			return false;

		if (spot.X() == end.X()) {
			if (wb) {
				if (end.Y() - spot.Y() == 1)
					return true;
				if (end.Y() == 4 && spot.Y() == 2)
					if (!chess.spotAt(spot.X(), 3).occupied())
						return true;
			} else {

				if (end.Y() - spot.Y() == -1)
					return true;
				if (end.Y() == 5 && spot.Y() == 7)
					if (!chess.spotAt(spot.X(), 6).occupied())
						return true;
			}
		}
		return false;
	}

	protected Move canAttack(Square end) {
		if (legalPostionCapture(end))
			return new Move(this, spot, end.getPiece(), end, chess.getTime());
		return null;
	}

	private boolean legalPostionCapture(Square end) {
		if (spot == null)
			return false;
		if (Math.abs(end.X() - spot.X()) == 1) {
			if (wb) {
				if (end.Y() - spot.Y() == 1)
					return true;
			} else {
				if (end.Y() - spot.Y() == -1)
					return true;
			}
		}
		return false;
	}

	protected Move canCapture(Square end) {
		// if (!canAttack(end))
		// return false;
		Move capture = canAttack(end);
		if (capture == null)
			return null;

		if (end.occupiedBy(!wb)) {
			if (chess.giveAwayKing(this, spot, end.getPiece(), end, wb))
				return null;
			return capture;
		} else {
			if (chess.canEnPassant(end))
				return new EnPassant(this, spot, chess.spotAt(end.X(), spot.Y()).getPiece(), end, chess.getTime());
			return null;
		}
		// TODO: will it be necessary to create an En passant move class
		// return chess.canEnPassant(end);
	}

	protected boolean canPromote(Square end) {
		boolean promotion = false;
		if (wb) {
			if (end.Y() == 8)
				promotion = true;
		} else {
			if (end.Y() == 1)
				promotion = true;
		}
		return promotion;
	}

	@Override
	public int getValue() {
		return VALUE;
	}

	@Override
	public char getType() {
		return 'P';
	}
	 

}