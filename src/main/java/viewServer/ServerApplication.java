package viewServer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions.Builder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {

  public static void main(String[] args) throws FileNotFoundException {
    try {
      InputStream serviceAccount =
          ServerApplication.class.getClassLoader()
              .getResourceAsStream("secret/chess-2c8e2-firebase-adminsdk-1iya2-555539dd58.json");

      FirebaseOptions options = new Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://chess-2c8e2.firebaseio.com")
          .build();

      FirebaseApp.initializeApp(options);
    } catch (java.io.IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Starting server");
    Server server = new Server(FirebaseDatabase.getInstance());
    try {
      ServerSocket serverSocket = new ServerSocket(8080);
      System.out.println("Listening for connection on port 8080 ....");
      while (true) {
        Socket clientSocket = null;
        clientSocket = serverSocket.accept();
        clientSocket.getOutputStream().write(server.getGames().toString().getBytes());
        clientSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
