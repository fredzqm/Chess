/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Piece } from './Piece'; 
import { Square } from './Square'; 
import { Chess } from './Chess'; 
import { Move } from './Move'; 
import { RegularMove } from './RegularMove'; 
/**
 * constructs a Bishop with initial square
 * 
 * @param wb
 * @param {model.Square} Position
 * @param {model.Chess} chess
 * @param {boolean} isWhite
 * @class
 */
export class Bishop extends Piece {
    /*private*/ VALUE : number = 4;

    public constructor(isWhite : boolean, Position : Square, chess : Chess) {
        super(isWhite, Position, chess);
    }

    public legalPosition(end : Square) : Move {
        if(Bishop.legalPosition(this.spot, end, this.chess)) return new RegularMove(this, this.spot, end.getPiece(), end);
        return null;
    }

    static legalPosition(start : Square, end : Square, chess : Chess) : boolean {
        if(start.equals(end)) return false;
        if(Math.abs(start.getX() - end.getX()) === Math.abs(start.getY() - end.getY())) {
            let k : number = ((end.getX() - start.getX()) / (Math.abs(end.getX() - start.getX()))|0);
            let l : number = ((end.getY() - start.getY()) / (Math.abs(end.getY() - start.getY()))|0);
            let j : number = start.getY() + l;
            for(let i : number = start.getX() + k; i !== end.getX(); i += k, j += l) {
                if(chess.spotAt(i, j).isOccupied()) return false;
            }
            return true;
        }
        return false;
    }

    public getValue() : number {
        return this.VALUE;
    }

    public getType() : string {
        return 'B';
    }
}
Bishop["__class"] = "model.Bishop";
Bishop["__interfaces"] = ["java.lang.Comparable"];




