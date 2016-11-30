import java.util.*;

/* Sports Class is used for storing a game session AND individual athlete information
 * within that Sports type. (e.g if sports class created for storing one game 
 * (with gameID, Officials etc.) the class type will also create another class 
 * of its same type for storing individual athlete information(e.g. athlete ID, 
 * finishing time)).  
 */

public abstract class Sports 
{

    //Properties for setting up a game
	private String ID;
	private int count = 0;
	private String gameID;
	private boolean participantsReady = false;
	private boolean offAppointed = false;
	private String offID;
	private String gameType;
	
	//Properties for a player details
	private String playerID;
    double playerTime;

    //Constructor for setting up game. 
    public Sports(String x)
    {
        setGameID(x);
    }
    
    //Overloading constructor for a single athelete.
    //y var is a dummy to seperate constructors because we only need
    // one variable atm for creating a game and individual athletes.
    public Sports(String x, String y)
    {
    	playerID = x;	
    }
    
    /*
    public void AddM(String x)
    {
    	playerID = x;
    }
    */
    
    public void testMethod()
    {
    	System.out.println("testMethod excuted");
    }
    
    public double compete()
    {
		double r = 20;
		return r;   	
    }

   
    public void addMember(String x)
    {	
    }

    
    public void addOff(String x)
    {
    	offID = x;
    	offAppointed = true;
    }
    
    public void competeAll()
    {  	
    }
    
    public String showCompetes()
    {
    	String x ="";
    	return x;
    }
	public String getType(String x)
	{
		gameType = x;
	    return gameType;	
	}
    
   public boolean inGameCheck(String x)
   {
	   boolean y = false;
	   return y;
   }
    
   public String getPlayerID()
   {
	   return playerID;
   }
    
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int x)
	{
		this.count = x;
	}
	
	public void addCount(){
		count++;
	}
	
	//If a game has b/w 4 - 8 athletes return true
	//Used to confirm athlete count requirments
	public boolean gameStatus()
	{
		if(count > 3 && count <= 8)
		{
			participantsReady = true;
		}
		return participantsReady;
	}
	
	public boolean GetOffAppointed()
	{
		return offAppointed;
	}


	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) 
	{
		this.gameID = gameID;
	}

	public double getPlayerTime() {
		return playerTime;
	}

	public void setPlayerTime(double playerTime) {
		this.playerTime = playerTime;
	}

	public void awardPoints() {
		
	}
	public String showAwards() 
	{
    	String x ="";
    	return x;
	}
	

}
