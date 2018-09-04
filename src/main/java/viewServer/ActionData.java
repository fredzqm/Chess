package viewServer;

public class ActionData {
	public ClickData click;
	public boolean requestDraw, resign, agreeDraw;
	public String promotionTo;

	public ActionData() {
	}
	
	public static class ClickData {
		public long i, j;
		
		public ClickData() {
		}
	}
}
