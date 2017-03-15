package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import model.Chess;
import model.InvalidMoveException;
import model.Move;
import utility.LoadMoves;

public class ChessGameTest {

	@Test
	public void testWhiteCheckmate() throws FileNotFoundException {
		Chess chess = new Chess();
		
		assertTrue(LoadMoves.performRecordMoves(chess, "sampleGames/White_Checkmate.txt"));
		
		assertTrue(chess.getRecords().hasEnd());
		
		assertEquals(1, chess.getRecords().getEndGame().getResult());
	}
	
	@Test
	public void testBlackCheckmate() throws FileNotFoundException {
		Chess chess = new Chess();
		
		assertTrue(LoadMoves.performRecordMoves(chess, "sampleGames/Black_Checkmate.txt"));
		
		assertTrue(chess.getRecords().hasEnd());
		
		assertEquals(-1, chess.getRecords().getEndGame().getResult());
	}
}
