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

	getX() : number {
		switch (this.type) {
			case PieceType.KING:
				return 0;
			case PieceType.QUEEN:
				return -65;
			case PieceType.BISHOP:
				return -131;
			case PieceType.KNIGHT:
				return -198;
			case PieceType.ROOK:
				return -264;
			case PieceType.PAWN:
				return -330;
		}
	}

	getY() : number {
		if (this.side)
			return 0;
		else
			return -60;
	}

	getImagePosition() : string {
		return this.getX() + "px " + this.getY() + "px";
	}
}
