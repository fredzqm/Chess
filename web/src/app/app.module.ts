import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AngularFireModule } from 'angularfire2';
import { RouterModule } from '@angular/router';

import { routes } from './app.routes';
import { AppComponent } from './app.component';
import { SquareComponent } from './chess/board/square/square.component';
import { BoardComponent } from './chess/board/board.component';
import { ConsoleComponent } from './chess/console/console.component';
import { ChessComponent } from './chess/chess.component';
import { firebaseConfig } from '../environments/firebase.config';
import { RoomComponent } from './room/room.component';

@NgModule({
  declarations: [
    AppComponent,
    SquareComponent,
    BoardComponent,
    ChessComponent,
    ConsoleComponent,
    RoomComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AngularFireModule.initializeApp(firebaseConfig),
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
