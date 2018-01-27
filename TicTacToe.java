//Author: Jonathan Hassel
//Date: 2/1/17
//Class: COP3252 - Thrasher

public class TicTacToe
{
	public static final int INGAME = 0, XWINS = 1, OWINS = 2, DRAW = 3;
	public static int state = 0;
	public static int[][] board = new int[3][3];

	public static void main(String[] args)
	{
		initBoard();
		printBoard();


		if(args.length == 0)
		{
			//p1 v p2
			Player player1 = new Player(1,0);
			Player player2 = new Player(2,0);
			gameLoop(player1, player2);
		}
		else if(args.length == 1)
		{
			if("-c".equals(args[0]))
			{
				//cpu v cpu
				Player player1 = new Player(1,1);
				Player player2 = new Player(2,1);
				gameLoop(player1, player2);
			}
			else
				System.out.println("Usage: java TicTacToe [-c [1|2]]");
		}
		else if (args.length == 2)
		{
			if("-c".equals(args[0]))
			{
				if("1".equals(args[1]))
				{
					//cpu v p2
					Player player1 = new Player(1,1);
					Player player2 = new Player(2,0);
					gameLoop(player1, player2);
				}
				else if("2".equals(args[1]))
				{
					//p1 v cpu
					Player player1 = new Player(1,0);
					Player player2 = new Player(2,1);
					gameLoop(player1, player2);
				}
				else
					System.out.println("Usage: java TicTacToe [-c [1|2]]");
			}
			else
				System.out.println("Usage: java TicTacToe [-c [1|2]]");
		}
		else
			System.out.println("Usage: java TicTacToe [-c [1|2]]");
	}


	//METHODS----------------------------------------------------------------------------------------------
	public static void gameLoop(Player player1, Player player2)
	{	//main game loop, runs until state != INGAME. Prints out winner.
		while(state == INGAME)
		{
			player1.playerMove();
			if(state != INGAME)
				break;
			player2.playerMove();

		}
		if(state == 3)
			System.out.println("GAME IS A DRAW!");
		else
			System.out.println("PLAYER " + state + " WINS!");
	}


	public static void updateState()
	{	//updates the state of the game, only changes the state when someone wins or a draw occurs
		if(isWinner() == 1 || isWinner() == 2)
		{
			if(isWinner() == 1)
				state = XWINS;
			if(isWinner() == 2)
				state = OWINS;
		}
		else if(isWinner() == 3)
		{
			state = DRAW;
		}
		else
			state = INGAME;
	}


	public static int isWinner()
	{	//checking if p1 or p2 wins or if a draw occurs
		if(checkWin(1))
			return 1;
		if(checkWin(2))
			return 2;
		if(isBoardFull())
			return 3;
		return 0;
	}


	public static boolean checkWin(int p)
	{
		for(int i = 0; i < 3; ++i)
		{	//checking for vertical wins
			if(board[0][i] == p && board[1][i] == p && board[2][i] == p)
				return true;
		}


		for(int i = 0; i < 3; ++i)
		{	//checking for horizontal wins
			if(board[i][0] == p && board[i][1] == p && board[i][2] == p)
				return true;
		}


		//checking for diagonal wins(top left to bottom right diagonal)
		if(board[0][0] == p && board[1][1] == p && board[2][2] == p)
			return true;

		//checking for diagonal wins(bottom left to top right diagonal)
		if(board[2][0] == p && board[1][1] == p && board[0][2] == p)
			return true;

		//if none of these are true, return false
		return false;
	}


	public static boolean isBoardFull()
	{	//checks if game board is full
		for(int i = 0; i < 3; ++i)
		{
			for(int j = 0; j < 3; ++j)
			{
				if(board[i][j] == 0)
					return false;
			}
		}
		return true;
	}


	public boolean isEmpty(int position)
	{	//checks if a position is empty, returns true if it is, false otherwise
		boolean temp = false;

		switch(position)
		{
			case 1:
				if(board[0][0] == 0) temp = true;
				break;
			case 2:
				if(board[0][1] == 0) temp = true;
				break;
			case 3:
				if(board[0][2] == 0) temp = true;
				break;
			case 4:
				if(board[1][0] == 0) temp = true;
				break;
			case 5:
				if(board[1][1] == 0) temp = true;
				break;
			case 6:
				if(board[1][2] == 0) temp = true;
				break;
			case 7:
				if(board[2][0] == 0) temp = true;
				break;
			case 8:
				if(board[2][1] == 0) temp = true;
				break;
			case 9:
				if(board[2][2] == 0) temp = true;
				break;
		}
		return temp;
	}


	public static void initBoard()
	{	//initializes every board osition to 0
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
				board[i][j] = 0;
		}
	}


	public static void printBoard()
	{	//prints out gameBoard and instructionBoard
		System.out.println("Game Board:\t\t\tPositions:\n");

		System.out.print(printPosition(board[0][0]) + "|" + printPosition(board[0][1]) + "|" + printPosition(board[0][2]));
		System.out.println("\t\t\t 1 | 2 | 3 ");				//prints row 1

		System.out.println("-----------\t\t\t-----------");		//prints partition

		System.out.print(printPosition(board[1][0]) + "|" + printPosition(board[1][1]) + "|" + printPosition(board[1][2]));
		System.out.println("\t\t\t 4 | 5 | 6 ");				//prints row 2

		System.out.println("-----------\t\t\t-----------");		//prints partition

		System.out.print(printPosition(board[2][0]) + "|" + printPosition(board[2][1]) + "|" + printPosition(board[2][2]));
		System.out.println("\t\t\t 7 | 8 | 9 ");				//prints row 3
	}


	public static String printPosition(int position)
	{	//prints different string depending on if the position contains a 0, 1 or 2
		String temp = "";

		switch(position)
		{
			case 0:
				temp = "   ";
				break;

			case 1:
				temp = " X ";
				break;

			case 2:
				temp = " O ";
				break;
		}
		return temp;
	}
}
