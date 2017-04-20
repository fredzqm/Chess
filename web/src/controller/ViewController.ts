/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Chess } from '../model/Chess'; 
import { Draw } from '../model/Draw'; 
import { InvalidMoveException } from '../model/InvalidMoveException'; 
import { Move } from '../model/Move'; 
import { Pawn } from '../model/Pawn'; 
import { Piece } from '../model/Piece'; 
import { Promotion } from '../model/Promotion'; 
import { Record } from '../model/Record'; 
import { Square } from '../model/Square'; 
import { Win } from '../model/Win'; 
import { IChessViewer } from '../view/IChessViewer'; 
import { IChessViewerControl } from '../view/IChessViewerControl'; 
import { DrawManager } from './DrawManager'; 
import ArrayList = java.util.ArrayList;

import HashMap = java.util.HashMap;

import Map = java.util.Map;

export abstract class ViewController implements IChessViewerControl {
    /*private*/ chosen : Piece;

    chess : Chess;

    /*private*/ drawManager : DrawManager;

    public static ERROR_MESSAGE : string = "Please enter the move as (The type of chessman)(the start position)(its action)(the end position)\nyou can omit the \"P\" at the begining for a pawn.for casting, enter \"O-O\" or \"O-O-O\"\nfor examples, \"e2-e4\", \"Nb2-c3\" \nIf you need further help, type \"help\"";

    public static HELP_MESSAGE : string = "Enter commands:\nenter \'undo\' to undo the previous round;\nenter \'restart\' to start a new game over;\n\'enter \'print\' to print all the records;\nenter \'resign\' to give up;\nenter \'draw\' to request for draw;\nenter complete or abbreviated algebraic chess notation to make a move;\nenter \'rules for ....\' to get help about the rules of chess.\n    Castling, Pawn, King, Queen, Rook, Bishop, Knight, En Passant, Promotion.";

    public static rules : Map<string, string>; public static rules_$LI$() : Map<string, string> { if(ViewController.rules == null) ViewController.rules = new ViewController.ViewController$0(); return ViewController.rules; };

    public constructor() {
        this.chosen = null;
        this.chess = null;
        this.drawManager = null;
        this.chess = new Chess();
        this.drawManager = new DrawManager();
        this.chosen = null;
    }

    private restart() {
        this.chess = new Chess();
        this.chosen = null;
        let white : IChessViewer = this.chooesView(true);
        let black : IChessViewer = this.chooesView(false);
        this.restartView(white);
        if(black !== white) this.restartView(black);
    }

    private restartView(view : IChessViewer) {
        view.deHighLightWholeBoard();
        view.setStatusLabelText("       Welcome to Another Wonderful Chess Game         ");
        view.printOut("Start a new game!");
    }

    private repaintAll(view : IChessViewer) {
        for(let index121=this.chess.getBoard().iterator();index121.hasNext();) {
            let sq = index121.next();
            {
                if(sq.isOccupied()) {
                    view.upDatePiece(sq.getX(), sq.getY(), sq.getPiece().getType(), sq.getPiece().getWhiteOrBlack());
                } else {
                    view.clearLabel(sq.getX(), sq.getY());
                }
            }
        }
        view.repaint();
    }

    /**
     * This method is called if the user enter a command to undo his move. It
     * will undo two moves.
     * 
     * @param {model.Chess} chess
     * @param {view.IChessViewer} view
     * @private
     */
    private undo(chess : Chess, view : IChessViewer) {
        if(!chess.undoLastMove()) view.printOut("It is already the start of Game"); else view.printOut("Undo the Previous Move!");
    }

    /**
     * return the requested rules text.
     * 
     * @param {string} command
     * @param whiteOrBlack
     * @return
     * @param {view.IChessViewer} view
     * @param {java.util.Map} rules
     * @private
     */
    private showRules(command : string, view : IChessViewer, rules : Map<string, string>) {
        if(rules.containsKey(command)) view.printOut(rules.get(command));
        view.printOut("You can get rules for castling, pawn, king, queen, rook, bishop, knight, En Passant, promotion");
    }

    /**
     * print out the records of the game in starndart chess recording language
     * 
     * @param whiteOrBlack
     * 
     * @return {void} records
     * @param {view.IChessViewer} view
     * @param {model.Chess} chess
     * @private
     */
    private printRecords(view : IChessViewer, chess : Chess) {
        let records : Record = chess.getRecords();
        if(records.isEmpty()) {
            view.printOut("Game hasn\'t started yet.");
            return;
        }
        view.printOut(records.printDoc());
    }

    public resign(view : IChessViewer, chess : Chess) {
        let canClaimDraw : Draw = chess.canClaimDraw();
        if(canClaimDraw != null) {
            view.printOut("Actually, you can go with a draw!");
            chess.endGame(canClaimDraw);
            return;
        }
        if(chess.getWhoseTurn()) {
            chess.endGame(Win.WHITERESIGN_$LI$());
        } else {
            chess.endGame(Win.BLACKESIGN_$LI$());
        }
    }

    private askForDraw(whiteOrBlack : boolean) {
        let canClaimDraw : Draw = this.chess.canClaimDraw();
        if(canClaimDraw == null) {
            let request : IChessViewer = this.chooesView(whiteOrBlack);
            let response : IChessViewer = this.chooesView(!whiteOrBlack);
            if(this.drawManager.canAskFordraw(whiteOrBlack)) {
                while((true)) {
                    response.printOut(ViewController.side(whiteOrBlack) + " ask for draw, do you agreed?");
                    let command : string = response.getResponse("Do you agree draw?");
                    if(/* isEmpty */(command.length === 0)) continue;
                    if(/* startsWith */((str, searchString, position = 0) => str.substr(position, searchString.length) === searchString)(command.toLowerCase(), "yes")) {
                        this.chess.endGame(Draw.AGREEMENT_$LI$());
                        return;
                    } else if(/* startsWith */((str, searchString, position = 0) => str.substr(position, searchString.length) === searchString)(command.toLowerCase(), "no")) {
                        this.drawManager.setRightToRequestDraw(whiteOrBlack);
                        request.printOut("Request declined");
                        return;
                    }
                };
            } else {
                request.printOut("You cannot request for draw again now.");
            }
        } else {
            this.chess.endGame(canClaimDraw);
        }
    }

    updateChessBoard() {
        let white : IChessViewer = this.chooesView(true);
        let black : IChessViewer = this.chooesView(false);
        this.repaintAll(white);
        if(black !== white) this.repaintAll(black);
    }

    /**
     * This method will be called, if the user types a command to make a move.
     * 
     * Interpret the command, and find out if it is legal to do make this move.
     * If it is, make this move.
     * 
     * @param {string} moveCommand
     * the input command
     * @return
     * @param {view.IChessViewer} view
     * @return {boolean}
     */
    public makeMove(view : IChessViewer, moveCommand : string) : boolean {
        let move : Move = null;
        try {
            move = this.chess.interpreteMoveCommand(moveCommand);
            this.chess.makeMove(move);
            this.updateGuiAfterMove(move);
            return true;
        } catch(e) {
            switch((e.type)) {
            case InvalidMoveException.invalidFormat:
                view.printOut("The command is not in a valid format.");
                break;
            case InvalidMoveException.ambiguousMove:
                view.printOut("Fail to guess move: There is ambiguity, multiple possible moves.");
                break;
            case InvalidMoveException.castleNotAllowed:
                view.printOut("You cannot do castling, please check the rules for castling.");
                break;
            case InvalidMoveException.impossibleMove:
                view.printOut("This is not a possible move.");
                break;
            case InvalidMoveException.incorrectPiece:
                view.printOut("The chessman in the start Position is not correct! \n R(Root), N(Knight), B(Bishop), Q(Queen), K(King), omission for pawn");
                break;
            case InvalidMoveException.pieceNotPresent:
                view.printOut("There is no piece at the start position.");
                break;
            case InvalidMoveException.promotionTo:
                view.printOut("You should specify what piece you want to promote to");
                break;
            default:
                throw new Error(e);
            }
            return false;
        };
    }

    public handleCommand(input : string, whiteOrBlack : boolean) {
        let view : IChessViewer = this.chooesView(whiteOrBlack);
        if(input.length === 0) return;
        if(/* equals */(input === "print")) {
            this.printRecords(view, this.chess);
        } else if(/* equals */(input === "help")) {
            view.printOut(ViewController.HELP_MESSAGE);
        } else if(/* startsWith */((str, searchString, position = 0) => str.substr(position, searchString.length) === searchString)(input, "rules for ")) {
            this.showRules(input.substring(10), view, ViewController.rules_$LI$());
        } else if(/* equals */(input === "quit")) {
        } else if(/* equals */(input === "restart")) {
            this.restart();
            this.updateChessBoard();
        } else if(this.chess.hasEnd()) {
            view.printOut(this.chess.lastMoveDiscript());
        } else {
            this.chosen = null;
            view.deHighLightWholeBoard();
            if(/* equals */(input === "resign")) {
                this.resign(view, this.chess);
            } else if(/* equals */(input === "draw")) {
                this.askForDraw(whiteOrBlack);
            } else if(/* equals */(input === "undo")) {
                this.undo(this.chess, view);
            } else if(!this.makeMove(view, input)) {
                view.printOut(ViewController.ERROR_MESSAGE);
            }
            this.updateChessBoard();
        }
    }

    public click(file : number, rank : number, whiteOrBlack : boolean) {
        let clickedView : IChessViewer = this.chooesView(whiteOrBlack);
        if(this.chess.hasEnd()) {
            clickedView.printOut("Game is already over! Type restart to start a new game");
            return;
        }
        if(clickedView !== this.chooesView(this.chess.getWhoseTurn())) {
            clickedView.printOut("Please wait for your opponnet to finish");
            return;
        }
        let spot : Square = this.chess.spotAt(file, rank);
        if(this.chosen != null) {
            let move : Move = this.chosen.getMove(spot);
            if(move == null) {
                clickedView.cleanTemp();
            } else {
                if(move != null && move instanceof <any>Promotion) {
                    let promotion : Promotion = <Promotion>move;
                    let promoteTo : string = clickedView.getResponse("What do you want to promote to?");
                    promotion.setPromoteTo(Chess.getPieceType(promoteTo.charAt(0)));
                }
                this.chess.makeMove(move);
                this.updateGuiAfterMove(move);
            }
            this.chosen = null;
            clickedView.deHighLightWholeBoard();
        } else {
            if(spot.occupiedBy(this.chess.getWhoseTurn())) {
                this.chosen = spot.getPiece();
                let reachable : ArrayList<Square> = this.chosen.getReachableSquares();
                reachable.add(spot);
                for(let index122=reachable.iterator();index122.hasNext();) {
                    let sqr = index122.next();
                    {
                        clickedView.highLight(sqr.getX(), sqr.getY());
                    }
                }
                if(spot.getPiece().isType(Pawn)) clickedView.printTemp(spot.toString()); else clickedView.printTemp(spot.getPiece().getType() + spot.toString());
            }
        }
    }

    public abstract chooesView(whiteOrBlack : boolean) : IChessViewer;

    /**
     * print messages and update GUI when this move just get accomplished
     * 
     * @param {model.Move} move
     */
    abstract updateGuiAfterMove(move : Move);

    static side(whoseTurn : boolean) : string {
        return whoseTurn?"White":"Black";
    }
}
ViewController["__class"] = "controller.ViewController";
ViewController["__interfaces"] = ["view.IChessViewerControl"];



export namespace ViewController {

    export class ViewController$0 extends HashMap<string, string> {
        /**
         * 
         */
        static serialVersionUID : number = 1;

        constructor() {
            super();
            (() => {
                this.put("castling", "Only under those circumstances, you can castling\n1.Your king and the corresponding rook has never been moved.\n2.There is no chessman between your king and rook.\n3.The squres that your king goes over should not under attack by any pieces of the opponent.\n4.Your king cannot be in check either before and after the castling.");
                this.put("pawn", "The pawn may move forward to the unoccupied square immediately in front of it on the same file without capturing, or advance two squares along the same file without capturing on its first move;or capture an opponent\'s piece on a square diagonally in front of it on an adjacent file.\nEn Passant and promotion are also special rules for pawn.");
                this.put("king", "The king moves one square in any direction. You always want to protect your king.\nCastling is also a special rule for king.");
                this.put("queen", "The queen combines the power of the rook and bishop and can move any number of squares along rank, file, or diagonal, without going over any pieces");
                this.put("rook", "The rook can move any number of squares along any rank or file without going over any pieces");
                this.put("bishop", "The bishop can move any number of squares diagonally, without going over any pieces.");
                this.put("knight", "The knight moves to any of the closest squares that are not on the same rank, file, or diagonal, thus the move forms an \"L\"-shape:");
                this.put("en passant", "En passant move:\nWhen a player moves a pawn 2 squares then on the very next move, the other player moves their pawn diagonally forward 1 square to the square that pawn moved through, capturing it in the process, the latter is said to be doing en passant. Note that the pawn does not move to the square of the pawn it captured in en passant.\n");
                this.put("promotion", "When a pawn reaches its eighth rank, it is immediately changed into the player\'s choice of a queen, knight, rook, or bishop of the same color.");
            })();
        }
    }
}




ViewController.rules_$LI$();
