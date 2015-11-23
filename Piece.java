import javax.management.RuntimeErrorException;

/**
 * the super class for all different kinds of pieces.
 * @author zhangq2
 *
 */
public abstract class Piece implements Comparable<Piece> {
	protected Chess chess;
	protected boolean wb;
	protected Square spot;

	/**
	 * 
	 * @param wb
	 *            whether this piece is white or black
	 * @param p
	 *            the square this piece is at initially.
	 */
	public Piece( boolean wb, Square p) {
		this.chess = p.getChess();
		this.wb = wb;	
		moveTo(p);
	}

	// ------------------------------------------------------------------------------------------------------------------------
	// accessors
	
	public String getName(){
		return getClass().getName();
	}

	public Square getP() {
		return spot;
	}

	public boolean getWb() {
		return wb;
	}

	public abstract char getType();

	public boolean isType(Class<? extends Piece> p) {
		return getClass() == p;
	}

	public abstract int getValue();

	/**
	 * this method is implemented for the sort method in the constructor of
	 * chess.
	 */
	public int compareTo(Piece a) {
		return (a.getValue() - getValue());
	}

	public int getX() {
		return spot.X();
	}

	public int getY() {
		return spot.Y();
	}

	/**
	 * Only place in this program that can change the position of a spot.
	 * 
	 * @param p
	 */
	public void moveTo(Square p) {
		if (spot != null) {
			spot.setOccupied(null);
		}
		if (p != null) {
			p.setOccupied(this);
		}
		spot = p;
	}

	/**
	 * 
	 * @param end
	 *            the end position
	 * @param chess
	 * @return true if it is legal to move this piece to the end, regardless of
	 *         the piece at the end position
	 */
	public abstract boolean legalPosition(Square end);

	/**
	 * 
	 * @param end
	 * @param chess
	 * @return true if it is legal to move this piece to the end position
	 */
	public boolean canMove(Square end) {
		if (end.occupied())
			return false;
		return legalPosition(end)
				&& !chess.giveAwayKing(this, spot, end.getPiece(), end, wb);
	}

	/**
	 * 
	 * @param end
	 * @param chess
	 * @return true if it is attacking the end spot.
	 */
	public boolean canAttack(Square end) {
		if (spot == null)
			return false;
		return legalPosition(end);
	}

	/**
	 * 
	 * @param end
	 * @param chess
	 * @return true if it is legal to capture the piece at the end
	 */
	public boolean canCapture(Square end) {
		return canAttack(end) && end.occupiedBy(!wb)
				&& !chess.giveAwayKing(this, spot, end.getPiece(), end, wb);
	}

	/**
	 * Overiden in Pawn class
	 * 
	 * @param end
	 * @param chess
	 * @return carry on the move
	 */
	public void move(Square end) {
		makeMove(end, null);
	}

	/**
	 * Overiden in Pawn class
	 * 
	 * @param end
	 * @param taken
	 * @param chess
	 * @return carry on the capture
	 */
	public void capture(Square end, Piece taken) {
		makeMove(end, taken);
	}

	/**
	 * 
	 * @param end
	 *            the square moves to
	 * @param taken
	 *            the piece that will be taken, null if there is no piece taken
	 * @param chess
	 * @param takeOrNot
	 *            whether it takes a piece or not
	 * @return the output information it needs to print out in the box
	 */
	public void makeMove(Square end, Piece taken) {
		if (taken != null)
			chess.takeOffBoard(taken);
		Square start = spot;
		if (canPromote(end)) {
			Piece promotionTo = promotion(end);
			chess.addPromotionRecord(this, start, taken, end, promotionTo);
		} else {
			moveTo(end);
			chess.addRecord(this, start, taken, end);
		}
		chess.wrapMove();
	}

	/**
	 * This method is overriden by Pawn. It can return true only if this is a
	 * pawn. It will examine whether Pawn has reach the eighth rank and eligible
	 * for promotion.
	 * 
	 * @param end
	 * @return whether this piece can promote after this move.
	 */
	protected boolean canPromote(Square end) {
		return false;
	}

	/**
	 * 
	 * This method should only be called if this is a pawn, and it needs to promote.
	 * 
	 * It chooses one kind of piece to promote to, and return the new created
	 * piece.
	 * 
	 * @param end
	 * @param chess
	 * @return
	 */
	protected Piece promotion(Square end) {
		throw new RuntimeErrorException(new Error(
				"It should never reaches this line!!."));
	}

	public static Class<?extends Piece> getType(char c){
		switch (c) {
		case 'P':return Pawn.class;
		case 'R':return Rook.class;
		case 'N':return Knight.class;
		case 'B':return Bishop.class;
		case 'Q':return Queen.class;
		case 'K':return King.class;
		default:return Piece.class;
		}
	}
}
