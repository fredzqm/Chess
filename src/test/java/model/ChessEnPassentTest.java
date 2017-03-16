package model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;

public class ChessEnPassentTest {
	
	@Test
	public void testWhiteEnPassent() throws FileNotFoundException, InvalidMoveException {
		String file = "sampleGames/White_EnPassent.txt";
		String lastMove = getLastMoveInFile(file);
		
		Chess chess = new Chess();
		performRecordMovesButLast(chess, file);
		
		String move = lastMove.split("-")[1];
		int x = findIndex(move.charAt(0));
		 
		Square sq = chess.spotAt(x, Character.getNumericValue(move.charAt(1)));
		assertTrue(chess.canEnPassant(sq));
	}
	
	@Test
	public void TestWhiteInvalidWhiteEnPassent() throws FileNotFoundException, InvalidMoveException{
		String file = "sampleGames/White_InvalidEnPassent.txt";
		String lastMove = getLastMoveInFile(file);
		
		Chess chess = new Chess();
		performRecordMovesButLast(chess, file);
		
		String move = lastMove.split("-")[1];
		int x = findIndex(move.charAt(0));
		 
		Square sq = chess.spotAt(x, Character.getNumericValue(move.charAt(1)));
		assertFalse(chess.canEnPassant(sq));
	}

	@Test
	public void testBlackEnPassent() throws FileNotFoundException, InvalidMoveException {
		String file = "sampleGames/Black_EnPassent.txt";
		String lastMove = getLastMoveInFile(file);

		Chess chess = new Chess();
		performRecordMovesButLast(chess, file);
		
		String move = lastMove.split("-")[1];
		int x = findIndex(move.charAt(0));
		 
		Square sq = chess.spotAt(x, Character.getNumericValue(move.charAt(1)));
		assertTrue(chess.canEnPassant(sq));
	}
	
	@Test
	public void TestWhiteInvalidEnPassent() throws FileNotFoundException, InvalidMoveException{
		String file = "sampleGames/Black_InvalidEnPassent.txt";
		String lastMove = getLastMoveInFile(file);
		
		Chess chess = new Chess();
		performRecordMovesButLast(chess, file);
		
		String move = lastMove.split("-")[1];
		int x = findIndex(move.charAt(0));
		 
		Square sq = chess.spotAt(x, Character.getNumericValue(move.charAt(1)));
		assertFalse(chess.canEnPassant(sq));
	}
	

	private int findIndex(char c) {
		int index = 0;
		for (char i = 'a'; i <= c; i++) {
			index++;
		}
		return index;
	}

	private String getLastMoveInFile(String file) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(file));
		String lastMove = "";
		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			if (line.startsWith("["))
				continue;

			String[] parts = line.split("\\d+\\.| ");
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].length() == 0)
					continue;
				lastMove = parts[i];
			}
		}

		scan.close();
		return lastMove;
	}

	/**
	 * Read a file and perform the moves recorded in the file.
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	private void performRecordMovesButLast(Chess chess, String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));

		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			if (line.startsWith("["))
				continue;

			String[] parts = line.split("\\d+\\.| ");
			for (int i = 0; i < parts.length - 1; i++) {
				if (parts[i].length() == 0)
					continue;

				Move move;
				try {
					move = chess.getMove(parts[i]);
					chess.makeMove(move);
				} catch (InvalidMoveException e) {
					System.out.println(parts[i] + "\n");
				}
			}
		}

		scan.close();
	}
}
