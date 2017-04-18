/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    /**
     * 
     * @author zhangq2
     */
    export class Rook extends Piece {
        private VALUE : number = 5;

        /**
         * constructs a Rook with initial square
         * 
         * @param type
         * @param c
         * @param Position
         */
        public constructor(c : Piece.Player, Position : Square, chess : Chess) {
            super(c, Position, chess);
        }

        public legalPosition(end : Square) : Move {
            if(Rook.legalPosition(this.spot, end, this.chess)) return new RegularMove(this, this.spot, end.getPiece(), end);
            return null;
        }

        static legalPosition(start : Square, end : Square, chess : Chess) : boolean {
            if(start.equals(end)) return false;
            if(end.getX() === start.getX()) {
                let k : number = ((end.getY() - start.getY()) / (Math.abs(end.getY() - start.getY()))|0);
                for(let i : number = start.getY() + k; i !== end.getY(); i += k) {
                    if(chess.spotAt(start.getX(), i).isOccupied()) return false;
                }
                return true;
            } else if(end.getY() === start.getY()) {
                let k : number = ((end.getX() - start.getX()) / (Math.abs(end.getX() - start.getX()))|0);
                for(let i : number = start.getX() + k; i !== end.getX(); i += k) {
                    if(chess.spotAt(i, start.getY()).isOccupied()) return false;
                }
                return true;
            }
            return false;
        }

        public getValue() : number {
            return this.VALUE;
        }

        public getType() : string {
            return 'R';
        }
    }
    Rook["__class"] = "model.Rook";
    Rook["__interfaces"] = ["java.lang.Comparable"];


}

