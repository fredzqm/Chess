package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ChessViewer extends JFrame {
	
	private static final Font FONT_STATUS_LABEL = new Font("Serif", Font.PLAIN, 40);
	private static final int CONSOLE_FONT_SIZE = 20;
	private static final Font FONT_CONSOLE = new Font("Serif", Font.PLAIN, CONSOLE_FONT_SIZE);
	private static final ISpriteProvider DEFAULT_SYMBOL_PROVIDER = new SpriteProvider("Chess_symbols.png");

	private boolean isWhiteView;
	private IChessViewerControl viewControl;

	private JLabel statusLabel;
	private SquareLabel[][] labels;
	
	private JTextArea myConsole;
	private String existence;

	/**
	 * construct a chess view given a controller
	 * 
	 * @param controller
	 * @param b
	 */
	public ChessViewer(IChessViewerControl controller, String title, boolean whiteOrBlack) {
		this.viewControl = controller;
		this.isWhiteView = whiteOrBlack;
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.labels = setupChessBoard(controller, DEFAULT_SYMBOL_PROVIDER);
		this.statusLabel = setupStatusLabel();
		JPanel consolePanel = setupConsole();

		add(consolePanel, BorderLayout.SOUTH);
		setVisible(true);
		pack();
	}

	private SquareLabel[][] setupChessBoard(IChessViewerControl controller, ISpriteProvider symbolProvider) {
		JPanel chessBoardSpace = new JPanel();
		chessBoardSpace.setLayout(new FlowLayout());
		chessBoardSpace.setVisible(true);
		JPanel chessBoard = new JPanel();
		chessBoard.setSize(SquareLabel.SQUARE_WIDTH * 9, SquareLabel.SQUARE_WIDTH * 9);
		chessBoard.setLayout(new GridLayout(9, 9));
		chessBoard.setVisible(true);
		SquareLabel[][] labels = new SquareLabel[9][9];

		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (j == 8) {
					labels[i][j] = new SquareLabel();
					String s = "";
					if (i > 0)
						s += (char) (isWhiteView ? (i + 96) : (105 - i));
					labels[i][j].setText(s);
					labels[i][j].setOpaque(false);
				} else if (i == 0) {
					labels[i][j] = new SquareLabel();
					String s = "";
					s += isWhiteView ? (8 - j) : j + 1;
					labels[i][j].setText(s);
					labels[i][j].setOpaque(false);
				} else {
					labels[i][j] = new SquareLabel(i, j, controller, isWhiteView, symbolProvider);
				}
				chessBoard.add(labels[i][j]);
			}
		}
		chessBoardSpace.add(chessBoard);
		add(chessBoardSpace, BorderLayout.CENTER);
		return labels;
	}

	private JLabel setupStatusLabel() {
		JLabel label = new JLabel("            Welcome to Wonderful Chess Game             ", JLabel.CENTER);
		label.setFont(FONT_STATUS_LABEL);
		label.setVisible(true);
		add(label, BorderLayout.NORTH);
		return label;
	}

	private JPanel setupConsole() {
		JPanel consolePanel = new JPanel();
		consolePanel.setLayout(new FlowLayout());
		consolePanel.setVisible(true);
		myConsole = new JTextArea("Welcome to little Chess Game. Enter \"help\" for instructions.\n",
				130 / CONSOLE_FONT_SIZE, 1000 / CONSOLE_FONT_SIZE);
		myConsole.setFont(FONT_CONSOLE);
		myConsole.addKeyListener(new ConsoleListener());
		existence = myConsole.getText();
		consolePanel.add(new JScrollPane(myConsole));
		return consolePanel;
	}

	/**
	 * 
	 * @param file
	 * @param rank
	 * @return {link {@link SquareLabel} at (x , y) coordinate
	 */
	public SquareLabel labelAt(int file, int rank) {
		return isWhiteView ? labels[file][8 - rank] : labels[9 - file][rank - 1];
	}

	/**
	 * print the message in the console.
	 * 
	 * @param message
	 */
	public void printOut(String message) {
		existence = myConsole.getText();
		if (message != null) {
			existence = existence + message + "\n";
			myConsole.setText(existence);
		}
	}

	/**
	 * print out the temporal string in the console, without updating the
	 * record. The temp string printed can be clear with
	 * {@link ChessViewer#cleanTemp()}
	 * 
	 * @param temp
	 */
	public void printTemp(String temp) {
		myConsole.setText(existence + temp);
	}

	/**
	 * erase the temporal string in the console printed by
	 * {@link ChessViewer#printTemp(String)}
	 */
	public void cleanTemp() {
		myConsole.setText(existence);
	}

	/**
	 * 
	 * @param str
	 */
	public void setStatusLabelText(String str) {
		statusLabel.setText(str);
	}

	public void highLight(int file, int rank) {
		labelAt(file, rank).highLight();
	}

	/**
	 * dehighlight the whole board
	 */
	public void deHighLightWholeBoard() {
		for (SquareLabel[] row : this.labels) {
			for (SquareLabel label : row) {
				label.deHighLight();
			}
		}
	}

	public String getResponse(String message) {
		return JOptionPane.showInputDialog(message);
	}

	class ConsoleListener extends KeyAdapter {
		String input;

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				String text = myConsole.getText();
				if (existence.length() < text.length()) {
					input = text.substring(existence.length(), text.length() - 1);
					if (input.length() > 0) {
						viewControl.handleCommand(input, isWhiteView);
					}
				}
			}
		}

	}

}
