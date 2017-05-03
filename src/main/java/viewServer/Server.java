package viewServer;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import controller.DualViewChessControl;
import controller.ViewController;
import view.ChessViewer;
import view.IChessViewer;

public class Server {

	private DatabaseReference ref;
	private RootListener rootListener;
	private Map<String, ViewController> games = new HashMap<>();
	
	public Server(FirebaseDatabase data) {
		this.ref = data.getReference();
		this.rootListener = new RootListener();
		this.ref.addChildEventListener(this.rootListener);
	}
	
	private class RootListener implements ChildEventListener {

		@Override
		public void onCancelled(DatabaseError arg0) {
			throw new RuntimeException();
		}

		@Override
		public void onChildAdded(DataSnapshot data, String arg1) {
			String roomLoc = data.getKey();
			
			ServerChessView whiteview = ServerChessView
					.newInstance(FirebaseDatabase.getInstance().getReference(roomLoc + "/white"), true);
//			ServerChessView blackview = ServerChessView
//					.newInstance(FirebaseDatabase.getInstance().getReference(roomLoc + "/black"), false);
			IChessViewer blackview = new ChessViewer("hello", false);
			games.put(roomLoc, new DualViewChessControl(whiteview, blackview));
		}

		@Override
		public void onChildChanged(DataSnapshot arg0, String arg1) { }

		@Override
		public void onChildMoved(DataSnapshot arg0, String arg1) { }

		@Override
		public void onChildRemoved(DataSnapshot data) {
			String roomLoc = data.getKey();
			
			ViewController viewControl = games.remove(roomLoc);
			viewControl.close();
		}
		
	}
}
