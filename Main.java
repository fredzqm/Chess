//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.util.HashMap;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
/**
 * 
 * chessMain is the start of the whole program, it creates a chess, and a frame
 * to hold everything in it.
 * 
 * @author zhangq2
 *
 */
public class Main {
	public static void main(String[] args){
		new ChessControl();
	}
}
//	static final int WIDTH = 50;
//	private static final Font FONT_PIECE = new Font("Serif", Font.PLAIN, 40);
//	private static final Font FONT_STATUS = new Font("Serif", Font.PLAIN, 40);
//	static final int CONSOLE_FONT_SIZE = 20;
//	private static final Font FONT_CONSOLE = new Font("Serif", Font.PLAIN,
//			CONSOLE_FONT_SIZE);
//	private static HashMap<String, String> rules;
//	private JFrame myFrame;
//	private JLabel statusLabel;
//	private JPanel chessBoardSpace;
//	private JPanel consolePanel;
//	private Console myConsole;
//
//	private Main() {
//		myFrame = new JFrame("The Great Chess Game");
//		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		chessBoardSpace = new JPanel();
//		chessBoardSpace.setLayout(new FlowLayout());
//		chessBoardSpace.setVisible(true);
//
//		statusLabel = new JLabel("            Welcome to Wonderful Chess Game             ", JLabel.CENTER);
//		statusLabel.setFont(FONT_STATUS);
//		statusLabel.setVisible(true);
//
//		consolePanel = new JPanel();
//		consolePanel.setLayout(new FlowLayout());
//		consolePanel.setVisible(true);
//		myFrame.add(statusLabel, BorderLayout.NORTH);
//		myFrame.add(chessBoardSpace, BorderLayout.CENTER);
//		myFrame.add(consolePanel, BorderLayout.SOUTH);
//
//	}
//
//	/**
//	 * creates the chessBorad Panel, and puts it into the Frame
//	 * 
//	 * @param myChess
//	 */
//	private void createChessBoard(Chess myChess) {
//		JPanel chessBoard = new JPanel();
//		chessBoard.setSize(WIDTH * 9, WIDTH * 9);
//		chessBoard.setLayout(new GridLayout(9, 9));
//		chessBoard.setVisible(true);
//		for (int j = 0; j < 9; j++) {
//			for (int i = 0; i < 9; i++) {
//				JLabel labels;
//				if (j == 8 || i == 0) {
//					labels = new JLabel("", JLabel.CENTER);
//					if (j == 8) {
//						String s = "";
//						if (i > 0)
//							s += (char) (i + 96);
//						labels.setText(s);
//						;
//						labels.setOpaque(false);
//					} else {
//						String s = "";
//						s += (8 - j);
//						labels.setText(s);
//						;
//						labels.setOpaque(false);
//					}
//				} else {
//					labels = myChess.spotAt(i, 8 - j);
//				}
//				labels.setFont(FONT_PIECE);
//				chessBoard.add(labels);
//			}
//		}
//		chessBoardSpace.add(chessBoard);
//	}
//
//	/**
//	 * create the Console and put it into the frame
//	 * 
//	 * @param myChess
//	 */
//	private void createConsoleBox(Chess myChess) {
//		myConsole = new Console(myChess);
//		myConsole.setFont(FONT_CONSOLE);
//		consolePanel.add(new JScrollPane(myConsole));
//	}
//
//	/**
//	 * 
//	 * @param s
//	 *            provide the list of rules library
//	 */
//	private static void intializeRules() {
//		rules = new HashMap<String, String>();
//		rules.put(
//				"castling",
//				"Only under those circumstances, you can castling\n"
//						+ "1.Your king and the corresponding rook has never been moved.\n"
//						+ "2.There is no chessman between your king and rook.\n"
//						+ "3.The squres that your king goes over should not under attack by any pieces of the opponent.\n"
//						+ "4.Your king cannot be in check either before and after the castling.");
//		rules.put(
//				"pawn",
//				"The pawn may move forward to the unoccupied square immediately in front of it on the same file without capturing, "
//						+ "or advance two squares along the same file without capturing on its first move;"
//						+ "or capture an opponent's piece on a square diagonally in front of it on an adjacent file.\n"
//						+ "En Passant and promotion are also special rules for pawn.");
//		rules.put(
//				"king",
//				"The king moves one square in any direction. You always want to protect your king.\n"
//						+ "Castling is also a special rule for king.");
//		rules.put(
//				"queen",
//				"The queen combines the power of the rook and bishop and can move any number of squares along rank, file, or diagonal, without going over any pieces");
//		rules.put(
//				"rook",
//				"The rook can move any number of squares along any rank or file without going over any pieces");
//		rules.put(
//				"bishop",
//				"The bishop can move any number of squares diagonally, without going over any pieces.");
//		rules.put(
//				"knight",
//				"The knight moves to any of the closest squares that are not on the same rank, file, or diagonal, thus the move forms an \"L\"-shape:");
//		rules.put(
//				"en passant",
//				"En passant move:\n"
//						+ "When a player moves a pawn 2 squares then on the very next move, the other player moves their pawn diagonally forward 1 square to the square that pawn moved through, capturing it in the process, the latter is said to be doing en passant. "
//						+ "Note that the pawn does not move to the square of the pawn it captured in en passant.\n");
//		rules.put(
//				"promotion",
//				"When a pawn reaches its eighth rank, it is immediately changed into the player's choice of a queen, knight, rook, or bishop of the same color.");
//	}
//
//	/**
//	 * return the requested rules text.
//	 * @param command
//	 * @return
//	 */
//	private static String rules(String command) {
//		if (rules.containsKey(command))
//			return rules.get(command);
//		return "You can get rules for castling, pawn, king, queen, rook, bishop, knight, En Passant, promotion";
//	}
//
//
//	/**
//	 * handle command and call the relevant method
//	 * 
//	 * @param s
//	 *            the input string
//	 * @param chess
//	 *            the game class
//	 * @return the next output line
//	 */
//	protected static String handleCommand(String s, Chess chess) {
//		if (s.toLowerCase().equals("print")) {
//			return chess.print();
//		} else if (s.toLowerCase().equals("restart")) {
//			chess.intialize(false);
//			return "Start a new game!";
//		} else if (s.toLowerCase().startsWith("rules for ")) {
//			return rules(s.toLowerCase().substring(10));
//		} 
//		
//		if (chess.hasEnd())
//			return chess.lastMoveDiscript();
//		
//		if (s.length() == 0) {
//			return "";
//		} else if (s.toLowerCase().equals("help")) {
//			return "Enter commands:\n"
//					+ "enter 'undo' to undo the previous round;\n"
//					+ "enter 'restart' to start a new game over;\n'"
//					+ "enter 'print' to print all the records;\n"
//					+ "enter 'resign' to give up;\n"
//					+ "enter 'draw' to request for draw;\n"
//					+ "enter complete or abbreviated algebraic chess notation to make a move;\n"
//					+ "enter 'rules for ....' to get help about the rules of chess.\n"
//					+ "    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";
//		} else if (s.toLowerCase().equals("undo")) {
//			return chess.undoPreviousRround();
//		} else if (s.toLowerCase().equals("resign")) {
//			return chess.resign();
//		} else if (s.toLowerCase().equals("draw")) {
//			return chess.askForDraw();
//		} else if (s.toLowerCase().charAt(0) == 'o') {
//			return chess.castling(s);
//		} else if (s.length() == 6 || s.length() == 5) {
//			return chess.makeMove(s);
//		} else if (s.length() < 5) {
//			String fullStr = chess.figureOutTheAbbreviation(s);
//			if (fullStr != null)
//				if (fullStr.startsWith("A"))
//					return fullStr;
//				else
//					return handleCommand(fullStr, chess);
//			else
//				return "Incorrect format of abbreviation command.\n"
//						+ "You can omit the start spot of the move in the complete command.";
//		} else
//			return "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\n"
//					+ "you can omit the \"P\" at the begining for a pawn."
//					+ "for casting, enter \"O-O\" or \"O-O-O\"\n"
//					+ "for examples, \"e2-e4\", \"Nb2-c3\" ";
//	}
//
//	
//	/**
//	 * start my little chess game!!!!
//	 * 
//	 * @param args
//	 *            ignored
//	 */
//	public static void main(String args[]) {
//		Main myGame = new Main();
//		Chess myChess = new Chess();
//		intializeRules();
//		myGame.createConsoleBox(myChess);
//		myGame.createChessBoard(myChess);
//		myChess.initializedOutPutMethod(myGame.statusLabel, myGame.myConsole);
//		myGame.myFrame.setVisible(true);
//		myGame.myFrame.pack();
//	}
//
//}
