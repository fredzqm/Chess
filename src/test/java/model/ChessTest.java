package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessTest {

	@Test
	public void testSpotAt() {
		Chess chess = new Chess();

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Square square = chess.spotAt(i, j);
				char a = (char) (i - 1 + 'a');
				char b = (char) (j - 1 + '1');
				
				assertEquals("" + a + b, square.toString());
			}
		}
	}


	@Test
	public void testgetSquare() {
		Chess chess = new Chess();

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Square square = chess.spotAt(i, j);
				char a = (char) (i - 1 + 'a');
				char b = (char) (j - 1 + '1');
				Square squareGot = chess.getSquare("" +a + b);
				
				assertEquals(square, squareGot);
			}
		}
	}
}
