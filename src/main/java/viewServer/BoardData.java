package viewServer;

import java.util.ArrayList;
import java.util.List;

public class BoardData {
	public List<PieceData> pieces;
	public boolean whiteOrBlack;

	public List<PieceData> getPieces() {
		return pieces;
	}

	public void setPieces(List<PieceData> pieces) {
		this.pieces = pieces;
	}

	public boolean isWhiteOrBlack() {
		return whiteOrBlack;
	}

	public void setWhiteOrBlack(boolean whiteOrBlack) {
		this.whiteOrBlack = whiteOrBlack;
	}

	public BoardData() {
		this.pieces = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieces.add(new PieceData(null, false, false));
			}
		}
	}

	public BoardData(List<PieceData> pieces) {
	  this.pieces=pieces;
  }

  public static BoardData newInstance() {
    BoardData boardData = new BoardData();
    return boardData;
  }

  public void deHighLightWholeBoard() {
    for (PieceData d : this.pieces) {
			if (d != null) {
				d.setHightLight(false);
			}
    }
  }

	public void highLight(int i, int j) {
		getPieceData(i, j).setHightLight(true);
	}

	public void updatePiece(int i, int j, char pieceType, boolean whiteOrBlack) {
		getPieceData(i, j).setType(""+pieceType);
		getPieceData(i, j).setWhite(whiteOrBlack);
	}

	public void clearPiece(int i, int j) {
		getPieceData(i, j).setType(null);
	}

	private PieceData getPieceData(int i, int j) {
		return pieces.get(i*8+j);
	}
}
