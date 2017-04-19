/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Move } from './Move'; 
import { Piece } from './Piece'; 
import { Square } from './Square'; 
import { MoveNote } from './MoveNote'; 
import { Chess } from './Chess'; 
export class RegularMove extends Move {
    public constructor(movedPiece : Piece, startPosition : Square, capturedPiece : Piece, lastPosition : Square) {
        super(movedPiece, startPosition, capturedPiece, lastPosition);
    }

    /**
     * 
     * @return {string} the description of this move, which will appear in the top label
     * of the window
     */
    public getDescript() : string {
        let description : string = "";
        if(this.isWhite) description += "White"; else description += "Black";
        description += " " + this.movedPiece.getName();
        if(this.capturedPiece == null) description += " moves to " + this.lastPosition.toString(); else {
            description += " catches ";
            if(this.isWhite) description += "black "; else description += "white ";
            description += this.capturedPiece.getName();
            description += " on " + this.lastPosition.toString();
        }
        description += MoveNote["_$wrappers"][this.note].getDescriptEnd();
        return description;
    }

    /**
     * undo the last move, and restore the board
     * 
     * @param c
     * @param {model.Chess} chess
     */
    public undo(chess : Chess) {
        this.movedPiece.moveTo(this.startPosition);
        if(this.capturedPiece != null) chess.putBackToBoard(this.capturedPiece, this.lastPosition);
    }

    public performMove(chess : Chess) {
        if(this.capturedPiece != null) chess.takeOffBoard(this.capturedPiece);
        this.movedPiece.moveTo(this.lastPosition);
    }
}
RegularMove["__class"] = "model.RegularMove";



