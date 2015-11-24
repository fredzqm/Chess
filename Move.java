/**
 * this class is a record of all the moves in this game, so we can undo steps or
 * check for special rules.
 * 
 * @author zhangq2
 *
 */
public class Move {
	protected int time;
	protected boolean wb;
	protected Piece moved;
	protected Square start;
	protected Piece taken;
	protected Square end;
	protected boolean checkOrNot;
	protected String endGame;
	protected int result;

	/**
	 * constructs a record
	 * 
	 * @param moved
	 *            the piece moved
	 * @param start
	 *            the start position of this move
	 * @param taken
	 *            the piece that is captured, null if there is nothing captured.
	 * @param end
	 *            the end position of this move
	 * @param time
	 *            which round this move happens
	 * @param checkOrNot
	 *            whether this move check the opponent
	 */
	public Move(Piece moved, Square start, Piece taken, Square end, int time) {
		this.time = time;
		wb = moved.getWb();
		this.moved = moved;
		this.start = start;
		this.taken = taken;
		this.end = end;
		endGame = null;
		result = 0;
	}

	/**
	 * constructs a record
	 * 
	 * @param moved
	 *            the piece moved
	 * @param start
	 *            the start position of this move
	 * @param taken
	 *            the piece that is captured, null if there is nothing captured.
	 * @param end
	 *            the end position of this move
	 * @param time
	 *            which round this move happens
	 * @param checkOrNot
	 *            whether this move check the opponent
	 */
	public Move(Piece moved, Square start, Piece taken, Square end, int time, boolean checkOrNot) {
		this.time = time;
		wb = moved.getWb();
		this.moved = moved;
		this.start = start;
		this.taken = taken;
		this.end = end;
		this.checkOrNot = checkOrNot;
		endGame = null;
		result = 0;
	}

	/**
	 * @return the output words that will appeal in the box.
	 */
	public String outPrint() {
		String s = "";
		if (moved.isType(Pawn.class))
			s += moved.getType();
		s += start.toString();
		if (taken == null)
			s += "-";
		else
			s += "x";
		s += end.toString();

		if (endGame != null) {
			if (result != 0) {
				if (result > 0)
					s += "\n1-0";
				else
					s += "\n1-0";
			} else {
				s += "\n1/2-1/2";
			}
		} else {
			if (checkOrNot)
				s += "+";
		}
		return s;
	}

	/**
	 * 
	 * @return the words that will appear in the top label of the window
	 */
	public String getDescript() {

		if (endGame != null)
			return endGame;

		String s = "";
		if (wb)
			s += "White";
		else
			s += "Black";
		s += " " + moved.getName();
		if (taken == null)
			s += " moves to " + end.toString();
		else {
			s += " catches ";
			if (wb)
				s += "black ";
			else
				s += "white ";
			s += taken.getName();
			s += " on " + end.toString();
		}
		if (checkOrNot)
			s += " Check!";
		return s;
	}

	// accessor
	/**
	 * 
	 * @return start square
	 */
	public Square getStart() {
		return start;
	}

	/**
	 * called when the program needs to find out whether it is legal to make a
	 * castling.
	 * 
	 * @param p
	 * @return true, this last move matches the rule, and it is legal to make a
	 *         castling
	 */
	public boolean canEnPassant(Square p) {
		return moved.isType(Pawn.class)
				&& (start.X() == p.X() && end.X() == p.X() && (start.Y() + end.Y()) == (p.Y() * 2));
	}

	/**
	 * undo the last move, and restore the board
	 * 
	 * @param c
	 */
	public void undo(Chess chess) {
		moved.moveTo(start);
		if (taken != null) {
			chess.putBackToBoard(taken, end);
			// check if this move is a En Passant, if it is move the taken Pawn
			// to where it supposed to go
			if (moved.isType(Pawn.class) && taken.isType(Pawn.class))
				if (chess.canEnPassantFromUndoMethod(end))
					taken.moveTo(chess.spotAt(end.X(), 3 + end.Y() / 3));
		}
	}

	/**
	 * called when the users require a full records of the whole game.
	 * 
	 * @return the complete results of the game till now.
	 */
	public String print() {
		String s = "";
		if (wb) {
			s += time + ". ";
			s += outPrint();
		} else {
			s += " ";
			s += outPrint();
			s += "\n";
		}
		return s;
	}

	/**
	 * This method is called to examine whether the game has meets the
	 * requirement of 'Fifty move rule".
	 * 
	 * @return true if this move can be redo over and over again later.
	 */
	public boolean notQuiet() {
		return taken != null || moved.isType(Pawn.class);
	}

	/**
	 * This methods is called to examine whether 'threefold repetition rule'.
	 * 
	 * @param x
	 * @return if two moves are exactly the same and repeatable.
	 */
	public boolean equals(Move x) {
		if (notQuiet() || x.notQuiet())
			return false;
		if (x instanceof Castling)
			return false;
		return moved.equals(x.moved) && start.equals(x.start) && end.equals(x.end);
	}

	// games ends
	/**
	 * 
	 * ends the game as draw
	 * 
	 * @param s
	 *            the description of how this game ends in draw
	 */
	public void draw(String s) {
		endGame = s;
	}

	/**
	 * ends the game when one player defeats the other player.
	 * 
	 * @param who
	 *            who wins
	 * @param s
	 *            the description of how this game ends in win.
	 */
	public void win(boolean who, String s) {
		if (who) {
			result = 1;
		} else {
			result = -1;
		}
		endGame = s;
	}

	public String toString() {
		return outPrint() + " " + getDescript();
	}

	public void performMove(Chess chess) {
		if (taken != null)
			chess.takeOffBoard(taken);
		moved.moveTo(end);
		checkOrNot = chess.checkOrNot(chess.getWhoseTurn());
	}

	public boolean getWhoseTurn() {
		return wb;
	}

}
