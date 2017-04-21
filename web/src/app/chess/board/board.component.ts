import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent  {
  pieces : any[][];

  @Output() onSquareClicked : EventEmitter<any> = new EventEmitter<any>();

  constructor() {
      this.pieces = [];
      for (let i = 0; i < 8; i++) {
          this.pieces[i] = [];
          for (let j = 0; j < 8; j++) {
              this.pieces[i][j] = {};
          }
      }
  }

  onSquareClick(i, j) {
    this.onSquareClicked.emit({
      file: i + 1,
      rank: 8-j,
      whiteOrBlack : true
    });
  }

  getPieceAt(file : number, rank : number) : any {
    return this.pieces[8-rank][file-1];
  }

  updateSquare(file : number, rank : number, pieceType : string, whiteOrBlack : boolean) {
    console.log(this.pieces);
    this.getPieceAt(file, rank).type = pieceType;
    this.getPieceAt(file, rank).isWhite = whiteOrBlack;
  }

  clearSquare(file : number, rank : number) {
    this.pieces[8-rank][file-1] = null;
  }

  highLight(file : number, rank : number) {
    this.getPieceAt(file, rank).isHightLight = true;
  }

  deHighLightWholeBoard() {
    for (let row of this.pieces) {
      for (let x of row) {
        x.isHightLight = false;
      }
    }
  }

}
