
export class Piece {
  isHightLight: boolean;
  isWhite: boolean;
  type: string;
}

export class Request {
  askForDraw?: boolean;
  promotionTo?: boolean;
}

export class Game {
  status: string;
  whiteOrBlack: boolean;
  pieces: Piece[][];
  moves: string[];
  request: Request;
}

export class Room {
  $key?: string;
  name: string;
  game?: Game;
}
