package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ChessGameException;
import model.Move;
import model.Pawn;
import model.Piece;
import model.Piece.Player;
import model.Square;
import view.ChessViewer;
import view.IChessViewerControl;
import view.SquareLabel;

/**
 * 
 * A chess controller that control two ChessView, each views the board
 * separately
 * 
 * @author zhang
 *
 */
public class DualViewChessControl extends ViewController {
	private ChessViewer whiteView;
	private ChessViewer blackView;

	/**
	 * start my little chess game!!!!
	 * 
	 * @param args
	 *            ignored
	 */
	public DualViewChessControl() {
		super();
		whiteView = new ChessViewer(this, "The Great Chess Game white view", true);
		blackView = new ChessViewer(this, "The Great Chess Game black view", false);
		updateChessBoard();
	}

	public ChessViewer chooesView(boolean whiteOrBlack) {
		return whiteOrBlack ? whiteView : blackView;
	}

	@Override
	public void handleCommand(String command, boolean whiteOrBlack) {
		ChessViewer viewer = chooesView(whiteOrBlack);
		handleSingleCommand(viewer, command, whiteOrBlack);
	}

	protected void updateGuiToMove(Move previousMove) {
		updateChessBoard();
		
		ChessViewer pre = chooesView(previousMove.getWhoseTurn() == Player.WHITE);
		ChessViewer next = chooesView(previousMove.getWhoseTurn() == Player.BLACK);

		whiteView.setStatusLabelText(chess.lastMoveDiscript());
		blackView.setStatusLabelText(chess.lastMoveDiscript());
		pre.cleanTemp();
		whiteView.printOut(chess.lastMoveOutPrint());
		blackView.printOut(chess.lastMoveOutPrint());
		next.printOut("Please make your move.");
		pre.printOut("Wait for the " + side(previousMove.getWhoseTurn() == Player.BLACK) + " to make a move");

	}

	public static void main(String[] args) {
		new DualViewChessControl();
	}

}
