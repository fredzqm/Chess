import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AngularFireModule } from 'angularfire2';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFirestoreModule } from 'angularfire2/firestore';
import { AngularFireStorageModule } from 'angularfire2/storage';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { MatSelectModule } from '@angular/material/select';
import {MatButtonModule } from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCardModule} from '@angular/material/card';


import { routes } from './app.routes';
import { AppComponent } from './app.component';
import { SquareComponent } from './chess/board/square/square.component';
import { BoardComponent } from './chess/board/board.component';
import { ChessComponent, DrawDialogContent, PromotionDialogContent } from './chess/chess.component';
import { environment  } from '../environments/environment';
import { RoomComponent, DialogContent } from './room/room.component';
import {MatToolbarModule} from '@angular/material';

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
    // FlexLayoutModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule, // imports firebase/firestore, only needed for database features
    AngularFireAuthModule, // imports firebase/auth, only needed for auth features,
    AngularFireStorageModule, // imports firebase/storage only needed for storage features
    RouterModule.forRoot(routes),
    MatDialogModule,
    MatSelectModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [DrawDialogContent, PromotionDialogContent, DialogContent],
  // schemas:
})
export class AppModule { }
