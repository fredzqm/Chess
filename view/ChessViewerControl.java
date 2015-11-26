package view;

public interface ChessViewerControl {

	/**
	 * Invoked when view receive input from command line
	 * 
	 * @param command
	 */
	public void handleCommand(String command, boolean whiteOrBlack);

	/**
	 * Invoked when chess board is clicked
	 * 
	 * @param squareLabel
	 *            the label clicked
	 */
	public void click(SquareLabel squareLabel, boolean whiteOrBlack);

}
