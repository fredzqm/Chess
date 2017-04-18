/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    /**
     * 
     * @author zhangq2
     */
    export class Knight extends Piece {
        private VALUE : number = 3;

        /**
         * constructs a Knight with initial square
         * 
         * @param type
         * @param c
         * @param Position
         * @param chess
         */
        public constructor(c : Piece.Player, Position : Square, chess : Chess) {
            super(c, Position, chess);
        }

        public legalPosition(end : Square) : Move {
            if(this.spot.equals(end)) return null;
            let a : number = Math.abs(this.spot.getX() - end.getX());
            let b : number = Math.abs(this.spot.getY() - end.getY());
            if(a + b === 3 && (a !== 0 && b !== 0)) return new RegularMove(this, this.spot, end.getPiece(), end); else return null;
        }

        public getValue() : number {
            return this.VALUE;
        }

        public getType() : string {
            return 'N';
        }
    }
    Knight["__class"] = "model.Knight";
    Knight["__interfaces"] = ["java.lang.Comparable"];


}

