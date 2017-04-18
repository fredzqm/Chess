/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace view {
    /**
     * Interface for a ChessView that is compatible with ViewController
     * 
     * @author zhang
     */
    export interface IChessViewer {
        /**
         * print the message in the console.
         * 
         * @param message
         */
        printOut(message : string);

        /**
         * print out the temporal string in the console, without updating the
         * record. The temp string printed can be clear with
         * {@link ChessViewer#cleanTemp()}
         * 
         * @param temp
         */
        printTemp(temp : string);

        /**
         * erase the temporal string in the console printed by
         * {@link ChessViewer#printTemp(String)}
         */
        cleanTemp();

        /**
         * 
         * @param str
         */
        setStatusLabelText(str : string);

        /**
         * hightlight the square at this position
         * 
         * @param file
         * @param rank
         */
        highLight(file : number, rank : number);

        /**
         * dehighlight the whole board
         */
        deHighLightWholeBoard();

        /**
         * ask the user for a resonse
         * 
         * @param message
         * @return the responsded string
         */
        getResponse(message : string) : string;

        /**
         * refresh the UI
         */
        repaint();

        /**
         * update Piece at this position
         * 
         * @param file
         * @param rank
         * @param pieceType
         * @param whiteOrBlack
         */
        upDatePiece(file : number, rank : number, pieceType : ChessPieceType, whiteOrBlack : boolean);

        /**
         * clear piece at certain square
         * 
         * @param file
         * @param rank
         */
        clearLabel(file : number, rank : number);
    }
}

