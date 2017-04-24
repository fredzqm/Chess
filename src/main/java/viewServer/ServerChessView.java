package viewServer;

import com.google.firebase.database.DatabaseReference;

import view.IChessViewer;

public class ServerChessView implements IChessViewer {
	private DatabaseReference ref;
	private GameData gameData;

	public ServerChessView(DatabaseReference firebaseReference) {
		this.ref = firebaseReference;
		this.gameData = GameData.newInstance(this.ref.getPath().toString().substring(1));
	}

	@Override
	public void printOut(String message) {
		
	}

	@Override
	public void printTemp(String temp) {
		
	}

	@Override
	public void cleanTemp() {
		
	}

	@Override
	public void setStatusLabelText(String str) {
		
	}

	@Override
	public void highLight(int file, int rank) {
		
	}

	@Override
	public void deHighLightWholeBoard() {
		
	}

	@Override
	public String getResponse(String message) {
		return null;
	}

	@Override
	public void repaint() {
		// do nothing
	}

	@Override
	public void upDatePiece(int file, int rank, char pieceType, boolean whiteOrBlack) {
		
	}

	@Override
	public void clearLabel(int file, int rank) {
		
	}

}
