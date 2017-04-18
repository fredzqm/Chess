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
            if(this.playerColor === Player.WHITE) s += "White "; else s += "Black ";
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
            if(promotToClass.equals(Queen)) return new Queen(this.playerColor, this.lastPosition, this.movedPiece.chess); else if(promotToClass.equals(Knight)) return new Knight(this.playerColor, this.lastPosition, this.movedPiece.chess);
            if(promotToClass.equals(Rook)) return new Rook(this.playerColor, this.lastPosition, this.movedPiece.chess);
            if(promotToClass.equals(Bishop)) return new Bishop(this.playerColor, this.lastPosition, this.movedPiece.chess);
            throw new Error("Invalid type of piece to promote to");
        }

        private checkPromotedTo() {
            if(this.promotedTo == null) throw new Error("promtedTo is not specified");
        }
    }
    Promotion["__class"] = "model.Promotion";

}

