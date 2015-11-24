import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * It is the system for a chess game. It has fields to store the condition of
 * the chess and method to acess and modify those information.
 * 
 * @author zhangq2
 *
 */
public class Chess {

	private int time;
	private boolean whoseTurn;
	private Square[][] spots;
	private ArrayList<Piece> white;
	private ArrayList<Piece> black;
	private Record records;

	private List<ChessListener> listeners;
	private Collection<Square> list;

	// ----------------------------------------------------------------------------------------------------------------------------
	// constructors and methods used to create and initializes the chess game.
	/**
	 * construct a default chess with start setting.
	 */
	public Chess() {
		whoseTurn = true;
		time = 0;
		records = new Record();
		spots = new Square[8][8];
		white = new ArrayList<Piece>();
		black = new ArrayList<Piece>();
		listeners = new ArrayList<>();
		list = new ArrayList<Square>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square t = new Square(i, j, this);
				spots[i][j] = t;
				int y = t.Y();
				if (y == 1) {
					white.add(startSet(t.X(), true, t));
				} else if (y == 2) {
					white.add(new Pawn(true, t));
				} else if (y == 7) {
					black.add(new Pawn(false, t));
				} else if (y == 8) {
					black.add(startSet(t.X(), false, t));
				}
				list.add(t);
			}
		}
		Collections.sort(white);
		Collections.sort(black);
	}

	/**
	 * 
	 * @param x
	 *            which file of this piece
	 * @param b
	 *            White or Black
	 * @param p
	 *            original position of this piece
	 * @return creates new pieces to put into the start chessboard.
	 */
	private Piece startSet(int x, boolean b, Square p) {
		if (x == 1 || x == 8)
			return new Rook(b, p);
		else if (x == 2 || x == 7)
			return new Knight(b, p);
		else if (x == 3 || x == 6)
			return new Bishop(b, p);
		else if (x == 4)
			return new Queen(b, p);
		else if (x == 5)
			return new King(b, p);
		else
			return null;
	}

	// ---------------------------------------------------------------------------
	// Accessors

	public boolean getWhoseTurn() {
		return whoseTurn;
	}

	// methods that send message to control
	
	public int getRound() {
		return time / 2 + 1;
	}

	/**
	 * 
	 * @return true if the game has terminated.
	 */
	public boolean hasEnd() {
		return records.hasEnd();
	}

	/**
	 * 
	 * @param s
	 *            the name of the square
	 * @return the position of the square
	 */
	public Square getSquare(String s) {
		if (s.length() != 2)
			return null;
		char x = s.charAt(0);
		char y = s.charAt(1);
		if (x < 'a' || x > 'h' || y < '1' || y > '8')
			return null;
		return spots[(int) (x - 'a')][7 - (int) (y - '1')];
	}

	/**
	 * 
	 * @param i
	 *            the file of the square
	 * @param j
	 *            the rank of the square
	 * @return the position of the square
	 */
	public Square spotAt(int i, int j) {
		return spots[i - 1][8 - j];
	}

	public Collection<Square> getAllSquares() {
		return list;
	}

	public ArrayList<Move> getRecords() {
		return records.getArrayList();
	}

	// ------------------------------------------------------------------------------------------------------

	// /**
	// * add a record about promotion of pawn
	// *
	// * @param promoteTo
	// * the piece that the pawn promoted to
	// */
	// protected void addPromotionRecord(Piece moved, Square start, Piece taken,
	// Square end, Piece promotedTo) {
	//
	// takeOffBoard(moved);
	// putBackToBoard(promotedTo, end);
	//
	// Promotion a = new Promotion(moved, start, taken, end, time,
	// checkOrNot(whoseTurn), promotedTo);
	// records.add(a);
	// }

	// -------------------------------------------------------------------------------------------------------------------
	// find out about the condition of the game, (In check etc.)

	public ArrayList<Piece> possibleMovers(Class<? extends Piece> type, Square end) {
		ArrayList<Piece> possible = new ArrayList<Piece>();
		ArrayList<Piece> set;
		if (getWhoseTurn())
			set = white;
		else
			set = black;

		for (Piece i : set) {
			if (i.isType(type) && i.canGo(end))
				possible.add(i);
		}
		return possible;
	}

	/**
	 * find out whether a certain move will put your own king in check
	 * 
	 * @param moved
	 * @param start
	 * @param taken
	 * @param end
	 * @param wb
	 * @return
	 */
	public boolean giveAwayKing(Piece moved, Square start, Piece taken, Square end, boolean wb) {
		boolean giveAway = true;
		if (taken != null) {
			taken.moveTo(null);
			moved.moveTo(end);
			if (!checkOrNot(!wb))
				giveAway = false;
			moved.moveTo(start);
			taken.moveTo(end);
		} else {
			moved.moveTo(end);
			if (!checkOrNot(!wb))
				giveAway = false;
			moved.moveTo(start);
		}
		return giveAway;
	}

	/**
	 * 
	 * @param b
	 *            who is attacking and tries to make this check
	 * @return whether the king is in check
	 */
	public boolean checkOrNot(boolean attack) {
		if (attack)
			return isAttacked(white, black.get(0).getP());
		else
			return isAttacked(black, white.get(0).getP());
	}

	/**
	 * 
	 * @param attack
	 *            the pieces that can make this attack
	 * @param p
	 *            the position that is examined
	 * @return true if this position is attacked by the designated pieces.
	 */
	private boolean isAttacked(ArrayList<Piece> attack, Square p) {
		for (Piece i : attack) {
			if (i.canAttack(p) != null)
				return true;
		}
		return false;
	}

	/**
	 * used to find if there is a checkmake, and stalment. if this method
	 * returns true, and it is now in check, it is a checkmate; but if it is now
	 * not in check, the opponent cannot make any legitimate move, so it is a
	 * draw by stalment.
	 * 
	 * @return after the opponent makes one move, is it possible for his king
	 *         not to be in check
	 */
	private boolean checkMate() {
		ArrayList<Piece> inCheck;
		if (whoseTurn)
			inCheck = black;
		else
			inCheck = white;
		for (Piece i : inCheck) {
			for (Square[] r : spots) {
				for (Square p : r) {
					if (i.canGo(p))
						return false;
				}
			}
		}
		return true;
	}

	/**
	 * whether I can claim draw according to the rules. if it meets the
	 * requirement of 'Fifty-move rule', or 'threefold repetition rule', add
	 * marks to canClaimDraw. So both players can claim draw if they want.
	 */
	Draw canClaimDraw() {
		int recordNum = time;
		if (recordNum > 50) {
			boolean quiet = true;
			for (int i = recordNum - 50; i < recordNum; i++) {
				if (records.get(i).notQuiet()) {
					quiet = false;
					break;
				}
			}
			if (quiet)
				return Draw.FIFTY_MOVE;
		}
		for (int i = 2; i < 25; i++) {
			if (i * 4 > recordNum)
				return null;
			boolean repetition = true;
			for (int j = 1; j <= 2 * i; j++) {
				if (!records.get(recordNum - j).equals(records.get(recordNum - j - 2 * i))) {
					repetition = false;
					break;
				}
			}
			if (repetition)
				return Draw.REPETITION;
		}
		return null;
	}

	/**
	 * 
	 * En Passant is a very special rule of chess. An En Passant move can only
	 * be made immediately after the opponent moves his pawn by two squares. So
	 * this method is called to examine whether the records indicate a
	 * legitimate En Passant move.
	 * 
	 * @param end
	 *            the square the opponent's pawn just went through, and my pawn
	 *            try to go as an En Passant move.
	 * @return whether the En Passant move is legal right now.
	 */
	public boolean canEnPassant(Square end) {
		Move move = lastMove();
		if (move == null)
			return false;
		return move.canEnPassant(end);
	}

	// /**
	// * this method is only used by undo method of Move, it will check whether
	// * that move was an En Passant move.
	// *
	// * @param end
	// * @return true, if that move was an En Passant move.
	// */
	// public boolean canEnPassantFromUndoMethod(Square end) {
	// return records.get(records.size() - 2).canEnPassant(end);
	// }

	/**
	 * En Passant is a very special rule of chess. Castling move can only be
	 * made if the king and rook in this castling have never moved yet, plus any
	 * squares the king goes through cannot be attacked.
	 * 
	 * find out whether castling is legal.
	 * 
	 * @param k
	 *            the king that tries to do this castling.
	 * @param longOrShort
	 *            long Casting happens on the Queen side, while the short
	 *            castling happens on the King side.
	 * @return true if it is legal to make the castling of this side right now
	 */
	public Move canCastling(King k, boolean longOrShort) {
		ArrayList<Piece> attack;
		if (k.getWb()) {
			attack = black;
		} else {
			attack = white;
		}
		if (longOrShort) {
			if (canNotLongCastling(k.getY(), attack))
				return null;
			return new Castling(k, k.getP(), spotAt(3, k.getY()), (Rook) (spotAt(1, k.getY()).getPiece()),
					spotAt(1, k.getY()), getRound());
		} else {
			if (canNotShortCastling(k.getY(), attack))
				return null;
			return new Castling(k, k.getP(), spotAt(7, k.getY()), (Rook) (spotAt(8, k.getY()).getPiece()),
					spotAt(8, k.getY()), getRound());
		}
	}

	private boolean canNotLongCastling(int y, ArrayList<Piece> attack) {
		return records.hasMoved(spotAt(1, y), Rook.class, time) || records.hasMoved(spotAt(5, y), King.class, time)
				|| spotAt(2, y).occupied() || spotAt(3, y).occupied() || spotAt(4, y).occupied()
				|| isAttacked(attack, spotAt(5, y)) || isAttacked(attack, spotAt(3, y))
				|| isAttacked(attack, spotAt(4, y));
	}

	private boolean canNotShortCastling(int y, ArrayList<Piece> attack) {
		return records.hasMoved(spotAt(8, y), Rook.class, time) || records.hasMoved(spotAt(5, y), King.class, time)
				|| spotAt(6, y).occupied() || spotAt(7, y).occupied() || isAttacked(attack, spotAt(5, y))
				|| isAttacked(attack, spotAt(6, y)) || isAttacked(attack, spotAt(7, y));
	}

	/**
	 * 
	 * @return the out put of the last move, which will be displayed in the box
	 *         or as part of the records.
	 */
	public String lastMoveOutPrint() {
		Move move = lastMove();
		if (move == null)
			return "Hasn't start the chess yet!";
		return move.outPrint();
	}

	/**
	 * 
	 * @return the description of the last move, which will be printed out in
	 *         the top label.
	 */
	public String lastMoveDiscript() {
		Move move = lastMove();
		if (move == null)
			return "Hasn't start the chess yet!";
		return move.getDescript();
	}

	// --------------------------------------------------------------------------------------------------------------
	// the methods to modifies the chess board.

	public Move lastMove() {
		if (time == 0)
			return null;
		return records.get(time - 1);
	}

	/**
	 * 
	 * If a piece is captured, and we need to put it off the board, we call this
	 * method. This method is usually called with moveTo(Spot s) method in
	 * Piece.
	 * 
	 * @param taken
	 *            the piece that is captured
	 */
	public void takeOffBoard(Piece taken) {
		Square p = taken.getP();
		if (p == null)
			return;
		taken.getP().setOccupied(null);
		if (taken.getWb())
			white.remove(taken);
		else
			black.remove(taken);
	}

	/**
	 * This method is only used to undo previous move.
	 * 
	 * @param taken
	 *            the piece that is returned to the board
	 */
	public void putBackToBoard(Piece taken, Square spot) {
		if (taken != null) {
			if (taken.getWb()) {
				white.add(taken);
			} else {
				black.add(taken);
			}
			taken.moveTo(spot);
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	// Methods to deal with the commands and requested moves by the user.
	/**
	 * changes the records and the chessboard, so everything will return back to
	 * the state of previous round.
	 */
	public boolean undoLastMove() {
		Move lastMove = lastMove();
		if (lastMove == null)
			return false;
		lastMove.undo(this);
		records.removeLast();//TODO: records can be improved
		time--;
		whoseTurn = !whoseTurn;
		return true;
	}

	// ----------------------------------------------------------------------------------------------------------
	// Methods to deal with the commands and requested moves by the user.

	/**
	 * This method will be called if the user request to make a castling.
	 * 
	 * @param longOrShort
	 * @return
	 */
	public boolean castling(boolean longOrShort) {
		King king;
		if (whoseTurn)
			king = (King) white.get(0);
		else
			king = (King) black.get(0);

		Move move = canCastling(king, longOrShort);
		if (move != null) {
			makeMove(move);
			return true;
		}
		return false;
	}

	/**
	 * perform command to move piece to certain spot
	 * 
	 * @param piece
	 * @param end
	 * @return true if move is valid, false if not allowed by chess rule
	 */
	public boolean performMove(Piece piece, Square end) {
		Move move = piece.canMove(end);
		if (move == null)
			move = piece.canCapture(end);
		
		if (move != null) {
			makeMove(move);
		} else
			return false;
		return true;
	}

	private void makeMove(Move move) {
		move.performMove(this);
		records.add(move);

		// String s = "";
		if (checkOrNot(whoseTurn)) {
			// s = "Check!! ";
			if (checkMate()) {
				endGame(Win.BLACKCHECKMATE);
				return;
			}
		} else {
			if (checkMate()) {
				endGame(Draw.STALEMENT);
				return;
				// return "Draw -- Stalement!\n" ;
				// + print(); TODO: add this feature back when other things are
				// fixed
			}
			// s = "" + canClaimDraw;
		}

		whoseTurn = !whoseTurn;
		time++;

		for (ChessListener listener : listeners)
			listener.nextMove(move);
	}

	
	// methods that end the game

	void endGame(EndGame endgame) {
		records.endGame(endgame);
		for (ChessListener listener : listeners)
			listener.endGame(endgame);
	}

	/**
	 * According to the chess law, no player can request for draw consecutively.
	 * if he has just made a request for draw, he cannot make another request
	 * for draw, untill his opponent makes a request for draw, and is declined.
	 * 
	 * The class is made to decide whether a player can request for draw.
	 */

	// ---------------------------------------------------------------------------------------------------------------------------
	// methods that send message to control

	public void addChessListener(ChessListener chessListener) {
		listeners.add(chessListener);
	}

	public void removeChessListener(ChessControl chessControl) {
		listeners.remove(chessControl);
	}

	public void updateSquare(Square square) {
		for (ChessListener listener : listeners)
			listener.updateSquare(square);
	}

	

	public Piece promotion(boolean wb, Square end) { //TODO: bad design
		for (ChessListener listener : listeners) {
			return listener.promote(wb , end);
		}
		throw new ChessGameException("OOOOps!");
	}

}