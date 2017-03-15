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
	private Chess chess;
	private int x;
	private int y;
	private String name;
	private Piece occupied;

	/**
	 * 
	 * @param i
	 *            file of this spot
	 * @param j
	 *            rank of this square
	 * @param chess
	 */
	public Square(int i, int j, Chess chess) {
		this.chess = chess;
		x = i + 1;
		y = 8 - j;
		char col = (char) (97 + i);
		int row = 8 - j;
		name = "" + col + row;
		occupied = null;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// accessors

	public String toString() {
		return name;
	}

	public int X() {
		return x;
	}

	public int Y() {
		return y;
	}

	/**
	 * 
	 * @return the piece at that square
	 */
	public Piece getPiece() {
		return occupied;
	}

	/**
	 * 
	 * @return
	 */
	public Chess getChess() {
		return chess;
	}

	/**
	 * 
	 * @return true if there is any piece occupy this squre
	 */
	public boolean occupied() {
		return occupied != null;
	}

	/**
	 * 
	 * @param whoseTurn
	 *            white or black
	 * @return whether this square is ocupied by piece of that color.
	 */
	public boolean occupiedBy(boolean whoseTurn) {
		if (occupied())
			return whoseTurn == occupied.getWOrB();
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
		occupied = piece;
	}

}