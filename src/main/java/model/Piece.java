package model;

import java.util.ArrayList;

/**
 * The super class for all different kinds of pieces.
 * 
 * @author zhangq2
 *
 */
public abstract class Piece implements Comparable<Piece> {
	protected Chess chess;
	protected Player color;
	protected Square spot;
	
	public enum Player {
		WHITE,
		BLACK
	}

	/**
	 * 
	 * @param wb
	 *            whether this piece is white or black
	 * @param p
	 *            the square this piece is at initially.
	 */
	public Piece(Player c, Square p) {
		this.chess = p.getChess();
		this.color = c;
		moveTo(p);
	}

	public String getName() {
		String s = getClass().getName();
		return s.substring(s.lastIndexOf(".") + 1);
	}

	/**
	 * 
	 * @return the spot this piece is on. {@Code null} if this piece is not on
	 *         the board (taken away)
	 */
	public Square getSpot() {
		return spot;
	}

	/**
	 * 
	 * @return Whether this piece is owned by white or black
	 */
	public Player getWhiteOrBlack() {
		return this.color;
	}

	/**
	 * 
	 * @return the single character that represents this piece
	 */
	public abstract char getType();

	/**
	 * 
	 * @param type
	 * @return true if this piece is of this type
	 */
	public boolean isType(Class<? extends Piece> type) {
		return getClass() == type;
	}

	/**
	 * This method is not really used right now
	 * 
	 * @return the value or power of this piece
	 */
	public abstract int getValue();

	/**
	 * this method is implemented for the sort method in the constructor of
	 * chess.
	 */
	public int compareTo(Piece a) {
		return (a.getValue() - getValue());
	}

	/**
	 * 
	 * @return X position of this spot
	 */
	public int getX() {
		return spot.X();
	}

	/**
	 * 
	 * @return Y position of this spot
	 */
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
	 * This method takes everything into account, including giving away king,
	 * castling, En Passant.
	 * 
	 * 
	 * @param end
	 * @return true if it is safe to make this move without violation of the
	 *         rule
	 */
	public boolean canGo(Square end) {
		return getMove(end) != null;
	}

	/**
	 * This method gets a legitimate move to the end Square if one exists. This
	 * method is overridden by {@link King} and {@link Pawn}, because they have
	 * special rules
	 * 
	 * For pieces except King and Pawn, @{link {@link Piece#legalPosition}
	 * capture all possible moves
	 * 
	 * @param end
	 *            the spot to move to
	 * @return the legal move of this piece toward the end Square, {@Code null}
	 *         if this move is illegal
	 */
	protected Move getMove(Square end) {
		// cannot move to own piece
		if (end.occupiedBy(this.color))
			return null;
		Move legalMove = legalPosition(end);
		if (legalMove == null)
			return null;
		// check if this move is giving away the king
		if (chess.giveAwayKing(legalMove))
			return null;
		return legalMove;
	}
	
	/**
	 * 
	 * @param chosen
	 * @return the list of Squares that this piece can reach
	 */
	public ArrayList<Square> getReachableSquares() {
		ArrayList<Square> list = new ArrayList<>();
		for (Square i : this.chess.getAllSquares())
			if (this.canGo(i))
				list.add(i);
		return list;
	}

	/**
	 * This method is overridden by Pawn because it has special rule for
	 * attacking
	 * 
	 * For other pieces, the all legal position they can move to are the spots
	 * they can attack
	 * 
	 * @param end
	 *            the spot to attack
	 * @return true if this piece can attack this spot
	 */
	protected Move canAttack(Square end) {
		if (spot == null)
			return null;
		return legalPosition(end);
	}
	
	/**
	 * For most pieces in chess, there is a common way of moving. This checks if
	 * certain move match such common.
	 * 
	 * For pawn this method only specifies the move. The capture logic is
	 * defined in {@link Pawn#getMove(Square)}
	 * 
	 * @param end
	 *            the end position
	 * @param chess
	 * @return true if it is legal to move this piece to the end, regardless of
	 *         the piece at the end position
	 */
	public abstract Move legalPosition(Square end);

	/**
	 * This method convert the character to one type of {@link Piece} class. It
	 * used mostly for parsing commands like
	 * <p>
	 * e2-e4 <br />
	 * Nb1-c3
	 * </p>
	 * 
	 * @param character
	 *            the character representing the piece
	 * @return the corresponding class
	 */
	public static Class<? extends Piece> getType(char character) {
		switch (Character.toUpperCase(character)) {
		case 'P':
			return Pawn.class;
		case 'R':
			return Rook.class;
		case 'N':
			return Knight.class;
		case 'B':
			return Bishop.class;
		case 'Q':
			return Queen.class;
		case 'K':
			return King.class;
		default:
			return Piece.class;
		}
	}

	public String toString() {
		return getName() + " at " + getSpot();
	}
}
