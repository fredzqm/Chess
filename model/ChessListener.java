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

	/**
	 * invoked when one move is performed and ask for input for another move
	 * 
	 * @param previousMove
	 */
	void nextMove(Move previousMove);

	/**
	 * invoked when pawn reached the bottom line and asked for what it wants to
	 * promote to
	 * 
	 * @param whiteOrBlack
	 * @param square
	 *            the end square pawn reached
	 * @return
	 */
	Piece promote(boolean whiteOrBlack, Square square);

}
