/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import Player = model.Piece.Player;

    export class EnPassant extends Move {
        private pawnPos : Square;

        /**
         * constructs an En Passant record
         * 
         * @param moved
         * the pawn moved
         * @param start
         * the start position of this pawn
         * @param taken
         * the En Passant pawn that is captured
         * @param end
         * the end position of moved pawn
         * @param time
         * which round this move happens
         */
        public constructor(moved : Piece, start : Square, taken : Piece, end : Square, round : number) {
            super(moved, start, taken, end);
            this.pawnPos = taken.getSpot();
        }

        public getDescript() : string {
            let s : string = "";
            if(this.playerColor === Player.WHITE) s += "White"; else s += "Black";
            s += " Pawn";
            s += " moves to " + this.lastPosition.toString();
            s += " catches En passant pawn on " + this.pawnPos.toString();
            s += model.MoveNote["_$wrappers"][this.note].getDescriptEnd();
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

}

