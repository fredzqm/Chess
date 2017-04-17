export enum PieceType {
	KING,
	QUEEN,
	KNIGHT,
	BISHOP,
	ROOK,
	PAWN
}

export class PieceSquare {
	constructor(
		public type : PieceType, 
		public side : boolean
		) {}
}
