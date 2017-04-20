/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Piece } from './Piece'; 
import { Square } from './Square'; 
import { Chess } from './Chess'; 
import { Move } from './Move'; 
import { RegularMove } from './RegularMove'; 
/**
 * constructs a Knight with initial square
 * 
 * @param type
 * @param {boolean} isWhite
 * @param {model.Square} Position
 * @param {model.Chess} chess
 * @class
 */
export class Knight extends Piece {
    /*private*/ VALUE : number = 3;

    public constructor(isWhite : boolean, Position : Square, chess : Chess) {
        super(isWhite, Position, chess);
    }

    public legalPosition(end : Square) : Move {
        if(this.spot === end) return null;
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




