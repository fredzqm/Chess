package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

import model.Chess;
import utility.LoadMoves;

public class EndGameTest {

	private Chess setupChessGame(String fileName) throws FileNotFoundException {
		Chess chess = new Chess();
		assertTrue(LoadMoves.performRecordMoves(chess, fileName));
		assertTrue(chess.getRecords().hasEnd());
		
		return chess;
	}
	
	@Test
	public void testWhiteCheckmate() throws FileNotFoundException {
		Chess chess = setupChessGame("sampleGames/White_Checkmate.txt");
		
		assertEquals(1, chess.getRecords().getEndGame().getResult());
	}
	
	@Test
	public void testBlackCheckmate() throws FileNotFoundException {
		Chess chess = setupChessGame("sampleGames/Black_Checkmate.txt");
		
		assertEquals(-1, chess.getRecords().getEndGame().getResult());
	}
	
	@Test
	public void testStalemate() throws FileNotFoundException {
		Chess chess = setupChessGame("sampleGames/Stalemate.txt");
		
		assertEquals(0, chess.getRecords().getEndGame().getResult());
	}
}
