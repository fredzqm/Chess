/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import ArrayList = java.util.ArrayList;

    import Iterator = java.util.Iterator;

    /**
     * The super class for all different kinds of pieces.
     * 
     * @author zhangq2
     */
    export abstract class Piece implements java.lang.Comparable<Piece> {
        chess : Chess;

        color : Piece.Player;

        spot : Square;

        /**
         * 
         * @param wb
         * whether this piece is white or black
         * @param p
         * the square this piece is at initially.
         * @param chess
         */
        public constructor(c : Piece.Player, p : Square, chess : Chess) {
            this.chess = chess;
            this.color = c;
            this.moveTo(p);
        }

        public getName() : string {
            let s : string = /* getName */(c => c["__class"]?c["__class"]:c.name)((<any>this.constructor));
            return s.substring(s.lastIndexOf(".") + 1);
        }

        /**
         * 
         * @return the spot this piece is on. {@Code null} if this piece is not on
         * the board (taken away)
         */
        public getSpot() : Square {
            return this.spot;
        }

        /**
         * 
         * @return Whether this piece is owned by white or black
         */
        public getWhiteOrBlack() : Piece.Player {
            return this.color;
        }

        /**
         * 
         * @return the single character that represents this piece
         */
        public abstract getType() : string;

        /**
         * 
         * @param type
         * @return true if this piece is of this type
         */
        public isType(type : any) : boolean {
            return (<any>this.constructor) === type;
        }

        /**
         * This method is not really used right now
         * 
         * @return the value or power of this piece
         */
        public abstract getValue() : number;

        /**
         * this method is implemented for the sort method in the constructor of
         * chess.
         */
        public compareTo(a : Piece) : number {
            return (a.getValue() - this.getValue());
        }

        /**
         * 
         * @return X position of this spot
         */
        public getX() : number {
            return this.spot.getX();
        }

        /**
         * 
         * @return Y position of this spot
         */
        public getY() : number {
            return this.spot.getY();
        }

        /**
         * Only place in this program that can change the position of a spot.
         * 
         * @param p
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
         * @param end
         * @return true if it is safe to make this move without violation of the
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
         * @param end
         * the spot to move to
         * @return the legal move of this piece toward the end Square, {@Code null}
         * if this move is illegal
         */
        public getMove(end : Square) : Move {
            if(end.occupiedBy(this.color)) return null;
            let legalMove : Move = this.legalPosition(end);
            if(legalMove == null) return null;
            if(this.chess.giveAwayKing(legalMove)) return null;
            return legalMove;
        }

        /**
         * 
         * @param chosen
         * @return the list of Squares that this piece can reach
         */
        public getReachableSquares() : ArrayList<Square> {
            let list : ArrayList<Square> = <any>(new ArrayList<any>());
            let itr : Iterator<Square> = this.chess.getBoard().iterator();
            while((itr.hasNext())){
                let i : Square = itr.next();
                if(this.canGo(i)) list.add(i);
            };
            return list;
        }

        /**
         * This method is overridden by Pawn because it has special rule for
         * attacking
         * 
         * For other pieces, the all legal position they can move to are the spots
         * they can attack
         * 
         * @param end
         * the spot to attack
         * @return true if this piece can attack this spot
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
         * @param end
         * the end position
         * @param chess
         * @return true if it is legal to move this piece to the end, regardless of
         * the piece at the end position
         */
        public abstract legalPosition(end : Square) : Move;

        /**
         * This method convert the character to one type of {@link Piece} class. It
         * used mostly for parsing commands like
         * <p>
         * e2-e4 <br />
         * Nb1-c3
         * </p>
         * 
         * @param character
         * the character representing the piece
         * @return the corresponding class
         */
        public static getType(character : string) : any {
            switch((javaemul.internal.CharacterHelper.toUpperCase(character))) {
            case 'P':
                return Pawn;
            case 'R':
                return Rook;
            case 'N':
                return Knight;
            case 'B':
                return Bishop;
            case 'Q':
                return Queen;
            case 'K':
                return King;
            default:
                return Piece;
            }
        }

        public toString() : string {
            return this.getName() + " at " + this.getSpot();
        }
    }
    Piece["__class"] = "model.Piece";
    Piece["__interfaces"] = ["java.lang.Comparable"];



    export namespace Piece {

        export enum Player {
            WHITE, BLACK
        }
    }

}

