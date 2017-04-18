package controller;

import model.Move;
import model.Piece.Player;
import view.ChessViewer;
import view.IChessViewer;

/**
 * The chess controller opens a single chess view
 * 
 * @author zhang
 *
 */
public class SingleViewChessControl extends ViewController {
	private IChessViewer view;

	/**
	 * start my little chess game!!!!
	 * 
	 * @param args
	 *            ignored
	 */
	public SingleViewChessControl() {
		super();
		view = new ChessViewer(this, "The Great Chess Game", true);
		updateChessBoard();
	}

	@Override
	public IChessViewer chooesView(boolean whiteOrBlack) {
		return view;
	}

	protected void updateGuiAfterMove(Move previousMove) {
		updateChessBoard();
		
		view.setStatusLabelText(chess.lastMoveDiscript());
		view.cleanTemp();
		view.printOut(chess.lastMoveOutPrint());
		view.printOut("Next move -- " + side(previousMove.getWhoseTurn() == Player.BLACK));
	}

	public static void main(String[] args) {
		new SingleViewChessControl();
	}
}
