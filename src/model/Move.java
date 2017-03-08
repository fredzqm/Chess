package model;

/**
 * this class is a record of all the moves in this game, so we can undo steps or
 * check for special rules.
 * 
 * @author zhangq2
 *
 */
public class Move {
	protected final int round;
	protected final boolean wb;
	protected final Piece moved;
	protected final Square start;
	protected final Piece taken;
	protected final Square end;
	// protected final EndGame endGame;
	// protected boolean checkOrNot;
	protected MoveNote note;

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
	 */
	public Move(Piece moved, Square start, Piece taken, Square end, int round) {
		this.round = round;
		wb = moved.getWb();
		this.moved = moved;
		this.start = start;
		this.taken = taken;
		this.end = end;
		this.note = MoveNote.NONE;
	}

	/**
	 * @return the documentation in standard chess convention
	 */
	public String getDoc() {
		String s = "";
		if (!moved.isType(Pawn.class))
			s += moved.getType();
		s += start.toString();
		if (taken == null)
			s += "-";
		else
			s += "x";
		s += end.toString();
		s += note.getDocEnd();
		// if (endGame == Win.WHITECHECKMATE || endGame == Win.BLACKCHECKMATE)
		// s += "+";

		// if (endGame != null) {
		// s += "\n"+endGame.getDoc();
		// }
		return s;
	}

	/**
	 * @return the messages necessary to printOut in the console
	 */
	public String getPrintOut() {
		return getDoc();
	}

	/**
	 * 
	 * @return the description of this move, which will appear in the top label
	 *         of the window
	 */
	public String getDescript() {
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
		s += note.getDescriptEnd();
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
		if (taken != null)
			chess.putBackToBoard(taken, end);
	}

	/**
	 * This method is called to examine whether the game has meets the
	 * requirement of 'Fifty move rule".
	 * 
	 * @return true if this move can be redo over and over again later.
	 */
	protected boolean notQuiet() {
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

	public String toString() {
		return getPrintOut() + " " + getDescript();
	}

	public void performMove(Chess chess) {
		if (taken != null)
			chess.takeOffBoard(taken);
		moved.moveTo(end);
	}

	public boolean getWhoseTurn() {
		return wb;
	}

	// /**
	// *
	// * ends the game as draw
	// *
	// * @param s
	// * the description of how this game ends in draw
	// */
	// public void endGame(EndGame endgame) {
	// endGame = endgame;
	// }

}
