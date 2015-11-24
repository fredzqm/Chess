import java.util.EventListener;

public interface ChessListener extends EventListener {

	void updateSquare(Square square);

	void endGame(EndGame end);
	
	void nextMove(Move previousMove);

	Piece promote(boolean wb , Square end);

}
