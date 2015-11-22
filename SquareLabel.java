import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class SquareLabel extends JLabel {

	static final int MWIDTH = 50;
	
	private static final Color HIGHLIGHT_COLOR = Color.yellow;
	private static final Color TEXT_COLOR_BALCK = Color.black;
	private static final Color TEXT_COLOR_WHITE = Color.red;

//	private String name;
	int x;
	int y;
	private Color originalColor;
	boolean highLight;

	public SquareLabel() {
		super("", JLabel.CENTER);
	}
	
	/**
	 * 
	 * @param i
	 *            file of this spot
	 * @param j
	 *            rank of this square
	 * @param chess
	 */
	public SquareLabel(int i, int j, ChessViewerControl chess) {
		super("", JLabel.CENTER);
		x = i;
		y = 8 - j;
		setPreferredSize(new Dimension(MWIDTH, MWIDTH));
		if ((i + j) % 2 != 0)
			originalColor = Color.gray;
		else
			originalColor = Color.white;
		setBackground(originalColor);
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		setOpaque(true);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chess.click(SquareLabel.this);
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------------------
	// accessors
	
//	public String toString() {
//		return name;
//	}
	public int X() {
		return x;
	}

	public int Y() {
		return y;
	}

	public boolean isHighLight() {
		return highLight;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// modifier

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
	public void upDatePiece(char type, boolean wb) {
			setText("" + type);
			if (wb)
				setForeground(TEXT_COLOR_WHITE);
			else
				setForeground(TEXT_COLOR_BALCK);
		
	}
	
}
