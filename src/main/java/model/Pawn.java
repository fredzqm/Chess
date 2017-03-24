package model;

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
	 * @param c
	 * @param p
	 * @param chess
	 */
	public Pawn(Player c, Square p, Chess chess) {
		super(c, p, chess);
	}

	@Override
	public Move legalPosition(Square end) {
		if (legalPosition(spot, end, chess, getWhiteOrBlack())) {
			if (canPromote(end))
				return new Promotion(this, spot, end.getPiece(), end, chess.getRound());
			return new RegularMove(this, spot, end.getPiece(), end);
		}
		return null;
	}

	public static boolean legalPosition(Square spot, Square end, Chess chess, Player c) {
		if (end.occupied() || spot == null)
			return false;

		if (spot.X() == end.X()) {
			if (c == Player.WHITE) {
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

	protected Move getMove(Square end) {
		Move move = legalPosition(end);
		if (move != null) {
			if (chess.giveAwayKing(move))
				return null;
			return move;
		}
		move = canAttack(end);
		if (move == null)
			return null;
		
		Player otherColor;
		if(super.color == Player.WHITE) {
			otherColor = Player.BLACK;
		} else {
			otherColor = Player.WHITE;
		}
		
		if (end.occupiedBy(otherColor)) {
			if (chess.giveAwayKing(move))
				return null;
			return move;
		}
		if (!chess.canEnPassant(end))
			return null;
		move = new EnPassant(this, spot, chess.spotAt(end.X(), spot.Y()).getPiece(), end, chess.getRound());
		if (chess.giveAwayKing(move))
			return null;
		return move;
	}

	protected Move canAttack(Square end) {
		if (legalPostionCapture(end)) {
			if (canPromote(end))
				return new Promotion(this, spot, end.getPiece(), end, chess.getRound());
			return new RegularMove(this, spot, end.getPiece(), end);
		}
		return null;
	}

	private boolean legalPostionCapture(Square end) {
		if (spot == null)
			return false;
		if (Math.abs(end.X() - spot.X()) == 1) {
			if (super.color == Player.WHITE) {
				if (end.Y() - spot.Y() == 1)
					return true;
			} else {
				if (end.Y() - spot.Y() == -1)
					return true;
			}
		}
		return false;
	}

	protected boolean canPromote(Square end) {
		boolean promotion = false;
		if (super.color == Player.WHITE) {
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