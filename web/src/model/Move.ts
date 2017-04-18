/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import Player = model.Piece.Player;

    /**
     * this class is a record of all the moves in this game, so we can undo steps or
     * check for special rules.
     * 
     * @author zhangq2
     */
    export abstract class Move {
        playerColor : Player;

        movedPiece : Piece;

        startPosition : Square;

        capturedPiece : Piece;

        lastPosition : Square;

        note : MoveNote;

        /**
         * constructs a record
         * 
         * @param movedPiece
         * the piece moved
         * @param startPosition
         * the start position of this move
         * @param capturedPiece
         * the piece that is captured, null if there is nothing captured.
         * @param lastPosition
         * the end position of this move
         * @param time
         * which round this move happens
         */
        public constructor(movedPiece : Piece, startPosition : Square, capturedPiece : Piece, lastPosition : Square) {
            this.playerColor = movedPiece.getWhiteOrBlack();
            this.movedPiece = movedPiece;
            this.startPosition = startPosition;
            this.capturedPiece = capturedPiece;
            this.lastPosition = lastPosition;
            this.note = MoveNote.NONE;
        }

        /**
         * 
         * @return start square
         */
        public getStart() : Square {
            return this.startPosition;
        }

        /**
         * called when the program needs to find out whether it is legal to make a
         * castling.
         * 
         * @param p
         * @return true, this last move matches the rule, and it is legal to make a
         * castling
         */
        public canEnPassant(p : Square) : boolean {
            return this.movedPiece.isType(Pawn) && (this.startPosition.getX() === p.getX() && this.lastPosition.getX() === p.getX() && (this.startPosition.getY() + this.lastPosition.getY()) === (p.getY() * 2));
        }

        /**
         * This methods is called to examine whether 'threefold repetition rule'.
         * 
         * @param x
         * @return if two moves are exactly the same and repeatable.
         */
        public equals(x : Move) : boolean {
            if(this.notQuiet() || x.notQuiet()) return false;
            if(x != null && x instanceof model.Castling) return false;
            return this.movedPiece.equals(x.movedPiece) && this.startPosition.equals(x.startPosition) && this.lastPosition.equals(x.lastPosition);
        }

        public toString() : string {
            return this.getPrintOut() + " " + this.getDescript();
        }

        public getWhoseTurn() : Player {
            return this.playerColor;
        }

        /**
         * @return the documentation in standard chess convention
         */
        public getDoc() : string {
            let doc : string = "";
            if(!this.movedPiece.isType(Pawn)) doc += this.movedPiece.getType();
            doc += this.startPosition.toString();
            if(this.capturedPiece == null) doc += "-"; else doc += "x";
            doc += this.lastPosition.toString();
            doc += model.MoveNote["_$wrappers"][this.note].getDocEnd();
            return doc;
        }

        /**
         * @return the messages necessary to printOut in the console
         */
        public getPrintOut() : string {
            return this.getDoc();
        }

        /**
         * This method is called to examine whether the game has meets the
         * requirement of 'Fifty move rule".
         * 
         * @return true if this move can be redo over and over again later.
         */
        public notQuiet() : boolean {
            return this.capturedPiece != null || this.movedPiece.isType(Pawn);
        }

        public abstract getDescript() : string;

        public abstract performMove(chess : Chess);

        public abstract undo(chess : Chess);
    }
    Move["__class"] = "model.Move";

}

