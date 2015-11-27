package view;

public class ChessGameWrongTypeException extends RuntimeException {
	
	public ChessGameWrongTypeException(char type){
		super(""+type);
	}
}
