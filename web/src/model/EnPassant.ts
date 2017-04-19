/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Move } from './Move'; 
import { Square } from './Square'; 
import { Piece } from './Piece'; 
import { MoveNote } from './MoveNote'; 
import { Chess } from './Chess'; 
/**
 * constructs an En Passant record
 * 
 * @param {model.Piece} moved
 * the pawn moved
 * @param {model.Square} start
 * the start position of this pawn
 * @param {model.Piece} taken
 * the En Passant pawn that is captured
 * @param {model.Square} end
 * the end position of moved pawn
 * @param time
 * which round this move happens
 * @param {number} round
 * @class
 */
export class EnPassant extends Move {
    /*private*/ pawnPos : Square;

    public constructor(moved : Piece, start : Square, taken : Piece, end : Square, round : number) {
        super(moved, start, taken, end);
        this.pawnPos = null;
        this.pawnPos = taken.getSpot();
    }

    public getDescript() : string {
        let s : string = "";
        if(this.isWhite) s += "White"; else s += "Black";
        s += " Pawn";
        s += " moves to " + this.lastPosition.toString();
        s += " catches En passant pawn on " + this.pawnPos.toString();
        s += MoveNote["_$wrappers"][this.note].getDescriptEnd();
        return s;
    }

    public undo(chess : Chess) {
        this.movedPiece.moveTo(this.startPosition);
        if(this.capturedPiece != null) {
            chess.putBackToBoard(this.capturedPiece, this.pawnPos);
        }
    }

    public performMove(chess : Chess) {
        if(this.capturedPiece != null) chess.takeOffBoard(this.capturedPiece);
        this.movedPiece.moveTo(this.lastPosition);
    }
}
EnPassant["__class"] = "model.EnPassant";



