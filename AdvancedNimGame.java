/* AdvancedNimGame.java
 * The class AdvancedNimGame implements the advanced Nim game.
 * Author: Natasha A. Thomas
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

import java.util.Scanner;
import java.util.Arrays;

public class AdvancedNimGame extends NimGame
{
	// Default constructor
	public AdvancedNimGame()
	{
		
	}
	
	// Parameterized constructor that initializes the instance variables and 
	// displays game information
	public AdvancedNimGame(int initStones, NimPlayer play1, 
			NimPlayer play2)
	{
		super(initStones, play1, play2);
		int i;
		System.out.println("\nInitial stone count: " + getInitialStones());
		System.out.print("Stones display: ");
		for(i = 1; i < initStones; i++)
		{
			System.out.print("<" + i + ",*> ");
		}
		System.out.print("<" + i + ",*>");
		System.out.println("\nPlayer 1: " + play1.getGivenName() + " " 
				+ play1.getFamilyName());
		System.out.println("Player 2: " + play2.getGivenName() + " " 
				+ play2.getFamilyName());
		System.out.println();
	}

	// Function that implements rules of advanced Nim game
	public void playGame(Scanner scanner, NimPlayer play1, NimPlayer play2)
	{
		int i, counter = 0, removeNumber = 0, initStones, upBound, 
				remainingStones;
		
		initStones = getInitialStones();
		upBound = 2;
		remainingStones = initStones;
		
		boolean available[] = new boolean[initStones]; // represents stones
		Arrays.fill(available, true);
		
		// Increments the number of games played by each player
		play1.updateNumGamesPlayed();
		play2.updateNumGamesPlayed();
		
		// The players remove stones in turns. The loop iterates till the 
		// number of stones becomes 0.
		while (remainingStones > 0)
 		{
			// Each player enters removes stones in turns. The loop 
			// iterates till there are no stones remaining.
			
			System.out.print(remainingStones + " stones left: ");
				
			// Displays stones on the screen
			for(i = 0; i < initStones - 1; i++)
			{
				if(available[i] == true)
				   	System.out.print("<" + (i + 1) + ",*> ");
			    else
			    	System.out.print("<" + (i + 1) + ",x> ");
			}
			if(available[i] == true)
				System.out.print("<" + (i + 1) + ",*>\n");
			else
				System.out.print("<" + (i + 1) + ",x>\n");
								
			// The value of counter initially set as 0 helps to determine 
			// which players turn it is.
			if (counter % 2 == 0)
			{
				System.out.print(play1.getGivenName() + 
						"'s turn - which to remove?\n");
				// Identify the type of player and invoke the appropriate 
				// function to make a move.
				// removeNumber indicates how many stones the player
				// removed.
				if(play1 instanceof NimHumanPlayer) 
				{
					removeNumber = 
								((NimHumanPlayer) play1).makeAdvancedMove
								(scanner, available, initStones);
				}
				else if(play1 instanceof NimAIPlayer)
				{
					removeNumber = ((NimAIPlayer) play1).makeMove
								(initStones, upBound);
					System.out.println();
				}
			}
			else
			{	
				System.out.print(play2.getGivenName() + "'s turn - "
						+ "which to remove?\n");
				// Identify the type of player and invoke the appropriate 
				// function to make a move.
				// removeNumber indicates how many stones the player
				// removed.
				if(play2 instanceof NimHumanPlayer)
				{
					removeNumber = 
							((NimHumanPlayer) play2).makeAdvancedMove
							(scanner, available, initStones);
				}
				else if(play2 instanceof NimAIPlayer)
				{
					removeNumber = ((NimAIPlayer) play2).makeMove
							(initStones, upBound);
					System.out.println();
				}
			}		
			
			remainingStones = remainingStones - removeNumber;
			
			// The counter is incremented to indicate next players turn.
			if (removeNumber != 0)
				counter++;
		}
		
		System.out.println("Game Over");
		
		if(counter % 2 == 0)
		{
			System.out.println(play1.getGivenName() + " " + 
					play1.getFamilyName() + " wins!");
			play1.updateNumGamesWon();
		}
			
		else
		{
			System.out.println(play2.getGivenName() + " " + 
					play2.getFamilyName() + " wins!");
			play2.updateNumGamesWon();
		}
		
	}
	
}
