import { Component, OnInit, Optional } from '@angular/core';
import { MdDialog, MdDialogRef } from '@angular/material';
import {AngularFireDatabase, FirebaseListObservable} from 'angularfire2/database';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  rooms: FirebaseListObservable<any[]>;

  constructor(af: AngularFireDatabase, private _dialog: MdDialog) {
    this.rooms = af.list('/');
  }

  ngOnInit() {

  }

  openDialog() {
    let dialogRef = this._dialog.open(DialogContent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.createNewRoom(result);
      }
    })
  }

  createNewRoom(roomId: string) {
    this.rooms.push({id: roomId});
  }

  deleteRoom(room) {
    this.rooms.remove(room);
  }
}

@Component({
  template: `
    <p>Choose a Room Number: </p>
    <input #dialogInput>
    <div>
      <button md-button (click)="dialogRef.close(dialogInput.value)">OK</button>
      <button md-button (click)="dialogRef.close()"> CLOSE </button>
    </div>
  `,
})
export class DialogContent {
  constructor(@Optional() public dialogRef: MdDialogRef<DialogContent>) { }
}
