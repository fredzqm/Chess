/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Piece } from './Piece'; 
import { Square } from './Square'; 
import { Chess } from './Chess'; 
import { Move } from './Move'; 
import { RegularMove } from './RegularMove'; 
/**
 * constructs a King with initial square
 * 
 * @param {boolean} c
 * @param {model.Square} Position
 * @param {model.Chess} chess
 * @class
 */
export class King extends Piece {
    /*private*/ VALUE : number = 100;

    public constructor(c : boolean, Position : Square, chess : Chess) {
        super(c, Position, chess);
    }

    public legalPosition(end : Square) : Move {
        if(Math.abs(this.spot.getX() - end.getX()) > 1 || Math.abs(this.spot.getY() - end.getY()) > 1) return null;
        if(this.spot.equals(end)) return null; else {
            return new RegularMove(this, this.spot, end.getPiece(), end);
        }
    }

    public getMove(end : Square) : Move {
        if(end.occupiedBy(this.isWhite)) return null;
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




