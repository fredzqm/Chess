/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Board } from './Board'; 
import { Piece } from './Piece'; 
import { Record } from './Record'; 
import { Square } from './Square'; 
import { Pawn } from './Pawn'; 
import { Rook } from './Rook'; 
import { Knight } from './Knight'; 
import { Bishop } from './Bishop'; 
import { Queen } from './Queen'; 
import { King } from './King'; 
import { Move } from './Move'; 
import { Draw } from './Draw'; 
import { Castling } from './Castling'; 
import { MoveNote } from './MoveNote'; 
import { Win } from './Win'; 
import { EndGame } from './EndGame'; 
import { InvalidMoveException } from './InvalidMoveException'; 
import { IMovePatternMatcher } from './IMovePatternMatcher'; 
import { MovePatternMatcher } from './MovePatternMatcher'; 
import { Promotion } from './Promotion'; 
import ArrayList = java.util.ArrayList;

import Collection = java.util.Collection;

import Collections = java.util.Collections;

import Iterator = java.util.Iterator;

/**
 * construct a default chess with start setting.
 * 
 * @class
 */
export class Chess {
    /*private*/ time : number;

    /*private*/ board : Board;

    /*private*/ white : ArrayList<Piece>;

    /*private*/ black : ArrayList<Piece>;

    /*private*/ records : Record;

    /*private*/ list : Collection<Square>;

    public constructor() {
        this.time = 0;
        this.board = null;
        this.white = null;
        this.black = null;
        this.records = null;
        this.list = null;
        this.time = 0;
        this.records = new Record();
        this.board = new Board();
        this.white = <any>(new ArrayList<Piece>());
        this.black = <any>(new ArrayList<Piece>());
        this.list = <any>(new ArrayList<Square>());
        for(let i : number = 1; i <= 8; i++) {
            for(let j : number = 1; j <= 8; j++) {
                let t : Square = this.board.spotAt(i, j);
                let y : number = t.getY();
                if(y === 1) {
                    this.white.add(this.startSet(t.getX(), true, t));
                } else if(y === 2) {
                    this.white.add(new Pawn(true, t, this));
                } else if(y === 7) {
                    this.black.add(new Pawn(false, t, this));
                } else if(y === 8) {
                    this.black.add(this.startSet(t.getX(), false, t));
                }
                this.list.add(t);
            }
        }
        Collections.sort<any>(this.white);
        Collections.sort<any>(this.black);
    }

    /**
     * 
     * @param {number} x
     * which file of this piece
     * @param {boolean} c
     * White or Black
     * @param {model.Square} p
     * original position of this piece
     * @return {model.Piece} creates new pieces to put into the start chessboard.
     * @private
     */
    private startSet(x : number, c : boolean, p : Square) : Piece {
        if(x === 1 || x === 8) return new Rook(c, p, this); else if(x === 2 || x === 7) return new Knight(c, p, this); else if(x === 3 || x === 6) return new Bishop(c, p, this); else if(x === 4) return new Queen(c, p, this); else if(x === 5) return new King(c, p, this); else return null;
    }

    public getWhoseTurn() : boolean {
        return this.time % 2 === 0;
    }

    public getRound() : number {
        return (this.time / 2|0) + 1;
    }

    /**
     * 
     * @return {boolean} true if the game has terminated.
     */
    public hasEnd() : boolean {
        return this.records.hasEnd();
    }

    /**
     * 
     * @return {model.Board} the board of the chess
     */
    public getBoard() : Board {
        return this.board;
    }

    /**
     * 
     * @param {number} file
     * the file of the square
     * @param {number} rank
     * the rank of the square
     * @return {model.Square} the position of the square
     */
    public spotAt(file : number, rank : number) : Square {
        return this.board.spotAt(file, rank);
    }

    public getRecords() : Record {
        return this.records;
    }

    public toString() : string {
        return this.board.toString();
    }

    /**
     * 
     * @param {Function<>} type
     * type of the piece
     * @param {model.Square} end
     * square to move to
     * @return {java.util.ArrayList} all possible pieces that can move to given square
     */
    public possibleMovers(type : any, end : Square) : ArrayList<Piece> {
        let possible : ArrayList<Piece> = <any>(new ArrayList<Piece>());
        let set : ArrayList<Piece>;
        if(this.getWhoseTurn()) set = this.white; else set = this.black;
        for(let j : number = 0; j < set.size(); j++) {
            let i : Piece = set.get(j);
            if(i.isType(type) && i.canGo(end)) possible.add(i);
        }
        return possible;
    }

    /**
     * Find out whether a certain move will put your own king in check.
     * 
     * The concept is simple here, presumably make the first and check if the
     * opponent is checking {@link Chess#checkOrNot}. Then undo the move
     * 
     * @param {model.Move} move
     * @return {boolean} true if this move will give away the king
     */
    public giveAwayKing(move : Move) : boolean {
        move.performMove(this);
        let giveAway : boolean = this.checkOrNot(!move.getWhoseTurn());
        move.undo(this);
        return giveAway;
    }

    /**
     * checkOrNot(true) will return true if the white is checking the black's
     * king
     * 
     * checkOrNot(false) will return true if the black is checking the white's
     * king
     * 
     * @param {boolean} attacker
     * who is attacking and tries to make this check
     * @return {boolean} whether it is checking
     */
    public checkOrNot(attacker : boolean) : boolean {
        if(attacker) return this.isAttacked(true, this.black.get(0).getSpot()); else return this.isAttacked(false, this.white.get(0).getSpot());
    }

    /**
     * 
     * @param {boolean} whiteOrBlack
     * who is attacking (true, the while, false, the black)
     * @param {model.Square} square
     * the square to check for
     * @return {boolean} true if square is attacked
     */
    public isAttacked(whiteOrBlack : boolean, square : Square) : boolean {
        let attacker : ArrayList<Piece>;
        if(whiteOrBlack) attacker = this.white; else attacker = this.black;
        for(let j : number = 0; j < attacker.size(); j++) {
            let i : Piece = attacker.get(j);
            if(i.canAttack(square) != null) return true;
        }
        return false;
    }

    /**
     * 
     * used to find if there is a checkmake, and stalment. if this method
     * returns true, and it is now in check, it is a checkmate; but if it is now
     * not in check, the opponent cannot make any legitimate move, so it is a
     * draw by stalment.
     * 
     * @param {boolean} checked
     * the side that can be potentially checkmaked
     * @return {boolean} after the opponent makes one move, is it possible for his king
     * not to be in check
     * @private
     */
    private checkMate(checked : boolean) : boolean {
        let inCheck : ArrayList<Piece>;
        if(checked) inCheck = this.white; else inCheck = this.black;
        for(let j : number = 0; j < inCheck.size(); j++) {
            let i : Piece = inCheck.get(j);
            let iter : Iterator<Square> = this.board.iterator();
            while((iter.hasNext())) {
                let p : Square = iter.next();
                if(i.canGo(p)) {
                    return false;
                }
            };
        }
        return true;
    }

    /**
     * whether I can claim draw according to the rules. if it meets the
     * requirement of 'Fifty-move rule', or 'threefold repetition rule', add
     * marks to canClaimDraw. So both players can claim draw if they want.
     * 
     * @return {model.Draw} the type of draw if the game meets the automatic requirement for
     * draw, null if requirements not met
     */
    public canClaimDraw() : Draw {
        let recordNum : number = this.time;
        if(recordNum > 50) {
            let quiet : boolean = true;
            for(let i : number = recordNum - 50; i < recordNum; i++) {
                if(this.records.get(i).notQuiet()) {
                    quiet = false;
                    break;
                }
            }
            if(quiet) return Draw.FIFTY_MOVE_$LI$();
        }
        for(let i : number = 2; i < 25; i++) {
            if(i * 4 > recordNum) return null;
            let repetition : boolean = true;
            for(let j : number = 1; j <= 2 * i; j++) {
                if(!this.records.get(recordNum - j).equals(this.records.get(recordNum - j - 2 * i))) {
                    repetition = false;
                    break;
                }
            }
            if(repetition) return Draw.REPETITION_$LI$();
        }
        return null;
    }

    /**
     * 
     * En Passant is a very special rule of chess. An En Passant move can only
     * be made immediately after the opponent moves his pawn by two squares. So
     * this method is called to examine whether the records indicate a
     * legitimate En Passant move.
     * 
     * @param {model.Square} end
     * the square the opponent's pawn just went through, and my pawn
     * try to go as an En Passant move.
     * @return {boolean} whether the En Passant move is legal right now.
     */
    public canEnPassant(end : Square) : boolean {
        let move : Move = this.lastMove();
        if(move == null) return false;
        return move.canEnPassant(end);
    }

    /**
     * Castling is a very special rule of chess. Castling move can only be made
     * if the king and rook in this castling have never moved yet, plus any
     * squares the king goes through cannot be attacked.
     * 
     * find out whether castling is legal.
     * 
     * @param {model.King} k
     * the king that tries to do this castling.
     * @param longOrShort
     * long Casting happens on the Queen side, while the short
     * castling happens on the King side.
     * @return {model.Move} the castling move if it is legal to make the castling of this
     * side right now, null otherwise
     */
    public canShortCastling(k : King) : Move {
        if(this.canNotShortCastling(k.getY(), !k.getWhiteOrBlack())) return null;
        return new Castling(k, k.getSpot(), this.spotAt(7, k.getY()), <Rook>(this.spotAt(8, k.getY()).getPiece()), this.spotAt(8, k.getY()), this.getRound());
    }

    public canLongCastling(k : King) : Move {
        if(this.canNotLongCastling(k.getY(), !k.getWhiteOrBlack())) return null;
        return new Castling(k, k.getSpot(), this.spotAt(3, k.getY()), <Rook>(this.spotAt(1, k.getY()).getPiece()), this.spotAt(1, k.getY()), this.getRound());
    }

    private canNotLongCastling(y : number, attack : boolean) : boolean {
        return this.spotAt(2, y).isOccupied() || this.spotAt(3, y).isOccupied() || this.spotAt(4, y).isOccupied() || this.isAttacked(attack, this.spotAt(5, y)) || this.isAttacked(attack, this.spotAt(3, y)) || this.isAttacked(attack, this.spotAt(4, y)) || this.records.hasMoved(this.spotAt(1, y), Rook, this.time) || this.records.hasMoved(this.spotAt(5, y), King, this.time);
    }

    private canNotShortCastling(y : number, attack : boolean) : boolean {
        return this.spotAt(6, y).isOccupied() || this.spotAt(7, y).isOccupied() || this.isAttacked(attack, this.spotAt(5, y)) || this.isAttacked(attack, this.spotAt(6, y)) || this.isAttacked(attack, this.spotAt(7, y)) || this.records.hasMoved(this.spotAt(8, y), Rook, this.time) || this.records.hasMoved(this.spotAt(5, y), King, this.time);
    }

    /**
     * 
     * @return {string} the output of the last move, which will be displayed in the box
     * or as part of the records. null if the game has not started yet
     */
    public lastMoveOutPrint() : string {
        let move : Move = this.lastMove();
        if(move == null) return null;
        return move.getPrintOut();
    }

    /**
     * 
     * @return {string} the description of the last move, which will be printed out in
     * the top label.
     */
    public lastMoveDiscript() : string {
        if(this.records.hasEnd()) return this.records.getEndGameDescript();
        let move : Move = this.lastMove();
        if(move == null) return "Hasn\'t start the chess yet!";
        return move.getDescript();
    }

    public lastMove() : Move {
        if(this.time === 0) return null;
        return this.records.get(this.time - 1);
    }

    /**
     * 
     * If a piece is captured, and we need to put it off the board, we call this
     * method. This method is usually called with moveTo(Spot s) method in
     * Piece.
     * 
     * @param {model.Piece} taken
     * the piece that is captured
     */
    public takeOffBoard(taken : Piece) {
        let p : Square = taken.getSpot();
        if(p == null) return;
        taken.getSpot().setOccupied(null);
        if(taken.getWhiteOrBlack()) this.white.remove(taken); else this.black.remove(taken);
    }

    /**
     * This method is only used to undo previous move.
     * 
     * @param {model.Piece} taken
     * the piece that is returned to the board
     * @param {model.Square} spot
     */
    public putBackToBoard(taken : Piece, spot : Square) {
        if(taken != null) {
            if(taken.getWhiteOrBlack()) {
                this.white.add(taken);
            } else {
                this.black.add(taken);
            }
            taken.moveTo(spot);
        }
    }

    /**
     * changes the records and the chessboard, so everything will return back to
     * the state of previous round.
     * @return {boolean}
     */
    public undoLastMove() : boolean {
        let lastMove : Move = this.lastMove();
        if(lastMove == null) return false;
        lastMove.undo(this);
        this.records.removeLast();
        this.time--;
        return true;
    }

    /**
     * perform command to move piece to certain spot
     * 
     * @param {model.Piece} piece
     * @param {model.Square} end
     * @return {model.Move} true if move is valid, false if not allowed by chess rule
     */
    public performMove(piece : Piece, end : Square) : Move {
        let move : Move = piece.getMove(end);
        if(move != null) {
            this.makeMove(move);
        }
        return move;
    }

    /**
     * All moves from the user input will go throw this method
     * 
     * @param {model.Move} move
     */
    public makeMove(move : Move) {
        move.performMove(this);
        this.records.add(move);
        this.time++;
        if(this.checkOrNot(!this.getWhoseTurn())) {
            if(this.checkMate(this.getWhoseTurn())) {
                move.note = MoveNote.CHECKMATE;
                if(this.getWhoseTurn()) this.endGame(Win.BLACKCHECKMATE_$LI$()); else this.endGame(Win.WHITECHECKMATE_$LI$());
                return;
            }
            move.note = MoveNote.CHECK;
        } else {
            if(this.checkMate(this.getWhoseTurn())) {
                this.endGame(Draw.STALEMATE_$LI$());
                return;
            }
        }
    }

    /**
     * record end game information and send message to control
     * 
     * @param {model.EndGame} endgame
     * the kind of end game occurred
     */
    public endGame(endgame : EndGame) {
        this.records.endGame(endgame);
    }

    /**
     * Create a move object for the standard chess record.
     * 
     * @param {string} moveCommand
     * String for the chess record
     * 
     * @return {model.Move} Move object representing the desired move
     * 
     */
    public interpreteMoveCommand(moveCommand : string) : Move {
        let move : Move = null;
        if(/* startsWith */((str, searchString, position = 0) => str.substr(position, searchString.length) === searchString)(moveCommand, "O")) {
            let king : King;
            if(this.getWhoseTurn()) {
                king = <King>this.white.get(0);
            } else {
                king = <King>this.black.get(0);
            }
            if(/* equals */(moveCommand === "O-O")) {
                move = this.canShortCastling(king);
            } else if(/* equals */(moveCommand === "O-O-O")) {
                move = this.canLongCastling(king);
            } else {
                throw new InvalidMoveException(moveCommand, InvalidMoveException.invalidFormat);
            }
            if(move != null) {
                return move;
            } else {
                throw new InvalidMoveException(moveCommand, InvalidMoveException.castleNotAllowed);
            }
        }
        let m : IMovePatternMatcher = new MovePatternMatcher(moveCommand);
        if(m.matches()) {
            let type : any = m.getGroup(1) == null?Pawn:Chess.getPieceType(m.getGroup(1).charAt(0));
            let start : Square = null;
            if((m.getGroup(2) != null) && (m.getGroup(3) != null)) {
                start = this.board.getSquare(m.getGroup(2) + m.getGroup(3));
            }
            let end : Square = this.board.getSquare(m.getGroup(5));
            if(start != null) {
                let movedPiece : Piece = start.getPiece();
                if(movedPiece == null) {
                    throw new InvalidMoveException(moveCommand, InvalidMoveException.pieceNotPresent);
                } else if(!(movedPiece.isType(type))) {
                    throw new InvalidMoveException(moveCommand, InvalidMoveException.incorrectPiece);
                }
                move = movedPiece.getMove(end);
            } else {
                let possible : ArrayList<Piece> = this.possibleMovers(type, end);
                if(possible.size() === 0) {
                    throw new InvalidMoveException(moveCommand, InvalidMoveException.impossibleMove);
                } else if(possible.size() === 1) {
                    move = possible.get(0).getMove(end);
                } else {
                    throw new InvalidMoveException(moveCommand, InvalidMoveException.ambiguousMove);
                }
            }
            if(move != null && move instanceof <any>Promotion) {
                let promotion : Promotion = <Promotion>move;
                if(m.getGroup(6) == null) throw new InvalidMoveException(moveCommand, InvalidMoveException.promotionTo);
                let promotToClass : any = Chess.getPieceType(m.getGroup(6).charAt(1));
                promotion.setPromoteTo(promotToClass);
            }
            return move;
        } else {
            throw new InvalidMoveException(moveCommand, InvalidMoveException.invalidFormat);
        }
    }

    /**
     * This method convert the character to one type of {@link Piece} class. It
     * used mostly for parsing commands like
     * <p>
     * e2-e4 <br />
     * Nb1-c3
     * </p>
     * 
     * @param {string} character
     * the character representing the piece
     * @return {Function<>} the corresponding class
     */
    public static getPieceType(character : string) : any {
        switch((javaemul.internal.CharacterHelper.toUpperCase(character))) {
        case 80 /* 'P' */:
            return Pawn;
        case 82 /* 'R' */:
            return Rook;
        case 78 /* 'N' */:
            return Knight;
        case 66 /* 'B' */:
            return Bishop;
        case 81 /* 'Q' */:
            return Queen;
        case 75 /* 'K' */:
            return King;
        default:
            return Piece;
        }
    }
}
Chess["__class"] = "model.Chess";



