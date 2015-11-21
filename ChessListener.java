import java.util.EventListener;
import java.util.EventObject;

public interface ChessListener extends EventListener {

	void updateSquare(EventObject eventObject);

}
