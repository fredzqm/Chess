package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class SquareTest {
	private Square square;

	@Before
	public void setUp() throws Exception {
		square = new Square(1, 1);
	}

	@Test
	public void testToString() {
		String expectedPosition = "b7";
		assertEquals(expectedPosition, square.toString());
	}

	@Test
	public void testGetX() {
		assertEquals(2, square.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(7, square.getY());
	}

	@Test
	public void testIsNotOccupied() {
		assertFalse(square.isOccupied());
	}

	@Test
	public void testSetOccupied() {
		Piece piece = mock(Piece.class);
		square.setOccupied(piece);
		assertTrue(square.isOccupied());
	}

	@Test
	public void testGetPiece() {
		Piece piece = mock(Piece.class);
		square.setOccupied(piece);
		assertEquals(piece, square.getPiece());
	}
	
	@Test
	public void testOccupiedBy() {
		Piece piece = mock(Piece.class);
		when(piece.getWhiteOrBlack()).thenReturn(true);
		square.setOccupied(piece);
		assertTrue(square.occupiedBy(true));
		assertFalse(square.occupiedBy(false));
	}
	
}