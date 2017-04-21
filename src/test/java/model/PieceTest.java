package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Piece.Player;

public class PieceTest {
	Square pieceSquare;
	Player player;
	Chess chess;
	Board board;
	Square square1;
	Square square2;
	Square square3;

	@Before
	public void setUpBoard() {
		player = Player.WHITE;
		pieceSquare = mock(Square.class);
		chess = mock(Chess.class);
		square1 = mock(Square.class);
		square2 = mock(Square.class);
		square3 = mock(Square.class);

		ArrayList<Square> squares = new ArrayList<>();
		squares.add(square1);
		squares.add(square2);
		squares.add(square3);

		// Stub our Chess.getReachableSquares
		board = mock(Board.class);
		when(board.iterator()).thenReturn(squares.iterator());
		when(chess.getBoard()).thenReturn(board);
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

		ArrayList<Square> reachableSquares = spy.getReachableSquares();
		assertTrue(reachableSquares.contains(square2));
		assertEquals(1, reachableSquares.size());
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
