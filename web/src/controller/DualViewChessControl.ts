/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { Move } from '../model/Move'; 
import { IChessViewer } from '../view/IChessViewer'; 
import { ViewController } from './ViewController'; 
/**
 * start my little chess game!!!!
 * 
 * @param args
 * ignored
 * @param {view.IChessViewer} whiteView
 * @param {view.IChessViewer} blackView
 * @class
 */
export class DualViewChessControl extends ViewController {
    /*private*/ whiteView : IChessViewer;

    /*private*/ blackView : IChessViewer;

    public constructor(whiteView : IChessViewer, blackView : IChessViewer) {
        super();
        this.whiteView = null;
        this.blackView = null;
        this.whiteView = whiteView;
        this.blackView = blackView;
        this.updateChessBoard();
    }

    public chooesView(whiteOrBlack : boolean) : IChessViewer {
        return whiteOrBlack?this.whiteView:this.blackView;
    }

    updateGuiAfterMove(previousMove : Move) {
        this.updateChessBoard();
        let pre : IChessViewer = this.chooesView(previousMove.getWhoseTurn());
        let next : IChessViewer = this.chooesView(!previousMove.getWhoseTurn());
        pre.setStatusLabelText(this.chess.lastMoveDiscript());
        next.setStatusLabelText(this.chess.lastMoveDiscript());
        pre.cleanTemp();
        pre.printOut(this.chess.lastMoveOutPrint());
        next.printOut(this.chess.lastMoveOutPrint());
        next.printOut("Please make your move.");
        pre.printOut("Wait for the " + ViewController.side(!previousMove.getWhoseTurn()) + " to make a move");
    }
}
DualViewChessControl["__class"] = "controller.DualViewChessControl";
DualViewChessControl["__interfaces"] = ["view.IChessViewerControl"];




