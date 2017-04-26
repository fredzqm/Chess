package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The magical class that keeps track of move history It tracks a list of played
 * in the chess, and can be used to query the history or undo steps
 * 
 * @author zhang
 *
 */
public class Record implements Iterable<Move> {
	private ArrayList<Move> list;
	private EndGame endgame;

	public Record() {
		list = new ArrayList<>();
		endgame = null;
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	/**
	 * add a move to the record
	 * 
	 * @param move
	 */
	public void add(Move move) {
		list.add(move);
	}

	/**
	 * remove the last move in the record. Used mostly for undoing steps
	 */
	public void removeLast() {
		list.remove(size() - 1);
	}

	/**
	 * Time tracks which move. A move at time happened at (time/2 + 1) round.
	 * white if even, black if odd
	 * 
	 * @param time
	 *            time represents the order of move
	 * @return the move at this time
	 */
	public Move get(int time) {
		return list.get(time);
	}

	@Override
	public Iterator<Move> iterator() {
		return list.iterator();
	}

	/**
	 * 
	 * @return the last move
	 */
	public Move getLastMove() {
		if (isEmpty())
			return null;
		return get(size() - 1);
	}

	/**
	 * 
	 * @return true if the game has already ended
	 */
	public boolean hasEnd() {
		return endgame != null;
	}

	/**
	 * specify that this game ended with certain end game situation
	 * 
	 * @param endgame
	 */
	public void endGame(EndGame endgame) {
		this.endgame = endgame;
	}

	/**
	 * 
	 * @param original
	 *            the original position of the piece
	 * @param type
	 *            the type of the piece originally at this square
	 * @return true if the original piece has ever moved or be taken since the
	 *         game started.
	 */
	public boolean hasMoved(Square original, Class<? extends Piece> type, int time) {
		if (!original.isOccupied() || !original.getPiece().isType(type))
			return true;
		for (int t = 0; t < time; t++) {
			if (original.equals(get(t).getStart()))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @return the documentation for all the records
	 */
	public String printDoc() {
		StringBuilder sb = new StringBuilder();
		int round = 1;
		for (Move r : list) {
			if (r.isWhite) {
				sb.append(round + ". " + r.getDoc());
			} else {
				round++;
				sb.append("   " + r.getDoc() + "\n");
			}
		}
		if (hasEnd()) {
			sb.append("\n" + endgame.getDoc());
		} else {
			Move last = getLastMove();
			if (last != null)
				if (last.isWhite)
					sb.append("   ...");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return the description for the end game
	 */
	public String getEndGameDescript() {
		return endgame.getDescript();
	}
	
	public EndGame getEndGame() {
		return endgame;
	}

}
