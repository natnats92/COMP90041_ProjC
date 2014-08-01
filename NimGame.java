/* NimGame.java
 * The class NimGame implements the Nim game.
 * Author: Natasha A. Thomas
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

import java.util.Scanner;

public class NimGame {

	private int initialStones, upperBound;
	private String player1, player2;
	
	// Default constructor
	public NimGame()
	{
		
	}
	
	// Parameterized constructor that initializes the instance variables and 
	// displays game information
	public NimGame(int initStones, int upBound, NimPlayer play1, 
			NimPlayer play2)
	{
		initialStones = initStones;
		upperBound = upBound;
		player1 = play1.getGivenName();
		player2 = play2.getGivenName();
		System.out.println("\nInitial stone count: " + initialStones);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + player1 + " " 
				+ play1.getFamilyName());
		System.out.println("Player 2: " + player2 + " " 
				+ play2.getFamilyName());
		System.out.println();
	}
	
	// Constructor for advanced Nim game
	public NimGame(int initStones, NimPlayer play1, 
			NimPlayer play2)
	{
		initialStones = initStones;
		upperBound = 2;
		player1 = play1.getGivenName();
		player2 = play2.getGivenName();
	}
	
	// Getter method for initialStones
	public int getInitialStones()
	{
		return initialStones;
	}
	
	// Function that implements the rules of Nim game
	public void playGame(Scanner scanner, NimPlayer play1, NimPlayer play2)
	{
		int counter = 0, removeNumber = 0;
			
		// Increments the number of games played by each player
		play1.updateNumGamesPlayed();
		play2.updateNumGamesPlayed();
		
		// The players remove stones in turns. The loop iterates till the 
		// number of stones becomes 0.
		while (initialStones > 0)
 		{
			// Each player enters number of stones to be removed. In case of 
			// a human player, the user is prompted to enter a number. In 
			// case of an AI player, the number is calculated according to a 
			// victory guaranteed strategy. The loop iterates till a valid 
			// value for removeNumber is entered.
			do
			{	
				System.out.print(initialStones + " stones left: ");
				
				// Displays stones on the screen
				for (int i = 0; i < initialStones - 1; i++)
				{
					System.out.print("* ");
				}
				System.out.print("*\n");
				
				// When the number of stones remaining is less than the 
				// upper bound, the user must not be able to remove a number
				// more than the number of stones remaining.
				if (initialStones < upperBound)
					upperBound = initialStones;
				
				// The value of counter initially set as 0 helps to determine 
				// which players turn it is.
				if (counter % 2 == 0)
				{
					System.out.print(player1 + "'s turn - remove how many?\n");
					// Identify the type of player and invoke the appropriate 
					// function to make a move
					if(play1 instanceof NimHumanPlayer) 
					{
						removeNumber = ((NimHumanPlayer) play1).makeMove
								(scanner);
					}
					else if(play1 instanceof NimAIPlayer)
					{
						removeNumber = ((NimAIPlayer) play1).makeMove
								(initialStones, upperBound);
						System.out.println();
					}
				}
				else
				{	
					System.out.print(player2 + "'s turn - remove how many?\n");
					// Identify the type of player and invoke the appropriate 
					// function to make a move
					if(play2 instanceof NimHumanPlayer)
					{
						removeNumber = ((NimHumanPlayer) play2).makeMove
								(scanner);
					}
					else if(play2 instanceof NimAIPlayer)
					{
						removeNumber = ((NimAIPlayer) play2).makeMove
								(initialStones, upperBound);
						System.out.println();
					}
				}
				
				try
				{
					// If the number to be removed is not a valid value, an 
					// exception is thrown.
					if (removeNumber > upperBound || removeNumber == 0)
					{
						InvalidMoveException e = new InvalidMoveException
								(upperBound);
						throw e;
					}
				}
				catch(InvalidMoveException e)
				{
					e.getMsg();
				}
				
			
			} while (removeNumber > upperBound || removeNumber == 0);
			
			// Remove stones
			initialStones = initialStones - removeNumber;
			
			// The counter is incremented to indicate next players turn.
			counter++;
		}
		
		System.out.println("Game Over");
		
		if(counter % 2 == 0)
		{
			System.out.println(player1 + " " + play1.getFamilyName() 
					+ " wins!");
			play1.updateNumGamesWon();
		}
			
		else
		{
			System.out.println(player2 + " " + play2.getFamilyName() 
					+ " wins!");
			play2.updateNumGamesWon();
		}
		
	}
}
