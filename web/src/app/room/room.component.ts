///<reference path="../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit, Optional} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Observable} from 'rxjs';
import {AngularFirestore, AngularFirestoreDocument, AngularFirestoreCollection} from 'angularfire2/firestore';
import {Room} from '../model';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  private roomCollection: AngularFirestoreCollection<Room>;
  public rooms: Observable<Room[]>;

  constructor(private afs: AngularFirestore, private _dialog: MatDialog) {
    this.roomCollection = this.afs.collection<Room>('rooms');
    this.rooms = this.roomCollection.valueChanges();
    console.error(this.afs);
  }

  ngOnInit() {

  }

  openDialog() {
    const dialogRef = this._dialog.open(DialogContent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.createNewRoom(result);
      }
    });
  }

  createNewRoom(name: string) {
    this.roomCollection.add({name: name });
  }

  deleteRoom(room) {
    if (room.id) {
      this.roomCollection.doc(room.id).delete();
    }
  }
}

@Component({
  template: `
    <p>Choose a Room Number: </p>
    <input #dialogInput>
    <div>
      <button mat-button (click)="dialogRef.close(dialogInput.value)">OK</button>
      <button mat-button (click)="dialogRef.close()"> CLOSE</button>
    </div>
  `,
})
export class DialogContent {
  constructor(@Optional() public dialogRef: MatDialogRef<DialogContent>) {
  }
}
