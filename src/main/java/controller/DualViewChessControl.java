package controller;

import model.Move;
import view.ChessViewer;
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
	 * @param blackView
	 * @param whiteView
	 * 
	 * @param args
	 *            ignored
	 */
	public DualViewChessControl(IChessViewer whiteView, IChessViewer blackView) {
		super();
		this.whiteView = whiteView;
		this.blackView = blackView;
		this.whiteView.initializeViewController(this);
		this.blackView.initializeViewController(this);
		updateChessBoard();
		this.whiteView.setStatusLabelText("Welcome to our game. You are white");
		this.blackView.setStatusLabelText("Welcome to our game. You are black");
	}

	public IChessViewer chooesView(boolean whiteOrBlack) {
		return whiteOrBlack ? whiteView : blackView;
	}

	protected void updateGuiAfterMove(Move previousMove) {
		updateChessBoard();
		IChessViewer pre = chooesView(previousMove.getWhoseTurn());
		IChessViewer next = chooesView(!previousMove.getWhoseTurn());

		updateStatusLabel();
		pre.cleanTemp();
		pre.printOut(chess.lastMoveOutPrint());
		next.printOut(chess.lastMoveOutPrint());
		next.printOut("Please make your move.");
		pre.printOut("Wait for the " + side(!previousMove.getWhoseTurn()) + " to make a move");
	}


	public static void main(String[] args) {
		new DualViewChessControl(new ChessViewer("The Great Chess Game white view", true),
				new ChessViewer("The Great Chess Game black view", false));
	}

}
