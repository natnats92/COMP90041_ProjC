/* Nimsys.java
 * The class Nimsys is the driver class. Commands are accepted from the user 
 * and actions are carried out accordingly.
 * Player data is read from and written to a file called players.dat
 * Author: Natasha A. Thomas
 * Student ID: 669907
 * Username: natashat1
 * Created on 30/05/2014
 */

import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class Nimsys
{
	public static final int MAX_PLAYERS = 100;
	public static  NimPlayer[] players = new NimPlayer[MAX_PLAYERS];
	public static int playerCount = 0;

	// Function to sort the list of players by ascending order of username
	public static void sortPlayers(NimPlayer[] players, int count)
	{
		int i, j;
		// Implementation of bubble sort for sorting player list
		for(i = 0; i < count; i++)
			for(j = 0; j < count - 1; j++)
			{	
				if(players[j].getUserName().compareTo
						(players[j+1].getUserName()) > 0)
				{
					// Swapping players at positions j and j + 1
					NimPlayer temp = new NimPlayer();
					temp = players[j];
					players[j] = players[j+1];
					players[j+1] = temp;
				}
			}
	}
	
	// Function to read the player data from a file to an array of type 
	// NimPlayer
	public static void readFromFile(File file)
	{
		FileInputStream fIn;
		ObjectInputStream ipStream;
		try
		{
			fIn = new FileInputStream(file);
			ipStream = new ObjectInputStream(fIn);
			players = (NimPlayer[])ipStream.readObject();
			ipStream.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	// Function which writes the player data as a NimPlayer[] object to the 
	// file
	public static void writeToFile(File file)
	{
		FileOutputStream fOut;
		ObjectOutputStream opStream;
		try
		{
			fOut = new FileOutputStream(file);
			opStream = new ObjectOutputStream(fOut); 
			opStream.writeObject(players);
			opStream.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	// Function that returns the number of players in the NimPlayer[] array
	public static int getPlayerCount()
	{
		int i;
		for(i = 0; i < players.length; i++)
		{
			if(players[i] == null)
				break;
		}
		return i;
	}

	
	public static void main(String args[])
	{
		int i, flag, flag1, flag2, index1 = 0, index2 = 0, initialStones, 
				upperBound;
		String userName, familyName, givenName, command, userResponse, player1, 
				player2;
		
		File file = new File("players.dat");
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch(IOException e)
			{
				System.out.println("File cannot be created");
			}
		}
				
		readFromFile(file); // Player details read from file
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to Nim");
		System.out.print("\n>");	
		
		String userCommand = scanner.nextLine();
		
		// Loop to accept commands from users. Loop terminates when the user 
		// enters "exit". 
		
		while (!userCommand.equals("exit")) 
		{
			// StringTokenizer splits tokens by " " and ","
			StringTokenizer st = new StringTokenizer(userCommand," //,");
			
			command = st.nextElement().toString();
			
			try
			{
				if (command.equals("addplayer") || command.equals("addaiplayer"))
				{

					playerCount = getPlayerCount();
					
					// Max no. of players is 100. A player can be added only if
					// there are less than 100 players currently.
					if(playerCount < MAX_PLAYERS)
					{
						// If sufficient no. of arguments are not provided, an
						// exception is thrown.
						if(st.countTokens() < 3)
							throw new InvalidArgumentCountException();
						userName = st.nextElement().toString();
						familyName = st.nextElement().toString();
						givenName = st.nextElement().toString();
										
						// Checking if the player already exists in the 
						// NimPlayer[] array
						flag = 0;
						for(i = 0; i < playerCount; i++)
						{
							if (players[i].getUserName().equals(userName))
							{
								System.out.println("The player already exists.");
								flag = 1;
								break;
							}
						}
						if (flag == 0) 	// Indicates that the player does not 
										// exist, so the player is inserted.
						{
							if(command.equals("addplayer"))
							{
								NimHumanPlayer humanPlayer = new NimHumanPlayer(userName, familyName, givenName);
								players[i] = humanPlayer;
							}
							else
							{
								NimAIPlayer aiPlayer = new NimAIPlayer(userName, familyName, givenName);
								players[i] = aiPlayer;
							}
						}
					}
					else
						System.out.println("Maximum number of players is 100");
				}
				
				else if (command.equals("removeplayer"))
				{
					// Checks if a username is specified and deletes that 
					// particular player
					if (st.hasMoreElements())
					{	
						userName = st.nextElement().toString();
												
						flag = 0;
						playerCount = getPlayerCount();
						for(i = 0; i < playerCount; i++)
						{
							if (players[i].getUserName().equals(userName))
							{
								// Overwrite the details of the player to be 
								// deleted with the details of the last player 
								// in the list
								players[i] = players[playerCount - 1];
								players[playerCount - 1] = null;
								flag = 1;
								break;
							}
						}
						if (flag == 0)
							System.out.println("The player does not exist.");
					}
				
					// When username is not specified, the user is given the 
					// option to delete all players.
					else
					{
						System.out.println("Are you sure you want to remove "
							+ "all players? (y/n)");
						userResponse = scanner.nextLine();
						if(userResponse.equals("y"))
						{
							playerCount = getPlayerCount();
							for(i = 0; i < playerCount; i++)
								players[i] = null;
						}					
					}
				}
			
				else if (command.equals("editplayer"))
				{
					// If sufficient no. of arguments are not provided, an
					// exception is thrown.
					if(st.countTokens() < 3)
						throw new InvalidArgumentCountException();
					userName = st.nextElement().toString();
					familyName = st.nextElement().toString();
					givenName = st.nextElement().toString();
					
					flag = 0;
					playerCount = getPlayerCount();
					// Checks if the player exists and if so, updates the 
					// details.
					for(i = 0; i < playerCount; i++)
					{
						if (players[i].getUserName().equals(userName))
						{
							players[i].setGivenName(givenName);
							players[i].setFamilyName(familyName);
							flag = 1;
							break;
						}
					}
					if (flag == 0)
						System.out.println("The player does not exist.");
				}
			
				else if (command.equals("resetstats"))
				{
					// To reset statistics of a particular player
					if (st.hasMoreElements())
					{	
						userName = st.nextElement().toString();
						
						flag = 0;
						playerCount = getPlayerCount();
						for(i = 0; i < playerCount; i++)
						{
							if(players[i].getUserName().equals(userName))
							{
								players[i].resetStats();
								flag = 1;
								break;
							}
						}
						if(flag == 0)
							System.out.println("The player does not exist.");
					}
				
					// A username is not provided by the user. So the user is 
					// given the option to reset statistics of all players.
					else
					{
						System.out.println("Are you sure you want to reset all "
							+ "player statistics? (y/n)");
						userResponse = scanner.nextLine();
						if(userResponse.equals("y"))
						{
							playerCount = getPlayerCount();
							for(i = 0; i < playerCount; i++)
							{
								players[i].resetStats();
							}
						}					
					}
				}
			
				else if (command.equals("displayplayer"))
				{
					// The user provides a username. The code searches for the 
					// username and displays details if found.
					if (st.hasMoreElements())
					{	
						userName = st.nextElement().toString();
												
						flag = 0;
						playerCount = getPlayerCount();
						for(i = 0; i < playerCount; i++)
						{
							if (players[i].getUserName().equals(userName))
							{
								System.out.println(players[i]);
								flag = 1;
								break;
							}
						}
						if (flag == 0)
							System.out.println("The player does not exist.");
					}
				
					// If the user does not provide a username, then details of 
					// all players are displayed after sorting by username.
					else 
					{
						playerCount = getPlayerCount();
						sortPlayers(players, playerCount); 	// To list the 
															// players in 
															// order of their
															// usernames
						for(i = 0; i < playerCount; i++)
						{
							System.out.println(players[i]);
						}
					}
				}
			
				else if (command.equals("rankings"))
				{
					int count = 0; 
					
					playerCount = getPlayerCount();
				
					// The list of players is bubble sorted by username.
					sortPlayers(players, playerCount);
					
					// The code bubble sorts the players by winning ratio. 
					// Also, since bubble sort is a stable sorting method, 
					// if two players have the same winning ratio, they remain 
					// sorted according to username.
					for(i = 0; i < playerCount; i++)
					{
						for(int j = 0; j < playerCount - 1; j++)
						{	
							double ratio1 = 0, ratio2 = 0;
						
							try
							{
								ratio1 = (players[j].getNumGamesWon() * 
									1.0/players[j].getNumGamesPlayed()) * 100;
							}
							catch(ArithmeticException e)
							{
								ratio1 = 0;
							}
							
							try
							{
								ratio2 = (players[j+1].getNumGamesWon() * 
									1.0/players[j+1].getNumGamesPlayed()) * 
									100;
							}
							catch(ArithmeticException e)
							{
								ratio2 = 0;
							}
							// Players j and j + 1 are swapped if ratio of j is 
							// less than ratio of j + 1
							if (ratio1 < ratio2)
							{
								NimPlayer temp = new NimPlayer();
								temp = players[j];
								players[j] = players[j+1];
								players[j+1] = temp;
							}
						}
					}
				
					// Loop to display ranked list of players
					for(i = 0; i < playerCount; i++)
					{
						double ratio = 0;
						int ratioFormatted;
					
						try
						{
							ratio = (players[i].getNumGamesWon() * 
								1.0 /players[i].getNumGamesPlayed()) * 100;
						}
						catch(ArithmeticException e)
						{
							ratio = 0;
						}
						// Rounding up to the nearest integer
						ratioFormatted = (int)(ratio + 0.5d);
					
						// Displaying ranking in the prescribed format
						System.out.printf("%-5s| %02d games | %s %s\n", 
							ratioFormatted + "%", 
							players[i].getNumGamesPlayed(), 
							players[i].getGivenName(), 
							players[i].getFamilyName());
					
						// Only top 10 players need to be displayed
						count = count + 1;
						if (count == 10)
							break;
					}
				}
			
				else if (command.equals("startgame") || command.equals("startadvancedgame"))
				{
					upperBound = 0;
					// If sufficient no. of arguments are not provided, an
					// exception is thrown.
					if (command.equals("startgame"))
					{	
						if(st.countTokens() < 4)
							throw new InvalidArgumentCountException();
						else
						{
							initialStones = Integer.parseInt
									(st.nextElement().toString());
							upperBound = Integer.parseInt
									(st.nextElement().toString());
							player1 = st.nextElement().toString();
							player2 = st.nextElement().toString();
						}
					}
					else
					{
						if(st.countTokens() < 3)
							throw new InvalidArgumentCountException();
						else
						{
							initialStones = Integer.parseInt
									(st.nextElement().toString());
							player1 = st.nextElement().toString();
							player2 = st.nextElement().toString();
						}
					}
					flag1 = 0;
					flag2 = 0;
					playerCount = getPlayerCount();
					// Loop to check if the two players exist or not
					for(i = 0; i < playerCount; i++)
					{
						if(players[i].getUserName().equals(player1))
						{
							flag1 = 1;
							index1 = i;
						}
						if(players[i].getUserName().equals(player2))
						{
							flag2 = 1;
							index2 = i;
						}
					}
				
					if (flag1 == 0 || flag2 ==0)
						System.out.println
						("One of the players does not exist.");
				
					// Both players exist. So a new game of Nim is started.
					else
					{
						if (command.equals("startgame"))
						{
							NimGame newGame = new NimGame(initialStones, 
								upperBound, players[index1], players[index2]);
							newGame.playGame(scanner, players[index1], 
								players[index2]);
						}
						else
						{
							AdvancedNimGame newGame = new AdvancedNimGame
								(initialStones, players[index1], 
								players[index2]);
							newGame.playGame(scanner, 
								players[index1], players[index2]);
						}
					}
				}
				
				else // Invalid command entered
				{
					InvalidCommandException e = 
							new InvalidCommandException(command);
					throw e;
				}
			}
			catch(InvalidCommandException e)
			{
				e.getMsg();
			}
			catch(InvalidArgumentCountException e)
			{
				e.getMsg();
			}
			finally
			{
				System.out.print("\n>");
				userCommand = scanner.nextLine();
			}
		}
		
		System.out.println();
		
		writeToFile(file); //Player details written back to file
	
		scanner.close(); 
		System.exit(0);
	}
}
