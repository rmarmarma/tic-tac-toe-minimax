import java.util.*;

/**
 * Program that allows a user to play tic-tac-toe against the CPU
 * @author Robert Allen
 * @Version 1.0 12/2/2021
 **/ 

public class TicTacToe {
	private static char x = 'X';
	private static char o = 'O';
	private static char emptySpace = '_';

	/**
	 * Determines if either the user or the cpu won the game
	 * @param board  The tic-tac-toe board
	 * @param letter  x or o corresponding to the two players
	 * @return True if one of the two players won the game and false otherwise
	 */
	public static boolean win(char[] board, char letter) {
		//horizontal win options
		if (board[0] == letter && board[1] == letter && board[2] == letter) {
			return true;
		}
		if (board[3] == letter && board[4] == letter && board[5] == letter) {
			return true;
		}
		if (board[6] == letter && board[7] == letter && board[8] == letter) {
			return true;
		}
		//vertical win options
		if (board[0] == letter && board[3] == letter && board[6] == letter) {
			return true;
		}
		if (board[1] == letter && board[4] == letter && board[7] == letter) {
			return true;
		}
		if (board[2] == letter && board[5] == letter && board[8] == letter) {
			return true;
		}
		//diagonal win options
		if (board[0] == letter && board[4] == letter && board[8] == letter) {
			return true;
		}
		if (board[2] == letter && board[4] == letter && board[6] == letter) {
			return true;
		}
		return false;
	}

	/**
	 * Counts how many moves have been made since the beginning of the game
	 * @param board  The tic-tac-toe board
	 * @return The number of moves made
	 */
	public static int getNumberOfMovesMade(char[] board) {
		int numMoves = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] != emptySpace) {
				numMoves++;
			}
		}
		return numMoves;
	}

	/**
	 * Determines whether or not the current state of the board is a terminal state
	 * @param board  The tic-tac-toe board
	 * @return True if it is a terminal state and false otherwise
	 */
	public static boolean isTerminalState(char[] board) {
		if (getNumberOfMovesMade(board) == board.length) {
			return true;
		}
		if (win(board, x) || win(board, o)) {
			return true;
		}
		return false;
	}

	/**
	 * Prints the tic-tac-toe board
	 * @param board  The tic-tac-toe board to be printed
	 */
	public static void displayBoard(char[] board) {
		System.out.println(board[0] + " " + board[1] + " " + board[2]);
		System.out.println(board[3] + " " + board[4] + " " + board[5]);
		System.out.println(board[6] + " " + board[7] + " " + board[8]);
	}

	/**
	 * Determines whether the move being attempted is within the rules of the game
	 * @param board  The tic-tac-toe board
	 * @param move  The index of the attempted move
	 * @return True if the move is valid and false otherwise
	 */
	public static boolean isValidMove(char[] board, int move) {
		if (move < 1 || move > board.length) {
			return false;
		}
		if (board[move - 1] != emptySpace) {
			return false;
		}
		return true;
	}

	/**
	 * Allows the user to make their move
	 * @param board  The tic-tac-toe board
	 * @param scan  Scanner object to read the user's move
	 * @return True if will win the game with the move they just made and false otherwise
	 */
	public static boolean userMove(char[] board, Scanner scan) {
		System.out.println("It is your turn to move!");
		boolean validMove = false;
		int move = scan.nextInt();
		while (!validMove) {
			if (isValidMove(board, move)) {
				board[move - 1] = o;
				validMove = true;
			}
			else {
				System.out.println("That is not a valid move. You can only move into a space that is empty and within the bounds of the board (1-9). Please try again.");
				displayBoard(board);
				move = scan.nextInt();
			}
		}
		if (win(board, o)) {
			return true;
		}
		return false;
	}

	/**
	 * Allows the CPU to make their move
	 * @param board  The tic-tac-toe board
	 * @return True if the CPU will win the game with the move they just made and false otherwise
	 */
	public static boolean cpuMove(char[] board) {
		System.out.println("It is the CPU's turn to move!");
		int maxEval = Integer.MIN_VALUE;
		int move = maxEval;
		for (int i = 0; i < board.length; i++) {
			if (isValidMove(board, i + 1)) {
				board[i] = x;
				int eval = minimax(board, false);
				int max = Math.max(eval, maxEval);
				board[i] = emptySpace;
				if (max == eval) {
					maxEval = eval;
					move = i;
				}
			}
		}
		board[move] = x;
		if (win(board, x)) {
			return true;
		}
		return false;
	}

	/**
	 * Implementation of the minimax algorithm that the CPU uses in order to select the best move
	 * @param board  The tic-tac-toe board
	 * @param maximizingPlayer  flag that tells method who the maximizing player is
	 * @return The index of the CPU's move
	 */
	public static int minimax(char[] board, boolean maximizingPlayer) {
		if (isTerminalState(board)) {
			if (win(board, x)) {
				return 1;
			}
			if (win(board, o)) {
				return -1;
			}
			return 0;
		}
		if (maximizingPlayer) {
			int maxEval = Integer.MIN_VALUE;
			for (int i = 0; i < board.length; i++) {
				if (isValidMove(board, i + 1)) {
					board[i] = x;
					int eval = minimax(board, false);
					maxEval = Math.max(eval, maxEval);
					board[i] = emptySpace;
				}
			}
			return maxEval;
		}
		int minEval = Integer.MAX_VALUE;
		for (int i = 0; i < board.length; i++) {
			if (isValidMove(board, i + 1)) {
				board[i] = o;
				int eval = minimax(board, true);
				minEval = Math.min(eval, minEval);
				board[i] = emptySpace;
			}
		}
		return minEval;
	}

	//Main method
	public static void main(String[] args) {
		int boardSize = 9;
		char board[] = {'_', '_', '_', '_', '_', '_', '_', '_', '_'};
		System.out.println("Welcome to Tic Tac Toe!");
		System.out.println("The spaces on the grid are labeled from 1 to 9 with the number 1 corresponding to the top left and 9 to the bottom right.");
		System.out.println("You will play as 'O' while the CPU will play as 'X'.");
		System.out.println("Would you like to move first? If yes, please type 'yes', if not, please type 'no'");
		Scanner scan = new Scanner(System.in);
		String firstMove = scan.next();
		boolean userTurn = false;
		boolean validResponse = false;
		while (!validResponse) {
			if (firstMove.toLowerCase().equals("yes")) {
				userTurn = true;
				validResponse = true;
			}
			else if (firstMove.toLowerCase().equals("no")) {
				validResponse = true;
			}
			else {
				System.out.println("Please type only 'yes' or 'no'. No other response will be accepted.");
				System.out.println("Would you like to move first? If yes, please type 'yes', if not, please type 'no'.");
				firstMove = scan.next();
			}
		}
		if (userTurn) {
			System.out.println("Okay! Please make the first move.");
			displayBoard(board);
		}
		else {
			System.out.println("Okay!");
		}
		int movesMade = 0;
		while (movesMade < board.length) {
			if (userTurn) {
				if (userMove(board, scan)) {
					displayBoard(board);
					System.out.println("You Won!!!!! Congratulations!!!");
					break;
				}
			}
			else if (cpuMove(board)) {
				displayBoard(board);
				System.out.println("You lost. Better luck next time.");
				break;
			}
			displayBoard(board);
			movesMade++;
			userTurn = !userTurn;
		}
		if (movesMade == board.length) {
			System.out.print("It's a draw.");
		}
	}
}