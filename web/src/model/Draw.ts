/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { EndGame } from './EndGame'; 
/**
 * End game type Draw
 * We can even add more complex draw type later, and change their outputs
 * 
 * @author FredZhang
 */
export class Draw implements EndGame {
    public static STALEMATE : Draw; public static STALEMATE_$LI$() : Draw { if(Draw.STALEMATE == null) Draw.STALEMATE = new Draw("Stalement", "Draw due to Stalement."); return Draw.STALEMATE; };

    public static FIFTY_MOVE : Draw; public static FIFTY_MOVE_$LI$() : Draw { if(Draw.FIFTY_MOVE == null) Draw.FIFTY_MOVE = new Draw("Quite", "Fifty-move rule."); return Draw.FIFTY_MOVE; };

    public static REPETITION : Draw; public static REPETITION_$LI$() : Draw { if(Draw.REPETITION == null) Draw.REPETITION = new Draw("Repetition", "threefold repetition."); return Draw.REPETITION; };

    public static AGREEMENT : Draw; public static AGREEMENT_$LI$() : Draw { if(Draw.AGREEMENT == null) Draw.AGREEMENT = new Draw("Agreement to draw", "Draw by Agreement."); return Draw.AGREEMENT; };

    /*private*/ descript : string;

    /*private*/ printOut : string;

    constructor(descript : string, printOut : string) {
        this.descript = null;
        this.printOut = null;
        this.descript = descript;
        this.printOut = printOut;
    }

    public getResult() : number {
        return 0;
    }

    public getDescript() : string {
        return this.descript;
    }

    public getPrintOut() : string {
        return this.printOut;
    }

    public getDoc() : string {
        if(this === Draw.AGREEMENT_$LI$()) {
            return "1/2-1/2 (agreement)";
        } else {
            return "1/2-1/2";
        }
    }
}
Draw["__class"] = "model.Draw";
Draw["__interfaces"] = ["model.EndGame"];





Draw.AGREEMENT_$LI$();

Draw.REPETITION_$LI$();

Draw.FIFTY_MOVE_$LI$();

Draw.STALEMATE_$LI$();
