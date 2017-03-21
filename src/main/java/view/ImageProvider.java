package view;

import java.awt.Color;
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

public class ImageProvider implements ChessSymbolProvider {
	private static final Color TRANSPARENT_COLOR = new Color(200, 200, 200);
	private int width;
	private int height;
	private BufferedImage spriteSheet;

	private int maxWidth;
	private int maxHeight;

	public ImageProvider(String file) {
		this.width = SquareLabel.SQUARE_WIDTH;
		this.height = SquareLabel.SQUARE_WIDTH;
		this.spriteSheet = loadImage(file);

		maxWidth = spriteSheet.getWidth() / width;
		maxHeight = spriteSheet.getHeight() / height;

	}

	private BufferedImage loadImage(String file) {
		BufferedImage rawImage;
		try {
			rawImage = ImageIO.read(new File(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ImageProducer ip = createTransparentPart(rawImage, TRANSPARENT_COLOR);
		return toBufferedImage(Toolkit.getDefaultToolkit().createImage(ip));
	}

	private ImageProducer createTransparentPart(BufferedImage rawImage, Color transparent) {
		int markerRGB = transparent.getRGB() | 0xFF000000;
		ImageProducer ip = new FilteredImageSource(rawImage.getSource(), new RGBImageFilter() {
			@Override
			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					return 0x00FFFFFF & rgb; // transparent
				} else {
					return rgb;
				}
			}
		});
		return ip;
	}

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	private static BufferedImage toBufferedImage(Image img) {
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
			color = 0;
		else
			color = 67;
		switch (type) {
		case Pawn:
			return imageAt(333, color);
		case Rook:
			return imageAt(268, color);
		case Bishop:
			return imageAt(135, color);
		case Knight:
			return imageAt(201, color);
		case Queen:
			return imageAt(67, color);
		case King:
			return imageAt(0, color);
		default:
			return null;
		}
	}

}
