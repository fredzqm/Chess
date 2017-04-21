package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BishopTest {

	@Test
	public void testBothBlackBishops() {
		Chess chess = new Chess();
		
		ArrayList<Bishop> list = new ArrayList<Bishop>();
		for(int i = 0; i < chess.black.size(); i++) {
			Piece p = chess.black.get(i);
			if(p instanceof Bishop) {
				list.add((Bishop) p);
			}
		}
		
		assertNotEquals(list.get(0).getBishopType(), list.get(1).getBishopType());
	}
	
	@Test
	public void testBothWhiteBishops() {
		Chess chess = new Chess();
		
		ArrayList<Bishop> list = new ArrayList<Bishop>();
		for(int i = 0; i < chess.white.size(); i++) {
			Piece p = chess.white.get(i);
			if(p instanceof Bishop) {
				list.add((Bishop) p);
			}
		}
		
		assertNotEquals(list.get(0).getBishopType(), list.get(1).getBishopType());
	}

}
