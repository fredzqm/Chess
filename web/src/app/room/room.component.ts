///<reference path="../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit, Optional} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Observable} from 'rxjs';
import {AngularFirestore, AngularFirestoreDocument, AngularFirestoreCollection} from 'angularfire2/firestore';
import {Room} from '../model';
import {AngularFireAuth} from 'angularfire2/auth';
import {merge} from 'rxjs/internal/observable/merge';
import {map} from 'rxjs/operators';
import {combineLatest} from 'rxjs/internal/observable/combineLatest';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  private roomCollection: AngularFirestoreCollection<any>;
  private invitedColletion: AngularFirestoreCollection<any>;
  public rooms: Observable<any[]>;
  public invited: Observable<any[]>;
  private user: firebase.User | null;

  constructor(private afs: AngularFirestore, public afAuth: AngularFireAuth, private _dialog: MatDialog) {
    this.afAuth.user.subscribe((user) => {
      this.user = user;
      const email = user.email;
      this.roomCollection = this.afs.collection<any>('rooms', ref => ref.where('owner', '==', email));
      this.rooms = this.roomCollection.valueChanges();
      this.invitedColletion = this.afs.collection<any>('rooms', ref => ref.where('invite', '==', email));
      this.invited = this.invitedColletion.valueChanges();
    });
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

  createNewRoom(data: any) {
    this.roomCollection.add({
      name: data.name,
      owner: this.user.email,
      invite: data.invite
    });
  }

  deleteRoom(room) {
    if (room.$key) {
      this.roomCollection.doc(room.$key).delete();
    }
  }
}

@Component({
  template: `
    <p>Choose a Room Name: </p>
    <input #nameInput>
    <p>Invite email: </p>
    <input #inviteInput>
    <div>
      <button mat-button
              (click)="dialogRef.close({'name':nameInput.value, 'invite': inviteInput.value})">
        OK
      </button>
      <button mat-button (click)="dialogRef.close()"> CLOSE</button>
    </div>
  `,
})
export class DialogContent {
  constructor(@Optional() public dialogRef: MatDialogRef<DialogContent>) {
  }
}
