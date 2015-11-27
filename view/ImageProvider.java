package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ImageProvider implements ChessSymbolProvider {

	private int width;
	private int height;
	private BufferedImage spriteSheet;

	private int maxWidth;
	private int maxHeight;

	public ImageProvider(String file) {
		this.width = SquareLabel.SQUARE_WIDTH;
		this.height = SquareLabel.SQUARE_WIDTH;
		spriteSheet = null;

		try {
			spriteSheet = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageFilter filter = new RGBImageFilter() {
			// the color we are looking for... Alpha bits are set to opaque
			public int markerRGB = (new Color(200,200,200)).getRGB() | 0xFF000000;

			@Override
			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				} else {
					// nothing to do
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(spriteSheet.getSource(), filter);
		Image img = Toolkit.getDefaultToolkit().createImage(ip);
		spriteSheet = toBufferedImage(img);

		maxWidth = spriteSheet.getWidth() / width;
		maxHeight = spriteSheet.getHeight() / height;

	}

	public BufferedImage imageAt(int xGrid, int yGrid) {
		if (xGrid < spriteSheet.getWidth() && yGrid < spriteSheet.getHeight()) {
			BufferedImage img = spriteSheet.getSubimage(xGrid, yGrid, width, height);
			return img;

		}
		return null;
	}

	@Override
	public BufferedImage getSymbol(ChessPieceType type, boolean whiteOrBlack) {
		int color;
		if (whiteOrBlack)
			color = 60;
		else
			color = 0;
		switch (type) {
		case Pawn:
			return imageAt(313, color);
		case Rook:
			return imageAt(127, color);
		case Bishop:
			return imageAt(190, color);
		case Knight:
			return imageAt(252, color);
		case Queen:
			return imageAt(65, color);
		case King:
			return imageAt(5, color);
		}
		return null;
	}

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

}
