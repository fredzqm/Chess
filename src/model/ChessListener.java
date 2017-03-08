package model;
import java.util.EventListener;

public interface ChessListener extends EventListener {

	/**
	 * invoked when the chess make some modification for certain square, and ask
	 * the view to be updated
	 * 
	 * @param square
	 */
	void updateSquare(Square square);

	/**
	 * invoked when the chess has reached an end game
	 * 
	 * @param end
	 */
	void endGame(EndGame end);

}
