package model;

/**
 * End game type Win
 * We can even add more complex draw type later, and change their outputs
 * 
 * @author FredZhang
 *
 */
public enum Win implements EndGame {
	WHITECHECKMATE(true, "White wins! -- CHECKMATE!!" , "WHITE Checkmates the BLACK\n WHITE wins!!" ),
	BLACKCHECKMATE(false, "Black wins! -- CHECKMATE!!" , "BLACK Checkmates the WHITE\n BLACK wins!!" ),
	WHITERESIGN(false, "White resigns! -- Black wins!", "White resigns\n Black wins."), 
	BLACKESIGN(true, "Black resigns! -- White wins!", "Black resigns\n White wins");
	
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
		switch(this){
		case WHITECHECKMATE: return "1-0";
		case BLACKCHECKMATE: return "0-1";
		case BLACKESIGN: return "1-0 (resign)";
		case WHITERESIGN: return "1-1 (resign)";
		default: throw new RuntimeException("What the hell!");
		}
	}
	
}
