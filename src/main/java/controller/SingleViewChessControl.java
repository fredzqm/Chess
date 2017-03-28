package controller;

import java.util.ArrayList;

import model.ChessGameException;
import model.InvalidMoveException;
import model.Move;
import model.Pawn;
import model.Piece.Player;
import model.Square;
import view.ChessViewer;
import view.IChessViewerControl;
import view.SquareLabel;

/**
 * The chess controller opens a single chess view
 * 
 * @author zhang
 *
 */
public class SingleViewChessControl extends ViewController implements IChessViewerControl {
	private ChessViewer view;

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
	public ChessViewer chooesView(boolean whiteOrBlack) {
		return view;
	}

	@Override
	public void click(SquareLabel label, boolean whiteOrBlack) {
		if (chess.hasEnd()) {
			view.printOut("Game is already over! Type restart to start a new game");
		} else {
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
					view.cleanTemp();
				chosen = null;
				view.deHighLightWholeBoard();
			} else {
				if (spot.occupiedBy(chess.getWhoseTurn())) {
					chosen = spot.getPiece();
					ArrayList<Square> reachable = chosen.getReachableSquares();
					reachable.add(spot);
					ArrayList<SquareLabel> hightlight = getAllViewLabels(reachable, view);
					view.highLightAll(hightlight);

					if (spot.getPiece().isType(Pawn.class))
						view.printTemp(spot.toString());
					else
						view.printTemp(spot.getPiece().getType() + spot.toString());

				}
			}
		}
		updateChessBoard();
	}

	private void updateGuiToMove(Move previousMove) {
		view.setStatusLabelText(chess.lastMoveDiscript());
		view.cleanTemp();
		view.printOut(chess.lastMoveOutPrint());
		view.printOut("Next move -- " + side(previousMove.getWhoseTurn() == Player.BLACK));
	}

	@Override
	public void handleCommand(String input, boolean isWhiteView) {
		handleSingleCommand(view, input, isWhiteView);
	}

	public static void main(String[] args) {
		new SingleViewChessControl();
	}
}
