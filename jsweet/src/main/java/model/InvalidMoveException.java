package model;

public class InvalidMoveException extends Exception {
	public static final String invalidFormat = "invalidFormat";
	public static final String castleNotAllowed = "castleNotAllowed";
	public static final String pieceNotPresent = "pieceNotPresent";
	public static final String incorrectPiece = "incorrectPiece";
	public static final String impossibleMove = "impossibleMove";
	public static final String ambiguousMove = "ambiguousMove";
	public static final String promotionTo = "promotionTo";

	public String type;
	
	public InvalidMoveException(String message, String type) {
		super(message + " : " + type.toString());
		this.type = type;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
