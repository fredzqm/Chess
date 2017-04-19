package model;

/**
 * A class that represents the squares on the chess board.
 * 
 * It is also the Jlabel that actually appears in the Frame.
 * 
 * e1,
 * 
 * @author zhangq2
 *
 */
public class Square {
	private int x;
	private int y;
	private String position;
	private Piece occupiedPiece;

	/**
	 * 
	 * @param i
	 *            file of this spot
	 * @param j
	 *            rank of this square
	 * @param chess
	 */
	public Square(int i, int j) {
		x = i + 1;
		y = 8 - j;
		char col = (char) (97 + i);
		int row = 8 - j;
		position = "" + col + row;
		occupiedPiece = null;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// accessors

	public String toString() {
		return position;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * 
	 * @return the piece at that square
	 */
	public Piece getPiece() {
		return occupiedPiece;
	}

	/**
	 * 
	 * @return true if there is any piece occupy this squre
	 */
	public boolean isOccupied() {
		return occupiedPiece != null;
	}

	/**
	 * 
	 * @param whoseTurn
	 *            white or black
	 * @return whether this square is occupied by piece of that color.
	 */
	public boolean occupiedBy(boolean color) {
		if (isOccupied())
			return color == (occupiedPiece.getWhiteOrBlack());
		else
			return false;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// modifier

	/**
	 * set the occupied piece.
	 * 
	 * @param piece
	 *            the piece
	 */
	public void setOccupied(Piece piece) {
		occupiedPiece = piece;
	}

}