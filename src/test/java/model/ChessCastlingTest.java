package model;

import java.io.FileNotFoundException;

import org.junit.Test;

import utility.TestUtility;

public class ChessCastlingTest {

	@Test
	public void testInitalBoardLayout() throws FileNotFoundException {
		Chess chess = new Chess();
		TestUtility.assertBoardFile(chess, "sampleBoards/start.txt");
	}

	@Test
	public void testShortCastlingSuccess() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtility.performRecordMoves(chess, "sampleGames/White_Short_Castling_success.txt");
		TestUtility.assertBoardFile(chess, "sampleBoards/White_Short_Castling_success.txt");
	}

	@Test(expected=InvalidMoveException.class)
	public void testShortCastlingKingMoved() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtility.performRecordMoves(chess, "sampleGames/White_Short_Castling_moved_king.txt");
	}
}
