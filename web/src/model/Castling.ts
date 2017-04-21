/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Move } from './Move'; 
import { Rook } from './Rook'; 
import { Square } from './Square'; 
import { King } from './King'; 
import { MoveNote } from './MoveNote'; 
import { Chess } from './Chess'; 
import { Piece } from './Piece'; 
/**
 * it creates a record of castling
 * 
 * @param {model.King} king
 * the king involved
 * @param {model.Square} kingStart
 * the start square of the king
 * @param {model.Square} kingEnd
 * the end square of the king
 * @param {model.Rook} rook
 * the rook involved in this castling
 * @param {model.Square} rookStart
 * the starting position of this rook
 * @param time
 * the round number of this casting
 * @param {number} round
 * @class
 */
export class Castling extends Move {
    longOrShort : boolean;

    /*private*/ rook : Rook;

    /*private*/ rookStart : Square;

    public constructor(king : King, kingStart : Square, kingEnd : Square, rook : Rook, rookStart : Square, round : number) {
        super(king, kingStart, null, kingEnd);
        this.longOrShort = false;
        this.rook = null;
        this.rookStart = null;
        this.rook = rook;
        if(rook.getX() < 4) this.longOrShort = true; else this.longOrShort = false;
        this.rookStart = rookStart;
    }

    public getDoc() : string {
        let s : string;
        if(this.longOrShort) s = "O-O-O"; else s = "O-O";
        s += MoveNote["_$wrappers"][this.note].getDocEnd();
        return s;
    }

    public getPrintOut() : string {
        return this.getDoc() + " Successfully castling!";
    }

    public undo(c : Chess) {
        this.movedPiece.moveTo(this.startPosition);
        this.rook.moveTo(this.rookStart);
    }

    public notQuiet() : boolean {
        return false;
    }

    public getDescript() : string {
        let s : string = "";
        if(this.isWhite) s += "White "; else s += "Black ";
        if(this.longOrShort) s += "does long castling"; else s += "does short castling";
        return s;
    }

    public performMove(chess : Chess) {
        let kingEnd : Square;
        let rookEnd : Square;
        let rook : Piece;
        let y : number = this.movedPiece.getY();
        if(this.longOrShort) {
            kingEnd = chess.spotAt(3, y);
            this.rookStart = chess.spotAt(1, y);
            rookEnd = chess.spotAt(4, y);
            rook = chess.spotAt(1, y).getPiece();
        } else {
            kingEnd = chess.spotAt(7, y);
            this.rookStart = chess.spotAt(8, y);
            rookEnd = chess.spotAt(6, y);
            rook = chess.spotAt(8, y).getPiece();
        }
        this.movedPiece.moveTo(kingEnd);
        rook.moveTo(rookEnd);
    }

    public equals(obj : any) : boolean {
        if(obj != null && obj instanceof <any>Castling) {
            let x : Castling = <Castling>obj;
            return this.longOrShort === x.longOrShort && this.rook.equals(x.rook) && this.rookStart.equals(x.rookStart) && super.equals(obj);
        }
        return false;
    }
}
Castling["__class"] = "model.Castling";



