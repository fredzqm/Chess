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
	 * @param squareLabel
	 *            the label that got clicked
	 * @param isWhiteView
	 */
	void click(SquareLabel squareLabel, boolean isWhiteView);

}
