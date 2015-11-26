package model;

/**
 * End game type Draw
 * We can even add more complex draw type later, and change their outputs
 * 
 * @author FredZhang
 *
 */
public enum Draw implements EndGame{
	STALEMENT("Stalement", "Draw due to Stalement."), 
	FIFTY_MOVE("Quite" , "Fifty-move rule."), 
	REPETITION("Repetition", "threefold repetition."),
	AGREEMENT("Agreement to draw", "Draw by Agreement.");
	;

	private final String descript;
	private final String printOut;
	
	private Draw(String descript,  String printOut) {
		this.descript = descript;
		this.printOut = printOut;
	}
	
	@Override
	public int getResult() {
		return 0;
	}
	
	@Override
	public String getDescript() {
		return descript;
	}

	@Override
	public String getPrintOut() {
		return printOut;
	}

	@Override
	public String getDoc() {
		return "1/2-1/2";
	}

	
}
