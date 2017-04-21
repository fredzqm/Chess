package controller;

public class DrawManager {
	private boolean whiteCanDraw;
	private boolean blackCanDraw;

	public DrawManager() {
		this.whiteCanDraw = true;
		this.blackCanDraw = true;
	}
	
	public boolean canAskFordraw(boolean whoseTurn) {
		if (whoseTurn)
			return whiteCanDraw;
		else
			return blackCanDraw;
	}

	public void setRightToRequestDraw(boolean whoseTurn) {
		if (whoseTurn) {
			whiteCanDraw = false;
			blackCanDraw = true;
		} else {
			whiteCanDraw = true;
			blackCanDraw = false;
		}
	}
	
}
