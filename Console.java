import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

/**
 * the console to make output
 * 
 * @author zhangq2
 *
 */
@SuppressWarnings("serial")
public class Console extends JTextArea {
	String existence;
	String temp;

	/**
	 * creates a new console for this game
	 * 
	 * @param myChess
	 */
	public Console(Chess myChess) {
		super(
				"Welcome to little Chess Game. Enter \"help\" for instructions.\n",
				130 / Main.CONSOLE_FONT_SIZE,
				1000 / Main.CONSOLE_FONT_SIZE);
		addKeyListener(new InputFromConsole(myChess));
		existence = getText();
		temp = "";
	}

	/**
	 * print out the outPut in the console.
	 * 
	 * @param outPut
	 */
	public void printOut(String outPut) {
		existence = existence + temp;
		temp = "";
		setText(existence + outPut + "\n");
		existence = getText();
	}

	/**
	 * print out the temporal string in the console. This string can be erase
	 * later.
	 * 
	 * @param s
	 */
	public void printTemp(String s) {
		setText(existence + s + "\n");
		temp = s;
	}

	/**
	 * earse the temporal string in the console
	 */
	public void cleanTemp() {
		temp = "";
	}

	/**
	 * the KeyListener for ths console class. It calls
	 * chess.handleCommand(String s) in Chess to process data, and printout the
	 * feedback
	 * 
	 * @author zhangq2
	 *
	 */
	private class InputFromConsole implements KeyListener {
		Chess myChess;

		public InputFromConsole(Chess c) {
			myChess = c;
			existence = getText();
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				String input = getText().substring(existence.length());
				if (input.length() > 0) {
					input = input.substring(0, input.length() - 1);
					String feedback = Main.handleCommand(input, myChess);
					existence = getText();
					printOut(feedback);
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

}