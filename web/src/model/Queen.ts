/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Piece } from './Piece'; 
import { Square } from './Square'; 
import { Chess } from './Chess'; 
import { Move } from './Move'; 
import { Bishop } from './Bishop'; 
import { Rook } from './Rook'; 
import { RegularMove } from './RegularMove'; 
/**
 * constructs a Queen with initial square
 * 
 * @param type
 * @param wb
 * @param {model.Square} position
 * @param {model.Chess} chess
 * @param {boolean} isWhite
 * @class
 */
export class Queen extends Piece {
    /*private*/ VALUE : number = 10;

    public constructor(isWhite : boolean, position : Square, chess : Chess) {
        super(isWhite, position, chess);
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




