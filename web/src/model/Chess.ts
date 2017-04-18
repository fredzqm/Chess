/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import ArrayList = java.util.ArrayList;

    import Collection = java.util.Collection;

    import Collections = java.util.Collections;

    import Player = model.Piece.Player;

    /**
     * It is the system for a chess game. It has fields to store the condition of
     * the chess and method to access and modify those information.
     * 
     * @author zhangq2
     */
    export class Chess {
        private time : number;

        private board : Board;

        private white : ArrayList<Piece>;

        private black : ArrayList<Piece>;

        private records : Record;

        private list : Collection<Square>;

        /**
         * construct a default chess with start setting.
         * 
         */
        public constructor() {
            this.time = 0;
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
                        this.white.add(this.startSet(t.getX(), Player.WHITE, t));
                    } else if(y === 2) {
                        this.white.add(new Pawn(Player.WHITE, t, this));
                    } else if(y === 7) {
                        this.black.add(new Pawn(Player.BLACK, t, this));
                    } else if(y === 8) {
                        this.black.add(this.startSet(t.getX(), Player.BLACK, t));
                    }
                    this.list.add(t);
                }
            }
            Collections.sort<any>(this.white);
            Collections.sort<any>(this.black);
        }

        /**
         * 
         * @param x
         * which file of this piece
         * @param c
         * White or Black
         * @param p
         * original position of this piece
         * @return creates new pieces to put into the start chessboard.
         */
        private startSet(x : number, c : Player, p : Square) : Piece {
            if(x === 1 || x === 8) return new Rook(c, p, this); else if(x === 2 || x === 7) return new Knight(c, p, this); else if(x === 3 || x === 6) return new Bishop(c, p, this); else if(x === 4) return new Queen(c, p, this); else if(x === 5) return new King(c, p, this); else return null;
        }

        public getWhoseTurn() : Player {
            if(this.time % 2 === 0) {
                return Player.WHITE;
            } else {
                return Player.BLACK;
            }
        }

        public getRound() : number {
            return (this.time / 2|0) + 1;
        }

        /**
         * 
         * @return true if the game has terminated.
         */
        public hasEnd() : boolean {
            return this.records.hasEnd();
        }

        /**
         * 
         * @return the board of the chess
         */
        public getBoard() : Board {
            return this.board;
        }

        /**
         * 
         * @param x
         * the file of the square
         * @param y
         * the rank of the square
         * @return the position of the square
         */
        public spotAt(x : number, y : number) : Square {
            return this.board.spotAt(x, y);
        }

        public getAllSquares() : Collection<Square> {
            return this.list;
        }

        public getRecords() : Record {
            return this.records;
        }

        public toString() : string {
            return this.board.toString();
        }

        /**
         * 
         * @param type
         * type of the piece
         * @param end
         * square to move to
         * @return all possible pieces that can move to given square
         */
        public possibleMovers(type : any, end : Square) : ArrayList<Piece> {
            let possible : ArrayList<Piece> = <any>(new ArrayList<Piece>());
            let set : ArrayList<Piece>;
            if(this.getWhoseTurn() === Player.WHITE) set = this.white; else set = this.black;
            for(let index121=set.iterator();index121.hasNext();) {
                let i = index121.next();
                {
                    if(i.isType(type) && i.canGo(end)) possible.add(i);
                }
            }
            return possible;
        }

        /**
         * Find out whether a certain move will put your own king in check.
         * 
         * The concept is simple here, presumably make the first and check if the
         * opponent is checking {@link Chess#checkOrNot}. Then undo the move
         * 
         * @param move
         * @return true if this move will give away the king
         */
        public giveAwayKing(move : Move) : boolean {
            move.performMove(this);
            let giveAway : boolean = this.checkOrNot(move.getWhoseTurn() === Player.BLACK);
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
         * @param attacker
         * who is attacking and tries to make this check
         * @return whether it is checking
         */
        public checkOrNot(attacker : boolean) : boolean {
            if(attacker) return this.isAttacked(true, this.black.get(0).getSpot()); else return this.isAttacked(false, this.white.get(0).getSpot());
        }

        /**
         * 
         * @param whiteOrBlack
         * who is attacking (true, the while, false, the black)
         * @param square
         * the square to check for
         * @return true if square is attacked
         */
        public isAttacked(whiteOrBlack : boolean, square : Square) : boolean {
            let attacker : ArrayList<Piece>;
            if(whiteOrBlack) attacker = this.white; else attacker = this.black;
            for(let index122=attacker.iterator();index122.hasNext();) {
                let i = index122.next();
                {
                    if(i.canAttack(square) != null) return true;
                }
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
         * @param checked
         * the side that can be potentially checkmaked
         * @return after the opponent makes one move, is it possible for his king
         * not to be in check
         */
        private checkMate(checked : boolean) : boolean {
            let inCheck : ArrayList<Piece>;
            if(checked) inCheck = this.white; else inCheck = this.black;
            for(let index123=inCheck.iterator();index123.hasNext();) {
                let i = index123.next();
                {
                    for(let index124=this.getAllSquares().iterator();index124.hasNext();) {
                        let p = index124.next();
                        {
                            if(i.canGo(p)) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }

        /**
         * whether I can claim draw according to the rules. if it meets the
         * requirement of 'Fifty-move rule', or 'threefold repetition rule', add
         * marks to canClaimDraw. So both players can claim draw if they want.
         * 
         * @return the type of draw if the game meets the automatic requirement for
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
         * @param end
         * the square the opponent's pawn just went through, and my pawn
         * try to go as an En Passant move.
         * @return whether the En Passant move is legal right now.
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
         * @param k
         * the king that tries to do this castling.
         * @param longOrShort
         * long Casting happens on the Queen side, while the short
         * castling happens on the King side.
         * @return the castling move if it is legal to make the castling of this
         * side right now, null otherwise
         */
        public canCastling(k : King, longOrShort : boolean) : Move {
            if(longOrShort) {
                if(this.canNotLongCastling(k.getY(), k.getWhiteOrBlack() === Player.BLACK)) return null;
                return new Castling(k, k.getSpot(), this.spotAt(3, k.getY()), <Rook>(this.spotAt(1, k.getY()).getPiece()), this.spotAt(1, k.getY()), this.getRound());
            } else {
                if(this.canNotShortCastling(k.getY(), k.getWhiteOrBlack() === Player.BLACK)) return null;
                return new Castling(k, k.getSpot(), this.spotAt(7, k.getY()), <Rook>(this.spotAt(8, k.getY()).getPiece()), this.spotAt(8, k.getY()), this.getRound());
            }
        }

        private canNotLongCastling(y : number, attack : boolean) : boolean {
            return this.spotAt(2, y).isOccupied() || this.spotAt(3, y).isOccupied() || this.spotAt(4, y).isOccupied() || this.isAttacked(attack, this.spotAt(5, y)) || this.isAttacked(attack, this.spotAt(3, y)) || this.isAttacked(attack, this.spotAt(4, y)) || this.records.hasMoved(this.spotAt(1, y), Rook, this.time) || this.records.hasMoved(this.spotAt(5, y), King, this.time);
        }

        private canNotShortCastling(y : number, attack : boolean) : boolean {
            return this.spotAt(6, y).isOccupied() || this.spotAt(7, y).isOccupied() || this.isAttacked(attack, this.spotAt(5, y)) || this.isAttacked(attack, this.spotAt(6, y)) || this.isAttacked(attack, this.spotAt(7, y)) || this.records.hasMoved(this.spotAt(8, y), Rook, this.time) || this.records.hasMoved(this.spotAt(5, y), King, this.time);
        }

        /**
         * 
         * @return the output of the last move, which will be displayed in the box
         * or as part of the records. null if the game has not started yet
         */
        public lastMoveOutPrint() : string {
            let move : Move = this.lastMove();
            if(move == null) return null;
            return move.getPrintOut();
        }

        /**
         * 
         * @return the description of the last move, which will be printed out in
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
         * @param taken
         * the piece that is captured
         */
        public takeOffBoard(taken : Piece) {
            let p : Square = taken.getSpot();
            if(p == null) return;
            taken.getSpot().setOccupied(null);
            if(taken.getWhiteOrBlack() === Player.WHITE) this.white.remove(taken); else this.black.remove(taken);
        }

        /**
         * This method is only used to undo previous move.
         * 
         * @param taken
         * the piece that is returned to the board
         */
        public putBackToBoard(taken : Piece, spot : Square) {
            if(taken != null) {
                if(taken.getWhiteOrBlack() === Player.WHITE) {
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
         * @param piece
         * @param end
         * @return true if move is valid, false if not allowed by chess rule
         */
        public performMove(piece : Piece, end : Square) : Move {
            let move : Move = piece.getMove(end);
            if(move != null) {
                this.makeMove(move);
            }
            return move;
        }

        /**
         * This method will be called if the user request to make a castling.
         * 
         * @param longOrShort
         * @return
         */
        public castling(longOrShort : boolean) : boolean {
            let king : King;
            if(this.getWhoseTurn() === Player.WHITE) king = <King>this.white.get(0); else king = <King>this.black.get(0);
            let move : Move = this.canCastling(king, longOrShort);
            if(move != null) {
                this.makeMove(move);
                return true;
            }
            return false;
        }

        /**
         * All moves from the user input will go throw this method
         * 
         * @param move
         */
        public makeMove(move : Move) {
            move.performMove(this);
            this.records.add(move);
            this.time++;
            if(this.checkOrNot(this.getWhoseTurn() === Player.BLACK)) {
                if(this.checkMate(this.getWhoseTurn() === Player.WHITE)) {
                    move.note = MoveNote.CHECKMATE;
                    if(this.getWhoseTurn() === Player.BLACK) this.endGame(Win.WHITECHECKMATE_$LI$()); else this.endGame(Win.BLACKCHECKMATE_$LI$());
                    return;
                }
                move.note = MoveNote.CHECK;
            } else {
                if(this.checkMate(this.getWhoseTurn() === Player.WHITE)) {
                    this.endGame(Draw.STALEMATE_$LI$());
                    return;
                }
            }
        }

        promotion(c : Player, end : Square) : Piece {
            return new Queen(c, end, this);
        }

        /**
         * record end game information and send message to control
         * 
         * @param endgame
         * the kind of end game occurred
         */
        public endGame(endgame : EndGame) {
            this.records.endGame(endgame);
        }
    }
    Chess["__class"] = "model.Chess";

}

