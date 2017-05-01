package viewServer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import view.IChessViewer;
import view.IChessViewerControl;

public class ServerChessView implements IChessViewer {
	public boolean isTurn;
	public boolean whiteOrBlack;
	public String status;
	public BoardData board;

	private DatabaseReference ref;
	private IChessViewerControl controller;
	private ActionEventListener actionListener;
	private ActionData action;

	public ServerChessView() {
	}

	public static ServerChessView newInstance(DatabaseReference firebaseReference, String roomID,
			boolean whiteOrBlack) {
		ServerChessView p = new ServerChessView();
		p.board = BoardData.newInstance();
		p.ref = firebaseReference;
		p.whiteOrBlack = whiteOrBlack;
		p.ref.child("board").setValue(p.board);
		p.ref.child("whiteOrBlack").setValue(p.whiteOrBlack);
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
	public void setStatusLabelText(String status) {
		this.status = status;
		this.ref.child("status").setValue(status);
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
	public void repaint() {
		this.ref.child("board").setValue(this.board);
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
		this.actionListener = new ActionEventListener(ref.child("action"));
	}

	private class ActionEventListener implements ValueEventListener {
		private DatabaseReference actionRef;

		public ActionEventListener(DatabaseReference actionRef) {
			this.actionRef = actionRef;
			this.actionRef.addValueEventListener(this);
		}

		@Override
		public void onCancelled(DatabaseError error) {
			System.out.println("onCancelled: " + error);
		}

		@Override
		public void onDataChange(DataSnapshot dataChange) {
			action = dataChange.getValue(ActionData.class);
			if (action.click != null) {
				controller.click((int) action.click.file, (int) action.click.rank, whiteOrBlack);
				this.actionRef.child("click").removeValue();
			}
			if (action.requestDraw == true) {
				controller.askForDraw(whiteOrBlack);
				this.actionRef.child("requestDraw").removeValue();
			}
			if (action.resign == true) {
				controller.resign(whiteOrBlack);
				this.actionRef.child("resign").removeValue();
			}
			if (action.agreeDraw != null) {
				this.notifyAll();
				this.actionRef.child("agreeDraw").removeValue();
			}
			if (action.promotionTo != null) {
				this.notifyAll();
				this.actionRef.child("promotionTo").removeValue();
			}
		}

	}

	@Override
	public boolean askForDraw() {
		this.ref.child("request").child("askForDraw").setValue(true);
		try {
			wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return action.agreeDraw.equals("Y");
	}

	@Override
	public String getPromoteTo() {
		this.ref.child("request").child("promotionTo").setValue(true);
		try {
			wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return this.action.promotionTo;
	}
}
