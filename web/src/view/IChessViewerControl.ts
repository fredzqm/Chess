/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
export interface IChessViewerControl {
    /**
     * Invoked when view receive input from command line
     * 
     * @param {string} input
     * @param {boolean} isWhiteView
     */
    handleCommand(input : string, isWhiteView : boolean);

    /**
     * Invoked when chess board is clicked
     * 
     * @param {number} file
     * the file of this square clicked
     * @param {number} rank
     * the rank of this square clicked
     * @param {boolean} whiteOrBlack
     * the orientation of the view
     */
    click(file : number, rank : number, whiteOrBlack : boolean);
}


