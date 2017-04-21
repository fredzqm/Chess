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
				Square squareGot = chess.getBoard().getSquare("" +a + b);
				
				assertEquals(square, squareGot);
			}
		}
	}
	
	@Test
	public void testNotImpossibleCheckMate() {
		Chess chess = new Chess();
		
		assertFalse(chess.impossibleCheckMate());
	}
	
	@Test
	public void testImpossibleCheckMateKingVKing() {
		Chess chess = new Chess();
		
		// Remove all but the King
		chess.white.removeIf(p -> {
			return !(p instanceof King);
		});
		chess.black.removeIf(p -> {
			return !(p instanceof King);
		});

		assertTrue(chess.impossibleCheckMate());
	}
	
	@Test
	public void testImpossibleCheckMateKingVKingAndBishop() {
		Chess chess = new Chess();
		
		// Remove all but the King
		chess.white.removeIf(p -> {
			return !(p instanceof King);
		});
		
		// Remove all but the King and one Bishop
		boolean first = true;
		for(int i = 0; i < chess.black.size(); i++) {
			Piece p = chess.black.get(i);
			if(!(p instanceof King)) {
				if(p instanceof Bishop) {
					if(first) {
						chess.black.remove(p);
						i--;
						first = false;
					}
				} else {
					chess.black.remove(p);
					i--;
				}
			}
		}

		assertTrue(chess.impossibleCheckMate());
	}
	
	
	@Test
	public void testImpossibleCheckMateKingVKingAndKnight() {
		Chess chess = new Chess();
		
		// Remove all but the King and one Knight
		boolean first = true;
		for(int i = 0; i < chess.white.size(); i++) {
			Piece p = chess.white.get(i);
			if(!(p instanceof King)) {
				if(p instanceof Knight) {
					if(first) {
						chess.white.remove(p);
						i--;
						first = false;
					}
				} else {
					chess.white.remove(p);
					i--;
				}
			}
		}
		
		// Remove all but the King
		chess.black.removeIf(p -> {
			return !(p instanceof King);
		});

		assertTrue(chess.impossibleCheckMate());
	}
}
