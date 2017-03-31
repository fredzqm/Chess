package model;

import java.io.FileNotFoundException;

import org.junit.Test;

import utility.TestUtility;

public class PawnPromotionTest {

	@Test
	public void testQueenPromotion() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtility.performRecordMoves(chess, "sampleGames/Pawn_promotion_1.txt");
		TestUtility.assertBoardFile(chess, "sampleBoards/Pawn_promotion_board_1.txt");
	}

	@Test
	public void testKnightPromotion() throws FileNotFoundException, InvalidMoveException {
		Chess chess = new Chess();

		TestUtility.performRecordMoves(chess, "sampleGames/Knight_promotion_1.txt");
		TestUtility.assertBoardFile(chess, "sampleBoards/Knight_promotion_board_1.txt");
	}
}
