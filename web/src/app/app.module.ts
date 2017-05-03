import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AngularFireModule } from 'angularfire2';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from '@angular/material';
import {FlexLayoutModule} from "@angular/flex-layout";

import { routes } from './app.routes';
import { AppComponent } from './app.component';
import { SquareComponent } from './chess/board/square/square.component';
import { BoardComponent } from './chess/board/board.component';
import { ConsoleComponent } from './chess/console/console.component';
import { ChessComponent } from './chess/chess.component';
import { firebaseConfig } from '../environments/firebase.config';
import { RoomComponent, DialogContent } from './room/room.component';


@NgModule({
  declarations: [
    AppComponent,
    SquareComponent,
    BoardComponent,
    ChessComponent,
    ConsoleComponent,
    RoomComponent,
    DialogContent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    AngularFireModule.initializeApp(firebaseConfig),
    RouterModule.forRoot(routes),
    MaterialModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [DialogContent]
})
export class AppModule { }
