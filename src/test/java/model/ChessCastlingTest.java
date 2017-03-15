package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

import utility.TestUtitlities;

public class ChessCastlingTest {
	
	@Test
	public void testInitalBoardLayout() throws FileNotFoundException {
		Chess chess = new Chess();
		TestUtitlities.assertBoard(chess, " R N B Q K B N R\n" + 
									 " P P P P P P P P\n" + 
									 "                \n" + 
									 "                \n" + 
									 "                \n" + 
									 "                \n" +
									 "*P*P*P*P*P*P*P*P\n" +
									 "*R*N*B*Q*K*B*N*R\n");
	}
	
	
	@Test
	public void testWhiteCheckmate() throws FileNotFoundException {
		Chess chess = new Chess();
		
		TestUtitlities.performRecordMoves(chess, "sampleGames/White_Checkmate.txt");
		
		assertTrue(chess.getRecords().hasEnd());
		
		assertEquals(1, chess.getRecords().getEndGame().getResult());
	}
	
	
	
}
