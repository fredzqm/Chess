import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

@Injectable()
export class ChessService {
  getAll() : any[] {
    return [
      {name: 'somebody', age: 3},
      {name: 'somebody2', age: 30}
    ];
  }
}
