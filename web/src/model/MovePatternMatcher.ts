/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { IMovePatternMatcher } from './IMovePatternMatcher'; 
export class MovePatternMatcher implements IMovePatternMatcher {
    /*private*/ matcher : string[];
    
    public constructor(moveCommand : string) {
        var myRegexp = /([PRNBQK])?([a-h])?([1-8])?([-x])?([a-h][1-8])(=[RNBQ])?(.*)/g;
        this.matcher = myRegexp.exec(moveCommand);
    }

    public getGroup(i : number) : string {
        return this.matcher[i];
    }

    public matches() : boolean {
        return this.matcher != null;
    }
}
MovePatternMatcher["__class"] = "model.MovePatternMatcher";
MovePatternMatcher["__interfaces"] = ["model.IMovePatternMatcher"];




