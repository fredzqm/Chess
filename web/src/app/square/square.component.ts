import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { PieceSquare, PieceType } from '../PieceSquare';

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.css']
})
export class SquareComponent implements OnInit, OnChanges {
  @Input() piece : PieceSquare;
  style = {};

  constructor() {
  	
  }

  ngOnInit() { }

  ngOnChanges(changes: SimpleChanges) {
  	if (changes['piece']) {
  		if (this.piece) {
	  		this.style = {
					'background-image' : 'url(../assets/Chess_symbols.png)',
					'background-position' : this.piece.getImagePosition()
	  		};
	  	} else {
	  		this.style = {};
	  	}
  	}
  }

}
