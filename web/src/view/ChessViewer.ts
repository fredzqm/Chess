/* Generated from Java with JSweet 2.0.0-SNAPSHOT - http://www.jsweet.org */
import { IChessViewer } from './IChessViewer'; 
import { ISpriteProvider } from './ISpriteProvider'; 
import { SpriteProvider } from './SpriteProvider'; 
import { IChessViewerControl } from './IChessViewerControl'; 
import { SquareLabel } from './SquareLabel'; 
import { ChessPieceType } from './ChessPieceType'; 
import BorderLayout = java.awt.BorderLayout;

import FlowLayout = java.awt.FlowLayout;

import Font = java.awt.Font;

import GridLayout = java.awt.GridLayout;

import KeyAdapter = java.awt.event.KeyAdapter;

import KeyEvent = java.awt.event.KeyEvent;

import JFrame = javax.swing.JFrame;

import JLabel = javax.swing.JLabel;

import JOptionPane = javax.swing.JOptionPane;

import JPanel = javax.swing.JPanel;

import JScrollPane = javax.swing.JScrollPane;

import JTextArea = javax.swing.JTextArea;

/**
 * construct a chess view given a controller
 * 
 * @param {view.IChessViewerControl} controller
 * @param b
 * @param {string} title
 * @param {boolean} whiteOrBlack
 * @class
 */
export class ChessViewer extends JFrame implements IChessViewer {
    static FONT_STATUS_LABEL : Font; public static FONT_STATUS_LABEL_$LI$() : Font { if(ChessViewer.FONT_STATUS_LABEL == null) ChessViewer.FONT_STATUS_LABEL = new Font("Serif", Font.PLAIN, 40); return ChessViewer.FONT_STATUS_LABEL; };

    static CONSOLE_FONT_SIZE : number = 20;

    static FONT_CONSOLE : Font; public static FONT_CONSOLE_$LI$() : Font { if(ChessViewer.FONT_CONSOLE == null) ChessViewer.FONT_CONSOLE = new Font("Serif", Font.PLAIN, ChessViewer.CONSOLE_FONT_SIZE); return ChessViewer.FONT_CONSOLE; };

    static DEFAULT_SYMBOL_PROVIDER : ISpriteProvider; public static DEFAULT_SYMBOL_PROVIDER_$LI$() : ISpriteProvider { if(ChessViewer.DEFAULT_SYMBOL_PROVIDER == null) ChessViewer.DEFAULT_SYMBOL_PROVIDER = new SpriteProvider("Chess_symbols.png"); return ChessViewer.DEFAULT_SYMBOL_PROVIDER; };

    /*private*/ isWhiteView : boolean;

    /*private*/ viewControl : IChessViewerControl;

    /*private*/ statusLabel : JLabel;

    /*private*/ labels : SquareLabel[][];

    /*private*/ myConsole : JTextArea;

    /*private*/ existence : string;

    public constructor(controller : IChessViewerControl, title : string, whiteOrBlack : boolean) {
        super();
        this.isWhiteView = false;
        this.viewControl = null;
        this.statusLabel = null;
        this.labels = null;
        this.myConsole = null;
        this.existence = null;
        this.viewControl = controller;
        this.isWhiteView = whiteOrBlack;
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.labels = this.setupChessBoard(controller, ChessViewer.DEFAULT_SYMBOL_PROVIDER_$LI$());
        this.statusLabel = this.setupStatusLabel();
        let consolePanel : JPanel = this.setupConsole();
        this.add(consolePanel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.pack();
    }

    setupChessBoard(controller : IChessViewerControl, symbolProvider : ISpriteProvider) : SquareLabel[][] {
        let chessBoardSpace : JPanel = new JPanel();
        chessBoardSpace.setLayout(new FlowLayout());
        chessBoardSpace.setVisible(true);
        let chessBoard : JPanel = new JPanel();
        chessBoard.setSize(SquareLabel.SQUARE_WIDTH * 9, SquareLabel.SQUARE_WIDTH * 9);
        chessBoard.setLayout(new GridLayout(9, 9));
        chessBoard.setVisible(true);
        let labels : SquareLabel[][] = <any> (function(dims) { let allocate = function(dims) { if(dims.length==0) { return undefined; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([9, 9]);
        for(let j : number = 0; j < 9; j++) {
            for(let i : number = 0; i < 9; i++) {
                if(j === 8) {
                    labels[i][j] = new SquareLabel();
                    let s : string = "";
                    if(i > 0) s += String.fromCharCode((this.isWhiteView?(i + 96):(105 - i)));
                    labels[i][j].setText(s);
                    labels[i][j].setOpaque(false);
                } else if(i === 0) {
                    labels[i][j] = new SquareLabel();
                    let s : string = "";
                    s += this.isWhiteView?(8 - j):j + 1;
                    labels[i][j].setText(s);
                    labels[i][j].setOpaque(false);
                } else {
                    labels[i][j] = new SquareLabel(i, j, controller, this.isWhiteView, symbolProvider);
                }
                chessBoard.add(labels[i][j]);
            }
        }
        chessBoardSpace.add(chessBoard);
        this.add(chessBoardSpace, BorderLayout.CENTER);
        return labels;
    }

    setupStatusLabel() : JLabel {
        let label : JLabel = new JLabel("            Welcome to Wonderful Chess Game             ", SwingConstants.CENTER);
        label.setFont(ChessViewer.FONT_STATUS_LABEL_$LI$());
        label.setVisible(true);
        this.add(label, BorderLayout.NORTH);
        return label;
    }

    setupConsole() : JPanel {
        let consolePanel : JPanel = new JPanel();
        consolePanel.setLayout(new FlowLayout());
        consolePanel.setVisible(true);
        this.myConsole = new JTextArea("Welcome to little Chess Game. Enter \"help\" for instructions.\n", (130 / ChessViewer.CONSOLE_FONT_SIZE|0), (1000 / ChessViewer.CONSOLE_FONT_SIZE|0));
        this.myConsole.setFont(ChessViewer.FONT_CONSOLE_$LI$());
        this.myConsole.addKeyListener(new ChessViewer.ConsoleListener(this));
        this.existence = this.myConsole.getText();
        consolePanel.add(new JScrollPane(this.myConsole));
        return consolePanel;
    }

    /**
     * 
     * @param {number} file
     * @param {number} rank
     * @return {view.SquareLabel} {link {@link SquareLabel} at (x , y) coordinate
     */
    public labelAt(file : number, rank : number) : SquareLabel {
        return this.isWhiteView?this.labels[file][8 - rank]:this.labels[9 - file][rank - 1];
    }

    public printOut(message : string) {
        this.existence = this.myConsole.getText();
        if(message != null) {
            this.existence = this.existence + message + "\n";
            this.myConsole.setText(this.existence);
        }
    }

    public printTemp(temp : string) {
        this.myConsole.setText(this.existence + temp);
    }

    public cleanTemp() {
        this.myConsole.setText(this.existence);
    }

    public setStatusLabelText(str : string) {
        this.statusLabel.setText(str);
    }

    public highLight(file : number, rank : number) {
        this.labelAt(file, rank).highLight();
    }

    public deHighLightWholeBoard() {
        for(let index125=0; index125 < this.labels.length; index125++) {
            let row = this.labels[index125];
            {
                for(let index126=0; index126 < row.length; index126++) {
                    let label = row[index126];
                    {
                        label.deHighLight();
                    }
                }
            }
        }
    }

    public getResponse(message : string) : string {
        return JOptionPane.showInputDialog(message);
    }

    public upDatePiece(file : number, rank : number, pieceType : ChessPieceType, whiteOrBlack : boolean) {
        this.labelAt(file, rank).upDatePiece(pieceType, whiteOrBlack);
    }

    public clearLabel(file : number, rank : number) {
        this.labelAt(file, rank).clearLabel();
    }
}
ChessViewer["__class"] = "view.ChessViewer";
ChessViewer["__interfaces"] = ["javax.swing.RootPaneContainer","javax.swing.TransferHandler.HasGetTransferHandler","java.awt.MenuContainer","view.IChessViewer","javax.accessibility.Accessible","javax.swing.WindowConstants","java.awt.image.ImageObserver","java.io.Serializable"];



export namespace ChessViewer {

    export class ConsoleListener extends KeyAdapter {
        public __parent: any;
        input : string;

        public keyReleased(arg0 : KeyEvent) {
            if(arg0.getKeyCode() === KeyEvent.VK_ENTER) {
                let text : string = this.__parent.myConsole.getText();
                if(this.__parent.existence.length < text.length) {
                    this.input = text.substring(this.__parent.existence.length, text.length - 1);
                    if(this.input.length > 0) {
                        this.__parent.viewControl.handleCommand(this.input, this.__parent.isWhiteView);
                    }
                }
            }
        }

        constructor(__parent: any) {
            super();
            this.__parent = __parent;
            this.input = null;
        }
    }
    ConsoleListener["__class"] = "view.ChessViewer.ConsoleListener";
    ConsoleListener["__interfaces"] = ["java.util.EventListener","java.awt.event.KeyListener"];


}




ChessViewer.DEFAULT_SYMBOL_PROVIDER_$LI$();

ChessViewer.FONT_CONSOLE_$LI$();

ChessViewer.FONT_STATUS_LABEL_$LI$();
