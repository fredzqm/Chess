package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Piece.Player;

/**
 * It is the system for a chess game. It has fields to store the condition of
 * the chess and method to access and modify those information.
 * 
 * @author zhangq2
 *
 */
public class Chess {
	private int time;
	private Board board;
	private ArrayList<Piece> white;
	private ArrayList<Piece> black;
	private Record records;

	private Collection<Square> list;

	// ----------------------------------------------------------------------------------------------------------------------------
	// constructors and methods used to create and initializes the chess game.
	/**
	 * construct a default chess with start setting.
	 * 
	 */
	public Chess() {
		time = 0;
		records = new Record();
		board = new Board();
		white = new ArrayList<Piece>();
		black = new ArrayList<Piece>();
		list = new ArrayList<Square>();

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Square t = board.spotAt(i, j);
				int y = t.Y();
				if (y == 1) {
					white.add(startSet(t.X(), Player.WHITE, t));
				} else if (y == 2) {
					white.add(new Pawn(Player.WHITE, t, this));
				} else if (y == 7) {
					black.add(new Pawn(Player.BLACK, t, this));
				} else if (y == 8) {
					black.add(startSet(t.X(), Player.BLACK, t));
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
	 * @param c
	 *            White or Black
	 * @param p
	 *            original position of this piece
	 * @return creates new pieces to put into the start chessboard.
	 */
	private Piece startSet(int x, Player c, Square p) {
		if (x == 1 || x == 8)
			return new Rook(c, p, this);
		else if (x == 2 || x == 7)
			return new Knight(c, p, this);
		else if (x == 3 || x == 6)
			return new Bishop(c, p, this);
		else if (x == 4)
			return new Queen(c, p, this);
		else if (x == 5)
			return new King(c, p, this);
		else
			return null;
	}

	// ---------------------------------------------------------------------------
	// Accessors

	public Player getWhoseTurn() {
		if(time % 2 == 0) {
			return Player.WHITE;
		} else {
			return Player.BLACK;
		}
	}

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
	 * @return the board of the chess
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * 
	 * @param x
	 *            the file of the square
	 * @param y
	 *            the rank of the square
	 * @return the position of the square
	 */
	public Square spotAt(int x, int y) {
		return board.spotAt(x, y);
	}

	public Collection<Square> getAllSquares() {
		return list;
	}

	public Record getRecords() {
		return records;
	}

	@Override
	public String toString() {
		return board.toString();
	}

	/**
	 * 
	 * @param type
	 *            type of the piece
	 * @param end
	 *            square to move to
	 * @return all possible pieces that can move to given square
	 */
	public ArrayList<Piece> possibleMovers(Class<? extends Piece> type, Square end) {
		ArrayList<Piece> possible = new ArrayList<Piece>();
		ArrayList<Piece> set;
		if (getWhoseTurn() == Player.WHITE)
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
	 * 
	 * @param chosen
	 * @return the list of Squares that this piece can reach
	 */
	public ArrayList<Square> reachable(Piece chosen) {
		ArrayList<Square> list = new ArrayList<>();
		for (Square i : getAllSquares())
			if (chosen.canGo(i))
				list.add(i);
		return list;
	}

	/**
	 * Find out whether a certain move will put your own king in check.
	 * 
	 * The concept is simple here, presumably make the first and check if the
	 * opponent is checking {@link Chess#checkOrNot}. Then undo the move
	 * 
	 * @param move
	 * @return true if this move will give away the king
	 */
	public boolean giveAwayKing(Move move) {
		move.performMove(this);
		boolean giveAway = checkOrNot(move.getWhoseTurn() == Player.BLACK);
		move.undo(this);
		return giveAway;
	}

	/**
	 * checkOrNot(true) will return true if the white is checking the black's
	 * king
	 * 
	 * checkOrNot(false) will return true if the black is checking the white's
	 * king
	 * 
	 * @param attacker
	 *            who is attacking and tries to make this check
	 * @return whether it is checking
	 */
	public boolean checkOrNot(boolean attacker) {
		if (attacker)
			return isAttacked(true, black.get(0).getSpot());
		else
			return isAttacked(false, white.get(0).getSpot());
	}

	/**
	 * 
	 * @param whiteOrBlack
	 *            who is attacking (true, the while, false, the black)
	 * @param square
	 *            the square to check for
	 * @return true if square is attacked
	 */
	public boolean isAttacked(boolean whiteOrBlack, Square square) {
		ArrayList<Piece> attacker;
		if (whiteOrBlack)
			attacker = white;
		else
			attacker = black;

		for (Piece i : attacker) {
			if (i.canAttack(square) != null)
				return true;
		}
		return false;
	}

	/**
	 * 
	 * used to find if there is a checkmake, and stalment. if this method
	 * returns true, and it is now in check, it is a checkmate; but if it is now
	 * not in check, the opponent cannot make any legitimate move, so it is a
	 * draw by stalment.
	 * 
	 * @param checked
	 *            the side that can be potentially checkmaked
	 * @return after the opponent makes one move, is it possible for his king
	 *         not to be in check
	 */
	private boolean checkMate(boolean checked) {
		ArrayList<Piece> inCheck;
		if (checked)
			inCheck = white;
		else
			inCheck = black;
		for (Piece i : inCheck) {
			for (Square p : getAllSquares()) {
				if (i.canGo(p)) {
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
	 * 
	 * @return the type of draw if the game meets the automatic requirement for
	 *         draw, null if requirements not met
	 */
	public Draw canClaimDraw() {
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

	/**
	 * Castling is a very special rule of chess. Castling move can only be made
	 * if the king and rook in this castling have never moved yet, plus any
	 * squares the king goes through cannot be attacked.
	 * 
	 * find out whether castling is legal.
	 * 
	 * @param k
	 *            the king that tries to do this castling.
	 * @param longOrShort
	 *            long Casting happens on the Queen side, while the short
	 *            castling happens on the King side.
	 * @return the castling move if it is legal to make the castling of this
	 *         side right now, null otherwise
	 */
	public Move canCastling(King k, boolean longOrShort) {
		if (longOrShort) {
			if (canNotLongCastling(k.getY(), k.getWhiteOrBlack() == Player.BLACK))
				return null;
			return new Castling(k, k.getSpot(), spotAt(3, k.getY()), (Rook) (spotAt(1, k.getY()).getPiece()),
					spotAt(1, k.getY()), getRound());
		} else {
			if (canNotShortCastling(k.getY(), k.getWhiteOrBlack() == Player.BLACK))
				return null;
			return new Castling(k, k.getSpot(), spotAt(7, k.getY()), (Rook) (spotAt(8, k.getY()).getPiece()),
					spotAt(8, k.getY()), getRound());
		}
	}

	private boolean canNotLongCastling(int y, boolean attack) {
		return spotAt(2, y).occupied() || spotAt(3, y).occupied() || spotAt(4, y).occupied()
				|| isAttacked(attack, spotAt(5, y)) || isAttacked(attack, spotAt(3, y))
				|| isAttacked(attack, spotAt(4, y)) || records.hasMoved(spotAt(1, y), Rook.class, time)
				|| records.hasMoved(spotAt(5, y), King.class, time);
	}

	private boolean canNotShortCastling(int y, boolean attack) {
		return spotAt(6, y).occupied() || spotAt(7, y).occupied() || isAttacked(attack, spotAt(5, y))
				|| isAttacked(attack, spotAt(6, y)) || isAttacked(attack, spotAt(7, y))
				|| records.hasMoved(spotAt(8, y), Rook.class, time) || records.hasMoved(spotAt(5, y), King.class, time);
	}

	/**
	 * 
	 * @return the output of the last move, which will be displayed in the box
	 *         or as part of the records. null if the game has not started yet
	 */
	public String lastMoveOutPrint() {
		Move move = lastMove();
		if (move == null)
			return null;
		return move.getPrintOut();
	}

	/**
	 * 
	 * @return the description of the last move, which will be printed out in
	 *         the top label.
	 */
	public String lastMoveDiscript() {
		if (records.hasEnd())
			return records.getEndGameDescript();
		Move move = lastMove();
		if (move == null)
			return "Hasn't start the chess yet!";
		return move.getDescript();
	}

	public Move lastMove() {
		if (time == 0)
			return null;
		return records.get(time - 1);
	}

	// modifiers

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
		Square p = taken.getSpot();
		if (p == null)
			return;
		taken.getSpot().setOccupied(null);
		if (taken.getWhiteOrBlack() == Player.WHITE)
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
			if (taken.getWhiteOrBlack() == Player.WHITE) {
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
		records.removeLast();// TODO: records can be improved
		time--;
		return true;
	}

	// ----------------------------------------------------------------------------------------------------------
	// Methods to deal with the commands and requested moves by the user.

	/**
	 * perform command to move piece to certain spot
	 * 
	 * @param piece
	 * @param end
	 * @return true if move is valid, false if not allowed by chess rule
	 */
	public Move performMove(Piece piece, Square end) {
		Move move = piece.getMove(end);
		if (move != null) {
			makeMove(move);
		}

		return move;
	}

	/**
	 * This method will be called if the user request to make a castling.
	 * 
	 * @param longOrShort
	 * @return
	 */
	public boolean castling(boolean longOrShort) {
		King king;
		if (getWhoseTurn() == Player.WHITE)
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

	// ---------------------------------------------------------------------------------------------------------------------------
	// methods that send message to control

	/**
	 * All moves from the user input will go throw this method
	 * 
	 * @param move
	 */
	public void makeMove(Move move) {
		// make the move
		move.performMove(this);
		// add rocord
		records.add(move);

		// update time
		time++;

		// check end game situations
		if (checkOrNot(getWhoseTurn() == Player.BLACK)) {
			if (checkMate(getWhoseTurn() == Player.WHITE)) {
				move.note = MoveNote.CHECKMATE;
				if (getWhoseTurn() == Player.BLACK)
					endGame(Win.WHITECHECKMATE);
				else
					endGame(Win.BLACKCHECKMATE);
				return;
			}
			move.note = MoveNote.CHECK;
		} else {
			if (checkMate(getWhoseTurn() == Player.WHITE)) {
				endGame(Draw.STALEMENT);
				return;
			}
		}
	}

	protected Piece promotion(Player c, Square end) {
		return new Queen(c, end, this);
	}

	/**
	 * record end game information and send message to control
	 * 
	 * @param endgame
	 *            the kind of end game occurred
	 */
	public void endGame(EndGame endgame) {
		records.endGame(endgame);
	}

	/**
	 * Create a move object for the standard chess record.
	 * 
	 * @param s
	 *            String for the chess record
	 * 
	 * @return Move object representing the desired move
	 * 
	 */
	public Move getMove(String s) throws InvalidMoveException {
		Move move = null;

		if (s.startsWith("O")) {
			King king;
			if (getWhoseTurn() == Player.WHITE) {
				king = (King) white.get(0);
			} else {
				king = (King) black.get(0);
			}

			if (s.equals("O-O")) {
				move = canCastling(king, false);
			} else if (s.equals("O-O-O")) {
				move = canCastling(king, true);
			} else {
				throw new InvalidMoveException(s, InvalidMoveException.Type.invalidFormat);
			}

			if (move != null) {
				return move;
			} else {
				throw new InvalidMoveException(s, InvalidMoveException.Type.castleNotAllowed);
			}
		}

		Pattern p = Pattern.compile("([PRNBQK])?([a-h])?([1-8])?.*?([a-h][1-8]).*");
		Matcher m = p.matcher(s);
		if (m.matches()) {
			Class<? extends Piece> type = m.group(1) == null ? Pawn.class : Piece.getType(m.group(1).charAt(0));

			Square start = null;
			if ((m.group(2) != null) && (m.group(3) != null)) {
				start = board.getSquare(m.group(2) + m.group(3));
			}
			Square end = board.getSquare(m.group(4));

			if (start != null) {
				Piece movedChessman = start.getPiece();
				if (movedChessman == null) {
					throw new InvalidMoveException(s, InvalidMoveException.Type.pieceNotPresent);
				} else if (!(movedChessman.isType(type))) {
					throw new InvalidMoveException(s, InvalidMoveException.Type.incorrectPiece);
				}
				move = movedChessman.getMove(end);
			} else {
				ArrayList<Piece> possible = possibleMovers(type, end);
				if (possible.size() == 0) {
					throw new InvalidMoveException(s, InvalidMoveException.Type.impossibleMove);
				} else if (possible.size() == 1) {
					move = possible.get(0).getMove(end);
				} else {
					throw new InvalidMoveException(s, InvalidMoveException.Type.ambiguousMove);
				}
			}
		} else {
			throw new InvalidMoveException(s, InvalidMoveException.Type.invalidFormat);
		}

		return move;
	}

}