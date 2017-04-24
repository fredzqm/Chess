package viewServer;

public class BoardData {
	public String message;
	public PieceData[][] pieces;
	
	public BoardData() {
		this.pieces = new PieceData[8][8];
	}
}
