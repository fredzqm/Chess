import java.util.EventListener;

public interface ChessListener extends EventListener {

	void updateSquare(Square square);

	void win(boolean whiteOrBlack , String outprint , String descript);
	
	void draw(String outprint , String descript);

	void moveFeedback(Move m);

	void nextMove(boolean whoseTurn);
	
}
