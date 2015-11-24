
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
	public int result() {
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


	
}
