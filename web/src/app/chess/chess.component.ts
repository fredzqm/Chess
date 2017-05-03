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
  id: any;
  player: any;
  isWhite: any;

  @ViewChild("board") board : BoardComponent;
  @ViewChild("console") console : ConsoleComponent;

  constructor(private route: ActivatedRoute, private af: AngularFire) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
       this.id = params['id'];
       this.isWhite = params['isWhite'] == 'white' ? 'white' : 'black';
       this.player = this.af.database.object('/' + this.id + '/' + this.isWhite);
    });
  }

  click(event : any) {
    this.af.database.object('/' + this.id +'/' + this.isWhite + '/action').set({click: {i: event.i, j: event.j}});
  }
}
