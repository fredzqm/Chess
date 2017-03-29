package model;

import java.io.FileNotFoundException;

import org.junit.Test;

import utility.TestUtility;

public class PawnPromotionTest {

	@Test
	public void testShortCastlingSuccess() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtility.performRecordMoves(chess, "sampleGames/Pawn_promotion_1.txt");
		TestUtility.assertBoardFile(chess, "sampleBoards/Pawn_promotion_board_1.txt");
	}

	@Test(expected=InvalidMoveException.class)
	public void testShortCastlingKingMoved() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtility.performRecordMoves(chess, "sampleGames/White_Short_Castling_moved_king.txt");
	}
}
