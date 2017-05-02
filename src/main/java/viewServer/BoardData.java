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

	public void highLight(int file, int rank) {
		pieces.get(rank - 1).get(file - 1).isHightLight = true;
	}

	public void updatePiece(int file, int rank, PieceData pieceData) {
		pieces.get(rank - 1).set(file - 1, pieceData);
	}
}
