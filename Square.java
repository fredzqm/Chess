import java.awt.Color;
import javax.swing.JLabel;

/**
 * A class that represents the squares on the chess board.
 * 
 * It is also the Jlabel that actually appears in the Frame.
 * 
 * @author zhangq2
 *
 */
@SuppressWarnings("serial")
public class Square extends JLabel {
	private static final Color HIGHLIGHT_COLOR = Color.yellow;
	private static final Color TEXT_COLOR_BALCK = Color.black;
	private static final Color TEXT_COLOR_WHITE = Color.red;

	private int x;
	private int y;
	private String name;
	private Piece occupied;
	
	// TODO:
	private SquareLabel label;

	/**
	 * 
	 * @param i
	 *            file of this spot
	 * @param j
	 *            rank of this square
	 * @param chess
	 */
	public Square(int i, int j, Chess chess) {
		super("", JLabel.CENTER);
		x = i + 1;
		y = 8 - j;
		char col = (char) (97 + i);
		int row = 8 - j;
		name = "" + col + row;
		occupied = null;
		
		label = new SquareLabel( i, j, chess.outBox.source) ;
	}

	// ------------------------------------------------------------------------------------------------------------------
		// accessors
		
		public String toString() {
			return name;
		}

		public int X() {
			return x;
		}

		public int Y() {
			return y;
		}

		/**
		 * 
		 * @return the piece at that square
		 */
		public Piece getPiece() {
			return occupied;
		}
		/**
		 * 
		 * @return true if there is any piece occupy this squre
		 */
		public boolean occupied() {
			return occupied != null;
		}

		/**
		 * 
		 * @param whoseTurn
		 *            white or black
		 * @return whether this square is ocupied by piece of that color.
		 */
		public boolean occupiedBy(boolean whoseTurn) {
			if (occupied())
				return whoseTurn == occupied.getWb();
			else
				return false;
		}

		public boolean isHighLight() {
			return label.highLight;
		}

		// ------------------------------------------------------------------------------------------------------------------
		// modifier

		/**
		 * set the occupied piece.
		 * 
		 * @param piece
		 *            the piece
		 */
		public void setOccupied(Piece piece) {
			occupied = piece;
			upDatePiece();
		}

		/**
		 * hight light this spot and set the back ground color to highlight color.
		 */
		public void highLight() {
			label.highLight();
		}

		public void deHighLight() {
			label.deHighLight();
		}

		/**
		 * upDate the color and text of this JLabel.
		 */
		public void upDatePiece() {
			if (occupied != null) {
				setText("" + occupied.getType());
				if (occupied.getWb())
					setForeground(TEXT_COLOR_WHITE);
				else
					setForeground(TEXT_COLOR_BALCK);
			} else {
				setText("");
			}
		}
}