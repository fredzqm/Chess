/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace model {
    export class InvalidMoveException extends Error {
        public type : InvalidMoveException.Type;

        public constructor(message : string, type : InvalidMoveException.Type) {
            super(message + " : " + model.InvalidMoveException.Type["_$wrappers"][type].toString()); this.message=message + " : " + model.InvalidMoveException.Type["_$wrappers"][type].toString();
            this.type = type;
        }

        /**
         * 
         */
        static serialVersionUID : number = 1;
    }
    InvalidMoveException["__class"] = "model.InvalidMoveException";
    InvalidMoveException["__interfaces"] = ["java.io.Serializable"];



    export namespace InvalidMoveException {

        export enum Type {
            invalidFormat, castleNotAllowed, pieceNotPresent, incorrectPiece, impossibleMove, ambiguousMove, promotionTo
        }
    }

}

