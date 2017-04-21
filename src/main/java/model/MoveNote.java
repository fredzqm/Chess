package model;

public class MoveNote {
	public static final MoveNote NONE = new MoveNote("", "");
	public static final MoveNote CHECK = new MoveNote("+", " Check!");
	public static final MoveNote CHECKMATE = new MoveNote("++", " CheckMate!!");
	public static final MoveNote GOOD = new MoveNote("!", "good move!");
	public static final MoveNote BAD = new MoveNote("?", "bad move");

	String doc;
	String descript;

	private MoveNote(String docEnd, String descriptEnd) {
		doc = docEnd;
		descript = descriptEnd;
	}

	public String getDocEnd() {
		return doc;
	}

	public String getDescriptEnd() {
		return descript;
	}
}
