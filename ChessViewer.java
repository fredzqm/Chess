import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ChessViewer extends JFrame {

	private static final int WIDTH = 50;
	private static final Font FONT_PIECE = new Font("Serif", Font.PLAIN, 40);
	private static final Font FONT_STATUS = new Font("Serif", Font.PLAIN, 40);
	private static final int CONSOLE_FONT_SIZE = 20;
	private static final Font FONT_CONSOLE = new Font("Serif", Font.PLAIN, CONSOLE_FONT_SIZE);

	ChessViewerControl viewControl;
	private JLabel statusLabel;
	private SquareLabel[][] labels;

	private JTextArea myConsole;
	private String existence;

	public ChessViewer(ChessViewerControl controller) {
		super("The Great Chess Game");

		this.viewControl = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel chessBoardSpace = new JPanel();
		chessBoardSpace.setLayout(new FlowLayout());
		chessBoardSpace.setVisible(true);
		JPanel chessBoard = new JPanel();
		chessBoard.setSize(WIDTH * 9, WIDTH * 9);
		chessBoard.setLayout(new GridLayout(9, 9));
		chessBoard.setVisible(true);

		labels = new SquareLabel[9][9];
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (j == 8) {
					labels[i][j] = new SquareLabel();
					String s = "";
					if (i > 0)
						s += (char) (i + 96);
					labels[i][j].setText(s);
					labels[i][j].setOpaque(false);
				} else if (i == 0) {
					labels[i][j] = new SquareLabel();
					String s = "";
					s += (8 - j);
					labels[i][j].setText(s);
					labels[i][j].setOpaque(false);
				} else {
					labels[i][j] = new SquareLabel(i, j, controller);
				}
				labels[i][j].setPreferredSize(new Dimension(WIDTH, WIDTH));
				labels[i][j].setFont(FONT_PIECE);
				chessBoard.add(labels[i][j]);
			}
		}
		chessBoardSpace.add(chessBoard);

		statusLabel = new JLabel("            Welcome to Wonderful Chess Game             ", JLabel.CENTER);
		statusLabel.setFont(FONT_STATUS);
		statusLabel.setVisible(true);

		JPanel consolePanel = new JPanel();
		consolePanel.setLayout(new FlowLayout());
		consolePanel.setVisible(true);
		myConsole = new JTextArea("Welcome to little Chess Game. Enter \"help\" for instructions.\n",
				130 / CONSOLE_FONT_SIZE, 1000 / CONSOLE_FONT_SIZE);
		myConsole.setFont(FONT_CONSOLE);
		myConsole.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String text = myConsole.getText();
					if (existence.length() < text.length()) {
						String input = text.substring(existence.length(), text.length() - 1);
						if (input.length() > 0) {
							viewControl.handleCommand(input);
						}
					}
				}
			}
		});
		existence = myConsole.getText();
		consolePanel.add(new JScrollPane(myConsole));

		add(statusLabel, BorderLayout.NORTH);
		add(chessBoardSpace, BorderLayout.CENTER);
		add(consolePanel, BorderLayout.SOUTH);
	}

	public SquareLabel labelAt(int i, int j) {
		return labels[i][8 - j];
	}

	/**
	 * print out the outPut in the console.
	 * 
	 * @param outPut
	 */
	public void printOut( String outPut) {
			existence = myConsole.getText();
		if (outPut != null) {
			existence = existence + outPut + "\n";
			myConsole.setText(existence);
		}
	}

	/**
	 * print out the temporal string in the console. This string can be erase
	 * later.
	 * 
	 * @param s
	 */
	public void printTemp(String s) {
		myConsole.setText(existence + s);
	}

	/**
	 * erase the temporal string in the console
	 */
	public void cleanTemp() {
		myConsole.setText(existence);
	}

	public void setStatusLabelText(String s) {
		statusLabel.setText(s);
	}

	/**
	 * show the label at (x, y) in the chess board view
	 * 
	 * @param x
	 * @param y
	 * @param type
	 *            type of piece to display
	 * @param wb
	 *            white or black
	 */
	public void showLabel(int x, int y, char type, boolean wb) {
		labelAt(x, y).upDatePiece(type, wb);
	}

	/**
	 * clean the label at (x, y) in the chess board view
	 * 
	 * @param x
	 * @param y
	 */
	public void cleanLabel(int x, int y) {
		labelAt(x, y).setText("");
	}

	/**
	 * dehighlight the whole board
	 */
	public void deHighLightWholeBoard() {
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				SquareLabel l = labelAt(i, j);
				if (l.isHighLight())
					l.deHighLight();
			}
		}
	}

}
