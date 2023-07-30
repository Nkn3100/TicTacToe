# TicTacToe
TicTacToe game using Java

Requirement gathering

1.Size of the board - N*N
2.No of players - N-1
3.Every player have their own symbol
4.Two players can't have same symbol
5.There will be bot
6.Only 1 bot per game
7.Bots will have different difficulty level
8.Who will make the first move ?
    - At start of the game we will randomize the list and they will play in the order of the list
9.how is winner decided ?
    - We want to keep our game more extensible. User at start of the game can list what all rules to apply to decide a winner.
10.Winner rules
    1) All cells of 1 row
    2) All cells of 1 column
    3) All corners of the board
11.Game ends when we have a winner or it is draw
12.No play/pause/exit game
13.Undo a move
    1) global undo button
    2) any can undo the last move
14.Show the replay of the game
