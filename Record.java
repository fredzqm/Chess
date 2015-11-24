import java.util.ArrayList;
import java.util.Iterator;

public class Record implements Iterable<Move> {
	ArrayList<Move> list;
	
	public Record() {
		list = new ArrayList<>();
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

	
	public Move lastMove() {
		if (isEmpty())
			return null ;
		return get(size() - 1) ;
	}

//	public String lastOutPrint() {
//		if (isEmpty())
//			return "Hasn't start the chess yet!" ;
//		return get(size() - 1).outPrint();
//	}
//
//	public String lastDescript() {
//		if (isEmpty())
//			return "Hasn't start the chess yet!" ;
//		return get(size() - 1).getDescript();
//	}

	public boolean draw(String descript) {
		if (isEmpty())
			return false;
		get(size() - 1).draw(descript);
		return true;
	}

	public boolean win(boolean who, String descrpt) {
		if (isEmpty())
			return false;
		get(size() - 1).win(who , descrpt);
		return true;
	}



}
