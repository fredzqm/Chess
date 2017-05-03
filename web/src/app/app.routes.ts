import { Routes } from '@angular/router';

import { ChessComponent } from './chess/chess.component';
import { RoomComponent } from './room/room.component';

export const routes: Routes = [
  { path: 'room/:id/:isWhite', component: ChessComponent},
  { path: '', component: RoomComponent }
];
