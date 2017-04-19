package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovePatternMatcher implements IMovePatternMatcher {
	private static Pattern pattern =
			Pattern.compile("([PRNBQK])?([a-h])?([1-8])?([-x])?([a-h][1-8])(=[RNBQ])?(.*)");
	
	private Matcher match;
	
	public MovePatternMatcher(String moveCommand) {
		this.match = pattern.matcher(moveCommand);
	}
	
	public String getGroup(int i) {
		return match.group(i);
	}
	
	public boolean matches() {
		return match.matches();
	}
}
