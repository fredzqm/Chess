package viewServer;

import model.Piece;

public class PieceData {
	private String type;
	private boolean isWhite;
	private boolean isHightLight;

	public PieceData() {
	}

	public PieceData(String type, boolean isWhite, boolean isHightLight) {
		this.type = type;
		this.isWhite = isWhite;
		this.isHightLight = isHightLight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean white) {
		isWhite = white;
	}

	public boolean isHightLight() {
		return isHightLight;
	}

	public void setHightLight(boolean hightLight) {
		isHightLight = hightLight;
	}
}
