/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
/**
 * The interface representing the end state of the game
 * 
 * @author FredZhang
 */
export interface EndGame {
    /**
     * 
     * @return {number} the result of the game, 1 if the white wins, -1 if the black
     * wins, 0 if it is a draw
     */
    getResult() : number;

    /**
     * 
     * @return {string} the description of this end game
     */
    getDescript() : string;

    /**
     * 
     * @return {string} the necessary messages to be printed out
     */
    getPrintOut() : string;

    /**
     * 
     * @return {string} documentation in standard chess recording convention
     */
    getDoc() : string;
}


