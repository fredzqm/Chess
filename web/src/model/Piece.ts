/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Chess } from './Chess'; 
import { Square } from './Square'; 
import { Move } from './Move'; 
import { Board } from './Board'; 
import ArrayList = java.util.ArrayList;

/**
 * 
 * @param wb
 * whether this piece is white or black
 * @param {model.Square} p
 * the square this piece is at initially.
 * @param {model.Chess} chess
 * @param {boolean} c
 * @class
 */
export abstract class Piece implements java.lang.Comparable<Piece> {
    chess : Chess;

    isWhite : boolean;

    spot : Square;

    public constructor(c : boolean, p : Square, chess : Chess) {
        this.chess = null;
        this.isWhite = false;
        this.spot = null;
        this.chess = chess;
        this.isWhite = c;
        this.moveTo(p);
    }

    public getName() : string {
        let s : string = /* getName */(c => c["__class"]?c["__class"]:c["name"])((<any>this.constructor));
        return s.substring(s.lastIndexOf(".") + 1);
    }

    /**
     * 
     * @return {model.Square} the spot this piece is on. {@Code null} if this piece is not on
     * the board (taken away)
     */
    public getSpot() : Square {
        return this.spot;
    }

    /**
     * 
     * @return {boolean} Whether this piece is owned by white or black
     */
    public getWhiteOrBlack() : boolean {
        return this.isWhite;
    }

    /**
     * 
     * @return {string} the single character that represents this piece
     */
    public abstract getType() : string;

    /**
     * 
     * @param {Function<>} type
     * @return {boolean} true if this piece is of this type
     */
    public isType(type : any) : boolean {
        return (<any>this.constructor) === type;
    }

    /**
     * This method is not really used right now
     * 
     * @return {number} the value or power of this piece
     */
    public abstract getValue() : number;

    /**
     * this method is implemented for the sort method in the constructor of
     * chess.
     * @param {model.Piece} a
     * @return {number}
     */
    public compareTo(a : Piece) : number {
        return (a.getValue() - this.getValue());
    }

    /**
     * 
     * @return {number} X position of this spot
     */
    public getX() : number {
        return this.spot.getX();
    }

    /**
     * 
     * @return {number} Y position of this spot
     */
    public getY() : number {
        return this.spot.getY();
    }

    /**
     * Only place in this program that can change the position of a spot.
     * 
     * @param {model.Square} p
     */
    public moveTo(p : Square) {
        if(this.spot != null) {
            this.spot.setOccupied(null);
        }
        if(p != null) {
            p.setOccupied(this);
        }
        this.spot = p;
    }

    /**
     * This method takes everything into account, including giving away king,
     * castling, En Passant.
     * 
     * 
     * @param {model.Square} end
     * @return {boolean} true if it is safe to make this move without violation of the
     * rule
     */
    public canGo(end : Square) : boolean {
        return this.getMove(end) != null;
    }

    /**
     * This method gets a legitimate move to the end Square if one exists. This
     * method is overridden by {@link King} and {@link Pawn}, because they have
     * special rules
     * 
     * For pieces except King and Pawn, @{link {@link Piece#legalPosition}
     * capture all possible moves
     * 
     * @param {model.Square} end
     * the spot to move to
     * @return {model.Move} the legal move of this piece toward the end Square, {@Code null}
     * if this move is illegal
     */
    public getMove(end : Square) : Move {
        if(end.occupiedBy(this.isWhite)) return null;
        let legalMove : Move = this.legalPosition(end);
        if(legalMove == null) return null;
        if(this.chess.giveAwayKing(legalMove)) return null;
        return legalMove;
    }

    /**
     * 
     * @param chosen
     * @return {java.util.ArrayList} the list of Squares that this piece can reach
     */
    public getReachableSquares() : ArrayList<Square> {
        let list : ArrayList<Square> = <any>(new ArrayList<any>());
        for(let index123=this.chess.getBoard().iterator();index123.hasNext();) {
            let i = index123.next();
            if(this.canGo(i)) list.add(i);
        }
        return list;
    }

    /**
     * This method is overridden by Pawn because it has special rule for
     * attacking
     * 
     * For other pieces, the all legal position they can move to are the spots
     * they can attack
     * 
     * @param {model.Square} end
     * the spot to attack
     * @return {model.Move} true if this piece can attack this spot
     */
    public canAttack(end : Square) : Move {
        if(this.spot == null) return null;
        return this.legalPosition(end);
    }

    /**
     * For most pieces in chess, there is a common way of moving. This checks if
     * certain move match such common.
     * 
     * For pawn this method only specifies the move. The capture logic is
     * defined in {@link Pawn#getMove(Square)}
     * 
     * @param {model.Square} end
     * the end position
     * @param chess
     * @return {model.Move} true if it is legal to move this piece to the end, regardless of
     * the piece at the end position
     */
    public abstract legalPosition(end : Square) : Move;

    public toString() : string {
        return this.getName() + " at " + this.getSpot();
    }
}
Piece["__class"] = "model.Piece";
Piece["__interfaces"] = ["java.lang.Comparable"];




