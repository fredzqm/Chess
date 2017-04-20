import { Component, OnInit, Input, Output, HostListener, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { SquareData } from './squareData';

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.css']
})
export class SquareComponent implements OnInit, OnChanges {
  @Input() piece : SquareData;
  @Input() i : number;
  @Input() j : number;
  @Output() clickSquare : EventEmitter<any> = new EventEmitter<any>();

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

  @HostListener('click', ['$event']) 
  onClick(event) {
    this.clickSquare.emit({
      i : this.i,
      j : this.j
    });
  }

}
