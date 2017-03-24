package view;

import model.Chess;
import model.Draw;
import model.Piece.Player;
import model.Win;

public class Request {
	private boolean white;
	private boolean black;
	private Chess chess;
	private boolean whiteOrBlack;

	protected Request(Chess chess, Boolean whiteOrBlack) {
		white = true;
		black = true;
		this.chess = chess;
	}

	private void setRightToRequestDraw(boolean whoseTurn) {
		if (whoseTurn) {
			white = false;
			black = true;
		} else {
			white = true;
			black = false;
		}
	}

	private boolean canAskFordraw(boolean whoseTurn) {
		if (whoseTurn)
			return white;
		else
			return black;
	}

	/**
	 * invoked when one player is asking for a draw.
	 * 
	 * Find out if the game satisfied automatic draw condition due to FIFTY_MOVE
	 * or REPETITION {@link Draw. End
	 * 
	 * @param whiteOrBlack
	 *            the game and claim draw if those conditions are met.
	 *            Otherwise, send a request for draw, and wait for the reply of
	 *            opponent.
	 * 
	 * @return
	 */
	public void askForDraw() {
		Draw canClaimDraw = chess.canClaimDraw();
		boolean whiteOrBlack = chess.getWhoseTurn() == Player.WHITE;
		if (canClaimDraw == null) {
			ChessViewer request = chooesView(whiteOrBlack);
			ChessViewer response = chooesView(!whiteOrBlack);
			if (canAskFordraw(whiteOrBlack)) {
				while (true) {
					response.printOut(side(whiteOrBlack) + " ask for draw, do you agreed?");
					String command = response.getResponse("Do you agree draw?");
					command = "no";
					if (command.isEmpty())
						continue;
					if (command.toLowerCase().startsWith("yes")) {
						chess.endGame(Draw.AGREEMENT);
						return;
					} else if (command.toLowerCase().startsWith("no")) {
						setRightToRequestDraw(whiteOrBlack);
						request.printOut("Request declined");
						return;
					}
				}
			} else {
				request.printOut("You cannot request for draw again now.");
			}
		} else {
			chess.endGame(canClaimDraw);
		}
	}

	/**
	 * Invoked if a player resigns. It will ends the game. However, if the game
	 * satisfied automatic draw condition, the game will be draw instead.
	 * 
	 * @param whiteOrBlack
	 * 
	 * @return
	 */
	public void resign(boolean whiteOrBlack) {
		Draw canClaimDraw = chess.canClaimDraw();
		if (canClaimDraw != null) {
			side(whiteOrBlack).printOut("Actually, you can go with a draw!");
			chess.endGame(canClaimDraw);
			return;
		}
		if (whiteOrBlack) {
			chess.endGame(Win.WHITERESIGN);
		} else {
			chess.endGame(Win.BLACKESIGN);
		}
	}
	private String side(boolean whoseTurn) {
		return whoseTurn ? "White" : "Black";
	}

}
