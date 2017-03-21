package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
	public static final int SQUARE_WIDTH = 65;
	public static final Font FONT_PIECE = new Font("Serif", Font.PLAIN, 40);
	public static final Color HIGHLIGHT_COLOR = Color.yellow;

	private int x;
	private int y;
	private boolean isWhite;
	private Color originalColor;
	private boolean highLight;
	private BufferedImage image;
	private ChessSymbolProvider symbolProvider;

	public SquareLabel() {
		super("", JLabel.CENTER);
		setPreferredSize(new Dimension(SquareLabel.SQUARE_WIDTH, SquareLabel.SQUARE_WIDTH));
		setFont(FONT_PIECE);
	}

	/**
	 * 
	 * @param i
	 *            file of this spot
	 * @param j
	 *            rank of this square
	 * @param chess
	 * @param symbolProvider2
	 */
	public SquareLabel(int i, int j, ChessViewerControl chess, boolean whiteOrBlack,
			ChessSymbolProvider symbolProvider) {
		this();
		if (whiteOrBlack) {
			x = i;
			y = 8 - j;
		} else {
			x = 9 - i;
			y = 1 + j;
		}
		isWhite = whiteOrBlack;
		if ((i + j) % 2 != 0)
			originalColor = Color.white;
		else
			originalColor = Color.gray;
		this.symbolProvider = symbolProvider;

		setBackground(originalColor);
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		setOpaque(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chess.click(SquareLabel.this, isWhite);
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------------------
	// accessors

	public String toString() {
		return "(" + x + "," + y + ")";
	}

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
	protected void highLight() {
		highLight = true;
		setBackground(HIGHLIGHT_COLOR);
	}

	protected void deHighLight() {
		highLight = false;
		setBackground(originalColor);
	}

	/**
	 * upDate the color and text of this JLabel.
	 */
	public void upDatePiece(ChessPieceType chessPieceType, boolean wb) {
		image = symbolProvider.getSymbol(chessPieceType, wb);
	}

	public void clearLabel() {
		image = null;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, null, 0, 0);
		}
	}

}
