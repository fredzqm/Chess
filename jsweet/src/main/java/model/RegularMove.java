package model;

public class RegularMove extends Move {

	public RegularMove(Piece movedPiece, Square startPosition, Piece capturedPiece, Square lastPosition) {
		super(movedPiece, startPosition, capturedPiece, lastPosition);
	}

	/**
	 * 
	 * @return the description of this move, which will appear in the top label
	 *         of the window
	 */
	public String getDescript() {
		String description = "";
		if (this.isWhite)
			description += "White";
		else
			description += "Black";
		description += " " + movedPiece.getName();
		if (capturedPiece == null)
			description += " moves to " + lastPosition.toString();
		else {
			description += " catches ";
			if (this.isWhite)
				description += "black ";
			else
				description += "white ";
			description += capturedPiece.getName();
			description += " on " + lastPosition.toString();
		}
		description += note.getDescriptEnd();
		return description;
	}

	/**
	 * undo the last move, and restore the board
	 * 
	 * @param c
	 */
	public void undo(Chess chess) {
		movedPiece.moveTo(startPosition);
		if (capturedPiece != null)
			chess.putBackToBoard(capturedPiece, lastPosition);
	}

	public void performMove(Chess chess) {
		if (capturedPiece != null)
			chess.takeOffBoard(capturedPiece);
		movedPiece.moveTo(lastPosition);
	}

}
