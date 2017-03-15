package model;

public class InvalidMoveException extends Exception {

	public enum Type {
		invalidFormat,
		castleNotAllowed,
		pieceNotPresent,
		incorrectPiece,
		impossibleMove,
		ambiguousMove
	}

	public Type type;
	
	public InvalidMoveException(Type type) {
		this.type = type;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
