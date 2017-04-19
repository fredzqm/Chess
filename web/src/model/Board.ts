/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Square } from './Square'; 
import { Piece } from './Piece'; 
import Iterator = java.util.Iterator;

/**
 * create a standard chess board
 * @class
 */
export class Board implements java.lang.Iterable<Square> {
    /*private*/ spots : Square[][];

    public constructor() {
        this.spots = null;
        this.spots = <any> (function(dims) { let allocate = function(dims) { if(dims.length==0) { return undefined; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([8, 8]);
        for(let i : number = 0; i < 8; i++) {
            for(let j : number = 0; j < 8; j++) {
                let t : Square = new Square(i, j);
                this.spots[i][j] = t;
            }
        }
    }

    /**
     * s needs to be a string of length 2, like a3, e8.
     * 
     * @param {string} s
     * the name of the square
     * @return {model.Square} the position of the square, null if fail to find a corresponding
     * square
     */
    public getSquare(s : string) : Square {
        if(s.length !== 2) return null;
        let x : string = s.charAt(0);
        let y : string = s.charAt(1);
        if((c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(x) < 'a'.charCodeAt(0) || (c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(x) > 'h'.charCodeAt(0) || (c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(y) < '1'.charCodeAt(0) || (c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(y) > '8'.charCodeAt(0)) return null;
        return this.spots[(<number>((c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(x) - 'a'.charCodeAt(0))|0)][7 - (<number>((c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(y) - '1'.charCodeAt(0))|0)];
    }

    /**
     * 
     * @param {number} x
     * the file of the square
     * @param {number} y
     * the rank of the square
     * @return {model.Square} the position of the square
     */
    public spotAt(x : number, y : number) : Square {
        return this.spots[x - 1][8 - y];
    }

    public toString() : string {
        let sb : java.lang.StringBuilder = new java.lang.StringBuilder();
        for(let i : number = 8; i >= 1; i--) {
            for(let j : number = 1; j <= 8; j++) {
                let square : Square = this.spotAt(j, i);
                let piece : Piece = square.getPiece();
                if(piece == null) {
                    sb.append("  ");
                } else {
                    if(piece.getWhiteOrBlack()) {
                        sb.append('*');
                    } else {
                        sb.append(' ');
                    }
                    sb.append(piece.getType());
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public iterator() : Iterator<Square> {
        return new Board.BoardIterator(this);
    }
}
Board["__class"] = "model.Board";
Board["__interfaces"] = ["java.lang.Iterable"];



export namespace Board {

    export class BoardIterator implements Iterator<Square> {
        public __parent: any;
        i : number;

        public hasNext() : boolean {
            return this.i !== 63;
        }

        public next() : Square {
            this.i++;
            return this.__parent.spots[this.i % 8][(this.i / 8|0)];
        }

        public forEachRemaining(consumer : (p1: any) => void) {
            Iterator.super.forEachRemaining(consumer);
        }

        constructor(__parent: any) {
            this.__parent = __parent;
            this.i = -1;
        }
    }
    BoardIterator["__class"] = "model.Board.BoardIterator";
    BoardIterator["__interfaces"] = ["java.util.Iterator"];


}



