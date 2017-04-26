package viewServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import controller.DualViewChessControl;
import view.IChessViewer;
import view.IChessViewerControl;

public class ServerChessView implements IChessViewer {
	public BoardData board;
	public ActionData action;
	public String status;
	public String roomID;
	public boolean isTurn;

	@Exclude
	private DatabaseReference ref;
	@Exclude
	private IChessViewerControl controller;

	public ServerChessView() {
	}

	public static ServerChessView newInstance(DatabaseReference firebaseReference, String roomID) {
		ServerChessView p = new ServerChessView();
		p.board = BoardData.newInstance();
		p.ref = firebaseReference;
		p.roomID = roomID;
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
	}
}
