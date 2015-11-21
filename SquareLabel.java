import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class SquareLabel extends JLabel{
	
	/**
	 * A class that represents the squares on the chess board.
	 * 
	 * It is also the Jlabel that actually appears in the Frame.
	 * 
	 * @author zhangq2
	 *
	 */
	static final int MWIDTH = 50;

		private static final Color HIGHLIGHT_COLOR = Color.yellow;
		private static final Color TEXT_COLOR_BALCK = Color.black;
		private static final Color TEXT_COLOR_WHITE = Color.red;

		private Color originalColor;
		boolean highLight;

		public SquareLabel() {
			super("", JLabel.CENTER);
			setPreferredSize(new Dimension(MWIDTH, MWIDTH));
		}

		/**
		 * 
		 * @param i
		 *            file of this spot
		 * @param j
		 *            rank of this square
		 * @param chess
		 */
		public SquareLabel(int i, int j, ChessGameViewerSource chess) {
			this();
			if ((i + j) % 2 != 0)
				originalColor = Color.gray;
			else
				originalColor = Color.white;
			setBackground(originalColor);
			
			setBorder(BorderFactory.createLineBorder(Color.black, 1));
			setOpaque(true);
			addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseClicked(MouseEvent arg0) {
						chess.click(i , j);
					}
			});
		}

		// ------------------------------------------------------------------------------------------------------------------
		// accessors
		

		// ------------------------------------------------------------------------------------------------------------------
		// modifier

		/**
		 * hight light this spot and set the back ground color to highlight color.
		 */
		public void highLight() {
			highLight = true;
			setBackground(HIGHLIGHT_COLOR);
		}

		public void deHighLight() {
			highLight = false;
			setBackground(originalColor);
		}


	
}
