/* NimAIPlayer.java
 * This class is a subclass of NimPlayer.java and implements the moves made by
 * an AI player for Nim game and advanced Nim game.
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

import java.util.Random;

public class NimAIPlayer extends NimPlayer implements Testable
{
	public NimAIPlayer()
	{
		
	}
	
	public NimAIPlayer(String uname, String lname, String fname)
	{
		super(uname,lname,fname);
	}
	
	// Determines the number of stones to be removed by the AI player 
	// according to a guaranteed victory strategy for Nim game
	public int makeMove(int initialStones, int upperBound)
	{
		int numStonesToRemove = 0, k;
		
		for(k = 0; (k * (upperBound + 1) + 1) <= initialStones; k++);
		
		k--;
		
		if ((k * (upperBound + 1) + 1) == initialStones)
		{
			Random random = new Random(); 
			numStonesToRemove = random.nextInt(upperBound) + 1; // Random no.
																// between 1
																// and the
																// upper bound
		}
		else
		{
			numStonesToRemove = initialStones - (k * (upperBound + 1) + 1);
		}
		
		return numStonesToRemove;
	}
	
	// Determines the number of stones to be removed by the AI player 
	// according to a guaranteed victory strategy for advanced Nim game
	public String advancedMove(boolean[] available, String lastMove) 
	{
        String str = "";
        return str;
	}
}
