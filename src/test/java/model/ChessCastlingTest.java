package model;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import utility.TestUtitlities;

public class ChessCastlingTest {
	
	@Test
	public void testInitalBoardLayout() throws FileNotFoundException {
		Chess chess = new Chess();
		TestUtitlities.assertBoardFile(chess, "sampleBoards/start.txt");
	}
	
	
	@Test
	public void testWhiteShortCastling() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();
		
		List<String> moveStrLs = TestUtitlities.getMoveString("sampleGames/White_Short_Castling1.txt");
		for (String moveStr : moveStrLs) {
			System.out.println(moveStr);
			Move move = chess.getMove(moveStr);
			chess.makeMove(move);
			System.out.println(chess);
		}
		
		TestUtitlities.assertBoardFile(chess, "sampleBoards/White_Short_Castling_Board.txt");
	}
	
}
