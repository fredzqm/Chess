/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { ISpriteProvider } from './ISpriteProvider'; 
import Color = java.awt.Color;

import Graphics2D = java.awt.Graphics2D;

import Image = java.awt.Image;

import Toolkit = java.awt.Toolkit;

import BufferedImage = java.awt.image.BufferedImage;

import FilteredImageSource = java.awt.image.FilteredImageSource;

import ImageProducer = java.awt.image.ImageProducer;

import RGBImageFilter = java.awt.image.RGBImageFilter;

import File = java.io.File;

import IOException = java.io.IOException;

import ImageIO = javax.imageio.ImageIO;

/**
 * This class provides the sprite for displaying the image
 * 
 * @author zhang
 */
export class SpriteProvider implements ISpriteProvider {
    static TRANSPARENT_COLOR : Color; public static TRANSPARENT_COLOR_$LI$() : Color { if(SpriteProvider.TRANSPARENT_COLOR == null) SpriteProvider.TRANSPARENT_COLOR = new Color(200, 200, 200); return SpriteProvider.TRANSPARENT_COLOR; };

    /*private*/ spriteSheet : BufferedImage;

    public constructor(file : string) {
        this.spriteSheet = null;
        this.spriteSheet = this.loadImage(file);
    }

    private loadImage(file : string) : BufferedImage {
        let rawImage : BufferedImage;
        try {
            rawImage = ImageIO.read(new File(file));
        } catch(e) {
            throw new Error(e);
        };
        let ip : ImageProducer = this.createTransparentPart(rawImage, SpriteProvider.TRANSPARENT_COLOR_$LI$());
        return SpriteProvider.toBufferedImage(Toolkit.getDefaultToolkit().createImage(ip));
    }

    private createTransparentPart(rawImage : BufferedImage, transparent : Color) : ImageProducer {
        let markerRGB : number = transparent.getRGB() | -16777216;
        let ip : ImageProducer = new FilteredImageSource(rawImage.getSource(), new SpriteProvider.SpriteProvider$0(this, markerRGB));
        return ip;
    }

    /**
     * Converts a given Image into a BufferedImage
     * 
     * @param {java.awt.Image} img
     * The Image to be converted
     * @return {java.awt.image.BufferedImage} The converted BufferedImage
     * @private
     */
    private static toBufferedImage(img : Image) : BufferedImage {
        if(img != null && img instanceof <any>BufferedImage) {
            return <BufferedImage>img;
        }
        let bimage : BufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        let bGr : Graphics2D = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    /**
     * get this part of the image
     * 
     * @param {number} xGrid
     * @param {number} yGrid
     * @param {number} width
     * @param {number} height
     * @return
     * @return {java.awt.image.BufferedImage}
     */
    public imageAt(xGrid : number, yGrid : number, width : number, height : number) : BufferedImage {
        if(xGrid < 0) throw new java.lang.IndexOutOfBoundsException("xGrid cannot be negative");
        if(yGrid < 0) throw new java.lang.IndexOutOfBoundsException("yGrid cannot be negative");
        if(xGrid + width > this.spriteSheet.getWidth()) throw new java.lang.IndexOutOfBoundsException("xGrid exceed the width");
        if(yGrid + height > this.spriteSheet.getHeight()) throw new java.lang.IndexOutOfBoundsException("yGrid exceed the height");
        return this.spriteSheet.getSubimage(xGrid, yGrid, width, height);
    }
}
SpriteProvider["__class"] = "view.SpriteProvider";
SpriteProvider["__interfaces"] = ["view.ISpriteProvider"];



export namespace SpriteProvider {

    export class SpriteProvider$0 extends RGBImageFilter {
        public __parent: any;
        public filterRGB(x : number, y : number, rgb : number) : number {
            if((rgb | -16777216) === this.markerRGB) {
                return 16777215 & rgb;
            } else {
                return rgb;
            }
        }

        constructor(__parent: any, private markerRGB: any) {
            super();
            this.__parent = __parent;
        }
    }
}




SpriteProvider.TRANSPARENT_COLOR_$LI$();
