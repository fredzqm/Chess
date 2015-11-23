import java.util.EventListener;

public interface ChessListener extends EventListener {

	void updateSquare(Square square);

	void win(boolean whiteOrBlack , String outprint , String descript);
	
	void draw(String outprint , String descript);

	void endOfGame(EndGame end);
	
	void nextMove(boolean whoseTurn);
	
}
