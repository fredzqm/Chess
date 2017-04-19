/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
export class InvalidMoveException extends Error {
    public static invalidFormat : string = "invalidFormat";

    public static castleNotAllowed : string = "castleNotAllowed";

    public static pieceNotPresent : string = "pieceNotPresent";

    public static incorrectPiece : string = "incorrectPiece";

    public static impossibleMove : string = "impossibleMove";

    public static ambiguousMove : string = "ambiguousMove";

    public static promotionTo : string = "promotionTo";

    public type : string;

    public constructor(message : string, type : string) {
        super(message + " : " + type.toString()); this.message=message + " : " + type.toString();
        this.type = null;
        this.type = type;
    }

    /**
     * 
     */
    static serialVersionUID : number = 1;
}
InvalidMoveException["__class"] = "model.InvalidMoveException";
InvalidMoveException["__interfaces"] = ["java.io.Serializable"];




