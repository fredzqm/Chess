package view;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

public class MyConsole extends JTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int CONSOLE_FONT_SIZE = 20;
	private static final Font FONT_CONSOLE = new Font("Serif", Font.PLAIN, CONSOLE_FONT_SIZE);

	public static final String ERROR_MESSAGE = "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\n"
			+ "you can omit the \"P\" at the begining for a pawn." + "for casting, enter \"O-O\" or \"O-O-O\"\n"
			+ "for examples, \"e2-e4\", \"Nb2-c3\" \n" + "If you need further help, type \"help\"";

	public static final String HELP_MESSAGE = "Enter commands:\n" + "enter 'undo' to undo the previous round;\n"
			+ "enter 'restart' to start a new game over;\n'" + "enter 'print' to print all the records;\n"
			+ "enter 'resign' to give up;\n" + "enter 'draw' to request for draw;\n"
			+ "enter complete or abbreviated algebraic chess notation to make a move;\n"
			+ "enter 'rules for ....' to get help about the rules of chess.\n"
			+ "    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";

	public static final Map<String, String> rules = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("castling",
					"Only under those circumstances, you can castling\n"
							+ "1.Your king and the corresponding rook has never been moved.\n"
							+ "2.There is no chessman between your king and rook.\n"
							+ "3.The squres that your king goes over should not under attack by any pieces of the opponent.\n"
							+ "4.Your king cannot be in check either before and after the castling.");
			put("pawn",
					"The pawn may move forward to the unoccupied square immediately in front of it on the same file without capturing, "
							+ "or advance two squares along the same file without capturing on its first move;"
							+ "or capture an opponent's piece on a square diagonally in front of it on an adjacent file.\n"
							+ "En Passant and promotion are also special rules for pawn.");
			put("king", "The king moves one square in any direction. You always want to protect your king.\n"
					+ "Castling is also a special rule for king.");
			put("queen",
					"The queen combines the power of the rook and bishop and can move any number of squares along rank, file, or diagonal, without going over any pieces");
			put("rook", "The rook can move any number of squares along any rank or file without going over any pieces");
			put("bishop", "The bishop can move any number of squares diagonally, without going over any pieces.");
			put("knight",
					"The knight moves to any of the closest squares that are not on the same rank, file, or diagonal, thus the move forms an \"L\"-shape:");
			put("en passant", "En passant move:\n"
					+ "When a player moves a pawn 2 squares then on the very next move, the other player moves their pawn diagonally forward 1 square to the square that pawn moved through, capturing it in the process, the latter is said to be doing en passant. "
					+ "Note that the pawn does not move to the square of the pawn it captured in en passant.\n");
			put("promotion",
					"When a pawn reaches its eighth rank, it is immediately changed into the player's choice of a queen, knight, rook, or bishop of the same color.");
		}
	};

	private String existence;
	private IChessViewerControl viewControl;
	private boolean isWhite;

	public MyConsole(IChessViewerControl controller, boolean isWhiteView, String title, int height, int width) {
		super(title, height / CONSOLE_FONT_SIZE, width / CONSOLE_FONT_SIZE);
		this.viewControl = controller;
		this.isWhite = isWhiteView;
		this.setFont(FONT_CONSOLE);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String text = getText();
					if (existence.length() < text.length()) {
						String input = text.substring(existence.length(), text.length() - 1);
						if (input.length() > 0) {
							handleCommand(input, isWhiteView);
						}
					}
				}
			}
		});
		existence = this.getText();
	}

	public void handleCommand(String input, boolean whiteOrBlack) {
		if (input.length() == 0)
			return;
		if (input.equals("print")) {
			printRecords();
		} else if (input.equals("help")) {
			printOut(HELP_MESSAGE);
		} else if (input.startsWith("rules for ")) {
			showRules(input.substring(10), rules);
		} else if (input.equals("quit")) {
			System.exit(0);
		} else if (input.equals("restart")) {
			this.viewControl.restart();
		} else if (viewControl.hasEnd()) {
			printOut("The game has end");
		} else {
			if (input.equals("resign")) {
				this.viewControl.resign(isWhite);
			} else if (input.equals("draw")) {
				this.viewControl.askForDraw(isWhite);
			} else if (input.equals("undo")) {
				this.viewControl.undo(isWhite);
			} else if (!this.viewControl.makeMove(isWhite, input)) {
				printOut(ERROR_MESSAGE);
			}
		}
	}

	/**
	 * return the requested rules text.
	 * 
	 * @param command
	 * @param whiteOrBlack
	 * @return
	 */
	private void showRules(String command, Map<String, String> rules) {
		if (rules.containsKey(command))
			printOut(rules.get(command));
		printOut("You can get rules for castling, pawn, king, queen, rook, bishop, knight, En Passant, promotion");
	}

	/**
	 * print out the records of the game in starndart chess recording language
	 * 
	 * @param whiteOrBlack
	 * 
	 * @return records
	 */
	private void printRecords() {
		String records = viewControl.getRecords();
		if (records.length() == 0) {
			printOut("Game hasn't started yet.");
			return;
		}
		printOut(records);
	}

	public void printOut(String message) {
		existence = getText();
		if (message != null) {
			existence = existence + message + "\n";
			setText(existence);
		}
	}

	public void printTemp(String temp) {
		setText(existence + temp);
	}

	public void cleanTemp() {
		setText(existence);
	}

}
