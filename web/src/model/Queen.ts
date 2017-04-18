/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    /**
     * 
     * @author zhangq2
     */
    export class Queen extends Piece {
        private VALUE : number = 10;

        /**
         * constructs a Queen with initial square
         * 
         * @param type
         * @param wb
         * @param position
         * @param chess
         */
        public constructor(c : Piece.Player, position : Square, chess : Chess) {
            super(c, position, chess);
        }

        public legalPosition(end : Square) : Move {
            if(Bishop.legalPosition(this.spot, end, this.chess) || Rook.legalPosition(this.spot, end, this.chess)) return new RegularMove(this, this.spot, end.getPiece(), end);
            return null;
        }

        public getValue() : number {
            return this.VALUE;
        }

        public getType() : string {
            return 'Q';
        }
    }
    Queen["__class"] = "model.Queen";
    Queen["__interfaces"] = ["java.lang.Comparable"];


}

