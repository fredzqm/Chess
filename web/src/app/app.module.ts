import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from '@angular/material';
import {FlexLayoutModule} from "@angular/flex-layout";

import { routes } from './app.routes';
import { AppComponent } from './app.component';
import { SquareComponent } from './chess/board/square/square.component';
import { BoardComponent } from './chess/board/board.component';
import { ChessComponent, DrawDialogContent, PromotionDialogContent } from './chess/chess.component';
import { firebaseConfig } from '../environments/firebase.config';
import { RoomComponent, DialogContent } from './room/room.component';

@NgModule({
  declarations: [
    AppComponent,
    SquareComponent,
    BoardComponent,
    ChessComponent,
    DrawDialogContent,
    PromotionDialogContent,
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
    AngularFireDatabaseModule,
    RouterModule.forRoot(routes),
    MaterialModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [DrawDialogContent, PromotionDialogContent, DialogContent]
})
export class AppModule { }
