package viewServer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import view.IChessViewer;
import view.IChessViewerControl;

public class ServerChessView implements IChessViewer {
	private boolean whiteOrBlack;
	private BoardData board;

	private DatabaseReference ref;
	private IChessViewerControl controller;
	private ActionEventListener actionListener;
	private ActionData action;

	public ServerChessView() {
	}

	public static ServerChessView newInstance(DatabaseReference firebaseReference, boolean whiteOrBlack) {
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
		// System.out.println("[printOut]: "+message);
	}

	@Override
	public void printTemp(String temp) {
		// System.out.println("[printTemp]: "+temp);
	}

	@Override
	public void cleanTemp() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setStatusLabelText(String status) {
		this.ref.child("status").setValue(status);
	}

	@Override
	public void highLight(int file, int rank) {
		this.board.highLight(getI(file, rank), getJ(file, rank));
	}

	@Override
	public void deHighLightWholeBoard() {
		this.board.deHighLightWholeBoard();
	}

	@Override
	public void repaint() {
		this.ref.child("board").setValue(this.board);
	}

	private DatabaseReference actionRef;

	@Override
	public void upDatePiece(int file, int rank, char pieceType, boolean whiteOrBlack) {
		this.board.updatePiece(getI(file, rank), getJ(file, rank), pieceType, whiteOrBlack);
	}

	private int getFile(int i, int j) {
		if (this.whiteOrBlack)
			return 1 + j;
		return 8 - j;
	}

	private int getJ(int file, int rank) {
		if (this.whiteOrBlack)
			return file - 1;
		return 8 - file;
	}

	private int getRank(int i, int j) {
		if (this.whiteOrBlack)
			return 8 - i;
		return 1 + i;
	}

	private int getI(int file, int rank) {
		if (this.whiteOrBlack)
			return 8 - rank;
		return rank - 1;
	}

	@Override
	public void clearLabel(int file, int rank) {
		this.board.clearPiece(getI(file, rank), getJ(file, rank));
	}

	@Override
	public void initializeViewController(IChessViewerControl controller) {
		this.controller = controller;
		this.actionListener = new ActionEventListener(ref.child("action"));
	}

	private class ActionEventListener implements ValueEventListener {
		public ActionEventListener(DatabaseReference actionRef) {
			ServerChessView.this.actionRef = actionRef;
			actionRef.addValueEventListener(this);
		}

		@Override
		public void onCancelled(DatabaseError error) {
			System.out.println("onCancelled: " + error);
		}

		@Override
		public void onDataChange(DataSnapshot dataChange) {
			(new Thread(() -> {
				action = dataChange.getValue(ActionData.class);
				if (action == null)
					return;
				if (action.click != null) {
					int i = (int) action.click.i;
					int j = (int) action.click.j;
					controller.click(getFile(i, j), getRank(i, j), whiteOrBlack);
					actionRef.child("click").removeValue();
				}
				if (action.requestDraw == true) {
					controller.askForDraw(whiteOrBlack);
					actionRef.child("requestDraw").removeValue();

				}
				if (action.resign == true) {
					controller.resign(whiteOrBlack);
					actionRef.child("resign").removeValue();
				}
				if (action.agreeDraw != null) {
					synchronized (ServerChessView.this) {
						ServerChessView.this.notifyAll();
					}
				}
				if (action.promotionTo != null) {
					synchronized (ServerChessView.this) {
						ServerChessView.this.notifyAll();
					}
				}
			})).start();
		}

	}

	@Override
	public synchronized boolean askForDraw() {
		this.ref.child("request").child("askForDraw").setValue(true);
		try {
			wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("after wait: " + action.agreeDraw);
		boolean yes = action.agreeDraw.equals("Y");
		this.actionRef.child("agreeDraw").removeValue();
		return yes;
	}

	@Override
	public synchronized String getPromoteTo() {
		this.ref.child("request").child("promotionTo").setValue(true);
		try {
			wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		String x = this.action.promotionTo;
		actionRef.child("promotionTo").removeValue();
		return x;
	}

	@Override
	public void close() {
		this.ref.removeEventListener(this.actionListener);
	}
}
