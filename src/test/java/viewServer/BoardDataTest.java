package viewServer;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BoardDataTest {
	private BoardData board;
	
	@Before
	public void setupBoardData() {
		board = new BoardData();
		
		// Check each piece
		for(PieceData p : board.pieces) {
				assertEquals(null, p.getType());
		}
	}
	
	@Test
	public void testSetupBoardData() { }
	
	@Test
	public void testHighlight11() {
		board.highLight(1, 1);
		
		assertTrue(board.pieces.get(1*8+1).isHightLight());
	}
	
	@Test
	public void testHighlight77() {
		board.highLight(7, 7);
		
		assertTrue(board.pieces.get(7*8+7).isHightLight());
	}
	
	@Test
	public void testHighlight57() {
		board.highLight(5, 7);
		
		assertTrue(board.pieces.get(5*8+7).isHightLight());
	}
	
	@Test
	public void testDeHighLightWholeBoard() {
		board.highLight(0, 0);
		board.highLight(2, 5);
		board.highLight(7, 7);
		
		board.deHighLightWholeBoard();
		
		for(PieceData p : board.pieces) {
			assertFalse(p.isHightLight());
		}
	}
	
	@Test
	public void testUpdatePiece11() {
		board.updatePiece(1, 1, 'K', false);
		
		assertEquals("K", board.pieces.get(1*8+1).getType());
		assertEquals(false, board.pieces.get(1*8+1).isWhite());
	}
	
	@Test
	public void testUpdatePiece57() {
		board.updatePiece(5, 7, 'N', true);
		
		assertEquals("N", board.pieces.get(5*8+7).getType());
		assertEquals(true, board.pieces.get(5*8+7).isWhite());
	}

}
