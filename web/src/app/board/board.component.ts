import { Component, OnInit } from '@angular/core';
import { PieceSquare, PieceType } from '../PieceSquare';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {
  pieces : PieceSquare[][];

  constructor() {
      this.pieces = [];
      for (let i = 0; i < 8; i++) {
          this.pieces[i] = [];
          for (let j = 0; j < 8; j++) {
              this.pieces[i][j] = this.getInitialPiece(j+1, i+1);
          }
      }
      console.log(this.pieces);
  }

  getInitialPiece(i : number, j : number) : PieceSquare {
      if (j === 2)
          return new PieceSquare(PieceType.PAWN, true);
      if (j === 7)
          return new PieceSquare(PieceType.PAWN, false);
      let isWhite : boolean = null;
      if (j === 1)
          isWhite = true;
      if (j === 8)
          isWhite = false;
      if (isWhite === null)
          return null;
      if (i === 1 || i === 8)
          return new PieceSquare(PieceType.ROOK, isWhite);
      if (i === 2 || i === 7)
          return new PieceSquare(PieceType.KNIGHT, isWhite);
      if (i === 3 || i === 6)
          return new PieceSquare(PieceType.BISHOP, isWhite);
      if (i === 4)
          return new PieceSquare(PieceType.QUEEN, isWhite);
      if (i === 5)
          return new PieceSquare(PieceType.KING, isWhite);
      throw "invalid coordinate (" + i + ", "+ j + ")";
  }

  ngOnInit() {

  }

}
