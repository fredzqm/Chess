package model;

import static org.mockito.Mockito.spy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Piece.Player;

public class PieceTest {
	Square pieceSquare;
	Player player;
	Chess chess;
	ArrayList<Square> squares;
	Square square1;
	Square square2;
	Square square3;
	
	@Before
	public void setUpBoard() {
		player = Player.WHITE;
		pieceSquare = mock(Square.class);
		chess = mock(Chess.class);
		squares = new ArrayList<Square>();
		square1 = mock(Square.class);
		square2 = mock(Square.class);
		square3 = mock(Square.class);
		
		squares.add(square1);
		squares.add(square2);
		squares.add(square3);
		
		// Stub our Chess.getReachableSquares
		when(chess.getAllSquares()).thenReturn(squares);
	}
	
	@Test
	public void testGetNoReachableSquares() {
		Piece piece = new Pawn(player, pieceSquare, chess);
		Piece spy = spy(piece);
		
		// Stub out canGo()
		doReturn(null).when(spy).getMove(any());
		
		assertTrue(spy.getReachableSquares().isEmpty());
	}
	
	@Test
	public void testGetOneReachableSquare() {
		Move move = mock(Move.class);
		
		Piece piece = new Pawn(player, pieceSquare, chess);
		Piece spy = spy(piece);
		
		// Stub out canGo()
		doReturn(null).when(spy).getMove(square1);
		doReturn(move).when(spy).getMove(square2);
		doReturn(null).when(spy).getMove(square3);
		
		assertTrue(spy.getReachableSquares().contains(square2));
		assertEquals(1, spy.getReachableSquares().size());
	}
	
	@Test
	public void testGetAllReachableSquare() {
		Move move = mock(Move.class);
		
		Piece piece = new Pawn(player, pieceSquare, chess);
		Piece spy = spy(piece);
		
		// Stub out canGo()
		doReturn(move).when(spy).getMove(square1);
		doReturn(move).when(spy).getMove(square2);
		doReturn(move).when(spy).getMove(square3);
		
		assertEquals(3, spy.getReachableSquares().size());
	}
}
