import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AngularFireModule } from 'angularfire2';

import { AppComponent } from './app.component';
import { SquareComponent } from './chess/board/square/square.component';
import { BoardComponent } from './chess/board/board.component';
import { ConsoleComponent } from './chess/console/console.component';
import { ChessComponent } from './chess/chess.component';
import { firebaseConfig } from '../environments/firebase.config';

@NgModule({
  declarations: [
    AppComponent,
    SquareComponent,
    BoardComponent,
    ChessComponent,
    ConsoleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AngularFireModule.initializeApp(firebaseConfig)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
