/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
export class DrawManager {
    /*private*/ whiteCanDraw : boolean;

    /*private*/ blackCanDraw : boolean;

    public constructor() {
        this.whiteCanDraw = false;
        this.blackCanDraw = false;
        this.whiteCanDraw = true;
        this.blackCanDraw = true;
    }

    public canAskFordraw(whoseTurn : boolean) : boolean {
        if(whoseTurn) return this.whiteCanDraw; else return this.blackCanDraw;
    }

    public setRightToRequestDraw(whoseTurn : boolean) {
        if(whoseTurn) {
            this.whiteCanDraw = false;
            this.blackCanDraw = true;
        } else {
            this.whiteCanDraw = true;
            this.blackCanDraw = false;
        }
    }
}
DrawManager["__class"] = "controller.DrawManager";



