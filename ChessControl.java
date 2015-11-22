import java.util.HashMap;

public class ChessControl implements ChessViewerController, ChessListener {

	private final static HashMap<String, String> rules = new HashMap<String, String>() {
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

	ChessGameViewer view;
	Chess chess;
	Piece highLight;

	/**
	 * start my little chess game!!!!
	 * 
	 * @param args
	 *            ignored
	 */
	public ChessControl() {
		chess = new Chess();
		chess.addChessListener(this);
		highLight = null;

		view = new ChessGameViewer(this);
		view.setVisible(true);
		view.pack();
		updateAll();
	}

	private void restart() {
		chess.removeChessListener(this);
		chess = new Chess();
		chess.addChessListener(this);
		highLight = null;
		updateAll();
	}

	private void updateAll() {
		for (Square s : chess.getAllSquares())
			updateSquare(s);
	}

	/**
	 * return the requested rules text.
	 * 
	 * @param command
	 * @return
	 */
	private String rules(String command) {
		if (rules.containsKey(command))
			return rules.get(command);
		return "You can get rules for castling, pawn, king, queen, rook, bishop, knight, En Passant, promotion";
	}

	/**
	 * handle command and call the relevant method
	 * 
	 * @param s
	 *            the input string
	 * @param chess
	 *            the game class
	 * @return the next output line
	 */
	public String handleCommand(String s) {
		if (s.toLowerCase().equals("print")) {
			return chess.print();
		} else if (s.toLowerCase().equals("restart")) {
			restart();
			return "Start a new game!";
		} else if (s.toLowerCase().startsWith("rules for ")) {
			return rules(s.toLowerCase().substring(10));
		}

		if (chess.hasEnd())
			return chess.lastMoveDiscript();

		if (s.length() == 0) {
			return "";
		} else if (s.toLowerCase().equals("help")) {
			return "Enter commands:\n" + "enter 'undo' to undo the previous round;\n"
					+ "enter 'restart' to start a new game over;\n'" + "enter 'print' to print all the records;\n"
					+ "enter 'resign' to give up;\n" + "enter 'draw' to request for draw;\n"
					+ "enter complete or abbreviated algebraic chess notation to make a move;\n"
					+ "enter 'rules for ....' to get help about the rules of chess.\n"
					+ "    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";
		} else if (s.toLowerCase().equals("undo")) {
			return chess.undoPreviousRround();
		} else if (s.toLowerCase().equals("resign")) {
			return chess.resign();
		} else if (s.toLowerCase().equals("draw")) {
			return chess.askForDraw();
		} else if (s.toLowerCase().charAt(0) == 'o') {
			return chess.castling(s);
		} else if (s.length() == 6 || s.length() == 5) {
			return chess.makeMove(s);
		} else if (s.length() < 5) {
			String fullStr = chess.figureOutTheAbbreviation(s);
			if (fullStr != null)
				if (fullStr.startsWith("A"))
					return fullStr;
				else
					return handleCommand(fullStr);
			else
				return "Incorrect format of abbreviation command.\n"
						+ "You can omit the start spot of the move in the complete command.";
		} else
			return "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\n"
					+ "you can omit the \"P\" at the begining for a pawn." + "for casting, enter \"O-O\" or \"O-O-O\"\n"
					+ "for examples, \"e2-e4\", \"Nb2-c3\" ";
	}

	/**
	 * print out the result in the box.
	 */
	public void printInBox(String s) {
		view.printOut(s);
	}

	/**
	 * print out the temporal piece that is chosen in the box
	 * 
	 * @param s
	 */
	protected void printchosenPiece(String s) {
		if (s.charAt(0) == 'P')
			view.printTemp(s.substring(1));
		else
			view.printTemp(s);
	}

	/**
	 * clean the temporal piece information, because the user suggests a illegal
	 * move
	 */
	protected void printCleanTemp() {
		view.cleanTemp();
	}

	/**
	 * print out the outputs in the head label.
	 * 
	 * @param s
	 */
	public void printInLabel(String s) {
		view.setText(s);
	}

	@Override
	public void updateSquare(Square sq) {
		view.updateSquare(sq);
	}


	/**
	 * this mehod will be called if the user click on the board. It will find
	 * out whether the user has suggest a legal move, and provides proper
	 * outputs.
	 * 
	 * @param spot
	 *            the square that is clicked
	 */
	public void click(SquareLabel sql) {
		Square spot = chess.spotAt(sql.X(), sql.Y());
		if (highLight != null) {
			if (spot.isHighLight() && !spot.equals(highLight.getP())) {
				String s = "";
				if (highLight.canMove(spot))
					s = highLight.move(spot);
				else if (highLight.canCapture(spot))
					s = highLight.capture(spot, spot.getPiece());

				printchosenPiece(chess.lastMoveOutPrint());
				printInBox("\n" + s);
				printInLabel(chess.lastMoveDiscript());
			} else
				printCleanTemp();
			deHighLightWholeBoard();
		} else {
			if (spot.occupiedBy(chess.getWhoseTurn())) {
				setHighLightPiece(spot.getPiece());
				printchosenPiece(spot.getPiece().getType() + spot.toString());
			}
		}
	}

	/**
	 * when one possible piece is chosen, highlight it and all the spots it can
	 * move to.
	 * 
	 * @param piece
	 */
	public void setHighLightPiece(Piece piece) {
		highLight = piece;
		piece.getP().highLight();
		for (Square i : chess.getAllSquares())
			if (!i.occupiedBy(chess.getWhoseTurn()))
				if (highLight.canMove(i) || highLight.canCapture(i))
					i.highLight();
	}

	/**
	 * dehighlight the whole board
	 */
	public void deHighLightWholeBoard() {
		highLight = null;
		for (Square i : chess.getAllSquares())
			if (i.isHighLight())
				i.deHighLight();
	}

}
