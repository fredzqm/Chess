package view;

public interface IChessViewerControl {
	/**
	 * 
	 * @return true if the game has End
	 */
	boolean hasEnd();

	/**
	 * Invoked when chess board is clicked
	 * 
	 * @param file
	 *            the file of this square clicked
	 * @param rank
	 *            the rank of this square clicked
	 * @param whiteOrBlack
	 *            the orientation of the view
	 */
	void click(int file, int rank, boolean whiteOrBlack);

	/**
	 * 
	 * @return the standard records of this chess game
	 */
	String getRecords();

	/**
	 * restart the game
	 */
	void restart();

	/**
	 * Resigns the game
	 * 
	 * @param isWhite
	 */
	void resign(boolean isWhite);

	/**
	 * requests for a draw
	 * 
	 * @param isWhite
	 */
	void askForDraw(boolean isWhite);

	/**
	 * Undo a move
	 */
	void undo(boolean isWhite);

	/**
	 * Perform a move based on the command
	 * 
	 * @param isWhite
	 * @param move
	 * @return true if this move is successful
	 */
	boolean makeMove(boolean isWhite, String move);
}
