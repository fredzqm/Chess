package model;

import java.io.FileNotFoundException;

import org.junit.Test;

import utility.TestUtitlities;

public class ChessCastlingTest {
	
	@Test
	public void testInitalBoardLayout() throws FileNotFoundException {
		Chess chess = new Chess();
		TestUtitlities.assertBoard(chess, "sampleBoards/start.txt");
	}
	
	
	@Test
	public void testWhiteShortCastling() throws FileNotFoundException {
		Chess chess = new Chess();
		
		TestUtitlities.performRecordMoves(chess, "sampleGames/White_Short_Castling1.txt");
		
		System.out.println(chess);
		
		TestUtitlities.assertBoard(chess, "sampleBoards/White_Short_Castling_Board.txt");
	}
	
}
