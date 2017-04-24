import { Component, OnInit, ViewChild } from '@angular/core';
import { AngularFire, FirebaseListObservable } from 'angularfire2';
import { ChessService } from './app.chess.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ChessService]
})

export class AppComponent implements OnInit {
  items: FirebaseListObservable<any[]>;
  somebody: any[] = [];

  constructor(af: AngularFire, private _chessService : ChessService) {
    this.items = af.database.list('/items');
    this.somebody = _chessService.getAll();
  }

  ngOnInit() {

  }

}
