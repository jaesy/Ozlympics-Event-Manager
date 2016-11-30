import java.util.*;
import java.awt.List;
public class GamesSession 
{

	
	//game list to store a information of current session before the
	//run game method executed.
	//Game History will store all completed games.
	private Sports[] game = new Sports[10];	
	private Sports[] gameHistory = new Sports[100];
	
	private int GameCount; // no. of games in session
	private int GameHistoryCount; // no. of total games overall
	private String GameID;
	//foundGameList used for validating athletes in games already registered
	private ArrayList<Integer> foundGameList = new ArrayList<Integer>();	
	

	public boolean CheckAllGamesReady()
	{
		boolean gameWarning = false;
		for(int i = 0; i < GameCount; i++)
		{
           
			System.out.println("Game ID: " + game[i].getGameID());
			
			if(GameReadyCheck(i)==true)
			{
				System.out.println("Game is Ready!!");
			}
			else
			{		
				gameWarning = true;
			}	   
		}
		return gameWarning; //if returned true, there is a warning (no off or/and not enough players).
	}
	
	

	//Using game index parameter, will check if that game x
	//pass all requirments (no. of athletes, an appointed ref).
	public boolean GameReadyCheck(int x)
	{
		
		boolean checkComplete = false;
		boolean enoughAth = false;
		boolean refAppointed = false;
		
		if(game[x].gameStatus()==true)
		{		
			enoughAth = true;;	
		}
		else
		{
			System.out.println("Not enough players");//return a msg if not enough
		}                                            //enough athletes
		
		if(game[x].GetOffAppointed()==true)
		{	
			refAppointed = true;;		
		}
		else
		{
			System.out.println("No Appointed Official");
		}
		
		//
		if(enoughAth == true && refAppointed == true)
		{
			checkComplete = true; //executed if ref appointed and enough ath.
		}	
		return checkComplete;	
	}
	
	
	
	public String StartAllGames()
	{
		
	    String output="";
		
	    for(int i = 0; i < GameCount; i++)
		{
		    output += "===========================================================\n";
	        //If all validations are true (enought players and an official) then if
		    //loop will be executed, running all methods to run and game and constructing
		    // string to show results of output
			if(GameReadyCheck(i)==true)
			{
		        output += "Starting Game:\n";
		        output += "Game Type: ";
		        if(game[i] instanceof Swimming){
		        	output += "Swimming\n";
		        }    
		        else if(game[i] instanceof Running){
		        	output += "Running\n";
		        }
		        else if(game[i] instanceof Cycling){
		        	output += "Cycling\n";
		        }
		        
				output += "Game ID: " + game[i].getGameID()+"\n";
				game[i].competeAll();
				output += game[i].showCompetes();
			    game[i].awardPoints();
				output+=game[i].showAwards()+"\n";
			}
			else //If game doesn't meet requirements, else loop executed with messages
			{
			    output += "Game ID: " + game[i].getGameID();
				output += " was cancelled because there was not enough"
						+ " participants or/and no official was appointed\n";	
			}

			output += "===========================================================\n";
		}
		return output;
	}
	
	//Method for sending a string parameter to return a message when a method 
	//already has a return type.
	public static String globalMsg;
	public static void GlobalMsg(String x)
	{
		globalMsg = "";
		globalMsg += x;	
	}

	public void resetfoundGameList() 
	{
		foundGameList.clear();
	}
	
	//clear variables when finishing complete games in a session
	public void restartGameSession()
	{
		game = null;
		game = new Sports[10];
		GameCount = 0;	
	}
	
	//Creates a game using int game type parameter
	//if parmeter is 1, create a new Swimming Class
	public int createGame(int x)
	{
		int newGameArray = -1;
		GameCount++;
		StringBuilder sb = new StringBuilder();
		int gameType = x;
		
        switch (x)
        {
            case 1: 
            	//String constucting for a new ID
            	sb.append("S"); 
            	sb.append(GameCount);
        		String id = sb.toString();
        		
        		game[GameCount-1] = new Swimming(id);   
        		newGameArray = GameCount - 1;
            	;
                break;
     	       
            case 2: 
            	sb.append("C");
            	sb.append(GameCount);
        		String id1 = sb.toString();
        		game[GameCount-1] = new Cycling(id1);    
        		newGameArray = GameCount - 1;
            	;
                break;
	
            case 3: 
            	sb.append("R");
            	sb.append(GameCount);
            	
        		String id2 = sb.toString();
        		game[GameCount-1] = new Running(id2);    
        		newGameArray = GameCount - 1;
            	;
                break;
        }	
		return newGameArray;
		
	}

	public void gameStart()
	{
		game[GameCount].compete();
	}

	public void addAthToGame(String x)
	{
		 game[GameCount].addMember(x);	
	}
	
	public void addRefToGame(String x)
	{	
		game[GameCount].addOff(x);
		
	}
	
	

	public void addAthWithID(int x, String y)
	{
		game[x].addMember(y);	
	}


	
	//Method will use the foundGameList array to check if an athlete
	//is able to enter the games found (will check there are spaces
	//for athlete to enter). If suitable game found, will add the athlete
    public boolean checkFoundGames(String x)
	{
	    boolean matchFound = false;
		Phase1:
		for(int i = 0; i < foundGameList.size(); i++)
		{
		    int tempR = foundGameList.get(i).intValue();
			if(game[tempR].getCount() < 8) //if loop executed if there are under 8 participants
			{
			    game[tempR].addMember(x);
				matchFound = true;
				break Phase1;
			}
		}
		return matchFound;
	}
	
	public boolean checkOpenGames(int x, String y)
	{
		//if stays false, then a new game has been created whether its from no 
		//gamecount or no games of type interested in (e.g. Cycling, Running etc)
		boolean findMatch = false; 
		//if true, we are ready for next phase to check if games 
		//are over 8 people.
		boolean foundGamesReady = false; 
		int newGameArray = -1;
		//Create new game if game is empty(count = 0)
		if(GameCount == 0)
		{
		    newGameArray = createGame(x);
			addAthWithID(newGameArray, y);
		}
		else
		{
			for(int i = 0; i < GameCount; i++)
			{	
		        switch (x)
		        {
		            //If a created game is found it will temporary store the index
		            // number on foundGameList var to check if these game 
		            //are not full.
		            case 1: 
					    if(game[i] instanceof Swimming)
					    {
							foundGameList.add(i);
							findMatch = true;
							foundGamesReady = true;
						}
		                break;
		             
		            case 2: 
						if(game[i] instanceof Cycling)
						{
							foundGameList.add(i);
							findMatch = true;
							foundGamesReady = true;
						}
			             break;
			             
		            case 3: 
						if(game[i] instanceof Running)
						{
							foundGameList.add(i);
							findMatch = true;
							foundGamesReady = true;
						}
			             break;
		        } 
			}
			//If games have been created but there is type that
			//the athlete would like to participant we need to create
			// a new game.
			if(findMatch==false)
			{
				newGameArray = createGame(x);
				addAthWithID(newGameArray, y);
			}
		}	
		return foundGamesReady;
	}

	
	//To check whether an open game has appointed a ref,
	//if no ref appointed, if not appointed we add the
	//id number official
	public boolean checkOpenGamesRef(String x)
	{
		boolean refAdded = false;
		for(int i = 0; i < GameCount; i++)
		{
		    if (game[i].GetOffAppointed()==false){
		    	game[i].addOff(x);
		    	refAdded = true;
		    	break;
		    }
		}	
		return refAdded;
	}
	
	//Check if an athlete is already registered in a game session
    public boolean checkAthInGame(String x)
    {
		boolean AlreadyIngame = false;
		for(int i = 0; i < GameCount; i++)
		{
            if(game[i].inGameCheck(x) ==true)
            {
                AlreadyIngame = true;
            }
		}	
		return AlreadyIngame;
	}
	
	//Copy all completed games in session to games history
    public void addGamesToHistory(){
	    for(int i = 0; i < GameCount; i++)
		{			
			if(GameReadyCheck(i)==true)
			{
			    gameHistory[GameHistoryCount] = game[i];
				GameHistoryCount++;
			}
		}
		
	}
		
    public String showGameHistory()
    {	
		String output = "";
		for(int i = 0; i < GameHistoryCount; i++)
		{
			output += "Game Type: ";
            if(gameHistory[i] instanceof Running)
            {
            	output += "Running";
            }
            else if(gameHistory[i] instanceof Swimming)
            {
            	output += "Swimming";
            }
            else if(gameHistory[i] instanceof Cycling)
            {
            	output += "Cycling";
            }
			output += "\nGame ID: " + gameHistory[i].getGameID()+"\n";
			output += gameHistory[i].showCompetes();
			output += gameHistory[i].showAwards();
								
			output += "=======================================\n";
		}
		
		
		return output;
		
	}
	
	
}
