import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-console',
  templateUrl: './console.component.html',
  styleUrls: ['./console.component.css']
})
export class ConsoleComponent implements OnInit {
  consoleMessage: string
  existence: string

  @Output() enteredCommand = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.existence = 'Welcome to little Chess Game. Type \"help\" for instructions.';
    this.consoleMessage = this.existence;
  }

  submit() {
    if (this.existence.length < this.consoleMessage.length) {
      let input = this.consoleMessage.substring(this.existence.length);
      if (input.length > 0) {
        this.enteredCommand.emit(input);
      }
      this.existence = this.consoleMessage;
    }
  }

  printOut(input : string) {
    if (input != null) {
  		this.existence = this.existence + input + '\n';
      this.consoleMessage = this.existence;
    }
  }

  printTemp(temp) {
    this.consoleMessage = this.existence + temp;
  }

  cleanTemp() {
    this.consoleMessage = this.existence;
  }

}
