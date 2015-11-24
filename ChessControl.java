import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ChessControl implements ChessViewerControl, ChessListener {

	public static final String ERROR_MESSAGE = "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\n"
			+ "you can omit the \"P\" at the begining for a pawn." + "for casting, enter \"O-O\" or \"O-O-O\"\n"
			+ "for examples, \"e2-e4\", \"Nb2-c3\" ";

	public static final String HELP_MESSAGE = "Enter commands:\n" + "enter 'undo' to undo the previous round;\n"
			+ "enter 'restart' to start a new game over;\n'" + "enter 'print' to print all the records;\n"
			+ "enter 'resign' to give up;\n" + "enter 'draw' to request for draw;\n"
			+ "enter complete or abbreviated algebraic chess notation to make a move;\n"
			+ "enter 'rules for ....' to get help about the rules of chess.\n"
			+ "    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";

	public static final HashMap<String, String> rules = new HashMap<String, String>() {
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

	ChessViewer view;
	Chess chess;
	Piece chosen;

	/**
	 * start my little chess game!!!!
	 * 
	 * @param args
	 *            ignored
	 */
	public ChessControl() {
		chess = new Chess();
		chess.addChessListener(this);
		chosen = null;

		view = new ChessViewer(this);
		view.setVisible(true);
		view.pack();
		for (Square s : chess.getAllSquares())
			updateSquare(s);
	}

	private void restart() {
		chess.removeChessListener(this);
		chess = new Chess();
		chess.addChessListener(this);
		chosen = null;

		view.deHighLightWholeBoard();
		view.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
		for (Square s : chess.getAllSquares())
			updateSquare(s);
		view.printOut("Start a new game!");
	}

	/**
	 * return the requested rules text.
	 * 
	 * @param command
	 * @return
	 */
	private void showRules(String command) {
		if (rules.containsKey(command))
			view.printOut(rules.get(command));
		view.printOut("You can get rules for castling, pawn, king, queen, rook, bishop, knight, En Passant, promotion");
	}

	/**
	 * This method is called if the user enter a command to undo his move. It
	 * will undo two moves.
	 * 
	 */
	private void undo() {
		if (!chess.undoLastMove())
			view.printOut("It is already the start of Game");
		else
			view.printOut("Undo the Previous Move!");
	}

	/**
	 * print out the records of the game in starndart chess recording language
	 * 
	 * @return records
	 */
	private void printRecords() {
		ArrayList<Move> records = chess.getRecords();
		if (records.size() == 0) {
			view.printOut("Game hasn't started yet.");
			return;
		}
		StringBuilder s = new StringBuilder();
		for (Move i : records) {
			s.append(i.print());
		}
		s.deleteCharAt(s.length() - 1);
		view.printOut(s.toString());
	}

	/**
	 * This method will be called, if the user types a command to make a move.
	 * 
	 * Interpret the command, and find out if it is legal to do make this move.
	 * If it is, make this move.
	 * 
	 * @param s
	 *            the input command
	 * @return
	 */
	private boolean makeMove(String s) {
		if (checkCastling(s))
			return true;

		Pattern p = Pattern.compile("([prnbqk])?([a-h])?([1-8])?.*?([a-h][1-8]).*");
		Matcher m = p.matcher(s);
		if (m.matches()) {
			Class<? extends Piece> type = m.group(1) == null ? Pawn.class : Piece.getType(m.group(1).charAt(0));
			if (type == Piece.class) {
				view.printOut(
						"Please enter valid initial of chessman -- R(Root), N(Knight), B(Bishop), Q(Queen), K(King). If you omit it, it is assumed as Pawn.");
				// return true;
			}
			Square start = null;
			if ((m.group(2) != null) && (m.group(3) != null)) {
				start = chess.getSquare(m.group(2) + m.group(3));
			}
			Square end = chess.getSquare(m.group(4));

			if (start != null) {
				Piece movedChessman = start.getPiece();
				if (movedChessman == null) {
					if (chess.getWhoseTurn())
						view.printOut("There should be a white chessman in the start Position!");
					else
						view.printOut("There should be a black chessman in the start Position!");
				} else if (!(movedChessman.isType(type))) {
					view.printOut(
							"The chessman in the start Position is not corret! \n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
				}
				if (!chess.performMove(movedChessman, end))
					view.printOut("Illegal move! Please check the rule of " + movedChessman.getName() + "!");
				// return;
			} else {
				System.out.println(" " + type + " " + end);
				ArrayList<Piece> possible = chess.possibleMovers(type, end);
				System.out.println(possible);
				if (possible.size() == 0) {
					view.printOut("Fail to guess move: No one can reach that spot.");
				} else if (possible.size() == 1) {
					if (!chess.performMove(possible.get(0), end))
						throw new RuntimeException("OOOOO!");
				} else {
					view.printOut("Fail to guess move: There is ambiguity, multiple possible moves.");
				}

			}
			return true;
		}
		return false;
		// char type;
		// if (s.length() == 5) {
		// s = 'P' + s;
		// type = 'P';
		// } else {
		// type = s.toUpperCase().charAt(0);
		// if (!(type == 'R' || type == 'N' || type == 'B' || type == 'Q' ||
		// type == 'K' || type == 'P')) {
		// view.printOut(
		// "Please enter valid initial of chessman -- R(Root), N(Knight),
		// B(Bishop), Q(Queen), K(King). If you omit it, it is assumed as
		// Pawn.");
		// return false;
		// }
		// }
		//
		// s = s.toLowerCase();
		// Square start = chess.getSquare(s.substring(1, 3));
		// if (start == null) {
		// view.printOut("please enter a valid start Position");
		// return;
		// }
		// boolean takeOrNot;
		// char action = s.charAt(3);
		// if (action == '-')
		// takeOrNot = false;
		// else if (s.charAt(3) == 'x')
		// takeOrNot = true;
		// else {
		// view.printOut("Pleae enter \"-\" or \"x\" to indicate whether this
		// move takes some piece or not.");
		// return;
		// }
		// Square end = chess.getSquare(s.substring(4));
		// if (end == null) {
		// view.printOut("please enter a valid end Position");
		// return;
		// }
		// Piece movedChessman = start.getPiece();
		// if (movedChessman == null) {
		// if (chess.getWhoseTurn())
		// view.printOut("There should be a white chessman in the start
		// Position!");
		// else
		// view.printOut("There should be a black chessman in the start
		// Position!");
		// return;
		// }
		// if (!(movedChessman.isType(Piece.getType(type)))) {
		// view.printOut(
		// "The chessman in the start Position is not corret! \n R(Root),
		// N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
		// return;
		// }

		// Piece chessmanTaken = end.getPiece();
		// if (takeOrNot) {
		// if (!movedChessman.canCapture(end)) {
		// view.printOut("Illegal move! Please check the rule of " +
		// movedChessman.getName() + "!");
		// return;
		// }
		// // view.printOut(movedChessman.capture(end, chessmanTaken));
		// } else {
		// if (chessmanTaken != null) {
		// view.printOut("It works this time,but please use \"x\" if you want to
		// take it next time. Thank you!");
		// // view.printOut(movedChessman.capture(end, chessmanTaken));
		// } else {
		// if (!movedChessman.canMove(end)) {
		// view.printOut("Illegal move! Please check the rule of " +
		// movedChessman.getName() + "!");
		// return;
		// }
		// // view.printOut(movedChessman.move(end));
		// }
		// }

		// if (!chess.performMove(movedChessman, end)) {
		// view.printOut("Illegal move! Please check the rule of " +
		// movedChessman.getName() + "!");
		// return;
		// }

	}

	private boolean checkCastling(String s) {
		boolean lors;
		if (s.equals("o-o")) {
			lors = false;
		} else if (s.equals("o-o-o")) {
			lors = true;
		} else
			return false;

		if (!chess.castling(lors))
			view.printOut("You cannot do castling, please check the rules for castling.");
		return true;
	}

	/**
	 * Tranformed the abbreviated command to the complete one, or return error
	 * if there is ambiguous about the abbreviated command.
	 * 
	 * @param s
	 * @return
	 */
	public void abbreviation(String s) {
		char type = Character.toUpperCase(s.charAt(0));
		if (type == 'R' || type == 'N' || type == 'B' || type == 'Q' || type == 'K' || type == 'P')
			s = s.substring(1);
		else
			type = 'P';

		boolean takeOrNot = (s.charAt(0) == 'x');

		if (takeOrNot)
			s = s.substring(1);

		Square end = null;
		if (s.length() == 2)
			end = chess.getSquare(s);
		if (end == null) {
			view.printOut("Incorrect format of abbreviation command.\n"
					+ "You can omit the start spot of the move in the complete command.");
			return;
		}

		ArrayList<Piece> possible = chess.possibleMovers(Piece.getType(type), end);
		if (possible.size() == 0) {
			view.printOut("Ambiguity: No one can reach that spot.");
		} else if (possible.size() == 1) {
			chess.performMove(possible.get(0), end);
		} else {
			view.printOut("Ambiguity: This can represent many different moves.");
		}
	}

	/**
	 * Find out if it is legal to claim draw. If it is, ends the game and claim
	 * draw, otherwise send a request for draw, and wait for the reply of
	 * opponent.
	 * 
	 * @return
	 */
	public void askForDraw() {
		int status = chess.askForDraw();
		if (status == 0) {
			while (true) {
				String command = JOptionPane.showInputDialog("Do you agree draw?");
				if (command.isEmpty())
					continue;
				if (command.toLowerCase().startsWith("yes")) {
					chess.draw("Agreement to draw", "Draw by Agreement.");
					return;
				} else if (command.toLowerCase().startsWith("no")) {
					chess.setRightToRequestDraw();
					view.printOut("Request declined");
					return;
				}
			}
		} else if (status == -1) {
			view.printOut("You cannot request for draw again now.");
		}

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
	 * when one possible piece is chosen, highlight it and all the spots it can
	 * move to.
	 * 
	 * @param piece
	 */
	public void setChosenPiece(Piece piece) {
		chosen = piece;
		squareToLabel(piece.getP()).highLight();
		for (Square i : chess.getAllSquares())
			if (!i.occupiedBy(chess.getWhoseTurn()))
				if (chosen.canGo(i)) {
					squareToLabel(i).highLight();
				}
	}

	private SquareLabel squareToLabel(Square sqr) {
		return view.labelAt(sqr.X(), sqr.Y());
	}

	private Square labelToSquare(SquareLabel sql) {
		return chess.spotAt(sql.X(), sql.Y());
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
	public void handleCommand(String command) {
		String c = command.toLowerCase();
		if (c.length() == 0)
			return;
		if (c.equals("print")) {
			printRecords();
		} else if (c.equals("quit")) {
			System.exit(0);
		} else if (c.equals("restart")) {
			restart();
		} else if (c.startsWith("rules for ")) {
			showRules(c.substring(10));
		} else if (chess.hasEnd()) {
			view.printOut(chess.lastMoveDiscript());
		} else if (c.equals("help")) {
			view.printOut(HELP_MESSAGE);
		} else {
			chosen = null;
			view.deHighLightWholeBoard();
			if (c.equals("undo")) {
				undo();
			} else if (c.equals("resign")) {
				chess.resign();
			} else if (c.equals("draw")) {
				askForDraw();
			} else if (!makeMove(c)) { // makeMove return true if the move is
										// performed
				view.printOut(ERROR_MESSAGE);
			}
		}

	}

	/**
	 * this mehod will be called if the user click on the board. It will find
	 * out whether the user has suggest a legal move, and provides proper
	 * outputs.
	 * 
	 * @param spot
	 *            the square that is clicked
	 */
	public void click(SquareLabel label) {
		if (chess.hasEnd()) {
			view.printOut("Game is already over! Type restart to start a new game");
		} else {
			Square spot = labelToSquare(label);
			if (chosen != null) {
				if (label.isHighLight() && !spot.equals(chosen.getP())) {
					if (!chess.performMove(chosen, spot))
						throw new ChessGameException(
								"Illegal move of " + chosen.getName() + " did not correctly caught from UI!");
				} else
					view.cleanTemp();
				chosen = null;
				view.deHighLightWholeBoard();
			} else {
				if (spot.occupiedBy(chess.getWhoseTurn())) {
					chosen = spot.getPiece();
					squareToLabel(chosen.getP()).highLight();
					for (Square i : chess.getAllSquares())
						if (!i.occupiedBy(chess.getWhoseTurn()))
							if (chosen.canGo(i))
								squareToLabel(i).highLight();

					if (spot.getPiece().isType(Pawn.class))
						view.printTemp(spot.toString());
					else
						view.printTemp(spot.getPiece().getType() + spot.toString());

				}
			}
		}
	}

	@Override
	public void updateSquare(Square sq) {
		if (sq.occupied()) {
			view.showLabel(sq.X(), sq.Y(), sq.getPiece().getType(), sq.getPiece().getWb());
		} else {
			view.cleanLabel(sq.X(), sq.Y());
		}
	}

	@Override
	public void win(boolean whiteOrBlack, String outprint, String descript) {
		view.setStatusLabelText(descript);
		view.printOut(outprint);
		// if (whiteOrBlack) {
		// view.printOut("WHITE wins!!");
		// } else {
		// view.printOut("BLACK wins!!");
		// }
	}

	@Override
	public void draw(String outprint, String descript) {
		view.setStatusLabelText(descript);
		view.printOut(outprint);
		// view.printOut("Draw.");
	}

	@Override
	public void nextMove(boolean whoseTurn) {
		view.setStatusLabelText(chess.lastMoveDiscript());
		view.cleanTemp();
		view.printOut(chess.lastMoveOutPrint());
		if (whoseTurn) {
			view.printOut("Next move -- white");
		} else {
			view.printOut("Next move -- black");
		}
	}

	@Override
	public void endOfGame(EndGame end) {
		// TODO Auto-generated method stub

	}

	@Override
	public Piece promote(boolean wb, Square end) {
		while (true) {
			view.printOut("Please choose one kind of piece to promote to -- Q, N,R, B");
			String s = JOptionPane.showInputDialog("Promotion to !?");
			if (!s.isEmpty()) {
				s = s.toUpperCase();
				char a = s.charAt(0);
				if (a == 'Q')
					return new Queen(wb, end);
				else if (a == 'R')
					return new Rook(wb, end);
				else if (a == 'B')
					return new Bishop(wb, end);
				else if (a == 'N')
					return new Knight(wb, end);

			}
		}
	}
}
