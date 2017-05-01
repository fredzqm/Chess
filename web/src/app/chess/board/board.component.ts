import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AngularFire, FirebaseListObservable } from 'angularfire2';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit{
  pieces : any;

  @Input() ref: string;
  @Output() onSquareClicked : EventEmitter<any> = new EventEmitter<any>();

  constructor(private af: AngularFire) {
      // this.pieces = [];
      // for (let i = 0; i < 8; i++) {
      //     this.pieces[i] = [];
      //     for (let j = 0; j < 8; j++) {
      //         this.pieces[i][j] = {};
      //     }
      // }
  }

  ngOnInit() {
      console.log(this.ref);
      this.pieces = this.af.database.object(this.ref+'/white');
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
    // for (let row of this.pieces) {
    //   for (let x of row) {
    //     x.isHightLight = false;
    //   }
    // }
  }

}
