package viewServer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions.Builder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ServerApplication {
	public static void main(String[] args) throws FileNotFoundException {
		new Server(FirebaseDatabase.getInstance());
		while (true)
			;
	}

	static {
		try {
			FileInputStream serviceAccount = new FileInputStream("secret/chess-2c8e2-firebase-adminsdk-1iya2-555539dd58.json");

				FirebaseOptions options = new Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://chess-2c8e2.firebaseio.com")
				.build();

			FirebaseApp.initializeApp(options);
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}
}
