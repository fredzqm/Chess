package viewServer;

public class GameData {
	public boolean isWhiteTurn;
	public BoardData whiteBoard, blackBoard;
	public ActionData white, black;
	public String roomID;

	public GameData() {
	}

	public static GameData newInstance(String roomID) {
		GameData gameData = new GameData();
		gameData.roomID = roomID;
		gameData.isWhiteTurn = true;
		gameData.whiteBoard = BoardData.newInstance();
		gameData.blackBoard = BoardData.newInstance();
		return gameData;
	}

}
