package model;

public enum MoveNote {
	NONE("" , ""),
	CHECK("+" , " Check!"),
	CHECKMATE("++" , " CheckMate!!"),
	GOOD("!" , "good move!"),
	BAD("?" , "bad move");

	String doc;
	String descript;
	
	private MoveNote(String docEnd , String descriptEnd) {
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
