package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import utility.TestUtitlities;

public class ChessEnPassentTest {
	
	@Test
	public void testWhiteEnPassent() throws FileNotFoundException, InvalidMoveException {
		String file = "sampleGames/White_EnPassent.txt";
		List<String> moveLs = TestUtitlities.getMoveString(file);
		String lastMove = moveLs.get(moveLs.size()-1);

		Chess chess = new Chess();
		TestUtitlities.performRecordMoves(chess, moveLs.subList(0, moveLs.size()-1));
		
		String move = lastMove.split("-")[1];
		int x = findIndex(move.charAt(0));
		 
		Square sq = chess.spotAt(x, Character.getNumericValue(move.charAt(1)));
		assertTrue(chess.canEnPassant(sq));
	}
	
	@Test
	public void TestWhiteInvalidWhiteEnPassent() throws FileNotFoundException, InvalidMoveException{
		String file = "sampleGames/White_InvalidEnPassent.txt";
		List<String> moveLs = TestUtitlities.getMoveString(file);
		String lastMove = moveLs.get(moveLs.size()-1);
		
		Chess chess = new Chess();
		TestUtitlities.performRecordMoves(chess, moveLs.subList(0, moveLs.size()-1));
		
		String move = lastMove.split("-")[1];
		int x = findIndex(move.charAt(0));
		 
		Square sq = chess.spotAt(x, Character.getNumericValue(move.charAt(1)));
		assertFalse(chess.canEnPassant(sq));
	}

	@Test
	public void testBlackEnPassent() throws FileNotFoundException, InvalidMoveException {
		String file = "sampleGames/Black_EnPassent.txt";
		List<String> moveLs = TestUtitlities.getMoveString(file);
		String lastMove = moveLs.get(moveLs.size()-1);

		Chess chess = new Chess();
		TestUtitlities.performRecordMoves(chess, moveLs.subList(0, moveLs.size()-1));
		
		String move = lastMove.split("-")[1];
		int x = findIndex(move.charAt(0));
		 
		Square sq = chess.spotAt(x, Character.getNumericValue(move.charAt(1)));
		assertTrue(chess.canEnPassant(sq));
	}
	
	@Test
	public void TestWhiteInvalidEnPassent() throws FileNotFoundException, InvalidMoveException{
		String file = "sampleGames/Black_InvalidEnPassent.txt";
		List<String> moveLs = TestUtitlities.getMoveString(file);
		String lastMove = moveLs.get(moveLs.size()-1);
		
		Chess chess = new Chess();
		TestUtitlities.performRecordMoves(chess, moveLs.subList(0, moveLs.size()-1));
		
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

}
