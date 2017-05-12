webpackJsonp([1,5],{

/***/ 132:
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 132;


/***/ }),

/***/ 133:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__(155);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__(158);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__(161);




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["a" /* enableProdMode */])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 157:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var AppComponent = (function () {
    function AppComponent() {
    }
    AppComponent.prototype.ngOnInit = function () {
    };
    return AppComponent;
}());
AppComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        selector: 'app-root',
        template: __webpack_require__(228),
        styles: [__webpack_require__(221)]
    }),
    __metadata("design:paramtypes", [])
], AppComponent);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 158:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_angularfire2__ = __webpack_require__(166);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_angularfire2_database__ = __webpack_require__(55);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__angular_router__ = __webpack_require__(91);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__angular_platform_browser_animations__ = __webpack_require__(156);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__angular_material__ = __webpack_require__(53);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__angular_flex_layout__ = __webpack_require__(151);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__app_routes__ = __webpack_require__(159);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__app_component__ = __webpack_require__(157);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__chess_board_square_square_component__ = __webpack_require__(160);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__chess_board_board_component__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__chess_chess_component__ = __webpack_require__(93);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__environments_firebase_config__ = __webpack_require__(162);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__room_room_component__ = __webpack_require__(94);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

















var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["b" /* NgModule */])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_11__app_component__["a" /* AppComponent */],
            __WEBPACK_IMPORTED_MODULE_12__chess_board_square_square_component__["a" /* SquareComponent */],
            __WEBPACK_IMPORTED_MODULE_13__chess_board_board_component__["a" /* BoardComponent */],
            __WEBPACK_IMPORTED_MODULE_14__chess_chess_component__["a" /* ChessComponent */],
            __WEBPACK_IMPORTED_MODULE_14__chess_chess_component__["b" /* DrawDialogContent */],
            __WEBPACK_IMPORTED_MODULE_14__chess_chess_component__["c" /* PromotionDialogContent */],
            __WEBPACK_IMPORTED_MODULE_16__room_room_component__["a" /* RoomComponent */],
            __WEBPACK_IMPORTED_MODULE_16__room_room_component__["b" /* DialogContent */],
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_7__angular_platform_browser_animations__["a" /* BrowserAnimationsModule */],
            __WEBPACK_IMPORTED_MODULE_9__angular_flex_layout__["FlexLayoutModule"],
            __WEBPACK_IMPORTED_MODULE_4_angularfire2__["a" /* AngularFireModule */].initializeApp(__WEBPACK_IMPORTED_MODULE_15__environments_firebase_config__["a" /* firebaseConfig */]),
            __WEBPACK_IMPORTED_MODULE_5_angularfire2_database__["a" /* AngularFireDatabaseModule */],
            __WEBPACK_IMPORTED_MODULE_6__angular_router__["a" /* RouterModule */].forRoot(__WEBPACK_IMPORTED_MODULE_10__app_routes__["a" /* routes */]),
            __WEBPACK_IMPORTED_MODULE_8__angular_material__["a" /* MaterialModule */].forRoot(),
        ],
        providers: [],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_11__app_component__["a" /* AppComponent */]],
        entryComponents: [__WEBPACK_IMPORTED_MODULE_14__chess_chess_component__["b" /* DrawDialogContent */], __WEBPACK_IMPORTED_MODULE_14__chess_chess_component__["c" /* PromotionDialogContent */], __WEBPACK_IMPORTED_MODULE_16__room_room_component__["b" /* DialogContent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 159:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__chess_chess_component__ = __webpack_require__(93);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__room_room_component__ = __webpack_require__(94);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return routes; });


var routes = [
    { path: 'room/:id/:isWhite', component: __WEBPACK_IMPORTED_MODULE_0__chess_chess_component__["a" /* ChessComponent */] },
    { path: '', component: __WEBPACK_IMPORTED_MODULE_1__room_room_component__["a" /* RoomComponent */] }
];
//# sourceMappingURL=app.routes.js.map

/***/ }),

/***/ 160:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SquareComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

function getX(type) {
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
function getY(isWhite) {
    if (isWhite)
        return 0;
    else
        return -60;
}
var SquareComponent = (function () {
    function SquareComponent() {
        this.clickSquare = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["h" /* EventEmitter */]();
        this.style = {};
    }
    SquareComponent.prototype.ngOnChanges = function () {
        var style = {};
        if (this.type) {
            style['background-image'] = 'url(../assets/Chess_symbols.png)';
            style['background-position'] = getX(this.type) + "px " + getY(this.isWhite) + "px";
        }
        if (this.isHightLight) {
            style['background-color'] = '#ff0';
        }
        this.style = style;
    };
    SquareComponent.prototype.ngOnInit = function () { };
    SquareComponent.prototype.onClick = function (event) {
        this.clickSquare.emit({
            i: this.i,
            j: this.j
        });
    };
    return SquareComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["j" /* Output */])(),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["h" /* EventEmitter */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["h" /* EventEmitter */]) === "function" && _a || Object)
], SquareComponent.prototype, "clickSquare", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["i" /* Input */])(),
    __metadata("design:type", Boolean)
], SquareComponent.prototype, "isHightLight", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["i" /* Input */])(),
    __metadata("design:type", String)
], SquareComponent.prototype, "type", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["i" /* Input */])(),
    __metadata("design:type", Boolean)
], SquareComponent.prototype, "isWhite", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["i" /* Input */])(),
    __metadata("design:type", Number)
], SquareComponent.prototype, "i", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["i" /* Input */])(),
    __metadata("design:type", Number)
], SquareComponent.prototype, "j", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_11" /* HostListener */])('click', ['$event']),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", void 0)
], SquareComponent.prototype, "onClick", null);
SquareComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        selector: 'app-square',
        template: __webpack_require__(230),
        styles: [__webpack_require__(223)]
    }),
    __metadata("design:paramtypes", [])
], SquareComponent);

var _a;
//# sourceMappingURL=square.component.js.map

/***/ }),

/***/ 161:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false,
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ 162:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return firebaseConfig; });
var firebaseConfig = {
    apiKey: 'AIzaSyColgYrNl3U0ZnJ7_MoS8j3GM9xxc8adkc',
    authDomain: 'chess-49b54.firebaseapp.com',
    databaseURL: 'https://chess-49b54.firebaseio.com',
    storageBucket: 'chess-49b54.appspot.com'
};
//# sourceMappingURL=firebase.config.js.map

/***/ }),

/***/ 221:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(18)();
// imports


// module
exports.push([module.i, ".fill-remaining-space {\r\n  -webkit-box-flex: 1;\r\n      -ms-flex: 1 1 auto;\r\n          flex: 1 1 auto;\r\n}\r\n\r\n.navbar-style {\r\n  margin: -8px;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 222:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(18)();
// imports


// module
exports.push([module.i, "#chess {\r\n  min-width: 580px;\r\n  max-width: 580px;\r\n  margin: auto;\r\n}\r\n\r\ntable#chess div app-square {\r\n  width: 70px;\r\n  height: 70px;\r\n  border: solid #000 1px;\r\n  float:left;\r\n}\r\n\r\ntable#chess div:nth-child(odd) app-square:nth-child(even),\r\ntable#chess div:nth-child(even) app-square:nth-child(odd) {\r\n  background: #255;\r\n}\r\n\r\ntable#chess div:nth-child(odd) app-square:nth-child(odd),\r\ntable#chess div:nth-child(even) app-square:nth-child(even) {\r\n  background: #fff;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 223:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(18)();
// imports


// module
exports.push([module.i, "div {\r\n\twidth: 100%;\r\n\theight: 100%;\r\n}\r\n\r\n.square {\r\n\tbackground-repeat: no-repeat;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 224:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(18)();
// imports


// module
exports.push([module.i, ".status-label {\r\n  text-align: center;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 225:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(18)();
// imports


// module
exports.push([module.i, ".new-room-button {\r\n  padding-top: 20px;\r\n}\r\n\r\n.room-card {\r\n  width: 200px;\r\n}\r\n\r\n.rooms-row {\r\n  padding-right: 65px;\r\n  padding-top: 20px;\r\n}\r\n\r\n.delete-button {\r\n  margin-top: -23px !important;\r\n  margin-left: 105%;\r\n  cursor: pointer;\r\n}\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 228:
/***/ (function(module, exports) {

module.exports = "<md-toolbar color=\"primary\" class=\"navbar-style\">\r\n  <span fxFlex=\"80\"> Chess Grand Master </span>\r\n  <span [routerLink]=\"['/']\" fxFlex=\"5\" fxFlexOffset=\"15\">Rooms</span>\r\n</md-toolbar>\r\n\r\n<div>\r\n  <router-outlet></router-outlet>\r\n</div>\r\n"

/***/ }),

/***/ 229:
/***/ (function(module, exports) {

module.exports = "<table id='chess'>\r\n\t<div *ngFor = \"let row of pieces; let i = index\">\r\n\t\t<app-square *ngFor = \"let data of row; let j = index\"\r\n\t\t\t(clickSquare) = \"onSquareClick(i, j)\"\r\n\t\t\t[isHightLight]=\"data.isHightLight\"\r\n\t\t\t[type]=\"data.type\"\r\n\t\t\t[isWhite]=\"data.isWhite\"\r\n\t\t\t[i]=\"i\"\r\n\t\t\t[j]=\"j\"\r\n\t\t\t>\r\n\t\t</app-square>\r\n\t</div>\r\n</table>\r\n"

/***/ }),

/***/ 230:
/***/ (function(module, exports) {

module.exports = "<div class='square'\r\n\t[ngStyle]=\"style\">\r\n</div>\r\n"

/***/ }),

/***/ 231:
/***/ (function(module, exports) {

module.exports = "<h1 class=\"status-label\">{{ (player|async)?.status }}</h1>\r\n<app-board #board\r\n\t[pieces] = \"(player | async)?.board.pieces\"\r\n\t(onSquareClicked)=\"click($event)\"\r\n></app-board>\r\n<div fxLayout=\"row\" fxLayoutAlign=\"center center\">\r\n\t<button md-raised-button (click)=\"requestForDraw()\">Request for Draw</button>\r\n\t<button md-raised-button (click)=\"resign()\">Resign</button>\r\n</div>\r\n"

/***/ }),

/***/ 232:
/***/ (function(module, exports) {

module.exports = "<div class=\"new-room-button\">\r\n    <button (click)=\"openDialog()\" md-raised-button color=\"accent\">Create New Room</button>\r\n</div>\r\n\r\n<div *ngIf=\"(rooms | async)?.length <= 0\" fxLayoutAlign=\"center center\">\r\n  No rooms created yet!\r\n</div>\r\n\r\n<div [fxLayout]=\"row\" fxLayoutWrap fxLayoutAlign=\"start center\">\r\n    <div *ngFor=\"let room of rooms | async\" class=\"rooms-row\">\r\n        <md-card class=\"room-card\">\r\n            <p class=\"delete-button\" (click)=\"deleteRoom(room)\">x</p>\r\n            <md-card-header>\r\n              <md-card-title>Room ID: {{room.id}}</md-card-title>\r\n            </md-card-header>\r\n            <md-card-actions fxLayoutAlign=\"space-around center\">\r\n              <button md-button [routerLink]=\"['/room', room.$key, 'white']\">JOIN WHITE</button>\r\n              <button md-button [routerLink]=\"['/room', room.$key, 'black']\">JOIN BLACK</button>\r\n            </md-card-actions>\r\n        </md-card>\r\n    </div>\r\n</div>\r\n"

/***/ }),

/***/ 281:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(133);


/***/ }),

/***/ 92:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BoardComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var BoardComponent = (function () {
    function BoardComponent() {
        this.onSquareClicked = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["h" /* EventEmitter */]();
    }
    BoardComponent.prototype.onSquareClick = function (i, j) {
        this.onSquareClicked.emit({
            i: i,
            j: j
        });
    };
    return BoardComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["i" /* Input */])(),
    __metadata("design:type", Object)
], BoardComponent.prototype, "pieces", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["j" /* Output */])(),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["h" /* EventEmitter */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["h" /* EventEmitter */]) === "function" && _a || Object)
], BoardComponent.prototype, "onSquareClicked", void 0);
BoardComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        selector: 'app-board',
        template: __webpack_require__(229),
        styles: [__webpack_require__(222)]
    }),
    __metadata("design:paramtypes", [])
], BoardComponent);

var _a;
//# sourceMappingURL=board.component.js.map

/***/ }),

/***/ 93:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(91);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_angularfire2_database__ = __webpack_require__(55);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__board_board_component__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_material__ = __webpack_require__(53);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ChessComponent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return DrawDialogContent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return PromotionDialogContent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};





var ChessComponent = (function () {
    function ChessComponent(route, af, _dialog) {
        this.route = route;
        this.af = af;
        this._dialog = _dialog;
    }
    ChessComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.id = params['id'];
            _this.isWhite = params['isWhite'] == 'white' ? 'white' : 'black';
            _this.player = _this.af.object('/' + _this.id + '/' + _this.isWhite);
            _this.actionBaseUrl = '/' + _this.id + '/' + _this.isWhite + '/action';
            _this.af.object('/' + _this.id + '/' + _this.isWhite + '/request').subscribe(function (data) {
                if (data.askForDraw) {
                    _this.openDrawDialog();
                }
                if (data.promotionTo) {
                    _this.openPromotionDialog();
                }
            });
        });
    };
    ChessComponent.prototype.openDrawDialog = function () {
        var _this = this;
        var dialogRef = this._dialog.open(DrawDialogContent);
        dialogRef.afterClosed().subscribe(function (accepted) {
            if (accepted) {
                _this.af.object(_this.actionBaseUrl).set({ agreeDraw: 'Y' });
            }
            else {
                _this.af.object(_this.actionBaseUrl).set({ agreeDraw: 'N' });
            }
        });
    };
    ChessComponent.prototype.openPromotionDialog = function () {
        var _this = this;
        var dialogRef = this._dialog.open(PromotionDialogContent);
        dialogRef.afterClosed().subscribe(function (promotion) {
            if (promotion != null) {
                _this.af.object(_this.actionBaseUrl).set({ promotionTo: promotion });
                _this.af.object('/' + _this.id + '/' + _this.isWhite + '/request').remove();
            }
        });
    };
    ChessComponent.prototype.click = function (event) {
        this.af.object(this.actionBaseUrl).set({ click: { i: event.i, j: event.j } });
    };
    ChessComponent.prototype.requestForDraw = function () {
        this.af.object(this.actionBaseUrl).set({ requestDraw: true });
    };
    ChessComponent.prototype.resign = function () {
        this.af.object(this.actionBaseUrl).set({ resign: true });
    };
    return ChessComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["z" /* ViewChild */])("board"),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__board_board_component__["a" /* BoardComponent */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__board_board_component__["a" /* BoardComponent */]) === "function" && _a || Object)
], ChessComponent.prototype, "board", void 0);
ChessComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        selector: 'app-chess',
        template: __webpack_require__(231),
        styles: [__webpack_require__(224)]
    }),
    __metadata("design:paramtypes", [typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2_angularfire2_database__["b" /* AngularFireDatabase */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2_angularfire2_database__["b" /* AngularFireDatabase */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__angular_material__["b" /* MdDialog */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_material__["b" /* MdDialog */]) === "function" && _d || Object])
], ChessComponent);

var DrawDialogContent = (function () {
    function DrawDialogContent(dialogRef) {
        this.dialogRef = dialogRef;
    }
    return DrawDialogContent;
}());
DrawDialogContent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        template: "\n    <p>Do you accept the draw?</p>\n    <div>\n      <button md-button (click)=\"dialogRef.close(true)\"> YES </button>\n      <button md-button (click)=\"dialogRef.close()\"> NO </button>\n    </div>\n  ",
    }),
    __param(0, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["e" /* Optional */])()),
    __metadata("design:paramtypes", [typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_4__angular_material__["c" /* MdDialogRef */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_material__["c" /* MdDialogRef */]) === "function" && _e || Object])
], DrawDialogContent);

var PromotionDialogContent = (function () {
    function PromotionDialogContent(dialogRef) {
        this.dialogRef = dialogRef;
        this.promotions = [
            { value: 'Q', viewValue: 'Queen' },
            { value: 'R', viewValue: 'Rook' },
            { value: 'N', viewValue: 'Knight' },
            { value: 'B', viewValue: 'Bishop' }
        ];
    }
    return PromotionDialogContent;
}());
PromotionDialogContent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        template: "\n    <md-select placeholder=\"Promotion\" [(ngModel)]=\"selectedPromotion\">\n       <md-option *ngFor=\"let p of promotions\" [value]=\"p.value\">\n         {{p.viewValue}}\n       </md-option>\n    </md-select>\n    <div>\n      <button md-button (click)=\"dialogRef.close(selectedPromotion)\"> Submit </button>\n      <button md-button (click)=\"dialogRef.close()\"> Cancel </button>\n    </div>\n  ",
    }),
    __param(0, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["e" /* Optional */])()),
    __metadata("design:paramtypes", [typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_4__angular_material__["c" /* MdDialogRef */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_material__["c" /* MdDialogRef */]) === "function" && _f || Object])
], PromotionDialogContent);

var _a, _b, _c, _d, _e, _f;
//# sourceMappingURL=chess.component.js.map

/***/ }),

/***/ 94:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_material__ = __webpack_require__(53);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_angularfire2_database__ = __webpack_require__(55);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RoomComponent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return DialogContent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};



var RoomComponent = (function () {
    function RoomComponent(af, _dialog) {
        this._dialog = _dialog;
        this.rooms = af.list('/');
    }
    RoomComponent.prototype.ngOnInit = function () {
    };
    RoomComponent.prototype.openDialog = function () {
        var _this = this;
        var dialogRef = this._dialog.open(DialogContent);
        dialogRef.afterClosed().subscribe(function (result) {
            if (result) {
                _this.createNewRoom(result);
            }
        });
    };
    RoomComponent.prototype.createNewRoom = function (roomId) {
        this.rooms.push({ id: roomId });
    };
    RoomComponent.prototype.deleteRoom = function (room) {
        this.rooms.remove(room);
    };
    return RoomComponent;
}());
RoomComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        selector: 'app-room',
        template: __webpack_require__(232),
        styles: [__webpack_require__(225)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2_angularfire2_database__["b" /* AngularFireDatabase */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2_angularfire2_database__["b" /* AngularFireDatabase */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_material__["b" /* MdDialog */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_material__["b" /* MdDialog */]) === "function" && _b || Object])
], RoomComponent);

var DialogContent = (function () {
    function DialogContent(dialogRef) {
        this.dialogRef = dialogRef;
    }
    return DialogContent;
}());
DialogContent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["p" /* Component */])({
        template: "\n    <p>Choose a Room Number: </p>\n    <input #dialogInput>\n    <div>\n      <button md-button (click)=\"dialogRef.close(dialogInput.value)\">OK</button>\n      <button md-button (click)=\"dialogRef.close()\"> CLOSE </button>\n    </div>\n  ",
    }),
    __param(0, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["e" /* Optional */])()),
    __metadata("design:paramtypes", [typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__angular_material__["c" /* MdDialogRef */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_material__["c" /* MdDialogRef */]) === "function" && _c || Object])
], DialogContent);

var _a, _b, _c;
//# sourceMappingURL=room.component.js.map

/***/ })

},[281]);
//# sourceMappingURL=main.bundle.js.map