/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace view {
    export interface IChessViewerControl {
        /**
         * Invoked when view receive input from command line
         * 
         * @param input
         * @param isWhiteView
         */
        handleCommand(input : string, isWhiteView : boolean);

        /**
         * Invoked when chess board is clicked
         * 
         * @param file
         * the file of this square clicked
         * @param rank
         * the rank of this square clicked
         * @param whiteOrBlack
         * the orientation of the view
         */
        click(file : number, rank : number, whiteOrBlack : boolean);
    }
}

