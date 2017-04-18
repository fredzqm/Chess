/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    /**
     * Pawn in the chess
     * 
     * @author zhangq2
     */
    export class Pawn extends Piece {
        private VALUE : number = 1;

        /**
         * constructs a Pawn with initial square
         * 
         * @param c
         * @param p
         * @param chess
         */
        public constructor(c : Piece.Player, p : Square, chess : Chess) {
            super(c, p, chess);
        }

        public legalPosition(end : Square) : Move {
            if(Pawn.legalPosition(this.spot, end, this.chess, this.getWhiteOrBlack())) {
                if(this.canPromote(end)) return new Promotion(this, this.spot, end.getPiece(), end, this.chess.getRound());
                return new RegularMove(this, this.spot, end.getPiece(), end);
            }
            return null;
        }

        public static legalPosition(spot : Square, end : Square, chess : Chess, c : Piece.Player) : boolean {
            if(end.isOccupied() || spot == null) return false;
            if(spot.getX() === end.getX()) {
                if(c === Piece.Player.WHITE) {
                    if(end.getY() - spot.getY() === 1) return true;
                    if(end.getY() === 4 && spot.getY() === 2) if(!chess.spotAt(spot.getX(), 3).isOccupied()) return true;
                } else {
                    if(end.getY() - spot.getY() === -1) return true;
                    if(end.getY() === 5 && spot.getY() === 7) if(!chess.spotAt(spot.getX(), 6).isOccupied()) return true;
                }
            }
            return false;
        }

        public getMove(end : Square) : Move {
            let move : Move = this.legalPosition(end);
            if(move != null) {
                if(this.chess.giveAwayKing(move)) return null;
                return move;
            }
            move = this.canAttack(end);
            if(move == null) return null;
            let otherColor : Piece.Player;
            if(this.color === Piece.Player.WHITE) {
                otherColor = Piece.Player.BLACK;
            } else {
                otherColor = Piece.Player.WHITE;
            }
            if(end.occupiedBy(otherColor)) {
                if(this.chess.giveAwayKing(move)) return null;
                return move;
            }
            if(!this.chess.canEnPassant(end)) return null;
            move = new EnPassant(this, this.spot, this.chess.spotAt(end.getX(), this.spot.getY()).getPiece(), end, this.chess.getRound());
            if(this.chess.giveAwayKing(move)) return null;
            return move;
        }

        public canAttack(end : Square) : Move {
            if(this.legalPostionCapture(end)) {
                if(this.canPromote(end)) return new Promotion(this, this.spot, end.getPiece(), end, this.chess.getRound());
                return new RegularMove(this, this.spot, end.getPiece(), end);
            }
            return null;
        }

        private legalPostionCapture(end : Square) : boolean {
            if(this.spot == null) return false;
            if(Math.abs(end.getX() - this.spot.getX()) === 1) {
                if(this.color === Piece.Player.WHITE) {
                    if(end.getY() - this.spot.getY() === 1) return true;
                } else {
                    if(end.getY() - this.spot.getY() === -1) return true;
                }
            }
            return false;
        }

        canPromote(end : Square) : boolean {
            let promotion : boolean = false;
            if(this.color === Piece.Player.WHITE) {
                if(end.getY() === 8) promotion = true;
            } else {
                if(end.getY() === 1) promotion = true;
            }
            return promotion;
        }

        public getValue() : number {
            return this.VALUE;
        }

        public getType() : string {
            return 'P';
        }
    }
    Pawn["__class"] = "model.Pawn";
    Pawn["__interfaces"] = ["java.lang.Comparable"];


}

