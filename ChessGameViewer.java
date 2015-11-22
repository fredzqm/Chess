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
public class ChessGameViewer extends JFrame {

	private static final int WIDTH = 50;
	private static final Font FONT_PIECE = new Font("Serif", Font.PLAIN, 40);
	private static final Font FONT_STATUS = new Font("Serif", Font.PLAIN, 40);
	private static final int CONSOLE_FONT_SIZE = 20;
	private static final Font FONT_CONSOLE = new Font("Serif", Font.PLAIN, CONSOLE_FONT_SIZE);

	ChessViewerController viewControl;
	private JLabel statusLabel;
	private SquareLabel[][] labels;
	
	private JTextArea myConsole;
	private String existence;
	private String temp;
	public ChessGameViewer(ChessViewerController controller) {
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
				} else if (i == 0){
					labels[i][j] = new SquareLabel();
					String s = "";
					s += (8 - j);
					labels[i][j].setText(s);
					labels[i][j].setOpaque(false);
				} else {
					labels[i][j] = new SquareLabel(i , j, controller);
//					labels[i][j] = myChess.spotAt(i, 8 - j);
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
		myConsole = new JTextArea("Welcome to little Chess Game. Enter \"help\" for instructions.\n", 130 / CONSOLE_FONT_SIZE,
				1000 / CONSOLE_FONT_SIZE);
		myConsole.setFont(FONT_CONSOLE);
		myConsole.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String input = myConsole.getText().substring(existence.length());
					if (input.length() > 0) {
						input = input.substring(0, input.length() - 1);
						String feedback = viewControl.handleCommand(input);
						existence = myConsole.getText();
						printOut(feedback);
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		existence = myConsole.getText();
		temp = "";
		consolePanel.add(new JScrollPane(myConsole));
		
		add(statusLabel, BorderLayout.NORTH);
		add(chessBoardSpace, BorderLayout.CENTER);
		add(consolePanel, BorderLayout.SOUTH);
	}

//	/**
//	 * the console to make output
//	 * 
//	 * @author zhangq2
//	 *
//	 */
//	@SuppressWarnings("serial")
//	class Console extends JTextArea implements KeyListener {
//
//		/**
//		 * creates a new console for this game
//		 */
//		public Console() {
//			super("Welcome to little Chess Game. Enter \"help\" for instructions.\n", 130 / CONSOLE_FONT_SIZE,
//					1000 / CONSOLE_FONT_SIZE);
//			addKeyListener(this);
//			existence = getText();
//			temp = "";
//		}
//
//		/**
//		 * print out the outPut in the console.
//		 * 
//		 * @param outPut
//		 */
		public void printOut(String outPut) {
			existence = existence + temp;
			temp = "";
			myConsole.setText(existence + outPut + "\n");
			existence = myConsole.getText();
		}

		/**
		 * print out the temporal string in the console. This string can be
		 * erase later.
		 * 
		 * @param s
		 */
		public void printTemp(String s) {
			myConsole.setText(existence + s + "\n");
			temp = s;
		}

		/**
		 * earse the temporal string in the console
		 */
		public void cleanTemp() {
			temp = "";
		}

		public void setText(String s) {
			statusLabel.setText(s);
		}

		public void updateSquare(Square sq) {
			
			labels[sq.X()][8 - sq.Y()].upDatePiece(sq);
		}

//		@Override
//		public void keyPressed(KeyEvent arg0) {
//			// TODO Auto-generated method stub
//		}
//
//		@Override
//		
//
//		@Override
//		public void keyTyped(KeyEvent arg0) {
//		}
//	}


}
