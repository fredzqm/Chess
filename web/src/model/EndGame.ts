/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    /**
     * The interface representing the end state of the game
     * 
     * @author FredZhang
     */
    export interface EndGame {
        /**
         * 
         * @return the result of the game, 1 if the white wins, -1 if the black
         * wins, 0 if it is a draw
         */
        getResult() : number;

        /**
         * 
         * @return the description of this end game
         */
        getDescript() : string;

        /**
         * 
         * @return the necessary messages to be printed out
         */
        getPrintOut() : string;

        /**
         * 
         * @return documentation in standard chess recording convention
         */
        getDoc() : string;
    }
}

