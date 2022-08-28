# tic-tac-toe-minimax

This project is a programming assignment from the Artificial Intelligence course taught at Trinity College in Hartford Connecticut.

The purpose of the assignment is to create a program that will play Tic-Tac-Toe against a living user. The CPU playing against the user
runs an implementation of the minimax algorithm (implementation written by me).

Description of the game from the assignment sheet: 
For this project you will write an implementation of Tic-Tac-Toe using MINIMAX.
Tic-Tac-Toe is played on a 3 x 3 grid. The two players are X and O. X always goes first. On each turn, a player 
marks its letter (X or O) on an empty square of the grid. The game ends when either:

(a) one player has succeeded in filling a row, column, or diagonal with its letter, or 

(b) as a tie when there are no more empty squares and neither player has won. 

We’ll consider the score to be +1 for a win, -1 for a loss, and 0 for a draw.
Your program will be X and the user will play O. On each turn, your program will 

(a) Run MINIMAX to determine the best move for X and update the game accordingly

(b) Prompt the user for its turn and update the game accordingly.

Identify the 9 squares as numbered 1-9 in row-major order.
