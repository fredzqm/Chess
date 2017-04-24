package viewServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.FirebaseDatabase;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException {
		FileInputStream serviceAccount = new FileInputStream("ServiceAccount.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
				.setDatabaseUrl("https://chess-49b54.firebaseio.com/").build();

		FirebaseApp.initializeApp(options);

		// As an admin, the app has access to read and write all data,
		// regardless of Security Rules
		// DatabaseReference ref =
		// FirebaseDatabase.getInstance().getReference("/");
		// ref.addValueEventListener(new ValueEventListener() {
		// @Override
		// public void onDataChange(DataSnapshot dataSnapshot) {
		// Object document = dataSnapshot.getValue();
		// System.out.println(document);
		// }
		//
		// @Override
		// public void onCancelled(DatabaseError arg0) {
		// System.out.println("onCancelled " + arg0);
		// }
		// });
		
		ServerChessView.newInstance(FirebaseDatabase.getInstance().getReference("MyRoomID"), "MyRoomID");
		
		while (true)
			;
	}

}
