import { Component, OnInit, ViewChild, Output, Optional } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {AngularFirestore, AngularFirestoreDocument, DocumentSnapshot} from 'angularfire2/firestore';
import { BoardComponent } from './board/board.component';
import { MatDialog, MatDialogRef } from '@angular/material';
import {Game, Room, Request} from '../model';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-chess',
  templateUrl: './chess.component.html',
  styleUrls: ['./chess.component.css']
})
export class ChessComponent implements OnInit {
  id: any;
  isWhite: boolean;
  game: Observable<any>;
  gameDoc: AngularFirestoreDocument<any>;

  @ViewChild('board') board: BoardComponent;

  constructor(private route: ActivatedRoute, private afs: AngularFirestore, private _dialog: MatDialog) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.isWhite = params['isWhite'] === 'white';
      this.gameDoc = this.afs.collection('rooms').doc(this.id).collection('display').doc(params['isWhite']);
      this.game = this.gameDoc.valueChanges();
      this.gameDoc.collection('request').stateChanges(['added']).subscribe(snapshots => {
        snapshots.map(snapshot => {
          const request: any = snapshot.payload.doc.data();
          if (request.askForDraw) {
            this.openDrawDialog();
          }
          if (request.promotionTo) {
            this.openPromotionDialog();
          }
        });
      });
    });
  }

  openDrawDialog() {
    const dialogRef = this._dialog.open(DrawDialogContent);

    dialogRef.afterClosed().subscribe(accepted => {
      if (accepted) {
        this.gameDoc.collection('action').add({agreeDraw: true});
      } else {
        this.gameDoc.collection('action').add({agreeDraw: false});
      }
    });
  }

  openPromotionDialog() {
    const dialogRef = this._dialog.open(PromotionDialogContent);

    dialogRef.afterClosed().subscribe(promotion => {
      this.gameDoc.collection('action').add({promotionTo: promotion});
    });
  }

  click(event: any) {
    this.gameDoc.collection('action').add({click: {i: event.i, j: event.j}});
  }

  requestForDraw() {
    this.gameDoc.collection('action').add({requestDraw: true});
  }

  resign() {
    this.gameDoc.collection('action').add({resign: true});
  }
}

@Component({
  template: `
    <p>Do you accept the draw?</p>
    <div>
      <button mat-button (click)="dialogRef.close(true)"> YES </button>
      <button mat-button (click)="dialogRef.close()"> NO </button>
    </div>
  `,
})
export class DrawDialogContent {
  constructor(@Optional() public dialogRef: MatDialogRef<DrawDialogContent>) { }
}


@Component({
  template: `
    <mat-select placeholder="Promotion" [(ngModel)]="selectedPromotion">
      <mat-option *ngFor="let p of promotions" [value]="p.value">
        {{p.viewValue}}
      </mat-option>
    </mat-select>
    <div>
      <button mat-button (click)="dialogRef.close(selectedPromotion)"> Submit </button>
      <button mat-button (click)="dialogRef.close()"> Cancel </button>
    </div>
  `,
})
export class PromotionDialogContent {
  selectedPromotion: string;
  promotions = [
    {value: 'Q', viewValue: 'Queen'},
    {value: 'R', viewValue: 'Rook'},
    {value: 'N', viewValue: 'Knight'},
    {value: 'B', viewValue: 'Bishop'}
  ];
  constructor(@Optional() public dialogRef: MatDialogRef<PromotionDialogContent>) { }
}
