/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import Player = model.Piece.Player;

    /**
     * A class that represents the squares on the chess board.
     * 
     * It is also the Jlabel that actually appears in the Frame.
     * 
     * e1,
     * 
     * @author zhangq2
     */
    export class Square {
        private x : number;

        private y : number;

        private position : string;

        private occupiedPiece : Piece;

        /**
         * 
         * @param i
         * file of this spot
         * @param j
         * rank of this square
         * @param chess
         */
        public constructor(i : number, j : number) {
            this.x = 0;
            this.y = 0;
            this.x = i + 1;
            this.y = 8 - j;
            let col : string = String.fromCharCode((97 + i));
            let row : number = 8 - j;
            this.position = "" + col + row;
            this.occupiedPiece = null;
        }

        public toString() : string {
            return this.position;
        }

        public getX() : number {
            return this.x;
        }

        public getY() : number {
            return this.y;
        }

        /**
         * 
         * @return the piece at that square
         */
        public getPiece() : Piece {
            return this.occupiedPiece;
        }

        /**
         * 
         * @return true if there is any piece occupy this squre
         */
        public isOccupied() : boolean {
            return this.occupiedPiece != null;
        }

        /**
         * 
         * @param whoseTurn
         * white or black
         * @return whether this square is occupied by piece of that color.
         */
        public occupiedBy(color : Player) : boolean {
            if(this.isOccupied()) return color === (this.occupiedPiece.getWhiteOrBlack()); else return false;
        }

        /**
         * set the occupied piece.
         * 
         * @param piece
         * the piece
         */
        public setOccupied(piece : Piece) {
            this.occupiedPiece = piece;
        }
    }
    Square["__class"] = "model.Square";

}

