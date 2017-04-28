import { Routes } from '@angular/router';

import { ChessComponent } from './chess/chess.component';
import { RoomComponent } from './room/room.component';

export const routes: Routes = [
  { path: 'game', component: ChessComponent },
  { path: 'room/:id', component: ChessComponent},
  { path: '', component: RoomComponent }
];
