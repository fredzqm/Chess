import java.util.EventListener;
import java.util.EventObject;

public interface ChessListener extends EventListener {

	void updateSquare(Square square);

	void highLight(EventObject eventObject);

	void dehighLight(EventObject eventObject);

	void printInLabel(String str);

}
