/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    /**
     * 
     * @author zhangq2
     */
    export class King extends Piece {
        private VALUE : number = 100;

        /**
         * constructs a King with initial square
         * 
         * @param c
         * @param Position
         * @param chess
         */
        public constructor(c : Piece.Player, Position : Square, chess : Chess) {
            super(c, Position, chess);
        }

        public legalPosition(end : Square) : Move {
            if(Math.abs(this.spot.getX() - end.getX()) > 1 || Math.abs(this.spot.getY() - end.getY()) > 1) return null;
            if(this.spot.equals(end)) return null; else {
                return new RegularMove(this, this.spot, end.getPiece(), end);
            }
        }

        public getMove(end : Square) : Move {
            if(end.occupiedBy(this.color)) return null;
            let legalMove : Move = this.legalPosition(end);
            if(legalMove == null) {
                if(this.getX() === 5 && this.getY() === end.getY()) {
                    if(end.getX() === 3) {
                        return this.chess.canLongCastling(this);
                    } else if(end.getX() === 7) {
                        return this.chess.canShortCastling(this);
                    }
                }
                return null;
            }
            if(this.chess.giveAwayKing(legalMove)) return null;
            return legalMove;
        }

        public getValue() : number {
            return this.VALUE;
        }

        public getType() : string {
            return 'K';
        }
    }
    King["__class"] = "model.King";
    King["__interfaces"] = ["java.lang.Comparable"];


}

