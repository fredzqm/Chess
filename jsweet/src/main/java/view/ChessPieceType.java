package view;

public class ChessPieceType {
	public final static ChessPieceType Pawn = new ChessPieceType();
	public final static ChessPieceType Rook = new ChessPieceType();
	public final static ChessPieceType Bishop = new ChessPieceType();
	public final static ChessPieceType Knight = new ChessPieceType();
	public final static ChessPieceType Queen = new ChessPieceType();
	public final static ChessPieceType King = new ChessPieceType();

	public static ChessPieceType from(char type) {
		switch (Character.toUpperCase(type)) {
		case 'P':
			return Pawn;
		case 'R':
			return Rook;
		case 'B':
			return Bishop;
		case 'N':
			return Knight;
		case 'Q':
			return Queen;
		case 'K':
			return King;
		default:
			throw new RuntimeException("Invalid chessPieceType: " + type);
		}
	}
}
