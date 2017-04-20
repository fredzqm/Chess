package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovePatternMatcherTest {

	@Test
	public void testGetPieceType() {
		MovePatternMatcher queen = new MovePatternMatcher("Qb1-b2");
		MovePatternMatcher bishop = new MovePatternMatcher("Ba3-h5");
		MovePatternMatcher empty = new MovePatternMatcher("a1-a8");
		
		assertTrue(queen.matches());
		assertTrue(bishop.matches());
		assertTrue(empty.matches());
		
		assertEquals("Q", queen.getGroup(1));
		assertEquals("B", bishop.getGroup(1));
		assertEquals(null, empty.getGroup(1));
	}
	
	@Test
	public void testStartLoc() {
		MovePatternMatcher queen = new MovePatternMatcher("Qb1-b2");
		MovePatternMatcher bishop = new MovePatternMatcher("Ba3-h5");
		MovePatternMatcher empty = new MovePatternMatcher("a1-a8");
		
		assertTrue(queen.matches());
		assertTrue(bishop.matches());
		assertTrue(empty.matches());
		
		assertEquals("b1", queen.getGroup(2) + queen.getGroup(3));
		assertEquals("a3", bishop.getGroup(2) + bishop.getGroup(3));
		assertEquals("a1", empty.getGroup(2) + empty.getGroup(3));
	}
	
	@Test
	public void testEndLoc() {
		MovePatternMatcher queen = new MovePatternMatcher("Qb1-b2");
		MovePatternMatcher bishop = new MovePatternMatcher("Ba3-h5");
		MovePatternMatcher empty = new MovePatternMatcher("a1-a8");
		
		assertTrue(queen.matches());
		assertTrue(bishop.matches());
		assertTrue(empty.matches());
		
		assertEquals("b2", queen.getGroup(5));
		assertEquals("h5", bishop.getGroup(5));
		assertEquals("a8", empty.getGroup(5));
	}
	
	@Test
	public void testNoEndLoc() {
		MovePatternMatcher match = new MovePatternMatcher("Q");
		
		assertFalse(match.matches());
	}
	
	@Test
	public void testNoStartLoc() {
		MovePatternMatcher match = new MovePatternMatcher("Qb5");
		
		assertTrue(match.matches());
	}
	
	@Test
	public void testPromotionMove() {
		MovePatternMatcher match = new MovePatternMatcher("a8=Q");
		
		assertTrue(match.matches());
	}

}
