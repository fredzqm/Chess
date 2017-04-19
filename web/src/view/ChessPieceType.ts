/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
export class ChessPieceType {
    public static Pawn : ChessPieceType; public static Pawn_$LI$() : ChessPieceType { if(ChessPieceType.Pawn == null) ChessPieceType.Pawn = new ChessPieceType(); return ChessPieceType.Pawn; };

    public static Rook : ChessPieceType; public static Rook_$LI$() : ChessPieceType { if(ChessPieceType.Rook == null) ChessPieceType.Rook = new ChessPieceType(); return ChessPieceType.Rook; };

    public static Bishop : ChessPieceType; public static Bishop_$LI$() : ChessPieceType { if(ChessPieceType.Bishop == null) ChessPieceType.Bishop = new ChessPieceType(); return ChessPieceType.Bishop; };

    public static Knight : ChessPieceType; public static Knight_$LI$() : ChessPieceType { if(ChessPieceType.Knight == null) ChessPieceType.Knight = new ChessPieceType(); return ChessPieceType.Knight; };

    public static Queen : ChessPieceType; public static Queen_$LI$() : ChessPieceType { if(ChessPieceType.Queen == null) ChessPieceType.Queen = new ChessPieceType(); return ChessPieceType.Queen; };

    public static King : ChessPieceType; public static King_$LI$() : ChessPieceType { if(ChessPieceType.King == null) ChessPieceType.King = new ChessPieceType(); return ChessPieceType.King; };

    public static from(type : string) : ChessPieceType {
        switch((javaemul.internal.CharacterHelper.toUpperCase(type))) {
        case 80 /* 'P' */:
            return ChessPieceType.Pawn_$LI$();
        case 82 /* 'R' */:
            return ChessPieceType.Rook_$LI$();
        case 66 /* 'B' */:
            return ChessPieceType.Bishop_$LI$();
        case 78 /* 'N' */:
            return ChessPieceType.Knight_$LI$();
        case 81 /* 'Q' */:
            return ChessPieceType.Queen_$LI$();
        case 75 /* 'K' */:
            return ChessPieceType.King_$LI$();
        default:
            throw new Error("Invalid chessPieceType: " + type);
        }
    }
}
ChessPieceType["__class"] = "view.ChessPieceType";




ChessPieceType.King_$LI$();

ChessPieceType.Queen_$LI$();

ChessPieceType.Knight_$LI$();

ChessPieceType.Bishop_$LI$();

ChessPieceType.Rook_$LI$();

ChessPieceType.Pawn_$LI$();
