package utility;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Chess;
import model.InvalidMoveException;
import model.Move;
import model.Piece;
import model.Square;

public class TestUtitlities {
	/**
	 * Read a file and perform the moves recorded in the file.
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public static boolean performRecordMoves(Chess chess, String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));

		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			if (line.startsWith("["))
				continue;

			String[] parts = line.split("\\d+\\.| ");
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].length() == 0)
					continue;

				Move move;
				try {
					move = chess.getMove(parts[i]);
					chess.makeMove(move);
				} catch (InvalidMoveException e) {
					scan.close();
					return false;
				}
			}
		}

		scan.close();
		return true;
	}

	public static void assertBoard(Chess chess, String board) {
		String[] sp = board.split("\n");

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Square square = chess.spotAt(j, i);
				Piece piece = square.getPiece();

				// test the chess type
				char actualType = ' ';
				if (piece != null)
					actualType = Character.toUpperCase(piece.getType());
				char expectedType = sp[i - 1].charAt(j * 2 - 1);
				assertEquals(
						String.format("At %s actual: %c expected: %c", square.toString(), actualType, expectedType),
						actualType, expectedType);

				// test the chess side
				if (piece != null) {
					boolean type = sp[i - 1].charAt(j * 2 - 2) != '*';
					assertEquals(String.format("At %s", square.toString()), type, piece.getWOrB());
				}
			}
		}
	}
}
