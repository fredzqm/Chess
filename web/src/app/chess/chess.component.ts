import { Component, OnInit, ViewChild, Output } from '@angular/core';
import { BoardComponent } from './board/board.component';
import { ConsoleComponent } from './console/console.component';
import { IChessViewer } from '../../view/IChessViewer';
import { SingleViewChessControl } from '../../controller/SingleViewChessControl';

@Component({
  selector: 'app-chess',
  templateUrl: './chess.component.html',
  styleUrls: ['./chess.component.css']
})
export class ChessComponent implements OnInit, IChessViewer {
  status: string;
  controller : SingleViewChessControl;

  @ViewChild("board") board : BoardComponent;
  @ViewChild("console") console : ConsoleComponent;
   
  constructor() {
    this.status  = "Welcome to my wonderful chess game!";
  }

  ngOnInit() {
    this.controller = new SingleViewChessControl(this);
  }

  click(event : any) {
    this.controller.click(event.file, event.rank, event.whiteOrBlack);
  }

  /**
   * print the message in the console.
   * 
   * @param message
   */
  printOut(message : string) {
    this.console.printOut(message);
  }

  /**
   * print out the temporal string in the console, without updating the
   * record. The temp string printed can be clear with
   * {@link ChessViewer#cleanTemp()}
   * 
   * @param temp
   */
  printTemp(temp : string) {
    this.console.printTemp(temp);
  }

  /**
   * erase the temporal string in the console printed by
   * {@link ChessViewer#printTemp(String)}
   */
  cleanTemp() {
    this.console.cleanTemp();
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
    this.board.highLight(file, rank);
  }

  /**
   * dehighlight the whole board
   */
  deHighLightWholeBoard() {
    this.board.deHighLightWholeBoard();
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
  repaint() {}

  /**
   * update Piece at this position
   * 
   * @param file
   * @param rank
   * @param pieceType
   * @param whiteOrBlack
   */
  upDatePiece(file : number, rank : number, pieceType : string, whiteOrBlack : boolean) {
    this.board.updateSquare(file, rank, pieceType, whiteOrBlack);
  }

  /**
   * clear piece at certain square
   * 
   * @param file
   * @param rank
   */
  clearLabel(file : number, rank : number) {
    this.board.updateSquare(file, rank, null, null);
  }
}
