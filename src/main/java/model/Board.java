package model;

import java.util.Iterator;

import model.Piece.Player;

/**
 * the class to represent the board of the chess
 * 
 * @author zhang
 *
 */
public class Board implements Iterable<Square> {
	private Square[][] spots;

	/**
	 * create a standard chess board
	 */
	public Board() {
		spots = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square t = new Square(i, j);
				spots[i][j] = t;
			}
		}
	}

	/**
	 * s needs to be a string of length 2, like a3, e8.
	 * 
	 * @param s
	 *            the name of the square
	 * @return the position of the square, null if fail to find a corresponding
	 *         square
	 */
	public Square getSquare(String s) {
		if (s.length() != 2)
			return null;
		char x = s.charAt(0);
		char y = s.charAt(1);
		if (x < 'a' || x > 'h' || y < '1' || y > '8')
			return null;
		return spots[(int) (x - 'a')][7 - (int) (y - '1')];
	}

	/**
	 * 
	 * @param x
	 *            the file of the square
	 * @param y
	 *            the rank of the square
	 * @return the position of the square
	 */
	public Square spotAt(int x, int y) {
		return spots[x - 1][8 - y];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 8; i >= 1; i--) {
			for (int j = 1; j <= 8; j++) {
				Square square = spotAt(j, i);
				Piece piece = square.getPiece();
				if (piece == null) {
					sb.append("  ");
				} else {
					if (piece.getWhiteOrBlack() == Player.WHITE) {
						sb.append('*');
					} else {
						sb.append(' ');
					}
					sb.append(piece.getType());
				}
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	public Iterator<Square> iterator() {
		return new BoardIterator();
	}

	private class BoardIterator implements Iterator<Square> {
		private int i = -1;

		@Override
		public boolean hasNext() {
			return i != 63;
		}

		@Override
		public Square next() {
			i++;
			return spots[i % 8][i / 8];
		}

	}
}
