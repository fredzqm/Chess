package model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;

import model.Piece.Player;

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
		when(piece.getWhiteOrBlack()).thenReturn(Player.WHITE);
		square.setOccupied(piece);
		assertTrue(square.occupiedBy(Player.WHITE));
		assertFalse(square.occupiedBy(Player.BLACK));
	}
	
}
