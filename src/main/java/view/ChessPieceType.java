package view;


public enum ChessPieceType {
	Pawn, Rook, Bishop, Knight, Queen, King;

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
