import { Component, OnInit, ViewChild, Output, Optional } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {AngularFireDatabase, FirebaseListObservable} from 'angularfire2/database';
import { BoardComponent } from './board/board.component';
import { MdDialog, MdDialogRef } from '@angular/material';

@Component({
  selector: 'app-chess',
  templateUrl: './chess.component.html',
  styleUrls: ['./chess.component.css']
})
export class ChessComponent implements OnInit {
  id: any;
  player: any;
  isWhite: any;
  actionBaseUrl: any;

  @ViewChild("board") board : BoardComponent;

  constructor(private route: ActivatedRoute, private af: AngularFireDatabase, private _dialog: MdDialog) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
       this.id = params['id'];
       this.isWhite = params['isWhite'] == 'white' ? 'white' : 'black';
       this.player = this.af.object('/' + this.id + '/' + this.isWhite);
       this.actionBaseUrl = '/' + this.id +'/' + this.isWhite + '/action';
       this.af.object('/' + this.id + '/' + this.isWhite + '/request').subscribe(data => {
          if (data.askForDraw) {
            this.openDrawDialog();
          }
          if (data.promotionTo) {
            this.openPromotionDialog();
          }
       });
    });
  }

  openDrawDialog() {
    let dialogRef = this._dialog.open(DrawDialogContent);

    dialogRef.afterClosed().subscribe(accepted => {
      if (accepted) {
        this.af.object(this.actionBaseUrl).set({agreeDraw: 'Y'});
      } else {
        this.af.object(this.actionBaseUrl).set({agreeDraw: 'N'});
      }
    })
  }

  openPromotionDialog() {
    let dialogRef = this._dialog.open(PromotionDialogContent);

    dialogRef.afterClosed().subscribe(promotion => {
      if (promotion != null) {
        this.af.object(this.actionBaseUrl).set({promotionTo: promotion});
        this.af.object('/' + this.id + '/' + this.isWhite + '/request').remove();
      }
    })
  }

  click(event : any) {
    this.af.object(this.actionBaseUrl).set({click: {i: event.i, j: event.j}});
  }

  requestForDraw() {
    this.af.object(this.actionBaseUrl).set({requestDraw: true});
  }

  resign() {
    this.af.object(this.actionBaseUrl).set({resign: true});
  }
}

@Component({
  template: `
    <p>Do you accept the draw?</p>
    <div>
      <button md-button (click)="dialogRef.close(true)"> YES </button>
      <button md-button (click)="dialogRef.close()"> NO </button>
    </div>
  `,
})
export class DrawDialogContent {
  constructor(@Optional() public dialogRef: MdDialogRef<DrawDialogContent>) { }
}


@Component({
  template: `
    <md-select placeholder="Promotion" [(ngModel)]="selectedPromotion">
       <md-option *ngFor="let p of promotions" [value]="p.value">
         {{p.viewValue}}
       </md-option>
    </md-select>
    <div>
      <button md-button (click)="dialogRef.close(selectedPromotion)"> Submit </button>
      <button md-button (click)="dialogRef.close()"> Cancel </button>
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
  constructor(@Optional() public dialogRef: MdDialogRef<PromotionDialogContent>) { }
}
