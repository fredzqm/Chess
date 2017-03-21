package view;

import java.awt.image.BufferedImage;

public interface ISpriteProvider {
	
	public BufferedImage imageAt(int xGrid, int yGrid, int width, int height);
	
}
