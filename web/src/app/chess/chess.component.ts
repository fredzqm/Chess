import { Component, OnInit, ViewChild, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AngularFire, FirebaseListObservable } from 'angularfire2';
import { BoardComponent } from './board/board.component';
import { ConsoleComponent } from './console/console.component';

@Component({
  selector: 'app-chess',
  templateUrl: './chess.component.html',
  styleUrls: ['./chess.component.css']
})
export class ChessComponent implements OnInit {
  status: string;
  id: any;
  player: any;

  @ViewChild("board") board : BoardComponent;
  @ViewChild("console") console : ConsoleComponent;
   
  constructor(private route: ActivatedRoute, private af: AngularFire) {
    this.status  = "Welcome to my wonderful chess game!";
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
       this.id = params['id'];
       const isWhite = params['isWhite'] == 'white' ? 'white' : 'black';
       this.player = this.af.database.object('/' + this.id + '/' + isWhite);
    });
  }

  click(event : any) {
    const whiteOrBlack = event.whiteOrBlack ? "white" : "black";
    this.af.database.object('/' + this.id +'/' + whiteOrBlack + '/action').set({click: {file: event.file, rank: event.rank}});
  }
}
