package model;

public class MovePatternMatcher implements IMovePatternMatcher {
	//private static Pattern pattern =
	//		Pattern.compile("([PRNBQK])?([a-h])?([1-8])?([-x])?([a-h][1-8])(=[RNBQ])?(.*)");
	
	//private Matcher match;
	
	public MovePatternMatcher(String moveCommand) {
		//this.match = pattern.matcher(moveCommand);
	}
	
	public String getGroup(int i) {
		return "";
		//return match.group(i);
	}
	
	public boolean matches() {
		return false;
		//return match.matches();
	}
}
