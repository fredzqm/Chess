package view;


public interface IChessViewerControl {

	void handleCommand(String input, boolean isWhiteView);

	void click(SquareLabel squareLabel, boolean isWhite);

}
