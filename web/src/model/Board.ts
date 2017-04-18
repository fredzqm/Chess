/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import Player = model.Piece.Player;

    /**
     * the class to represent the board of the chess
     * 
     * @author zhang
     */
    export class Board {
        private spots : Square[][];

        /**
         * create a standard chess board
         */
        public constructor() {
            this.spots = <any> (function(dims) { let allocate = function(dims) { if(dims.length==0) { return undefined; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([8, 8]);
            for(let i : number = 0; i < 8; i++) {
                for(let j : number = 0; j < 8; j++) {
                    let t : Square = new Square(i, j);
                    this.spots[i][j] = t;
                }
            }
        }

        /**
         * s needs to be a string of length 2, like a3, e8.
         * 
         * @param s
         * the name of the square
         * @return the position of the square, null if fail to find a corresponding
         * square
         */
        public getSquare(s : string) : Square {
            if(s.length !== 2) return null;
            let x : string = s.charAt(0);
            let y : string = s.charAt(1);
            if((x).charCodeAt(0) < ('a').charCodeAt(0) || (x).charCodeAt(0) > ('h').charCodeAt(0) || (y).charCodeAt(0) < ('1').charCodeAt(0) || (y).charCodeAt(0) > ('8').charCodeAt(0)) return null;
            return this.spots[(<number>((x).charCodeAt(0) - ('a').charCodeAt(0))|0)][7 - (<number>((y).charCodeAt(0) - ('1').charCodeAt(0))|0)];
        }

        /**
         * 
         * @param x
         * the file of the square
         * @param y
         * the rank of the square
         * @return the position of the square
         */
        public spotAt(x : number, y : number) : Square {
            return this.spots[x - 1][8 - y];
        }

        public toString() : string {
            let sb : java.lang.StringBuilder = new java.lang.StringBuilder();
            for(let i : number = 8; i >= 1; i--) {
                for(let j : number = 1; j <= 8; j++) {
                    let square : Square = this.spotAt(j, i);
                    let piece : Piece = square.getPiece();
                    if(piece == null) {
                        sb.append("  ");
                    } else {
                        if(piece.getWhiteOrBlack() === Player.WHITE) {
                            sb.append('*');
                        } else {
                            sb.append(' ');
                        }
                        sb.append(piece.getType());
                    }
                }
                sb.append('\n');
            }
            return sb.toString();
        }
    }
    Board["__class"] = "model.Board";

}

