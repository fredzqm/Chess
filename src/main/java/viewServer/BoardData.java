package viewServer;

public class BoardData {
	public String message;
	public PieceData[][] pieces;

	public BoardData() {
	}

	public static BoardData newInstance() {
		BoardData boardData = new BoardData();
		boardData.pieces = new PieceData[8][8];
		return boardData;
	}
}
