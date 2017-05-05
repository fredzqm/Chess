package model;

/**
 * End game type Draw
 * We can even add more complex draw type later, and change their outputs
 * 
 * @author FredZhang
 *
 */
public class Draw implements EndGame{
	public static Draw STALEMATE = new Draw("Stalemate", "Draw due to Stalemate."); 
	public static Draw FIFTY_MOVE = new Draw("Quite" , "Fifty-move rule.");
	public static Draw REPETITION = new Draw("Repetition", "threefold repetition.");
	public static Draw AGREEMENT = new Draw("Agreement to draw", "Draw by Agreement.");

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
		if(this == AGREEMENT) {
			return "1/2-1/2 (agreement)";
		} else {
			return "1/2-1/2";
		}
	}

	
}
