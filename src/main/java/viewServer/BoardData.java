package viewServer;

import java.util.ArrayList;
import java.util.List;

public class BoardData {
	public List<List<PieceData>> pieces;

	public BoardData() {
		this.pieces = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			ArrayList<PieceData> col = new ArrayList<>();
			this.pieces.add(col);
			for (int j = 0; j < 8; j++) {
				col.add(PieceData.newInstance(null, false));
			}
		}
	}

	public static BoardData newInstance() {
		BoardData boardData = new BoardData();
		return boardData;
	}

	public void deHighLightWholeBoard() {
		for (List<PieceData> row : this.pieces) {
			for (PieceData d : row) {
				d.isHightLight = false;
			}
		}
	}

	public void highLight(int i, int j) {
		pieces.get(i).get(j).isHightLight = true;
	}

	public void updatePiece(int i, int j, PieceData pieceData) {
		pieces.get(i).set(j, pieceData);
	}
}
