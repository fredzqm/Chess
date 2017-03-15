package model;

import java.io.FileNotFoundException;

import org.junit.Test;

import utility.TestUtitlities;

public class ChessCastlingTest {

	@Test
	public void testInitalBoardLayout() throws FileNotFoundException {
		Chess chess = new Chess();
		TestUtitlities.assertBoardFile(chess, "sampleBoards/start.txt");
	}

	@Test
	public void testShortCastlingSuccess() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtitlities.performRecordMoves(chess, "sampleGames/White_Short_Castling_success.txt");
		TestUtitlities.assertBoardFile(chess, "sampleBoards/White_Short_Castling_success.txt");
	}

	@Test(expected=InvalidMoveException.class)
	public void testShortCastlingKingMoved() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtitlities.performRecordMoves(chess, "sampleGames/White_Short_Castling_moved_king.txt");
	}
}
