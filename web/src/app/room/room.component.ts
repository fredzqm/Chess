import { Component, OnInit, Optional } from '@angular/core';
import { MdDialog, MdDialogRef } from '@angular/material';
import { AngularFire, FirebaseListObservable } from 'angularfire2';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  // rooms: FirebaseListObservable<any[]>;
  rooms: any[];
  lastDialogResult: string;

  constructor(af: AngularFire, private _dialog: MdDialog) {
    // this.rooms = af.database.list('/');
    // console.log(this.rooms);
    this.rooms = [];
  }

  ngOnInit() {
    //TODO: remove this to get it from firebase
    this.rooms.push({
      id: 1
    });
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
