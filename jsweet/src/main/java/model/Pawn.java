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
		if (end.isOccupied() || spot == null)
			return false;

		if (spot.getX() == end.getX()) {
			if (c == Player.WHITE) {
				if (end.getY() - spot.getY() == 1)
					return true;
				if (end.getY() == 4 && spot.getY() == 2)
					if (!chess.spotAt(spot.getX(), 3).isOccupied())
						return true;
			} else {

				if (end.getY() - spot.getY() == -1)
					return true;
				if (end.getY() == 5 && spot.getY() == 7)
					if (!chess.spotAt(spot.getX(), 6).isOccupied())
						return true;
			}
		}
		return false;
	}

	public Move getMove(Square end) {
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
		move = new EnPassant(this, spot, chess.spotAt(end.getX(), spot.getY()).getPiece(), end, chess.getRound());
		if (chess.giveAwayKing(move))
			return null;
		return move;
	}

	public Move canAttack(Square end) {
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
		if (Math.abs(end.getX() - spot.getX()) == 1) {
			if (super.color == Player.WHITE) {
				if (end.getY() - spot.getY() == 1)
					return true;
			} else {
				if (end.getY() - spot.getY() == -1)
					return true;
			}
		}
		return false;
	}

	protected boolean canPromote(Square end) {
		boolean promotion = false;
		if (super.color == Player.WHITE) {
			if (end.getY() == 8)
				promotion = true;
		} else {
			if (end.getY() == 1)
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