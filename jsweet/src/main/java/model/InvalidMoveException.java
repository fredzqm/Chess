package model;

public class InvalidMoveException extends Exception {

	public enum Type {
		invalidFormat,
		castleNotAllowed,
		pieceNotPresent,
		incorrectPiece,
		impossibleMove,
		ambiguousMove,
		promotionTo
	}

	public Type type;
	
	public InvalidMoveException(String message, Type type) {
		super(message + " : " + type.toString());
		this.type = type;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
