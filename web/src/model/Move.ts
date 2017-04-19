/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Piece } from './Piece'; 
import { Square } from './Square'; 
import { MoveNote } from './MoveNote'; 
import { Pawn } from './Pawn'; 
import { Castling } from './Castling'; 
import { Chess } from './Chess'; 
/**
 * constructs a record
 * 
 * @param {model.Piece} movedPiece
 * the piece moved
 * @param {model.Square} startPosition
 * the start position of this move
 * @param {model.Piece} capturedPiece
 * the piece that is captured, null if there is nothing captured.
 * @param {model.Square} lastPosition
 * the end position of this move
 * @param time
 * which round this move happens
 * @class
 */
export abstract class Move {
    isWhite : boolean;

    movedPiece : Piece;

    startPosition : Square;

    capturedPiece : Piece;

    lastPosition : Square;

    note : MoveNote;

    public constructor(movedPiece : Piece, startPosition : Square, capturedPiece : Piece, lastPosition : Square) {
        this.isWhite = false;
        this.movedPiece = null;
        this.startPosition = null;
        this.capturedPiece = null;
        this.lastPosition = null;
        this.note = null;
        this.isWhite = movedPiece.getWhiteOrBlack();
        this.movedPiece = movedPiece;
        this.startPosition = startPosition;
        this.capturedPiece = capturedPiece;
        this.lastPosition = lastPosition;
        this.note = MoveNote.NONE;
    }

    /**
     * 
     * @return {model.Square} start square
     */
    public getStart() : Square {
        return this.startPosition;
    }

    /**
     * called when the program needs to find out whether it is legal to make a
     * castling.
     * 
     * @param {model.Square} p
     * @return {boolean} true, this last move matches the rule, and it is legal to make a
     * castling
     */
    public canEnPassant(p : Square) : boolean {
        return this.movedPiece.isType(Pawn) && (this.startPosition.getX() === p.getX() && this.lastPosition.getX() === p.getX() && (this.startPosition.getY() + this.lastPosition.getY()) === (p.getY() * 2));
    }

    /**
     * This methods is called to examine whether 'threefold repetition rule'.
     * 
     * @param {model.Move} x
     * @return {boolean} if two moves are exactly the same and repeatable.
     */
    public equals(x : Move) : boolean {
        if(this.notQuiet() || x.notQuiet()) return false;
        if(x != null && x instanceof <any>Castling) return false;
        return this.movedPiece.equals(x.movedPiece) && this.startPosition.equals(x.startPosition) && this.lastPosition.equals(x.lastPosition);
    }

    public toString() : string {
        return this.getPrintOut() + " " + this.getDescript();
    }

    public getWhoseTurn() : boolean {
        return this.isWhite;
    }

    /**
     * @return {string} the documentation in standard chess convention
     */
    public getDoc() : string {
        let doc : string = "";
        if(!this.movedPiece.isType(Pawn)) doc += this.movedPiece.getType();
        doc += this.startPosition.toString();
        if(this.capturedPiece == null) doc += "-"; else doc += "x";
        doc += this.lastPosition.toString();
        doc += MoveNote["_$wrappers"][this.note].getDocEnd();
        return doc;
    }

    /**
     * @return {string} the messages necessary to printOut in the console
     */
    public getPrintOut() : string {
        return this.getDoc();
    }

    /**
     * This method is called to examine whether the game has meets the
     * requirement of 'Fifty move rule".
     * 
     * @return {boolean} true if this move can be redo over and over again later.
     */
    public notQuiet() : boolean {
        return this.capturedPiece != null || this.movedPiece.isType(Pawn);
    }

    public abstract getDescript() : string;

    public abstract performMove(chess : Chess);

    public abstract undo(chess : Chess);
}
Move["__class"] = "model.Move";



