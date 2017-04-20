import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { PieceSquare } from './square/pieceSquare';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent  {
  pieces : PieceSquare[][];

  @Output() click : EventEmitter<any> = new EventEmitter<any>();

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
    this.click.emit({
      file: pos.i + 1,
      rank: 8-pos.j,
      whiteOrBlack : true
    });
  }

  updateSquare(file : number, rank : number, pieceType : string, whiteOrBlack : boolean) {
    this.pieces[8-rank][file-1] = new PieceSquare(pieceType, whiteOrBlack);
  }

}
