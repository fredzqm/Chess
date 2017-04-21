package model;

import java.util.Objects;

/**
 * this class is a record of all the moves in this game, so we can undo steps or
 * check for special rules.
 * 
 * @author zhangq2
 *
 */
public abstract class Move {
	protected final boolean isWhite;
	protected final Piece movedPiece;
	protected final Square startPosition;
	protected final Piece capturedPiece;
	protected final Square lastPosition;
	protected MoveNote note;

	/**
	 * constructs a record
	 * 
	 * @param movedPiece
	 *            the piece moved
	 * @param startPosition
	 *            the start position of this move
	 * @param capturedPiece
	 *            the piece that is captured, null if there is nothing captured.
	 * @param lastPosition
	 *            the end position of this move
	 * @param time
	 *            which round this move happens
	 */
	public Move(Piece movedPiece, Square startPosition, Piece capturedPiece, Square lastPosition) {
		this.isWhite = movedPiece.getWhiteOrBlack();
		this.movedPiece = movedPiece;
		this.startPosition = startPosition;
		this.capturedPiece = capturedPiece;
		this.lastPosition = lastPosition;
		this.note = MoveNote.NONE;
	}

	/**
	 * 
	 * @return start square
	 */
	public Square getStart() {
		return startPosition;
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
		return movedPiece.isType(Pawn.class) && (startPosition.getX() == p.getX() && lastPosition.getX() == p.getX()
				&& (startPosition.getY() + lastPosition.getY()) == (p.getY() * 2));
	}

	public String toString() {
		return getPrintOut() + " " + getDescript();
	}

	public boolean getWhoseTurn() {
		return isWhite;
	}

	/**
	 * @return the documentation in standard chess convention
	 */
	public String getDoc() {
		String doc = "";
		if (!movedPiece.isType(Pawn.class))
			doc += movedPiece.getType();
		doc += startPosition.toString();
		if (capturedPiece == null)
			doc += "-";
		else
			doc += "x";
		doc += lastPosition.toString();
		doc += note.getDocEnd();
		return doc;
	}

	/**
	 * @return the messages necessary to printOut in the console
	 */
	public String getPrintOut() {
		return getDoc();
	}

	/**
	 * This method is called to examine whether the game has meets the
	 * requirement of 'Fifty move rule".
	 * 
	 * @return true if this move can be redo over and over again later.
	 */
	public boolean notQuiet() {
		return capturedPiece != null || movedPiece.isType(Pawn.class);
	}

	public abstract String getDescript();

	public abstract void performMove(Chess chess);

	public abstract void undo(Chess chess);

	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Move) {
			Move x = (Move) obj;
			return isWhite == x.isWhite && movedPiece.equals(x.movedPiece) && startPosition.equals(x.startPosition)
					&& lastPosition.equals(x.lastPosition) && Objects.equals(capturedPiece, x.capturedPiece);
		}
		return false;
	}
}
