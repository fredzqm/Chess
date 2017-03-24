package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class provides the sprite for displaying the image
 * 
 * @author zhang
 *
 */
public class SpriteProvider implements ISpriteProvider {
	private static final Color TRANSPARENT_COLOR = new Color(200, 200, 200);
	private BufferedImage spriteSheet;

	public SpriteProvider(String file) {
		this.spriteSheet = loadImage(file);
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

	/**
	 * get this part of the image
	 * 
	 * @param xGrid
	 * @param yGrid
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage imageAt(int xGrid, int yGrid, int width, int height) {
		if (xGrid < 0)
			throw new IndexOutOfBoundsException("xGrid cannot be negative");
		if (yGrid < 0)
			throw new IndexOutOfBoundsException("yGrid cannot be negative");
		if (xGrid + width > spriteSheet.getWidth())
			throw new IndexOutOfBoundsException("xGrid exceed the width");
		if (yGrid + height > spriteSheet.getHeight())
			throw new IndexOutOfBoundsException("yGrid exceed the height");
		return spriteSheet.getSubimage(xGrid, yGrid, width, height);
	}


}
