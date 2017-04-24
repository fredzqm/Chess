import { Routes } from '@angular/router';

import { ChessComponent } from './chess/chess.component';
import { RoomComponent } from './room/room.component';

export const routes: Routes = [
  { path: '', component: ChessComponent },
  { path: 'room', component: RoomComponent }
];
