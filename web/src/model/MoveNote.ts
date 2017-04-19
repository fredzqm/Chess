/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
export enum MoveNote {
    NONE, CHECK, CHECKMATE, GOOD, BAD
}

export class MoveNote_$WRAPPER {
    doc;

    descript;

    constructor(protected _$ordinal : number, protected _$name : string, docEnd, descriptEnd) {
        this.doc = null;
        this.descript = null;
        this.doc = docEnd;
        this.descript = descriptEnd;
    }

    public getDocEnd() : string {
        return this.doc;
    }

    public getDescriptEnd() : string {
        return this.descript;
    }
    public name() : string { return this._$name; }
    public ordinal() : number { return this._$ordinal; }
}
MoveNote["__class"] = "model.MoveNote";
MoveNote["__interfaces"] = ["java.lang.Comparable","java.io.Serializable"];

MoveNote["_$wrappers"] = [new MoveNote_$WRAPPER(0, "NONE", "", ""), new MoveNote_$WRAPPER(1, "CHECK", "+", " Check!"), new MoveNote_$WRAPPER(2, "CHECKMATE", "++", " CheckMate!!"), new MoveNote_$WRAPPER(3, "GOOD", "!", "good move!"), new MoveNote_$WRAPPER(4, "BAD", "?", "bad move")];



