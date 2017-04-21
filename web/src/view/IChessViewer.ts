/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
/**
 * Interface for a ChessView that is compatible with ViewController
 * 
 * @author zhang
 */
export interface IChessViewer {
    /**
     * print the message in the console.
     * 
     * @param {string} message
     */
    printOut(message : string);

    /**
     * print out the temporal string in the console, without updating the
     * record. The temp string printed can be clear with
     * {@link ChessViewer#cleanTemp()}
     * 
     * @param {string} temp
     */
    printTemp(temp : string);

    /**
     * erase the temporal string in the console printed by
     * {@link ChessViewer#printTemp(String)}
     */
    cleanTemp();

    /**
     * 
     * @param {string} str
     */
    setStatusLabelText(str : string);

    /**
     * hightlight the square at this position
     * 
     * @param {number} file
     * @param {number} rank
     */
    highLight(file : number, rank : number);

    /**
     * dehighlight the whole board
     */
    deHighLightWholeBoard();

    /**
     * ask the user for a resonse
     * 
     * @param {string} message
     * @return {string} the responsded string
     */
    getResponse(message : string) : string;

    /**
     * refresh the UI
     */
    repaint();

    /**
     * update Piece at this position
     * 
     * @param {number} file
     * @param {number} rank
     * @param {string} pieceType
     * @param {boolean} whiteOrBlack
     */
    upDatePiece(file : number, rank : number, pieceType : string, whiteOrBlack : boolean);

    /**
     * clear piece at certain square
     * 
     * @param {number} file
     * @param {number} rank
     */
    clearLabel(file : number, rank : number);
}


