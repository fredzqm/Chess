import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AngularFire, FirebaseObjectObservable } from 'angularfire2';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit{
  pieces : any;
  firebaseRef: FirebaseObjectObservable<any>;

  @Input() ref: string;
  @Output() onSquareClicked : EventEmitter<any> = new EventEmitter<any>();

  constructor(private af: AngularFire) {

  }

  ngOnInit() {
      console.log(this.ref);
      this.firebaseRef = this.af.database.object(this.ref);
      this.firebaseRef.subscribe((data)=>{
        this.pieces = [];
        let board = data.val();
        console.log(board);
        for (let i = 1; i <= 8; i++) {
          this.pieces.append([]);
          let row = board[""+i];
          for (let j = 1; j <= 8; j++) {
            this.pieces[i].append(row[""+j]);
          }
        }
      });
  }

  onSquareClick(i, j) {
    // this.onSquareClicked.emit({
    //   file: i + 1,
    //   rank: 8-j,
    //   whiteOrBlack : true
    // });
  }

  getPieceAt(file : number, rank : number) : any {
    return this.pieces[rank-1][file-1];
  }

  updateSquare(file : number, rank : number, pieceType : string, whiteOrBlack : boolean) {
    this.getPieceAt(file, rank).type = pieceType;
    this.getPieceAt(file, rank).isWhite = whiteOrBlack;
  }

  clearSquare(file : number, rank : number) {
    // this.pieces[8-rank][file-1] = null;
  }

  highLight(file : number, rank : number) {
    // this.getPieceAt(file, rank).isHightLight = true;
  }

  deHighLightWholeBoard() {
    // for (let row of this.pieces) {
    //   for (let x of row) {
    //     x.isHightLight = false;
    //   }
    // }
  }

}
