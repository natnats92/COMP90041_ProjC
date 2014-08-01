/* InvalidMoveException.java
 * This class is a user-defined exception which is thrown when the user tries 
 * to make an invalid move, ie. tries to remove more stones than the specified
 * upper bound or total number of stones remaining.
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

public class InvalidMoveException extends Exception
{
	int upperBound;
	
	public InvalidMoveException()
	{
		
	}
	
	public InvalidMoveException(int upperBound)
	{
		this.upperBound = upperBound;
	}
	
	public void getMsg()
	{
		System.out.println("Invalid move. You must remove between "
				+ "1 and " + upperBound + " stones.\n");
	}
	
	public void displayMessage()
	{
		System.out.println("\nInvalid move.\n");
	}
}
