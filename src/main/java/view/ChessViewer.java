package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ChessViewer extends JFrame implements IChessViewer {
	private static final Font FONT_STATUS_LABEL = new Font("Serif", Font.PLAIN, 40);
	private static final ISpriteProvider DEFAULT_SYMBOL_PROVIDER = new SpriteProvider("Chess_symbols.png");

	private boolean isWhiteView;

	private JLabel statusLabel;
	private SquareLabel[][] labels;
	private MyConsole myConsole;

	/**
	 * construct a chess view given a controller
	 * 
	 * @param controller
	 * @param b
	 */
	public ChessViewer(String title, boolean whiteOrBlack) {
		this.isWhiteView = whiteOrBlack;
		setTitle(title);
	}

	@Override
	public void initializeViewController(IChessViewerControl controller) {
		this.labels = setupChessBoard(controller, DEFAULT_SYMBOL_PROVIDER);
		this.statusLabel = setupStatusLabel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel consolePanel = setupConsole(controller);

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

	private JPanel setupConsole(IChessViewerControl controller) {
		JPanel consolePanel = new JPanel();
		consolePanel.setLayout(new FlowLayout());
		consolePanel.setVisible(true);
		myConsole = new MyConsole(controller, isWhiteView, "Welcome to little Chess Game. Enter \"help\" for instructions.\n", 130, 1000);
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

	@Override
	public void printOut(String message) {
		this.myConsole.printOut(message);
	}

	@Override
	public void printTemp(String temp) {
		myConsole.printTemp(temp);
	}

	@Override
	public void cleanTemp() {
		myConsole.cleanTemp();
	}

	@Override
	public void setStatusLabelText(String str) {
		statusLabel.setText(str);
	}

	@Override
	public void highLight(int file, int rank) {
		labelAt(file, rank).highLight();
	}

	@Override
	public void deHighLightWholeBoard() {
		for (SquareLabel[] row : this.labels) {
			for (SquareLabel label : row) {
				label.deHighLight();
			}
		}
	}

	@Override
	public String getResponse(String message) {
		return JOptionPane.showInputDialog(message);
	}

	@Override
	public void upDatePiece(int file, int rank, char pieceType, boolean whiteOrBlack) {
		labelAt(file, rank).upDatePiece(pieceType, whiteOrBlack);
	}

	@Override
	public void clearLabel(int file, int rank) {
		labelAt(file, rank).clearLabel();
	}

}
