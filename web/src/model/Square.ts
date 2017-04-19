/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Piece } from './Piece'; 
/**
 * 
 * @param {number} i
 * file of this spot
 * @param {number} j
 * rank of this square
 * @param chess
 * @class
 */
export class Square {
    /*private*/ x : number;

    /*private*/ y : number;

    /*private*/ position : string;

    /*private*/ occupiedPiece : Piece;

    public constructor(i : number, j : number) {
        this.x = 0;
        this.y = 0;
        this.position = null;
        this.occupiedPiece = null;
        this.x = i + 1;
        this.y = 8 - j;
        let col : string = String.fromCharCode((97 + i));
        let row : number = 8 - j;
        this.position = "" + col + row;
        this.occupiedPiece = null;
    }

    public toString() : string {
        return this.position;
    }

    public getX() : number {
        return this.x;
    }

    public getY() : number {
        return this.y;
    }

    /**
     * 
     * @return {model.Piece} the piece at that square
     */
    public getPiece() : Piece {
        return this.occupiedPiece;
    }

    /**
     * 
     * @return {boolean} true if there is any piece occupy this squre
     */
    public isOccupied() : boolean {
        return this.occupiedPiece != null;
    }

    /**
     * 
     * @param whoseTurn
     * white or black
     * @return {boolean} whether this square is occupied by piece of that color.
     * @param {boolean} color
     */
    public occupiedBy(color : boolean) : boolean {
        if(this.isOccupied()) return color === (this.occupiedPiece.getWhiteOrBlack()); else return false;
    }

    /**
     * set the occupied piece.
     * 
     * @param {model.Piece} piece
     * the piece
     */
    public setOccupied(piece : Piece) {
        this.occupiedPiece = piece;
    }
}
Square["__class"] = "model.Square";



