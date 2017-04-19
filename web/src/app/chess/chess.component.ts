import { Component, OnInit, ViewChild, Output } from '@angular/core';
import { BoardComponent } from '../board/board.component';
import { ConsoleComponent } from '../console/console.component';

@Component({
  selector: 'app-chess',
  templateUrl: './chess.component.html',
  styleUrls: ['./chess.component.css']
})
export class ChessComponent implements OnInit, view.IChessViewer {
  title = 'app works!';
  status: string;
  controller : controller.SingleViewChessControl;

  @ViewChild("board") board : BoardComponent;
  @ViewChild("console") console : ConsoleComponent;
   
  constructor() {}

  ngOnInit() {
    this.controller = new controller.SingleViewChessControl(this);
  }

  click(event : any) {
    console.log("click event: " + event);
    this.controller.click(event.file, event.rank, event.whiteOrBlack);
  }

  /**
   * print the message in the console.
   * 
   * @param message
   */
  printOut(message : string) {
    console.log("printOut " + message);
  }

  /**
   * print out the temporal string in the console, without updating the
   * record. The temp string printed can be clear with
   * {@link ChessViewer#cleanTemp()}
   * 
   * @param temp
   */
  printTemp(temp : string) {
    console.log("printOut " + temp);

  }

  /**
   * erase the temporal string in the console printed by
   * {@link ChessViewer#printTemp(String)}
   */
  cleanTemp() {
    console.log('cleanTemp');
  }

  /**
   * 
   * @param str
   */
  setStatusLabelText(str : string) {
    this.status = str;
  }

  /**
   * hightlight the square at this position
   * 
   * @param file
   * @param rank
   */
  highLight(file : number, rank : number) {

  }

  /**
   * dehighlight the whole board
   */
  deHighLightWholeBoard() {

  }

  /**
   * ask the user for a resonse
   * 
   * @param message
   * @return the responsded string
   */
  getResponse(message : string) : string {
    return "this is a reponse";
  }

  /**
   * refresh the UI
   */
  repaint() {

  }

  /**
   * update Piece at this position
   * 
   * @param file
   * @param rank
   * @param pieceType
   * @param whiteOrBlack
   */
  upDatePiece(file : number, rank : number, pieceType : view.ChessPieceType, whiteOrBlack : boolean) {
    
  }

  /**
   * clear piece at certain square
   * 
   * @param file
   * @param rank
   */
  clearLabel(file : number, rank : number) {
    console.log('clearLabel');
  }
}
