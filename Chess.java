import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

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
	private ArrayList<Move> records;
	private String canClaimDraw = "";
    Piece highLight;
//	private JLabel outLabel;
//	private Console outBox;
	private DrawRequest r;
	private boolean gameHasEnded;

	private List<ChessListener> listeners;
	
	// ----------------------------------------------------------------------------------------------------------------------------
	// constructors and methods used to create and initializes the chess game.
	/**
	 * construct a default chess with start setting.
	 */
	public Chess() {
		whoseTurn = true;
		time = 1;
		records = new ArrayList<Move>();
		spots = new Square[8][8];
		white = new ArrayList<Piece>();
		black = new ArrayList<Piece>();
		r = new DrawRequest();
		gameHasEnded = false;
		listeners = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square t;
					t = new Square(i , j , this);
					spots[i][j] = t;
				int y = t.Y();
				if (y == 1) {
					white.add(startSet(t.X(), true, t));
				} else if (y == 2) {
					white.add(new Pawn('P', true, t));
				} else if (y == 7) {
					black.add(new Pawn('P', false, t));
				} else if (y == 8) {
					black.add(startSet(t.X(), false, t));
				}
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
			return new Rook('R', b, p);
		else if (x == 2 || x == 7)
			return new Knight('N', b, p);
		else if (x == 3 || x == 6)
			return new Bishop('B', b, p);
		else if (x == 4)
			return new Queen('Q', b, p);
		else if (x == 5)
			return new King('K', b, p);
		else
			return null;
	}

//	protected void initializedOutPutMethod(JLabel j, Console a) {
//		outLabel = j;
//		outBox = a;
//	}

	// ---------------------------------------------------------------------------
	// Accessors

	public boolean getWhoseTurn() {
		return whoseTurn;
	}

	/**
	 * 
	 * @return true if the game has terminated.
	 */
	public boolean hasEnd() {
		return gameHasEnded;
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

	// ------------------------------------------------------------------------------------------------------
	// methods to add records

	/**
	 * add a record about an ordinary move
	 * 
	 * @param moved
	 *            the piece moved
	 * @param start
	 *            the starting position
	 * @param taken
	 *            the piece captured in this move, it could be null, if there is
	 *            no piece capture.
	 * @param end
	 *            the position moved to
	 */
	protected void addRecord(Piece moved, Square start, Piece taken, Square end) {
		records.add(new Move(moved, start, taken, end, time, checkOrNot(whoseTurn)));
	}

	/**
	 * 
	 * add a record about this castling
	 * 
	 * @param king
	 *            the king in this castling
	 * @param kingStart
	 *            the original position of the king
	 * @param kingEnd
	 *            the final posion of the king
	 * @param rook
	 *            the rook in this castling
	 * @param rookStart
	 *            the original positon of the rook
	 * @param rookEnd
	 *            the final positon of the rook, though this parameter is not
	 *            actually used
	 */
	protected void addRecord(King king, Square kingStart, Square kingEnd, Rook rook, Square rookStart, Square rookEnd) {
		records.add(new Castling(king, kingStart, kingEnd, rook, rookStart, time, checkOrNot(whoseTurn)));
	}

	/**
	 * add a record about promotion of pawn
	 * 
	 * @param promoteTo
	 *            the piece that the pawn promoted to
	 */
	protected void addPromotionRecord(Piece moved, Square start, Piece taken, Square end, Piece promotedTo) {

		takeOffBoard(moved);
		putBackToBoard(promotedTo, end);

		Promotion a = new Promotion(moved, start, taken, end, time, checkOrNot(whoseTurn), promotedTo);
		records.add(a);
	}

	// -------------------------------------------------------------------------------------------------------------------
	// find out about the condition of the game, (In check etc.)

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
			if (i.canAttack(p, this))
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
					if (i.canMove(p, this) || i.canCapture(p, this))
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
	private void canClaimDraw() {
		int recordNum = records.size();
		if (recordNum > 50) {
			boolean quiet = true;
			for (int i = recordNum - 50; i < recordNum; i++) {
				if (records.get(i).notQuiet()) {
					quiet = false;
					break;
				}
			}
			if (quiet) {
				canClaimDraw = "You can claim draw -- Fifty-move rule.\n";
				return;
			}
		}
		for (int i = 2; i < 25; i++) {
			if (i * 4 > recordNum)
				return;
			boolean repetition = true;
			for (int j = 1; j <= 2 * i; j++) {
				if (!records.get(recordNum - j).equals(records.get(recordNum - j - 2 * i))) {
					repetition = false;
					break;
				}
			}
			if (repetition) {
				canClaimDraw = "You can claim draw -- threefold repetition.\n";
				return;
			}
		}
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
		if (records.size() < 1)
			return false;
		return records.get(records.size() - 1).canEnPassant(end);
	}

	/**
	 * this method is only used by undo method of Move, it will check whether
	 * that move was an En Passant move.
	 * 
	 * @param end
	 * @return true, if that move was an En Passant move.
	 */
	public boolean canEnPassantFromUndoMethod(Square end) {
		return records.get(records.size() - 2).canEnPassant(end);
	}

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
	public boolean canCastling(King k, boolean longOrShort) {
		ArrayList<Piece> attack;
		if (k.getWb()) {
			attack = black;
		} else {
			attack = white;
		}
		if (longOrShort)
			return !canNotLongCastling(k.getY(), attack);
		else
			return !canNotShortCastling(k.getY(), attack);
	}

	private boolean canNotLongCastling(int y, ArrayList<Piece> attack) {
		return hasMoved(spotAt(1, y), 'R') || hasMoved(spotAt(5, y), 'K') || spotAt(2, y).occupied()
				|| spotAt(3, y).occupied() || spotAt(4, y).occupied() || isAttacked(attack, spotAt(5, y))
				|| isAttacked(attack, spotAt(3, y)) || isAttacked(attack, spotAt(4, y));
	}

	private boolean canNotShortCastling(int y, ArrayList<Piece> attack) {
		return hasMoved(spotAt(8, y), 'R') || hasMoved(spotAt(5, y), 'K') || spotAt(6, y).occupied()
				|| spotAt(7, y).occupied() || isAttacked(attack, spotAt(5, y)) || isAttacked(attack, spotAt(6, y))
				|| isAttacked(attack, spotAt(7, y));
	}

	/**
	 * 
	 * @param original
	 *            the original positon of the piece
	 * @param type
	 *            the type of the piece originally at this square
	 * @return true if the original piece has ever moved or be taken since the
	 *         game started.
	 */
	private boolean hasMoved(Square original, char type) {
		if (!original.occupied() || !original.getPiece().isType(type))
			return true;
		for (Move i : records) {
			if (original.equals(i.getStart()))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @return the out put of the last move, which will be displayed in the box
	 *         or as part of the records.
	 */
	public String lastMoveOutPrint() {
		return records.get(records.size() - 1).outPrint();
	}

	/**
	 * 
	 * @return the description of the last move, which will be printed out in
	 *         the top label.
	 */
	public String lastMoveDiscript() {
		if (records.size() == 0)
			return "Welcome to Greate Chess Game!";
		return records.get(records.size() - 1).getDiscript();
	}

	// --------------------------------------------------------------------------------------------------------------
	// the methods to modifies the chess board.

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
		System.out.print(p);
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

	/**
	 * changes the records and the chessboard, so everything will return back to
	 * the state of previous round.
	 */
	public void undoLastMove() {
		Move lastMove = records.get(records.size() - 1);
		lastMove.undo(this);
		records.remove(records.size() - 1);
	}

	/**
	 * After the smart program has decided that this move is legall and wants to
	 * process this move, this method is called.
	 * 
	 * 
	 * This method will check whether there is checkmake, or stalment in the
	 * game. Add comment if I have just make a check, terminate the game if
	 * necessary.
	 * 
	 * @return necessary information
	 */
	public String nextMove() {
		String s = "";
		if (checkOrNot(whoseTurn)) {
			s = "Check!! ";
			if (checkMate()) {
				if (whoseTurn) {
					win(true, "WHITE Checkmates the BLACK, WHITE wins!!");
					return "White wins! -- CHECKMATE!!/n";
				} else {
					win(false, "BLACK Checkmates the WHITE, BLACK wins!!");
					return "Black wins! -- CHECKMATE!!/n";
				}
			}
		} else {
			if (checkMate()) {
				draw("Draw due to Stalement.");
				return "Draw -- Stalement!\n" + print();
			}
			canClaimDraw();
			s = "" + canClaimDraw;
		}
		printInLabel(lastMoveDiscript());
		whoseTurn = !whoseTurn;
		s += "Next move -- ";
		if (whoseTurn) {
			time++;
			return s + "white";
		} else {
			return s + "black";
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	// Methods to deal with the commands and requested moves by the user.

	/**
	 * This method is called if the user enter a command to undo his move. It
	 * will undo two moves.
	 * 
	 * @return
	 */
	public String undoPreviousRround() {
		if (time == 1)
			return "It is already the start of Game";
		undoLastMove();
		undoLastMove();
		time--;
		return "Undo the Previous Move!";
	}

	/**
	 * print out the records of the game in starndart chess recording language
	 * 
	 * @return records
	 */
	public String print() {
		if (records.size() == 0)
			return "Game hasn't started yet.";
		String s = "";
		for (Move i : records) {
			s += i.print();
		}
		return s;
	}

	/**
	 * This method will be called if the user request to make a castling.
	 * 
	 * @param s
	 * @return
	 */
	public String castling(String s) {
		s = s.toLowerCase();
		boolean longOrShort;
		if (s.equals("o-o")) {
			longOrShort = false;
		} else if (s.equals("o-o-o")) {
			longOrShort = true;
		} else
			return "For short castling, enter \"O-O\" and for long castling, enter \"O-O-O\".";

		King king;
		if (whoseTurn)
			king = (King) white.get(0);
		else
			king = (King) black.get(0);

		if (canCastling(king, longOrShort))
			return king.castling(this, longOrShort);
		return "You cannot do castling, please check the rules for castling.";
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
	public String makeMove(String s) {
		char type;
		if (s.length() == 5) {
			s = 'P' + s;
			type = 'P';
		} else {
			type = s.toUpperCase().charAt(0);
			if (!(type == 'R' || type == 'N' || type == 'B' || type == 'Q' || type == 'K' || type == 'P'))
				return "Please enter valid initial of chessman -- R(Root), N(Knight), B(Bishop), Q(Queen), K(King). If you omit it, it is assumed as Pawn.";
		}

		s = s.toLowerCase();
		Square start = getSquare(s.substring(1, 3));
		if (start == null)
			return "please enter a valid start Position";

		boolean takeOrNot;
		char action = s.charAt(3);
		if (action == '-')
			takeOrNot = false;
		else if (s.charAt(3) == 'x')
			takeOrNot = true;
		else
			return "Pleae enter \"-\" or \"x\" to indicate whether this move takes some piece or not.";

		Square end = getSquare(s.substring(4));
		if (end == null)
			return "please enter a valid end Position";

		Piece movedChessman = start.getPiece();
		if (movedChessman == null) {
			if (whoseTurn)
				return "There should be a white chessman in the start Position!";
			else
				return "There should be a black chessman in the start Position!";
		}
		if (!(movedChessman.isType(type)))
			return "The chessman in the start Position is not corret! \n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn";

		Piece chessmanTaken = end.getPiece();

		if (takeOrNot) {
			if (movedChessman.canCapture(end, this))
				return movedChessman.capture(end, chessmanTaken, this);
			else
				return "Illegal move! Please check the rule of " + movedChessman.getName() + "!";
		} else {
			if (chessmanTaken != null) {
				return "It works this time,but please use \"x\" if you want to take it next time. Thank you!\n"
						+ makeMove(s.replace('-', 'x'));
			}
			if (movedChessman.canMove(end, this))
				return movedChessman.move(end, this);
			else
				return "Illegal move! Please check the rule of " + movedChessman.getName() + "!";
		}
	}

	/**
	 * Tranformed the abbreviated command to the complete one, or return error
	 * if there is ambiguous about the abbreviated command.
	 * 
	 * @param s
	 * @return
	 */
	public String figureOutTheAbbreviation(String s) {
		char type = s.charAt(0);
		if (type == 'R' || type == 'N' || type == 'B' || type == 'Q' || type == 'K' || type == 'P')
			s = s.substring(1);
		else
			type = 'P';

		boolean takeOrNot = (s.charAt(0) == 'x');

		if (takeOrNot)
			s = s.substring(1);

		Square end = null;
		if (s.length() == 2)
			end = getSquare(s);
		if (end == null)
			return null;

		ArrayList<Piece> possible = new ArrayList<Piece>();
		ArrayList<Piece> set;

		if (whoseTurn)
			set = white;
		else
			set = black;

		if (takeOrNot) {
			for (Piece i : set) {
				if (i.isType(type) && i.canCapture(end, this))
					possible.add(i);
			}
		} else {
			for (Piece i : set) {
				if (i.isType(type) && i.canMove(end, this))
					possible.add(i);
			}
		}
		if (possible.size() == 0) {
			return "Ambiguity: No one can reach that spot.";
		} else if (possible.size() == 1) {
			String newStr = "" + type;
			newStr += possible.get(0).getP().toString();
			if (takeOrNot)
				newStr += "x";
			else
				newStr += "-";
			newStr += end.toString();
			return newStr;
		} else {
			return "Ambiguity: This can represent many different moves.";
		}
	}

	/**
	 * Find out if it is legal to claim draw. If it is, ends the game and claim
	 * draw, otherwise send a request for draw, and wait for the reply of
	 * opponent.
	 * 
	 * @return
	 */
	public String askForDraw() {
		if (canClaimDraw.isEmpty()) {
			if (r.canAskFordraw(whoseTurn)) {
				while (true) {
					String command = JOptionPane.showInputDialog("Do you agree draw?");
					if (command.isEmpty())
						continue;
					if (command.toLowerCase().startsWith("yes")) {
						return draw("Draw by Agreement.");
					} else if (command.toLowerCase().startsWith("no"))
						break;
				}
				r.setRightToRequestDraw(whoseTurn);
				return "Request declined";
			} else {
				return "You cannot request for draw again now.";
			}

		} else {
			return draw(canClaimDraw);
		}

	}

	/**
	 * According to the chess law, no player can request for draw consecutively.
	 * if he has just made a request for draw, he cannot make another request
	 * for draw, untill his opponent makes a request for draw, and is declined.
	 * 
	 * The class is made to decide whether a player can request for draw.
	 */
	class DrawRequest {
		private boolean white;
		private boolean black;

		protected DrawRequest() {
			white = true;
			black = true;
		}

		/**
		 * If one player make a request for draw, and is declined, this method
		 * will be called.
		 * 
		 * @param whoseTurn
		 *            who makes the request for draw
		 */
		protected void setRightToRequestDraw(boolean whoseTurn) {
			if (whoseTurn) {
				white = false;
				black = true;
			} else {
				white = true;
				black = false;
			}
		}

		/**
		 * 
		 * @param whoseTurn
		 *            who should play next move
		 * @return true if he can request for draw
		 */
		protected boolean canAskFordraw(boolean whoseTurn) {
			if (whoseTurn)
				return white;
			else
				return black;
		}
	}

	/**
	 * This method is caled if the player resigns. It will ends the game.
	 * 
	 * @return
	 */
	public String resign() {
		if (whoseTurn) {
			return win(false, "White resigns, Black wins.");
		} else {
			return win(true, "Black resigns, White wins");
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// methods that end the game
	/**
	 * ends the game as draw
	 * 
	 * @param descript
	 * @return
	 */
	private String draw(String descript) {
		gameHasEnded = true;
		records.get(records.size() - 1).draw(descript);
		return "DRAW";
	}

	/**
	 * ends the game when one player beats the other
	 * 
	 * @param who
	 *            the winner
	 * @param descrpt
	 *            how he win
	 * @return
	 */
	private String win(boolean who, String descrpt) {
		gameHasEnded = true;
		records.get(records.size() - 1).win(who, descrpt);
		if (who) {
			return "WHITE wins!";
		} else {
			return "BLACK wins!";
		}
	}

	/**
	 * this mehod will be called if the user click on the board. It will find
	 * out whether the user has suggest a legal move, and provides proper
	 * outputs.
	 * 
	 * @param spot
	 *            the square that is clicked
	 */
	public void click(Square spot) {
//		if (highLight != null) {
//			if (spot.isHighLight() && !spot.equals(highLight.getP())) {
//				String s = "";
//
//				if (highLight.canMove(spot, this))
//					s = highLight.move(spot, this);
//				else if (highLight.canCapture(spot, this))
//					s = highLight.capture(spot, spot.getPiece(), this);
//
//				printchosenPiece(lastMoveOutPrint());
//				printInBox("\n" + s);
//				printInLabel(lastMoveDiscript());
//			} else
//				printCleanTemp();
//			deHighLightWholeBoard();
//		} else {
//			if (spot.occupiedBy(whoseTurn)) {
//				setHighLightPiece(spot.getPiece());
//				printchosenPiece(spot.getPiece().getType() + spot.toString());
//			}
//		}
	}


	// ----------------------------------------------------------------------------------------------------------------
	// all the methods to show the outputs
	/**
	 * when one possible piece is chosen, hightlight it and all the spots it can
	 * move to.
	 * 
	 * @param piece
	 */
	public void setHighLightPiece(Piece piece) {
		highLight = piece;
		piece.getP().highLight();
		for (Square[] j : spots) {
			for (Square i : j) {
				if (!i.occupiedBy(whoseTurn))
					if (highLight.canMove(i, this) || highLight.canCapture(i, this))
						i.highLight();
			}
		}
	}

	/**
	 * dehightlight the whole board
	 */
	public void deHighLightWholeBoard() {
		highLight = null;
		for (Square[] j : spots) {
			for (Square i : j) {
				if (i.isHighLight())
					i.deHighLight();
			}
		}
	}
//
//	/**
//	 * print out the result in the box.
//	 */
//	protected void printInBox(String s) {
//		outBox.printOut(s);
//	}
//
//	/**
//	 * print out the temporal piece that is chosen in the box
//	 * 
//	 * @param s
//	 */
//	protected void printchosenPiece(String s) {
//		if (s.charAt(0) == 'P')
//			outBox.printTemp(s.substring(1));
//		else
//			outBox.printTemp(s);
//	}
//
//	/**
//	 * clean the temporal piece information, because the user suggests a illegal
//	 * move
//	 */
//	protected void printCleanTemp() {
//		outBox.cleanTemp();
//	}
//
//	/**
//	 * print out the outputs in the head label.
//	 * 
//	 * @param s
//	 */
//	protected void printInLabel(String s) {
//		outLabel.setText(s);
//	}

	
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
	
	private void printInLabel(String str) {
		for (ChessListener listener : listeners)
			listener.printInLabel(str);
	}

	
}