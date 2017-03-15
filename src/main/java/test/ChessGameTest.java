package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import model.Chess;
import model.InvalidMoveException;
import model.Move;

public class ChessGameTest {

	@Test
	public void testWhiteCheckmate() throws FileNotFoundException {
		Chess chess = new Chess();
		
		performRecordMoves(chess, "sampleGames/White_Checkmate.txt");
		
		assertTrue(chess.getRecords().hasEnd());
		
		assertEquals(1, chess.getRecords().getEndGame().getResult());
	}
	
	@Test
	public void testBlackCheckmate() throws FileNotFoundException {
		Chess chess = new Chess();
		
		performRecordMoves(chess, "sampleGames/Black_Checkmate.txt");
		
		assertTrue(chess.getRecords().hasEnd());
		
		assertEquals(-1, chess.getRecords().getEndGame().getResult());
	}
	
	/**
	 * Read a file and perform the moves recorded in the file.
	 * 
	 * @param filename
	 * @throws FileNotFoundException 
	 */
	private void performRecordMoves(Chess chess, String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			
			if(line.startsWith("[")) continue;
			
			String[] parts = line.split("\\d\\.| ");
			for(int i = 0; i < parts.length; i++) {
				if(parts[i].length() == 0) continue;
				
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
