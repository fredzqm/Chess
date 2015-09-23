import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
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
	private Color originalColor;
	boolean highLight;

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
		setPreferredSize(new Dimension(Main.WIDTH, Main.WIDTH));
		if ((i + j) % 2 != 0)
			originalColor = Color.gray;
		else
			originalColor = Color.white;
		setBackground(originalColor);
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		setOpaque(true);
		addMouseListener(new Clicked(chess));
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
		return highLight;
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
		highLight = true;
		setBackground(HIGHLIGHT_COLOR);
	}

	public void deHighLight() {
		highLight = false;
		setBackground(originalColor);
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
	
	/**
	 * MouseListener to calls method in Chess to operate the input.
	 * @author zhangq2
	 *
	 */
	private class Clicked implements MouseListener {
		Chess chess;

		public Clicked(Chess chess) {
			this.chess = chess;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			chess.click(Square.this);
		}
	}

}
