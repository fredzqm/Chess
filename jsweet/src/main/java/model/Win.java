package model;

/**
 * End game type Win We can even add more complex draw type later, and change
 * their outputs
 * 
 * @author FredZhang
 *
 */
public class Win implements EndGame {
	public static Win WHITECHECKMATE = new Win(true, "White wins! -- CHECKMATE!!",
			"WHITE Checkmates the BLACK\n WHITE wins!!");
	public static Win BLACKCHECKMATE = new Win(false, "Black wins! -- CHECKMATE!!",
			"BLACK Checkmates the WHITE\n BLACK wins!!");
	public static Win WHITERESIGN = new Win(false, "White resigns! -- Black wins!", "White resigns\n Black wins.");
	public static Win BLACKESIGN = new Win(true, "Black resigns! -- White wins!", "Black resigns\n White wins");

	private final int winner;
	private final String descript;
	private final String printOut;

	private Win(boolean who, String descript, String printOut) {
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
		if (this == WHITECHECKMATE)
			return "1-0";
		if (this == BLACKCHECKMATE)
			return "0-1";
		if (this == BLACKESIGN)
			return "1-0 (resign)";
		if (this == WHITERESIGN)
			return "1-1 (resign)";
		throw new RuntimeException("What the hell!");
	}

}
