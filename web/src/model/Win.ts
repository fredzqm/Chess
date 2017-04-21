/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { EndGame } from './EndGame'; 
/**
 * End game type Win We can even add more complex draw type later, and change
 * their outputs
 * 
 * @author FredZhang
 */
export class Win implements EndGame {
    public static WHITECHECKMATE : Win; public static WHITECHECKMATE_$LI$() : Win { if(Win.WHITECHECKMATE == null) Win.WHITECHECKMATE = new Win(true, "White wins! -- CHECKMATE!!", "WHITE Checkmates the BLACK\n WHITE wins!!"); return Win.WHITECHECKMATE; };

    public static BLACKCHECKMATE : Win; public static BLACKCHECKMATE_$LI$() : Win { if(Win.BLACKCHECKMATE == null) Win.BLACKCHECKMATE = new Win(false, "Black wins! -- CHECKMATE!!", "BLACK Checkmates the WHITE\n BLACK wins!!"); return Win.BLACKCHECKMATE; };

    public static WHITERESIGN : Win; public static WHITERESIGN_$LI$() : Win { if(Win.WHITERESIGN == null) Win.WHITERESIGN = new Win(false, "White resigns! -- Black wins!", "White resigns\n Black wins."); return Win.WHITERESIGN; };

    public static BLACKESIGN : Win; public static BLACKESIGN_$LI$() : Win { if(Win.BLACKESIGN == null) Win.BLACKESIGN = new Win(true, "Black resigns! -- White wins!", "Black resigns\n White wins"); return Win.BLACKESIGN; };

    /*private*/ winner : number;

    /*private*/ descript : string;

    /*private*/ printOut : string;

    constructor(who : boolean, descript : string, printOut : string) {
        this.winner = 0;
        this.descript = null;
        this.printOut = null;
        if(who) this.winner = 1; else this.winner = -1;
        this.descript = descript;
        this.printOut = printOut;
    }

    public getResult() : number {
        return this.winner;
    }

    public getDescript() : string {
        return this.descript;
    }

    public getPrintOut() : string {
        return this.printOut;
    }

    public getDoc() : string {
        if(this === Win.WHITECHECKMATE_$LI$()) return "1-0";
        if(this === Win.BLACKCHECKMATE_$LI$()) return "0-1";
        if(this === Win.BLACKESIGN_$LI$()) return "1-0 (resign)";
        if(this === Win.WHITERESIGN_$LI$()) return "1-1 (resign)";
        throw new Error("What the hell!");
    }
}
Win["__class"] = "model.Win";
Win["__interfaces"] = ["model.EndGame"];





Win.BLACKESIGN_$LI$();

Win.WHITERESIGN_$LI$();

Win.BLACKCHECKMATE_$LI$();

Win.WHITECHECKMATE_$LI$();
