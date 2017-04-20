/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Move } from './Move'; 
import { Piece } from './Piece'; 
import { Square } from './Square'; 
import { Chess } from './Chess'; 
import { Queen } from './Queen'; 
import { Knight } from './Knight'; 
import { Rook } from './Rook'; 
import { Bishop } from './Bishop'; 
/**
 * creates a record for pawn promotion
 * 
 * @param {model.Piece} moved
 * the pawn that promotes
 * @param {model.Square} start
 * the start square of this move
 * @param {model.Piece} taken
 * the piece that in taken when the pawn promotes, null if there
 * is not one.
 * @param {model.Square} end
 * the square the pawn promotes
 * @param time
 * which round the pawn promotes
 * @param {number} round
 * @class
 */
export class Promotion extends Move {
    /*private*/ promotedTo : Piece;

    public constructor(moved : Piece, start : Square, taken : Piece, end : Square, round : number) {
        super(moved, start, taken, end);
        this.promotedTo = null;
        this.promotedTo = null;
    }

    public getDoc() : string {
        this.checkPromotedTo();
        return super.getDoc() + "(" + this.promotedTo.getType() + ")";
    }

    public getPrintOut() : string {
        this.checkPromotedTo();
        return this.getDoc() + " Successfully promote to " + this.promotedTo.getName() + "!";
    }

    public getDescript() : string {
        this.checkPromotedTo();
        let s : string = "";
        if(this.isWhite) s += "White "; else s += "Black ";
        s += "Pawn promotes to ";
        s += this.promotedTo.getName() + "!!";
        return s;
    }

    public notQuiet() : boolean {
        return true;
    }

    public undo(chess : Chess) {
        if(this.promotedTo == null) {
            this.movedPiece.moveTo(this.startPosition);
        } else {
            chess.takeOffBoard(this.promotedTo);
            chess.putBackToBoard(this.movedPiece, this.startPosition);
        }
        if(this.capturedPiece != null) chess.putBackToBoard(this.capturedPiece, this.lastPosition);
    }

    public performMove(chess : Chess) {
        if(this.capturedPiece != null) chess.takeOffBoard(this.capturedPiece);
        if(this.promotedTo == null) {
            this.movedPiece.moveTo(this.lastPosition);
        } else {
            chess.takeOffBoard(this.movedPiece);
            chess.putBackToBoard(this.promotedTo, this.lastPosition);
        }
    }

    public setPromoteTo(promotToClass : any) {
        this.promotedTo = this.getPromotedPiece(promotToClass);
    }

    private getPromotedPiece(promotToClass : any) : Piece {
        if(/* equals */(promotToClass === Queen)) return new Queen(this.isWhite, this.lastPosition, this.movedPiece.chess); else if(/* equals */(promotToClass === Knight)) return new Knight(this.isWhite, this.lastPosition, this.movedPiece.chess);
        if(/* equals */(promotToClass === Rook)) return new Rook(this.isWhite, this.lastPosition, this.movedPiece.chess);
        if(/* equals */(promotToClass === Bishop)) return new Bishop(this.isWhite, this.lastPosition, this.movedPiece.chess);
        throw new Error("Invalid type of piece to promote to");
    }

    private checkPromotedTo() {
        if(this.promotedTo == null) throw new Error("promtedTo is not specified");
    }

    public equals(obj : any) : boolean {
        if(obj != null && obj instanceof <any>Promotion) {
            let x : Promotion = <Promotion>obj;
            return x.promotedTo === x.promotedTo && super.equals(obj);
        }
        return false;
    }
}
Promotion["__class"] = "model.Promotion";



