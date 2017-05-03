import { Component, OnInit, Input, Output, HostListener, EventEmitter, OnChanges, HostBinding } from '@angular/core';


function getX(type : string) : number {
  switch (type) {
    case 'K':
      return 0;
    case 'Q':
      return -65;
    case 'B':
      return -131;
    case 'N':
      return -198;
    case 'R':
      return -264;
    case 'P':
      return -330;
  }
}

function getY(isWhite : boolean) : number {
  if (isWhite)
    return 0;
  else
    return -60;
}

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.css']
})
export class SquareComponent implements OnInit, OnChanges {
  @Output() clickSquare : EventEmitter<any> = new EventEmitter<any>();
  @Input() isHightLight : boolean
  @Input() type : string
  @Input() isWhite : boolean

  @Input() i : number;
  @Input() j : number;

  style = {};

  ngOnChanges() {
    let style = {};
    if (this.type) {
      style['background-image'] = 'url(../assets/Chess_symbols.png)';
      style['background-position'] = getX(this.type) + "px " + getY(this.isWhite) + "px";
    }
    if (this.isHightLight) {
      style['background-color'] = '#ff0';
    }
    this.style = style;
  }

  constructor() {
  }

  ngOnInit() { }

  @HostListener('click', ['$event'])
  onClick(event) {
    this.clickSquare.emit({
      i : this.i,
      j : this.j
    });
  }

}
