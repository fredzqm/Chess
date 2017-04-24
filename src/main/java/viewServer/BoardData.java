package viewServer;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class BoardData {
	public String message;
	public List<List<PieceData>> pieces;

	@Exclude
	private DatabaseReference ref;
	
	public BoardData() {
		this.pieces = new ArrayList<>();
		for (int i = 0 ; i < 8; i++) {
			ArrayList<PieceData> row = new ArrayList<>();
			this.pieces.add(row);
			for (int j = 0; j < 8; j++) {
				row.add(PieceData.newInstance(null, false));
			}
		}
	}

	public static BoardData newInstance(DatabaseReference databaseReference) {
		BoardData boardData = new BoardData();
		boardData.ref = databaseReference;
		boardData.pushToFirebase();
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
		pieces.get(file-1).get(rank-1).isHightLight = true;
	}

	public void updatePiece(int file, int rank, PieceData pieceData) {
		pieces.get(file-1).set(rank-1, pieceData);
	}
	
	public void pushToFirebase() {
		this.ref.setValue(this);
	}
}
