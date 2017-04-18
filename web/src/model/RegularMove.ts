/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import Player = model.Piece.Player;

    export class RegularMove extends Move {
        public constructor(movedPiece : Piece, startPosition : Square, capturedPiece : Piece, lastPosition : Square) {
            super(movedPiece, startPosition, capturedPiece, lastPosition);
        }

        /**
         * 
         * @return the description of this move, which will appear in the top label
         * of the window
         */
        public getDescript() : string {
            let description : string = "";
            if(this.playerColor === Player.WHITE) description += "White"; else description += "Black";
            description += " " + this.movedPiece.getName();
            if(this.capturedPiece == null) description += " moves to " + this.lastPosition.toString(); else {
                description += " catches ";
                if(this.playerColor === Player.WHITE) description += "black "; else description += "white ";
                description += this.capturedPiece.getName();
                description += " on " + this.lastPosition.toString();
            }
            description += model.MoveNote["_$wrappers"][this.note].getDescriptEnd();
            return description;
        }

        /**
         * undo the last move, and restore the board
         * 
         * @param c
         */
        public undo(chess : Chess) {
            this.movedPiece.moveTo(this.startPosition);
            if(this.capturedPiece != null) chess.putBackToBoard(this.capturedPiece, this.lastPosition);
        }

        public getWhoseTurn() : Player {
            return this.playerColor;
        }
    }
    RegularMove["__class"] = "model.RegularMove";

}

