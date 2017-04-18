package controller;

import model.Move;
import model.Piece.Player;
import view.IChessViewer;

/**
 * 
 * A chess controller that control two ChessView, each views the board
 * separately
 * 
 * @author zhang
 *
 */
public class DualViewChessControl extends ViewController {
	private IChessViewer whiteView;
	private IChessViewer blackView;

	/**
	 * start my little chess game!!!!
	 * 
	 * @param args
	 *            ignored
	 */
	public DualViewChessControl() {
		super();
//		whiteView = new ChessViewer(this, "The Great Chess Game white view", true);
//		blackView = new ChessViewer(this, "The Great Chess Game black view", false);
		updateChessBoard();
	}

	public IChessViewer chooesView(boolean whiteOrBlack) {
		return whiteOrBlack ? whiteView : blackView;
	}


	protected void updateGuiAfterMove(Move previousMove) {
		updateChessBoard();
		IChessViewer pre = chooesView(previousMove.getWhoseTurn() == Player.WHITE);
		IChessViewer next = chooesView(previousMove.getWhoseTurn() == Player.BLACK);

		pre.setStatusLabelText(chess.lastMoveDiscript());
		next.setStatusLabelText(chess.lastMoveDiscript());
		pre.cleanTemp();
		pre.printOut(chess.lastMoveOutPrint());
		next.printOut(chess.lastMoveOutPrint());
		next.printOut("Please make your move.");
		pre.printOut("Wait for the " + side(previousMove.getWhoseTurn() == Player.BLACK) + " to make a move");
	}

	public static void main(String[] args) {
		new DualViewChessControl();
	}

}
