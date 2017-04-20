import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-console',
  templateUrl: './console.component.html',
  styleUrls: ['./console.component.css']
})
export class ConsoleComponent implements OnInit {
  welcomeMessage: string
  consoleMessage: string
  existence: string

  @Output() enteredCommand = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.welcomeMessage = 'Welcome to little Chess Game. Type \"help\" for instructions.';
    this.existence = '';
    this.consoleMessage = '';
  }

  submit() {
    var splitted = this.consoleMessage.split("\n")
    var lastCommand = splitted[splitted.length - 1]
    this.enteredCommand.emit(lastCommand);
  }

  printOut(input) {
    this.existence = input;
    if (input != null) {
  		this.existence = this.existence + input + '\n';
    }
    this.consoleMessage = this.existence;
  }

  printTemp(temp) {
    this.consoleMessage = this.existence + temp;
  }

  cleanTemp() {
    this.consoleMessage = this.existence;
  }

}
