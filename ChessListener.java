import java.util.EventListener;
import java.util.EventObject;

public interface ChessListener extends EventListener {

	void updateSquare(Square square);

	void printInLabel(String str);

	void printInBox(String str);

}
