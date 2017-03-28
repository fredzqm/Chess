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
public class DualViewChessControl extends ViewController implements IChessViewerControl {
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

	@Override
	public void click(SquareLabel label, boolean whiteOrBlack) {
		ChessViewer clickedView = chooesView(whiteOrBlack);
		if (chess.hasEnd()) {
			clickedView.printOut("Game is already over! Type restart to start a new game");
		} else {
			if (whiteOrBlack != (chess.getWhoseTurn() == Player.WHITE)) {
				clickedView.printOut("Please wait for your opponnet to finish");
				return;
			}
			Square spot = labelToSquare(label, chess);
			if (chosen != null) {
				if (label.isHighLight() && !spot.equals(chosen.getSpot())) {
					Move move;
					if ((move = chess.performMove(chosen, spot)) == null) {
						throw new ChessGameException(
								"Illegal move of " + chosen.getName() + " did not correctly caught from UI!");
					} else {
						updateGuiToMove(move);
					}
				} else
					clickedView.cleanTemp();
				chosen = null;
				clickedView.deHighLightWholeBoard();
			} else {
				if (spot.occupiedBy(chess.getWhoseTurn())) {
					chosen = spot.getPiece();
					ArrayList<Square> reachable = chosen.getReachableSquares();
					reachable.add(spot);
					ArrayList<SquareLabel> hightlight = getAllViewLabels(reachable, chooesView(whiteOrBlack));
					clickedView.highLightAll(hightlight);

					if (spot.getPiece().isType(Pawn.class))
						clickedView.printTemp(spot.toString());
					else
						clickedView.printTemp(spot.getPiece().getType() + spot.toString());

				}
			}
		}
		
	}

	private void updateGuiToMove(Move previousMove) {
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
