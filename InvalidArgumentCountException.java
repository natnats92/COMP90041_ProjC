/* InvalidArgumentCountException.java
 * This class is a user-defined exception which is thrown when the user does 
 * not enter sufficient arguments for a command.
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

public class InvalidArgumentCountException extends Exception
{
	public InvalidArgumentCountException()
	{
		
	}
	
	public void getMsg()
	{
		System.out.println("Incorrect number of arguments supplied to command.");
	}
}
