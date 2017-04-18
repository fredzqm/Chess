/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    export enum MoveNote {
        NONE, CHECK, CHECKMATE, GOOD, BAD
    }

    export class MoveNote_$WRAPPER {
        doc;

        descript;

        constructor(private _$ordinal : number, private _$name : string, docEnd, descriptEnd) {
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

}

