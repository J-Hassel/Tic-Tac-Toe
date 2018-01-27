//Name: Jonathan Hassel
//Date: 2/1/17
//Class: COP3252 - Thrasher
import java.util.Scanner;
import java.util.Random;

public class Player extends TicTacToe
{
	Scanner input = new Scanner(System.in);
	Random rand = new Random();
	public final int playerNumber, playerType;
	public int position;

	//CONSTRUCTORS--------------------------------------------------------------------------------------
	Player()
	{
		playerNumber = 0;
		playerType = 0;
	}

	Player(int number, int type)
	{
		playerNumber = number;	//1 or 2
		playerType = type;		//0 == human, 1 == cpu
	}


	//METHODS------------------------------------------------------------------------------------------
	public void playerMove()
	{
		if(playerType == 0)
		{	//human
			System.out.print("Player " + playerNumber + ", please enter a move(1-9): ");
			position = input.nextInt();
			System.out.println();

			while(position < 1 || position > 9 || !(isEmpty(position)))
			{
				System.out.print("Invalid position. Enter an empty position in the range (1-9): ");
				position = input.nextInt();
			}
			fillPosition(position); 	//fills position
		}
		else
		{	//computer player
			computerLogic(playerNumber);

			fillPosition(position);		//fills position

			System.out.println("Player " + playerNumber + " chooses position " + position + ".\n");
		}
		printBoard();		//print board
		updateState();		//update game state
	}


	public void computerLogic(int playerNumber)
	{	//if statements to control logic when computer is either player1 or player2
		if(playerNumber == 1)
		{
			if(findWinningMove(1))			//takes comuters winning move
				position = position;		//position is changed in findWinningMove()
			else if(findWinningMove(2))		//blocks opponents winning move
				position = position;		//position is changed in findWinningMove()
			else if(board[1][1] == 0)		//if center is empty, take it
				position = 5;
			else
			{	//chooses random empty position
				int n = rand.nextInt(9) + 1;
				while(!isEmpty(n))
					n = rand.nextInt(9) + 1;
				position = n;
			}
		}
		else if(playerNumber == 2)
		{
			if(findWinningMove(2))			//takes computers winning move
				position = position;		//position is changed in findWinningMove()
			else if(findWinningMove(1))		//blocks opponents winning move
				position = position;		//position is changed in findWinningMove()
			else if(board[1][1] == 0)		//if center is empty, take it
				position = 5;
			else
			{	//chooses random empty position
				int n = rand.nextInt(9) + 1;
				while(!isEmpty(n))
					n = rand.nextInt(9) + 1;
				position = n;
			}
		}
	}


	public boolean findWinningMove(int p)
	{
		for(int i = 0; i < 3; ++i)			//checks for vertical and horizontal winning moves
		{
			//checking verticals
			if(board[0][i] == 0 && board[1][i] == p && board[2][i] == p)
			{	//top of vertical = winning positionn
				position = i + 1;
				return true;
			}

			if(board[0][i] == p && board[1][i] == 0 && board[2][i] == p)
			{	//middle of a vertical = winning position
				position = i + 4;
				return true;
			}

			if(board[0][i] == p && board[1][i] == p && board[2][i] == 0)
			{	//top of vertical = winning positionn
				position = i + 7;
				return true;
			}


			//checking horizontals
			if(board[i][0] == 0 && board[i][1] == p && board[i][2] == p)
			{	//first of horizontal = winning positionn
				position = (3 * i) + 1;
				return true;
			}

			if(board[i][0] == p && board[i][1] == 0 && board[i][2] == p)
			{	//middle of horizontal = winning position
				position = (3 * i) + 2;
				return true;
			}

			if(board[i][0] == p && board[i][1] == p && board[i][2] == 0)
			{	//last of horizontal position = winning positionn
				position = (3 * i) + 3;
				return true;
			}
		}

		//checking \ diagonal(top left)
		if(board[0][0] == 0 && board[1][1] == p && board[2][2] == p)
		{
			position = 1;
			return true;
		}

		//checking \ diagonal(middle)
		if(board[0][0] == p && board[1][1] == 0 && board[2][2] == p)
		{
			position = 5;
			return true;
		}

		//checking \ diagonal(bottom right)
		if(board[0][0] == p && board[1][1] == p && board[2][2] == 0)
		{
			position = 9;
			return true;
		}


		//checking / diagonal(bottom left)
		if(board[2][0] == 0 && board[1][1] == p && board[0][2] == p)
		{
			position = 7;
			return true;
		}

		//checking / diagonal(middle)
		if(board[2][0] == p && board[1][1] == 0 && board[0][2] == p)
		{
			position = 5;
			return true;
		}

		//checking / diagonal(top right)
		if(board[2][0] == p && board[1][1] == p && board[0][2] == 0)
		{
			position = 3;
			return true;
		}

		return false;
	}


	public void fillPosition(int position)
	{	//fills the position with the correct symbol
		switch(position)
		{
			case 1:
				board[0][0] = playerNumber;
				break;
			case 2:
				board[0][1] = playerNumber;
				break;
			case 3:
				board[0][2] = playerNumber;
				break;
			case 4:
				board[1][0] = playerNumber;
				break;
			case 5:
				board[1][1] = playerNumber;
				break;
			case 6:
				board[1][2] = playerNumber;
				break;
			case 7:
				board[2][0] = playerNumber;
				break;
			case 8:
				board[2][1] = playerNumber;
				break;
			case 9:
				board[2][2] = playerNumber;
				break;
		}
	}
}
