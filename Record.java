import java.util.ArrayList;
import java.util.Iterator;

public class Record implements Iterable<Move> {
	private ArrayList<Move> list;
	private boolean hasEnd;
	
	public Record() {
		list = new ArrayList<>();
		hasEnd = false;
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

	public ArrayList<Move> getArrayList() {
		return list;
	}

	public void add(Move move) {
		list.add(move);
	}

	public Move get(int i) {
		return list.get(i);
	}

	@Override
	public Iterator<Move> iterator() {
		return list.iterator();
	}

	public Move remove(int i) {
		return list.remove(i);
	}

	public void removeLast(){
		list.remove(size() - 1);
	}
	
	public Move lastMove() {
		if (isEmpty())
			return null ;
		return get(size() - 1) ;
	}
	
	public boolean hasEnd() {
		return hasEnd;
	}


	public void endGame(EndGame endgame){
		hasEnd = true;
		lastMove().endGame(endgame);
	}
	
	/**
	 * 
	 * @param original
	 *            the original positon of the piece
	 * @param type
	 *            the type of the piece originally at this square
	 * @return true if the original piece has ever moved or be taken since the
	 *         game started.
	 */
	public boolean hasMoved(Square original, Class<? extends Piece> type, int time) { 
		if (!original.occupied() || !original.getPiece().isType(type))
			return true;
		for (int t = 0 ; t < time ; t ++) {
			if (original.equals(get(t).getStart()))
				return true;
		}
		return false;
	}

}
