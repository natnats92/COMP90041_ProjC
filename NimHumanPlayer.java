/* NimHumanPlayer.java
 * This class is a subclass of NimPlayer.java and implements the moves made by
 * a human player for Nim game and advanced Nim game.
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class NimHumanPlayer extends NimPlayer 
{
	
	public NimHumanPlayer(String uname, String lname, String fname)
	{
		super(uname,lname,fname);
	}
	
	// Determines user move for Nim game by prompting the user
	public int makeMove(Scanner scanner)
	{
		int numStonesToRemove = 0;
		
		numStonesToRemove = scanner.nextInt();
		System.out.println();
		scanner.nextLine();
		
		return numStonesToRemove;
	}
	
	public void throwException()
	{
		try
		{
			InvalidMoveException e = new InvalidMoveException();
			throw e;
		}
		catch(InvalidMoveException e)
		{
			e.displayMessage();
		}
	}
	
	// Determines user move for advanced Nim game by prompting the user
	public int makeAdvancedMove(Scanner scanner, boolean available[], 
			int initialStones)
	{
		int i, n1 = 0, n2 = 0;
		
		// Read input from user
		String line = scanner.nextLine();
		
		// StringTokenizer is used to extract the elements from user input
		StringTokenizer st = new StringTokenizer(line, " ");
		n1 = Integer.parseInt(st.nextElement().toString());
		if(st.hasMoreElements())
			n2 = Integer.parseInt(st.nextElement().toString());
		if(st.hasMoreElements()) 	// If user has entered more than 2 values
									// the input is invalid.
		{
			throwException();
			return 0;
		}
		if (n2 == 0)
		{
			for(i = 0; i < initialStones; i++)
			{
				if(i == n1 - 1)
				{
					// Check if the stone has already been removed
					if (available[i] == false) 
					{
						throwException();
						return 0;
					}
					// Remove the stone only if it has not been removed
					// earlier
					else 
						available[i] = false;
				}
			}

			System.out.println();
			return 1;
		}
		else
		{
			// Check if the stones are adjacent to each other
			if(n2 - n1 == 1)
			{
				for(i = 0; i < initialStones; i++)
				{
					if(i == n1 - 1 || i == n2 - 1)
					{
						// Check if the stone has already been removed
						if (available[i] == false)
						{
							throwException();
							return 0;
						}
						// Remove the stone only if it hasn't been removed
						// earlier
						else 
							available[i] = false;
					}
				}
			}
			else
			{
				throwException();
				return 0;
			}
			System.out.println();
			return 2;
		}
	}
}
