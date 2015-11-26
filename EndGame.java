/**
 * 
 * @author FredZhang
 */
public interface EndGame {
	/**
	 * 
	 * @return the result of the game, 1 if the white wins, -1 if the black wins, 0 if it is a draw
	 */
	public int getResult();
	
	/**
	 * 
	 * @return the description of this end game
	 */
	public String getDescript();
	
	/**
	 * 
	 * @return the necessary messages to be printed out
	 */
	public String getPrintOut();

	/**
	 * 
	 * @return documentation in standard chess recording convention
	 */
	public String getDoc();
}
