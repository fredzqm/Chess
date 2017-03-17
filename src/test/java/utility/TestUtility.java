package utility;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Chess;
import model.InvalidMoveException;
import model.Move;
import model.Piece;
import model.Piece.Player;
import model.Square;

public class TestUtility {

	/**
	 * Read a file and perform the moves recorded in the file.
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 * @throws InvalidMoveException
	 */
	public static void performRecordMoves(Chess chess, String filename)
			throws FileNotFoundException, InvalidMoveException {
		performRecordMoves(chess, getMoveString(filename));
	}

	/**
	 * perform the list of records in the chess
	 * 
	 * @param chess
	 * @param recordMoves
	 * @throws InvalidMoveException
	 */
	public static void performRecordMoves(Chess chess, List<String> recordMoves) throws InvalidMoveException {
		for (String moveStr : recordMoves) {
			Move move = chess.getMove(moveStr);
			chess.makeMove(move);
		}
	}

	/**
	 * 
	 * @param filename
	 *            the file name of test cases
	 * @return the list of moves in this file
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static List<String> getMoveString(String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));
		List<String> ls = new ArrayList<>();
		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			if (line.startsWith("["))
				continue;

			String[] parts = line.split("\\d+\\.| ");
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].length() == 0)
					continue;
				ls.add(parts[i]);
			}
		}
		return ls;
	}

	/**
	 * 
	 * @param fileName
	 * @return the board string in this file
	 */
	public static String getBoardString(String fileName) {
		StringBuilder sb = new StringBuilder();
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
			for (int i = 0; i < 8; i++) {
				String line = in.nextLine();
				sb.append(line);
				for (int x = line.length(); x < 16; x++)
					sb.append(' ');
				sb.append('\n');
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null)
				in.close();
		}
		return sb.toString();
	}

	/**
	 * assert if this chess's current layout matches the boardString
	 * 
	 * @param chess
	 * @param boardString
	 */
	public static void assertBoard(Chess chess, String boardString) {
		String[] sp = boardString.split("\n");
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Square square = chess.spotAt(j, i);
				Piece piece = square.getPiece();

				// test the chess type
				char actualType = ' ';
				if (piece != null)
					actualType = Character.toUpperCase(piece.getType());
				char expectedType = sp[8 - i].charAt(j * 2 - 1);
				assertEquals(
						String.format("At %s actual: %c expected: %c", square.toString(), actualType, expectedType),
						actualType, expectedType);

				// test the chess side
				if (piece != null) {
					boolean type = sp[8 - i].charAt(j * 2 - 2) == '*';
					assertEquals(String.format("At %s", square.toString()), type, piece.getWhiteOrBlack() == Player.WHITE);
				}
			}
		}
	}

	/**
	 * assert if the chess's layout matches the board layout specified in the
	 * file
	 * 
	 * @param chess
	 * @param fileName
	 */
	public static void assertBoardFile(Chess chess, String fileName) {
		assertBoard(chess, getBoardString(fileName));
	}
}
