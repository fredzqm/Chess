/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { ISpriteProvider } from './ISpriteProvider'; 
import { IChessViewerControl } from './IChessViewerControl'; 
import { ChessPieceType } from './ChessPieceType'; 
import Color = java.awt.Color;

import Dimension = java.awt.Dimension;

import Font = java.awt.Font;

import Graphics = java.awt.Graphics;

import Graphics2D = java.awt.Graphics2D;

import MouseAdapter = java.awt.event.MouseAdapter;

import MouseEvent = java.awt.event.MouseEvent;

import BufferedImage = java.awt.image.BufferedImage;

import BorderFactory = javax.swing.BorderFactory;

import JLabel = javax.swing.JLabel;

/**
 * 
 * @param {number} i
 * file of this spot
 * @param {number} j
 * rank of this square
 * @param {view.IChessViewerControl} chess
 * @param symbolProvider2
 * @param {boolean} whiteOrBlack
 * @param {view.ISpriteProvider} symbolProvider
 * @class
 */
export class SquareLabel extends JLabel {
    public static SQUARE_WIDTH : number = 65;

    public static FONT_PIECE : Font; public static FONT_PIECE_$LI$() : Font { if(SquareLabel.FONT_PIECE == null) SquareLabel.FONT_PIECE = new Font("Serif", Font.PLAIN, 40); return SquareLabel.FONT_PIECE; };

    public static HIGHLIGHT_COLOR : Color; public static HIGHLIGHT_COLOR_$LI$() : Color { if(SquareLabel.HIGHLIGHT_COLOR == null) SquareLabel.HIGHLIGHT_COLOR = Color.yellow; return SquareLabel.HIGHLIGHT_COLOR; };

    /*private*/ x : number;

    /*private*/ y : number;

    /*private*/ isWhite : boolean;

    /*private*/ originalColor : Color;

    /*private*/ __highLight : boolean;

    /*private*/ image : BufferedImage;

    /*private*/ symbolProvider : ISpriteProvider;

    public constructor(i? : any, j? : any, chess? : any, whiteOrBlack? : any, symbolProvider? : any) {
        if(((typeof i === 'number') || i === null) && ((typeof j === 'number') || j === null) && ((chess != null && (chess["__interfaces"] != null && chess["__interfaces"].indexOf("view.IChessViewerControl") >= 0 || chess.constructor != null && chess.constructor["__interfaces"] != null && chess.constructor["__interfaces"].indexOf("view.IChessViewerControl") >= 0)) || chess === null) && ((typeof whiteOrBlack === 'boolean') || whiteOrBlack === null) && ((symbolProvider != null && (symbolProvider["__interfaces"] != null && symbolProvider["__interfaces"].indexOf("view.ISpriteProvider") >= 0 || symbolProvider.constructor != null && symbolProvider.constructor["__interfaces"] != null && symbolProvider.constructor["__interfaces"].indexOf("view.ISpriteProvider") >= 0)) || symbolProvider === null)) {
            let __args = Array.prototype.slice.call(arguments);
            {
                let __args = Array.prototype.slice.call(arguments);
                super("", SwingConstants.CENTER);
                this.x = 0;
                this.y = 0;
                this.isWhite = false;
                this.originalColor = null;
                this.__highLight = false;
                this.image = null;
                this.symbolProvider = null;
                this.x = 0;
                this.y = 0;
                this.isWhite = false;
                this.originalColor = null;
                this.__highLight = false;
                this.image = null;
                this.symbolProvider = null;
                (() => {
                    this.setPreferredSize(new Dimension(SquareLabel.SQUARE_WIDTH, SquareLabel.SQUARE_WIDTH));
                    this.setFont(SquareLabel.FONT_PIECE_$LI$());
                })();
            }
            (() => {
                if(whiteOrBlack) {
                    this.x = i;
                    this.y = 8 - j;
                } else {
                    this.x = 9 - i;
                    this.y = 1 + j;
                }
                this.isWhite = whiteOrBlack;
                if((i + j) % 2 !== 0) this.originalColor = Color.white; else this.originalColor = Color.gray;
                this.symbolProvider = symbolProvider;
                this.setBackground(this.originalColor);
                this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                this.setOpaque(true);
                this.addMouseListener(new SquareLabel.SquareLabel$0(this, chess));
            })();
        } else if(i === undefined && j === undefined && chess === undefined && whiteOrBlack === undefined && symbolProvider === undefined) {
            let __args = Array.prototype.slice.call(arguments);
            super("", SwingConstants.CENTER);
            this.x = 0;
            this.y = 0;
            this.isWhite = false;
            this.originalColor = null;
            this.__highLight = false;
            this.image = null;
            this.symbolProvider = null;
            this.x = 0;
            this.y = 0;
            this.isWhite = false;
            this.originalColor = null;
            this.__highLight = false;
            this.image = null;
            this.symbolProvider = null;
            (() => {
                this.setPreferredSize(new Dimension(SquareLabel.SQUARE_WIDTH, SquareLabel.SQUARE_WIDTH));
                this.setFont(SquareLabel.FONT_PIECE_$LI$());
            })();
        } else throw new Error('invalid overload');
    }

    public toString() : string {
        return "(" + this.x + "," + this.y + ")";
    }

    public isHighLight() : boolean {
        return this.__highLight;
    }

    /**
     * hight light this spot and set the back ground color to highlight color.
     */
    highLight() {
        this.__highLight = true;
        this.setBackground(SquareLabel.HIGHLIGHT_COLOR_$LI$());
    }

    deHighLight() {
        this.__highLight = false;
        this.setBackground(this.originalColor);
    }

    /**
     * upDate the color and text of this JLabel.
     * @param {view.ChessPieceType} chessPieceType
     * @param {boolean} wb
     */
    public upDatePiece(chessPieceType : ChessPieceType, wb : boolean) {
        this.image = this.getSymbol(chessPieceType, wb);
    }

    public getSymbol(type : ChessPieceType, whiteOrBlack : boolean) : BufferedImage {
        let color : number;
        if(whiteOrBlack) color = 0; else color = 67;
        return this.symbolProvider.imageAt(this.getXIndex(type), color, SquareLabel.SQUARE_WIDTH, SquareLabel.SQUARE_WIDTH);
    }

    private getXIndex(type : ChessPieceType) : number {
        if(type === ChessPieceType.Pawn_$LI$()) return 333;
        if(type === ChessPieceType.Rook_$LI$()) return 268;
        if(type === ChessPieceType.Bishop_$LI$()) return 135;
        if(type === ChessPieceType.Knight_$LI$()) return 201;
        if(type === ChessPieceType.Queen_$LI$()) return 67;
        if(type === ChessPieceType.King_$LI$()) return 0;
        throw new Error("Invalid type of ChessPiece " + type);
    }

    public clearLabel() {
        this.image = null;
    }

    paintComponent(g : Graphics) {
        super.paintComponent(g);
        if(this.image != null) {
            let g2 : Graphics2D = <Graphics2D>g;
            g2.drawImage(this.image, null, 0, 0);
        }
    }
}
SquareLabel["__class"] = "view.SquareLabel";
SquareLabel["__interfaces"] = ["javax.swing.TransferHandler.HasGetTransferHandler","java.awt.MenuContainer","javax.accessibility.Accessible","javax.swing.SwingConstants","java.awt.image.ImageObserver","java.io.Serializable"];



export namespace SquareLabel {

    export class SquareLabel$0 extends MouseAdapter {
        public __parent: any;
        public mouseClicked(arg0 : MouseEvent) {
            this.chess.click(this.__parent.x, this.__parent.y, this.__parent.isWhite);
        }

        constructor(__parent: any, private chess: any) {
            super();
            this.__parent = __parent;
        }
    }
}




SquareLabel.HIGHLIGHT_COLOR_$LI$();

SquareLabel.FONT_PIECE_$LI$();
