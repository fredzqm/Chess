/**
 * End game type Win
 * We can even add more complex draw type later, and change their outputs
 * 
 * @author FredZhang
 *
 */
public enum Win implements EndGame {
	WHITECHECKMATE(true, "White wins! -- CHECKMATE!!" , "WHITE Checkmates the BLACK, WHITE wins!!" ),
	BLACKCHECKMATE(false, "Black wins! -- CHECKMATE!!" , "BLACK Checkmates the WHITE, BLACK wins!!" ),
	WHITERESIGN(false, null, "White resigns, Black wins."), 
	BLACKESIGN(true, null, "Black resigns, White wins");
	
	private final int winner;
	private final String descript;
	private final String printOut;
	
	private Win(boolean who, String descript,  String printOut) {
		if (who)
			winner = 1;
		else
			winner = -1;
		this.descript = descript;
		this.printOut = printOut;
	}
	
	@Override
	public int getResult() {
		return winner;
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
			if (winner > 0)
				return "1-0";
			else
				return "1-0";
	}
	
}
