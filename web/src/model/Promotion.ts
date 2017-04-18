/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import Player = model.Piece.Player;

    export class Promotion extends Move {
        private promotedTo : Piece;

        /**
         * creates a record for pawn promotion
         * 
         * @param moved
         * the pawn that promotes
         * @param start
         * the start square of this move
         * @param taken
         * the piece that in taken when the pawn promotes, null if there
         * is not one.
         * @param end
         * the square the pawn promotes
         * @param time
         * which round the pawn promotes
         */
        public constructor(moved : Piece, start : Square, taken : Piece, end : Square, round : number) {
            super(moved, start, taken, end);
            this.promotedTo = null;
        }

        public getDoc() : string {
            return super.getDoc() + "(" + this.promotedTo.getType() + ")";
        }

        public getPrintOut() : string {
            return this.getDoc() + " Successfully promote to " + this.promotedTo.getName() + "!";
        }

        public undo(chess : Chess) {
            chess.takeOffBoard(this.promotedTo);
            chess.putBackToBoard(this.movedPiece, this.startPosition);
            if(this.capturedPiece != null) {
                chess.putBackToBoard(this.capturedPiece, this.lastPosition);
            }
        }

        public notQuiet() : boolean {
            return true;
        }

        public getDescript() : string {
            let s : string = "";
            if(this.playerColor === Player.WHITE) s += "White "; else s += "Black ";
            s += "Pawn promotes to ";
            s += this.promotedTo.getName() + "!!";
            return s;
        }

        public performMove(chess : Chess) {
            if(this.capturedPiece != null) chess.takeOffBoard(this.capturedPiece);
            this.movedPiece.moveTo(this.lastPosition);
            this.promotedTo = chess.promotion(this.playerColor, this.lastPosition);
            chess.takeOffBoard(this.capturedPiece);
            chess.putBackToBoard(this.promotedTo, this.lastPosition);
        }
    }
    Promotion["__class"] = "model.Promotion";

}

