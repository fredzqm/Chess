package view;

public interface IChessViewerControl {

	/**
	 * Invoked when view receive input from command line
	 * 
	 * @param input
	 * @param isWhiteView
	 */
	void handleCommand(String input, boolean isWhiteView);

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
}
