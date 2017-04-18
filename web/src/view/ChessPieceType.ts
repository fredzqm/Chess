/* Generated from Java with JSweet 1.2.0 - http://www.jsweet.org */
namespace view {
    export class ChessPieceType {
        public static Pawn : ChessPieceType; public static Pawn_$LI$() : ChessPieceType { if(ChessPieceType.Pawn == null) ChessPieceType.Pawn = new ChessPieceType(); return ChessPieceType.Pawn; };

        public static Rook : ChessPieceType; public static Rook_$LI$() : ChessPieceType { if(ChessPieceType.Rook == null) ChessPieceType.Rook = new ChessPieceType(); return ChessPieceType.Rook; };

        public static Bishop : ChessPieceType; public static Bishop_$LI$() : ChessPieceType { if(ChessPieceType.Bishop == null) ChessPieceType.Bishop = new ChessPieceType(); return ChessPieceType.Bishop; };

        public static Knight : ChessPieceType; public static Knight_$LI$() : ChessPieceType { if(ChessPieceType.Knight == null) ChessPieceType.Knight = new ChessPieceType(); return ChessPieceType.Knight; };

        public static Queen : ChessPieceType; public static Queen_$LI$() : ChessPieceType { if(ChessPieceType.Queen == null) ChessPieceType.Queen = new ChessPieceType(); return ChessPieceType.Queen; };

        public static King : ChessPieceType; public static King_$LI$() : ChessPieceType { if(ChessPieceType.King == null) ChessPieceType.King = new ChessPieceType(); return ChessPieceType.King; };

        public static from(type : string) : ChessPieceType {
            switch((javaemul.internal.CharacterHelper.toUpperCase(type))) {
            case 'P':
                return ChessPieceType.Pawn_$LI$();
            case 'R':
                return ChessPieceType.Rook_$LI$();
            case 'B':
                return ChessPieceType.Bishop_$LI$();
            case 'N':
                return ChessPieceType.Knight_$LI$();
            case 'Q':
                return ChessPieceType.Queen_$LI$();
            case 'K':
                return ChessPieceType.King_$LI$();
            default:
                throw new Error("Invalid chessPieceType: " + type);
            }
        }
    }
    ChessPieceType["__class"] = "view.ChessPieceType";

}


view.ChessPieceType.King_$LI$();

view.ChessPieceType.Queen_$LI$();

view.ChessPieceType.Knight_$LI$();

view.ChessPieceType.Bishop_$LI$();

view.ChessPieceType.Rook_$LI$();

view.ChessPieceType.Pawn_$LI$();
