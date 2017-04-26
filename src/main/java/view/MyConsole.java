package view;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;

public class MyConsole extends JTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int CONSOLE_FONT_SIZE = 20;
	private static final Font FONT_CONSOLE = new Font("Serif", Font.PLAIN, CONSOLE_FONT_SIZE);

	private String existence;
	private IChessViewerControl viewControl;
	private boolean isWhiteView;

	public MyConsole(IChessViewerControl controller, boolean isWhiteView, String title, int height, int width) {
		super(title, height / CONSOLE_FONT_SIZE, width / CONSOLE_FONT_SIZE);
		this.viewControl = controller;
		this.isWhiteView = isWhiteView;
		this.setFont(FONT_CONSOLE);
		this.addKeyListener(new ConsoleListener());
		existence = this.getText();
	}

	public void printOut(String message) {
		existence = getText();
		if (message != null) {
			existence = existence + message + "\n";
			setText(existence);
		}
	}

	public void printTemp(String temp) {
		setText(existence + temp);
	}

	public void cleanTemp() {
		setText(existence);
	}

	class ConsoleListener extends KeyAdapter {
		String input;

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				String text = getText();
				if (existence.length() < text.length()) {
					input = text.substring(existence.length(), text.length() - 1);
					if (input.length() > 0) {
						viewControl.handleCommand(input, isWhiteView);
					}
				}
			}
		}

	}

}
