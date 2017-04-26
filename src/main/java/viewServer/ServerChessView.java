package viewServer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.ValueEventListener;

import view.IChessViewer;
import view.IChessViewerControl;

public class ServerChessView implements IChessViewer {
	public boolean isTurn;
	public BoardData board;
	public ActionData action;
	public String status;
	public String roomID;
	public boolean whiteOrBlack;

	@Exclude
	private DatabaseReference ref;
	@Exclude
	private IChessViewerControl controller;

	public ServerChessView() {
	}

	public static ServerChessView newInstance(DatabaseReference firebaseReference, String roomID,
			boolean whiteOrBlack) {
		ServerChessView p = new ServerChessView();
		p.board = BoardData.newInstance();
		p.ref = firebaseReference;
		p.roomID = roomID;
		p.whiteOrBlack = whiteOrBlack;
		return p;
	}

	@Override
	public void printOut(String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printTemp(String temp) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanTemp() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setStatusLabelText(String str) {
		this.status = str;
	}

	@Override
	public void highLight(int file, int rank) {
		this.board.highLight(file, rank);
	}

	@Override
	public void deHighLightWholeBoard() {
		this.board.deHighLightWholeBoard();
	}

	@Override
	public String getResponse(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void repaint() {
		this.ref.setValue(this);
	}

	@Override
	public void upDatePiece(int file, int rank, char pieceType, boolean whiteOrBlack) {
		this.board.updatePiece(file, rank, PieceData.newInstance("" + pieceType, whiteOrBlack));
	}

	@Override
	public void clearLabel(int file, int rank) {
		this.board.updatePiece(file, rank, PieceData.newInstance(null, false));
	}

	@Override
	public void initializeViewController(IChessViewerControl controller) {
		this.controller = controller;
		this.ref.child("action").addValueEventListener(new ServerValueEventListener());
	}

	private class ServerValueEventListener implements ValueEventListener {

		@Override
		public void onCancelled(DatabaseError error) {
		}

		@Override
		public void onDataChange(DataSnapshot dataChange) {
			action = dataChange.getValue(ActionData.class);

			if (action.click != null) {
				controller.click((int) action.click.file, (int) action.click.rank, whiteOrBlack);
				action.click = null;
			}

			if (action.requestDraw == true) {
				controller.askForDraw(whiteOrBlack);
				action.requestDraw = false;
			}

			if (action.resign == true) {
				controller.resign(whiteOrBlack);
				action.resign = false;
			}
		}

	}
}
