/* InvalidCommandException.java
 * This class is a user-defined exception which is thrown when the user enters
 * an invalid command.
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

public class InvalidCommandException extends Exception
{
	private String invalidCommand;
	
	public InvalidCommandException(String command)
	{
		invalidCommand = command;
	}
	
	public void getMsg()
	{
		System.out.println("'" + invalidCommand + "'" + 
				" is not a valid command.");
	}

}
