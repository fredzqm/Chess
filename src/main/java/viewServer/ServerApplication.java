package viewServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.FirebaseDatabase;

public class ServerApplication {
	public static void main(String[] args) throws FileNotFoundException {
		new Server(FirebaseDatabase.getInstance());
		while (true)
			;
	}

	static {
		try {
			FileInputStream serviceAccount = new FileInputStream("ServiceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
					.setDatabaseUrl("https://chess-49b54.firebaseio.com/").build();

			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
