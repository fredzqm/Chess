package view;

/**
 * Interface for a ChessView that is compatible with ViewController
 * 
 * @author zhang
 *
 */
public interface IChessViewer {

	/**
	 * print the message in the console.
	 * 
	 * @param message
	 */
	void printOut(String message);

	/**
	 * print out the temporal string in the console, without updating the
	 * record. The temp string printed can be clear with
	 * {@link ChessViewer#cleanTemp()}
	 * 
	 * @param temp
	 */
	void printTemp(String temp);

	/**
	 * erase the temporal string in the console printed by
	 * {@link ChessViewer#printTemp(String)}
	 */
	void cleanTemp();

	/**
	 * 
	 * @param str
	 */
	void setStatusLabelText(String str);

	/**
	 * hightlight the square at this position
	 * 
	 * @param file
	 * @param rank
	 */
	void highLight(int file, int rank);

	/**
	 * dehighlight the whole board
	 */
	void deHighLightWholeBoard();

	/**
	 * ask the user for a resonse
	 * 
	 * @param message
	 * @return the responsded string
	 */
	String getResponse(String message);

	/**
	 * refresh the UI
	 */
	void repaint();

	/**
	 * update Piece at this position
	 * 
	 * @param file
	 * @param rank
	 * @param pieceType
	 * @param whiteOrBlack
	 */
	void upDatePiece(int file, int rank, char pieceType, boolean whiteOrBlack);

	/**
	 * clear piece at certain square
	 * 
	 * @param file
	 * @param rank
	 */
	void clearLabel(int file, int rank);

}