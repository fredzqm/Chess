export class SquareData {
	
  constructor(
    public type : string, 
    public side : boolean
    ) {}

  getX() : number {
    switch (this.type) {
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

  getY() : number {
    if (this.side)
      return 0;
    else
      return -60;
  }

  getImagePosition() : string {
    return this.getX() + "px " + this.getY() + "px";
  }
}
