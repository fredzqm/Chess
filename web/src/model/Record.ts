/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    import ArrayList = java.util.ArrayList;

    import Iterator = java.util.Iterator;

    import Player = model.Piece.Player;

    /**
     * The magical class that keeps track of move history It tracks a list of played
     * in the chess, and can be used to query the history or undo steps
     * 
     * @author zhang
     */
    export class Record {
        private list : ArrayList<Move>;

        private endgame : EndGame;

        public constructor() {
            this.list = <any>(new ArrayList<any>());
            this.endgame = null;
        }

        public size() : number {
            return this.list.size();
        }

        public isEmpty() : boolean {
            return this.list.isEmpty();
        }

        public contains(o : any) : boolean {
            return this.list.contains(o);
        }

        /**
         * add a move to the record
         * 
         * @param move
         */
        public add(move : Move) {
            this.list.add(move);
        }

        /**
         * remove the last move in the record. Used mostly for undoing steps
         */
        public removeLast() {
            this.list.remove(this.size() - 1);
        }

        /**
         * Time tracks which move. A move at time happened at (time/2 + 1) round.
         * white if even, black if odd
         * 
         * @param time
         * time represents the order of move
         * @return the move at this time
         */
        public get(time : number) : Move {
            return this.list.get(time);
        }

        public iterator() : Iterator<Move> {
            return this.list.iterator();
        }

        /**
         * 
         * @return the last move
         */
        public getLastMove() : Move {
            if(this.isEmpty()) return null;
            return this.get(this.size() - 1);
        }

        /**
         * 
         * @return true if the game has already ended
         */
        public hasEnd() : boolean {
            return this.endgame != null;
        }

        /**
         * specify that this game ended with certain end game situation
         * 
         * @param endgame
         */
        public endGame(endgame : EndGame) {
            this.endgame = endgame;
        }

        /**
         * 
         * @param original
         * the original position of the piece
         * @param type
         * the type of the piece originally at this square
         * @return true if the original piece has ever moved or be taken since the
         * game started.
         */
        public hasMoved(original : Square, type : any, time : number) : boolean {
            if(!original.isOccupied() || !original.getPiece().isType(type)) return true;
            for(let t : number = 0; t < time; t++) {
                if(original.equals(this.get(t).getStart())) return true;
            }
            return false;
        }

        /**
         * 
         * @return the documentation for all the records
         */
        public printDoc() : string {
            let sb : java.lang.StringBuilder = new java.lang.StringBuilder();
            let round : number = 1;
            for(let index126=this.list.iterator();index126.hasNext();) {
                let r = index126.next();
                {
                    if(r.playerColor === Player.WHITE) {
                        sb.append(round + ". " + r.getDoc());
                    } else {
                        round++;
                        sb.append("   " + r.getDoc() + "\n");
                    }
                }
            }
            if(this.hasEnd()) {
                sb.append("\n" + this.endgame.getDoc());
            } else {
                let last : Move = this.getLastMove();
                if(last != null) if(last.playerColor === Player.WHITE) sb.append("   ...");
            }
            return sb.toString();
        }

        /**
         * 
         * @return the description for the end game
         */
        public getEndGameDescript() : string {
            return this.endgame.getDescript();
        }

        public getEndGame() : EndGame {
            return this.endgame;
        }
    }
    Record["__class"] = "model.Record";

}

