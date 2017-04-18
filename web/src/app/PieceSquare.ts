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

	getImagePosition() : string {
		switch (this.type) {
			case PieceType.KING:
				return "0 0";
			default:
				return "0 0";
		}
	}
}
