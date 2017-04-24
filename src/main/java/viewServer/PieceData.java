package viewServer;

public class PieceData {
	public String type;
	public boolean isWhite;
	public boolean isHightLight;

	public PieceData() {
	}

	public static PieceData newInstance(String type, boolean whiteOrBlack) {
		PieceData pieceData = new PieceData();
		pieceData.type = type;
		pieceData.isWhite = whiteOrBlack;
		return pieceData;
	}
}
