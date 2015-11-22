import java.util.EventListener;

public interface ChessListener extends EventListener {

	void updateSquare(Square square);

	void printInLabel(String str);

	void printInBox(String str);

	void nextMove();

}
