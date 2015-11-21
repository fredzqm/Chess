import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ChessGameViewer extends JFrame {

	private static final int WIDTH = 50;
	private static final Font FONT_PIECE = new Font("Serif", Font.PLAIN, 40);
	private static final Font FONT_STATUS = new Font("Serif", Font.PLAIN, 40);
	private static final int CONSOLE_FONT_SIZE = 20;
	private static final Font FONT_CONSOLE = new Font("Serif", Font.PLAIN, CONSOLE_FONT_SIZE);

	JLabel statusLabel;
	private JPanel chessBoardSpace;
	private JPanel consolePanel;
	Console myConsole;

	public ChessGameViewer() {
		super("The Great Chess Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		chessBoardSpace = new JPanel();
		chessBoardSpace.setLayout(new FlowLayout());
		chessBoardSpace.setVisible(true);

		statusLabel = new JLabel("            Welcome to Wonderful Chess Game             ", JLabel.CENTER);
		statusLabel.setFont(FONT_STATUS);
		statusLabel.setVisible(true);

		consolePanel = new JPanel();
		consolePanel.setLayout(new FlowLayout());
		consolePanel.setVisible(true);

		add(statusLabel, BorderLayout.NORTH);
		add(chessBoardSpace, BorderLayout.CENTER);
		add(consolePanel, BorderLayout.SOUTH);
	}

	/**
	 * creates the chessBorad Panel, and puts it into the Frame
	 * 
	 * @param myChess
	 */
	void createChessBoard(Chess myChess) {
		JPanel chessBoard = new JPanel();
		chessBoard.setSize(WIDTH * 9, WIDTH * 9);
		chessBoard.setLayout(new GridLayout(9, 9));
		chessBoard.setVisible(true);
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				JLabel labels;
				if (j == 8 || i == 0) {
					labels = new JLabel("", JLabel.CENTER);
					if (j == 8) {
						String s = "";
						if (i > 0)
							s += (char) (i + 96);
						labels.setText(s);
						;
						labels.setOpaque(false);
					} else {
						String s = "";
						s += (8 - j);
						labels.setText(s);
						;
						labels.setOpaque(false);
					}
				} else {
					labels = myChess.spotAt(i, 8 - j);
				}
				labels.setFont(FONT_PIECE);
				chessBoard.add(labels);
			}
		}
		chessBoardSpace.add(chessBoard);
	}

	/**
	 * create the Console and put it into the frame
	 * 
	 * @param myChess
	 */
	void createConsoleBox(Chess myChess) {
		myConsole = new Console(myChess);
		myConsole.setFont(FONT_CONSOLE);
		consolePanel.add(new JScrollPane(myConsole));
	}

}
