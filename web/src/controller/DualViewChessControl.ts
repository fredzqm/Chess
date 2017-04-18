/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace controller {
    import Move = model.Move;

    import Player = model.Piece.Player;

    import IChessViewer = view.IChessViewer;

    /**
     * 
     * A chess controller that control two ChessView, each views the board
     * separately
     * 
     * @author zhang
     */
    export class DualViewChessControl extends ViewController {
        private whiteView : IChessViewer;

        private blackView : IChessViewer;

        /**
         * start my little chess game!!!!
         * 
         * @param args
         * ignored
         */
        public constructor() {
            super();
            this.updateChessBoard();
        }

        public chooesView(whiteOrBlack : boolean) : IChessViewer {
            return whiteOrBlack?this.whiteView:this.blackView;
        }

        updateGuiAfterMove(previousMove : Move) {
            this.updateChessBoard();
            let pre : IChessViewer = this.chooesView(previousMove.getWhoseTurn() === Player.WHITE);
            let next : IChessViewer = this.chooesView(previousMove.getWhoseTurn() === Player.BLACK);
            pre.setStatusLabelText(this.chess.lastMoveDiscript());
            next.setStatusLabelText(this.chess.lastMoveDiscript());
            pre.cleanTemp();
            pre.printOut(this.chess.lastMoveOutPrint());
            next.printOut(this.chess.lastMoveOutPrint());
            next.printOut("Please make your move.");
            pre.printOut("Wait for the " + ViewController.side(previousMove.getWhoseTurn() === Player.BLACK) + " to make a move");
        }

        public static main(args : string[]) {
            new DualViewChessControl();
        }
    }
    DualViewChessControl["__class"] = "controller.DualViewChessControl";
    DualViewChessControl["__interfaces"] = ["view.IChessViewerControl"];


}


controller.DualViewChessControl.main(null);
