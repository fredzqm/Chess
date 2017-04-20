import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { SquareData } from './square/squareData';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent  {
  pieces : SquareData[][];

  @Output() onSquareClicked : EventEmitter<any> = new EventEmitter<any>();

  constructor() {
      this.pieces = [];
      for (let i = 0; i < 8; i++) {
          this.pieces[i] = [];
          for (let j = 0; j < 8; j++) {
              this.pieces[i][j] = null;
          }
      }
  }

  onSquareClick(pos) {
    this.onSquareClicked.emit({
      file: pos.i + 1,
      rank: 8-pos.j,
      whiteOrBlack : true
    });
  }

  setPieceAt(file : number, rank : number, piece : SquareData) {
    this.pieces[8-rank][file-1] = piece;
  }

  updateSquare(file : number, rank : number, pieceType : string, whiteOrBlack : boolean) {
    this.setPieceAt(file, rank, pieceType? new SquareData(pieceType, whiteOrBlack) : null);
  }

  clearSquare(file : number, rank : number) {
    this.pieces[8-rank][file-1] = null;
  }

}
