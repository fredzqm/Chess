package model;

public interface ChessController extends ChessListener {
	
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
	Piece choosePromotePiece(boolean whiteOrBlack, Square square);
	
}
