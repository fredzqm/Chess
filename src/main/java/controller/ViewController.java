package controller;

import java.util.ArrayList;

import model.Chess;
import model.Draw;
import model.InvalidMoveException;
import model.Move;
import model.Pawn;
import model.Piece;
import model.Promotion;
import model.Square;
import model.Win;
import view.IChessViewer;
import view.IChessViewerControl;

public abstract class ViewController implements IChessViewerControl {
	private Piece chosen;
	protected Chess chess;
	private DrawManager drawManager;

	public ViewController() {
		chess = new Chess();
		drawManager = new DrawManager();
		chosen = null;
	}

	public void restart() {
		chess = new Chess();
		chosen = null;
		IChessViewer white = chooesView(true);
		IChessViewer black = chooesView(false);
		restartView(white);
		if (black != white)
			restartView(black);
		updateChessBoard();
	}

	private void restartView(IChessViewer view) {
		view.deHighLightWholeBoard();
		view.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
		view.printOut("Start a new game!");
	}

	private void repaintAll(IChessViewer view) {
		for (Square sq : chess.getBoard()) {
			if (sq.isOccupied()) {
				view.upDatePiece(sq.getX(), sq.getY(), sq.getPiece().getType(), sq.getPiece().getWhiteOrBlack());
			} else {
				view.clearLabel(sq.getX(), sq.getY());
			}
		}
		view.repaint();
	}

	/**
	 * This method is called if the user enter a command to undo his move. It
	 * will undo two moves.
	 * 
	 */
	public void undo(boolean isWhite) {
		IChessViewer view = chooesView(isWhite);
		clearHightLight(view);
		if (!chess.undoLastMove())
			view.printOut("It is already the start of Game");
		else
			view.printOut("Undo the Previous Move!");
		repaintAll(view);
	}

	protected void updateChessBoard() {
		IChessViewer white = chooesView(true);
		IChessViewer black = chooesView(false);
		repaintAll(white);
		if (black != white)
			repaintAll(black);
	}

	public void click(int file, int rank, boolean whiteOrBlack) {
		IChessViewer clickedView = chooesView(whiteOrBlack);
		if (chess.hasEnd()) {
			clickedView.printOut("Game is already over! Type restart to start a new game");
			return;
		}
		if (clickedView != chooesView(chess.getWhoseTurn())) {
			clickedView.printOut("Please wait for your opponnet to finish");
			return;
		}
		Square spot = chess.spotAt(file, rank);
		if (chosen != null) {
			Move move = chosen.getMove(spot);
			if (move == null) {
				clickedView.cleanTemp();
			} else {
				if (move instanceof Promotion) {
					Promotion promotion = (Promotion) move;
					String promoteTo = clickedView.getPromoteTo();
					promotion.setPromoteTo(Chess.getPieceClass(promoteTo.charAt(0)));
				}
				chess.makeMove(move);
				updateGuiAfterMove(move);
			}
			clearHightLight(clickedView);
		} else {
			if (spot.occupiedBy(chess.getWhoseTurn())) {
				chosen = spot.getPiece();
				ArrayList<Square> reachable = chosen.getReachableSquares();
				reachable.add(spot);
				for (Square sqr : reachable) {
					clickedView.highLight(sqr.getX(), sqr.getY());
				}

				if (spot.getPiece().isType(Pawn.class))
					clickedView.printTemp(spot.toString());
				else
					clickedView.printTemp(spot.getPiece().getType() + spot.toString());
			}
		}
	}

	public String getRecords() {
		return chess.getRecords().toString();
	}

	@Override
	public boolean hasEnd() {
		return chess.hasEnd();
	}

	@Override
	public void resign(boolean isWhite) {
		IChessViewer view = chooesView(isWhite);
		clearHightLight(view);
		Draw canClaimDraw = chess.canClaimDraw();
		if (canClaimDraw != null) {
			view.printOut("Actually, you can go with a draw!");
			chess.endGame(canClaimDraw);
			return;
		}
		if (chess.getWhoseTurn()) {
			chess.endGame(Win.WHITERESIGN);
		} else {
			chess.endGame(Win.BLACKESIGN);
		}
	}

	private void clearHightLight(IChessViewer view) {
		chosen = null;
		view.deHighLightWholeBoard();
	}

	public void askForDraw(boolean whiteOrBlack) {
		Draw canClaimDraw = chess.canClaimDraw();
		if (canClaimDraw == null) {
			IChessViewer request = chooesView(whiteOrBlack);
			IChessViewer response = chooesView(!whiteOrBlack);
			if (drawManager.canAskFordraw(whiteOrBlack)) {
				response.printOut(side(whiteOrBlack) + " ask for draw, do you agreed?");
				if (response.askForDraw()) {
					chess.endGame(Draw.AGREEMENT);
				} else {
					drawManager.setRightToRequestDraw(whiteOrBlack);
					request.printOut("Request declined");
				}
			} else {
				request.printOut("You cannot request for draw again now.");
			}
		} else {
			chess.endGame(canClaimDraw);
		}
	}

	/**
	 * This method will be called, if the user types a command to make a move.
	 * 
	 * Interpret the command, and find out if it is legal to do make this move.
	 * If it is, make this move.
	 * 
	 * @param moveCommand
	 *            the input command
	 * @return
	 */
	@Override
	public boolean makeMove(boolean isWhite, String moveCommand) {
		IChessViewer view = chooesView(isWhite);
		clearHightLight(view);
		Move move = null;
		try {
			move = chess.interpreteMoveCommand(moveCommand);
			chess.makeMove(move);
			updateGuiAfterMove(move);
			updateChessBoard();
			return true;
		} catch (InvalidMoveException e) {
			switch (e.type) {
			case InvalidMoveException.invalidFormat:
				view.printOut("The command is not in a valid format.");
				break;
			case InvalidMoveException.ambiguousMove:
				view.printOut("Fail to guess move: There is ambiguity, multiple possible moves.");
				break;
			case InvalidMoveException.castleNotAllowed:
				view.printOut("You cannot do castling, please check the rules for castling.");
				break;
			case InvalidMoveException.impossibleMove:
				view.printOut("This is not a possible move.");
				break;
			case InvalidMoveException.incorrectPiece:
				view.printOut("The chessman in the start Position is not correct! "
						+ "\n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
				break;
			case InvalidMoveException.pieceNotPresent:
				view.printOut("There is no piece at the start position.");
				break;
			case InvalidMoveException.promotionTo:
				view.printOut("You should specify what piece you want to promote to");
				break;
			default:
				throw new RuntimeException(e);
			}
			return false;
		}
	}

	public abstract IChessViewer chooesView(boolean whiteOrBlack);

	/**
	 * print messages and update GUI when this move just get accomplished
	 * 
	 * @param move
	 */
	protected abstract void updateGuiAfterMove(Move move);

	protected static String side(boolean whoseTurn) {
		return whoseTurn ? "White" : "Black";
	}

	public void close() {
		chooesView(false).close();
		chooesView(true).close();
	}

}
