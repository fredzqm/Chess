# Chess
My wonderful java chess game

A chess project created in java, including features of complete chess rule, recording, undoing and multiplayer.
This project is developed with MVC principle. It has separate model and view, so you would be welcome to create your own java view frame and still using our model easily. All public methods are well explained and decumented.
Be careful when you trying to make some changes inside model, you can easily break something. However, feel free to contact the author at <a href = "mailto:fredzqm@gmail.com">fredzqm@gmail.com</a> with bugs you found.

<h2>To start</h2>
To run this project, you can create a new java project in eclipse, copy files in the root folder into the project folder and others into the src folder.
Simply run the Main to start the game, and then you see two windows. One for the white view the other for the black view.
You can use those codes to create online chess gaming with multiple people viewing it.
<img src="img/start.png">

<h2>Make a move</h2>
To make a move in the game, you can either click in the panel or type a command in the standard chess recording format in the console. 
The UI ability makes it vey easy for user to make move.
If you want to use this software as an library, it would be easier to send instructions via command line.

<h3>use command line to make a move</h3>
It is very convenience to use command line input by simply typing chess moves like e2-e4, Ng1-f3. You can also type abbreviation like e4，Nf3. If you only mention the end square and kind of piece, our smart chess model will search the whole board and find the ove you mean. It can happen once in a while, when there are multiply possibilites or none of them. In those situations, you will see a message indicating that there is ambiguity, and you need to be more specific.
In general, it would be recommend to use the complete command if you are using it as a library, since it will take less time for the model to process.
<img src="img/moveCommand.png">

<h3>use Graphical user interface (GUI) to make a move</h3>
It is very easy to play the two-people game with our GUI system. You can just click on the piece you want to move, and all the possible square it can go will be hightlighted, then you can just click to make that move. As indicated by the rule of chess, your move can't give away your king. Our model is smart enought to detect and hide those illegal moves.
<img src="img/moveGUI.png">


<h2>Detect the end of game</h2>
This smart program is able to show you all those legal moves you can make and determine whether the game has end.

<h3>Checkmate</h3>

Some complex rules of chess like En passant and castling are also checked and suggested.
Here is some quick demo:

If you find a game interesting, you can also get the full records of the game by typing 'print' and a standard formated chess records would be printed in the console.
To restart a game, you can type 'restart' in the console.

If you have any questions or suggestion, you can contact me at fredzqm@gmail.com