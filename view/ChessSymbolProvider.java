package view;

import java.awt.image.BufferedImage;

public interface ChessSymbolProvider {
	
	public BufferedImage getSymbol(ChessPieceType type, boolean whiteOrBlack);
	
}
