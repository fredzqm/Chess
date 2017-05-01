package model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import utility.TestUtility;

public class DrawTest {

	private Chess setupGame(String fileName) throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();
		
		TestUtility.performRecordMoves(chess, fileName);
				
		return chess;
	}
	
	@Test
	public void testFiftyMoveRepetitionDraw() throws FileNotFoundException, InvalidMoveException {
		Chess chess = setupGame("sampleGames/FiftyMoveDraw.txt");
		
		assertNotEquals(null, chess.canClaimDraw());
	}
	
//	@Test
	public void testThreefoldRepetitionDraw() throws FileNotFoundException, InvalidMoveException {
		Chess chess = setupGame("sampleGames/ThreeFoldRepetition.txt");
		
		assertNotEquals(null, chess.canClaimDraw());
	}

}
