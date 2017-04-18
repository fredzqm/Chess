/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import Player = model.Piece.Player;

    /**
     * this class records the castling move
     * @author zhangq2
     */
    export class Castling extends Move {
        longOrShort : boolean;

        private rook : Rook;

        private rookStart : Square;

        /**
         * it creates a record of castling
         * 
         * @param king
         * the king involved
         * @param kingStart
         * the start square of the king
         * @param kingEnd
         * the end square of the king
         * @param rook
         * the rook involved in this castling
         * @param rookStart
         * the starting position of this rook
         * @param time
         * the round number of this casting
         */
        public constructor(king : King, kingStart : Square, kingEnd : Square, rook : Rook, rookStart : Square, round : number) {
            super(king, kingStart, null, kingEnd);
            this.longOrShort = false;
            this.rook = rook;
            if(rook.getX() < 4) this.longOrShort = true; else this.longOrShort = false;
            this.rookStart = rookStart;
        }

        public getDoc() : string {
            let s : string;
            if(this.longOrShort) s = "O-O-O"; else s = "O-O";
            s += model.MoveNote["_$wrappers"][this.note].getDocEnd();
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
            if(this.playerColor === Player.WHITE) s += "White "; else s += "Black ";
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
    }
    Castling["__class"] = "model.Castling";

}

