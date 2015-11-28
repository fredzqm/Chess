package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	
	
	private static final Font FONT_PIECE = new Font("Serif", Font.PLAIN, 40);
	private static final Color HIGHLIGHT_COLOR = Color.yellow;
	private ChessSymbolProvider DEFAULT_SYMBOL_PROVIDER = new ImageProvider("icons/Chess_symbols.png");
	
//	private static final Color TEXT_COLOR_BALCK = Color.black;
//	private static final Color TEXT_COLOR_WHITE = Color.red;

	private int x;
	private int y;
	private boolean wb;
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
	 */
	public SquareLabel(int i, int j, ChessViewerControl chess, boolean whiteOrBlack) {
		this();
		if (whiteOrBlack){
			x = i;
			y = 8 - j;
		}else{
			x = 9 - i;
			y = 1 + j;
		}
		wb = whiteOrBlack;
		if ((i + j) % 2 != 0)
			originalColor = Color.white;
		else
			originalColor = Color.gray;
		setBackground(originalColor);
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		setOpaque(true);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chess.click(SquareLabel.this, wb);
			}
		});
		symbolProvider = DEFAULT_SYMBOL_PROVIDER;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// accessors
	
	public String toString() {
		return "("+x+","+y+")";
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

	protected void setSymbolProvider(ChessSymbolProvider symProvider){
		this.symbolProvider = symProvider;
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

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (image != null ){
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, null, 0, 0);
		}
	}
	
}
