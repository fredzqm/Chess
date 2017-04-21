/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Move } from '../model/Move'; 
import { IChessViewer } from '../view/IChessViewer'; 
import { ViewController } from './ViewController'; 
/**
 * start my little chess game!!!!
 * 
 * @param args
 * ignored
 * @param {view.IChessViewer} chessView
 * @class
 */
export class SingleViewChessControl extends ViewController {
    /*private*/ view : IChessViewer;

    public constructor(chessView : IChessViewer) {
        super();
        this.view = null;
        this.view = chessView;
        this.updateChessBoard();
    }

    public chooesView(whiteOrBlack : boolean) : IChessViewer {
        return this.view;
    }

    updateGuiAfterMove(previousMove : Move) {
        this.updateChessBoard();
        this.view.setStatusLabelText(this.chess.lastMoveDiscript());
        this.view.cleanTemp();
        this.view.printOut(this.chess.lastMoveOutPrint());
        this.view.printOut("Next move -- " + ViewController.side(!previousMove.getWhoseTurn()));
    }
}
SingleViewChessControl["__class"] = "controller.SingleViewChessControl";
SingleViewChessControl["__interfaces"] = ["view.IChessViewerControl"];




