package controller;

import java.util.ArrayList;
import java.util.Collection;

import model.ChessGameException;
import model.Draw;
import model.EndGame;
import model.InvalidMoveException;
import model.Move;
import model.Pawn;
import model.Piece.Player;
import model.Square;
import model.Win;
import view.ChessViewer;
import view.IChessViewerControl;
import view.SquareLabel;
import view.ViewController;

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
		repaintAll(view);
	}

	public void restart() {
		restartView(view);
	}

	/**
	 * This method will be called, if the user types a command to make a move.
	 * 
	 * Interpret the command, and find out if it is legal to do make this move.
	 * If it is, make this move.
	 * 
	 * @param s
	 *            the input command
	 * @return
	 */
	public boolean makeMove(String s) {
		Move move = null;
		try {
			move = chess.getMove(s);
		} catch (InvalidMoveException e) {
			switch (e.type) {
			case invalidFormat:
				view.printOut("The command is not in a valid format.");
				break;
			case ambiguousMove:
				view.printOut("Fail to guess move: There is ambiguity, multiple possible moves.");
				break;
			case castleNotAllowed:
				view.printOut("You cannot do castling, please check the rules for castling.");
				break;
			case impossibleMove:
				view.printOut("This is not a possible move.");
				break;
			case incorrectPiece:
				view.printOut("The chessman in the start Position is not correct! "
						+ "\n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
				break;
			case pieceNotPresent:
				view.printOut("There is no piece at the start position.");
				break;
			}
		}

		if (move != null) {
			chess.makeMove(move);
			return true;
		}

		return false;
	}

	@Override
	public void handleCommand(String command, boolean whiteOrBlack) {
		String c = command;
		if (c.length() == 0)
			return;
		if (c.equals("print")) {
			printRecords(view, chess);
		} else if (c.equals("help")) {
			view.printOut(HELP_MESSAGE);
		} else if (c.startsWith("rules for ")) {
			showRules(c.substring(10), view, rules);
		} else if (c.equals("quit")) {
			System.exit(0);
		} else if (c.equals("restart")) {
			restart();
		} else if (chess.hasEnd()) {
			view.printOut(chess.lastMoveDiscript());
		} else {
			chosen = null;
			view.deHighLightWholeBoard();
			if (c.equals("resign")) {
				resign(view, chess);
			} else if (c.equals("draw")) {
				askForDraw(whiteOrBlack);
			} else if (c.equals("undo")) {
				undo(chess, view);
			} else if (!makeMove(c)) {
				// makeMove return false, so this move is not allowed.
				view.printOut(ERROR_MESSAGE);
			}
		}

		repaintAll(view);
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

		repaintAll(view);
	}

	public void endGame(EndGame end) {
		if (end == Win.BLACKCHECKMATE || end == Win.WHITECHECKMATE || end == Draw.STALEMENT) {
			view.cleanTemp();
			view.printOut(chess.lastMoveOutPrint());
		}

		view.setStatusLabelText(end.getDescript());
		view.printOut(end.getPrintOut());
	}

	private void updateGuiToMove(Move previousMove) {
		view.setStatusLabelText(chess.lastMoveDiscript());
		view.cleanTemp();
		view.printOut(chess.lastMoveOutPrint());
		view.printOut("Next move -- " + side(previousMove.getWhoseTurn() == Player.BLACK));
	}

	public void askForDraw(boolean whiteOrBlack) {
		Draw canClaimDraw = chess.canClaimDraw();
		if (canClaimDraw == null) {
			if (canAskFordraw(whiteOrBlack)) {
				while (true) {
					view.printOut(side(whiteOrBlack) + " ask for draw, do you agreed?");
					String command = view.getResponse("Do you agree draw?");
					if (command.isEmpty())
						continue;
					if (command.toLowerCase().startsWith("yes")) {
						chess.endGame(Draw.AGREEMENT);
						return;
					} else if (command.toLowerCase().startsWith("no")) {
						setRightToRequestDraw(whiteOrBlack);
						view.printOut("Request declined");
						return;
					}
				}
			} else {
				view.printOut("You cannot request for draw again now.");
			}
		} else {
			chess.endGame(canClaimDraw);
		}
	}

	public static void main(String[] args) {
		new SingleViewChessControl();
	}

}
