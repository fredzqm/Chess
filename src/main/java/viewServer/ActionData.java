package viewServer;

public class ActionData {
	public ClickData click;
	public boolean requestDraw, resign;

	public ActionData() {
	}
	
	public static class ClickData {
		public long file, rank;
		
		public ClickData() {
		}
	}
}
