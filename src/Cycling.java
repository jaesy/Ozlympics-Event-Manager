import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Cycling extends Sports
{
	//We need an instance of menu to refer to methods/data
	//from Menu (this is where athlete data is stored)
	//such as an athlete and awarding points 
    Menu menu = new Menu(); 
    //Generic List used to make use of other functions (e.g sort).
    ArrayList<Sports> ath = new ArrayList<Sports>();
    
    private final String gameType = "Cycling"; //Used to print Sports Type    
    private final int firstPlace = 5; //constant points for top finishers
    private final int secondPlace = 2;
    private final int thirdPlace = 1;
 
    //Constructors
	public Cycling(String x)
	{
		super(x);
	}

	public Cycling(String x, String y)
	{
		super(x,y);	
	}

	//getter for returning type in String
	public String getType()
	{
		String z = super.getType(this.gameType);
		return z;
	}

	//New Sports objs are created of a new type
	//to create new athletes.
	public void addMember(String x)
	{
		ath.add(new Cycling(x,""));
		setCount(ath.size());
	}
	
	public void addCount()
	{
		super.addCount();	
	}
	
	//for each athlete within a game, a compete() method
	//will be invoked.
	public void competeAll()
	{
		for(int i = 0; i < ath.size(); i++)
		{
			//ath will use a setter to set finishing time
			ath.get(i).setPlayerTime(ath.get(i).compete());
		}	
	}
		
	//Returns a random double number
	public double compete()
	{
		double start = 500;
		double end = 800;
		double random = new Random().nextDouble();
		//round to 2 decimals
		double result = start + (random * (end - start));
		result = Math.round(result * 100);
		result = result/100;
		return result;	
	}
		
	//return String of all athletes name and finishing time
	public String showCompetes()
	{
		String output="";
		for(int i = 0; i < ath.size(); i++)
		{		
			String name = menu.getPlayerName(ath.get(i).getPlayerID());		
			output +="Player Name: " + name + 
					", Finishing Time is: " + ath.get(i).getPlayerTime() +"\n";
		}	
		return output;
	}
	
	//To check if an athlete is in this game through
	// parameter using ID, return true if already registered
    public boolean inGameCheck(String x)
    {
	    boolean inGame = false;
		for(int i = 0; i < ath.size(); i++)
		{
			
			if(ath.get(i).getPlayerID().equals(x))
			{
				inGame = true;
			}
			
		}
		return inGame;
	}
	
	public void awardPoints()
	{
		//Comparator used for sorting player times from
		//fastest to longest
		Collections.sort(ath, new Comparator<Sports>() {
	        @Override public int compare(Sports p1, Sports p2) {
	            return (int) (p1.playerTime - p2.playerTime); //Ascending
	        }	        
	    });

		//after sorting index will show out top 3 rankings
		//index = 0 (first place), 2 (second) etc.
		//points will then be awarded and sent through menu where
		//athlete details are stored
		menu.awardPlayerPoints(ath.get(0).getPlayerID(),firstPlace);
		menu.awardPlayerPoints(ath.get(1).getPlayerID(),secondPlace);
		menu.awardPlayerPoints(ath.get(2).getPlayerID(),thirdPlace);

		//non-top-finishers will be passed to to another method
		// to check if they are super athletes(to deduct points).
		for(int i = 4; i < ath.size(); i++)
		{	
			menu.deductPoints(ath.get(i).getPlayerID());
		}

	}
	
	
	//Return string of top finishers name
	public String showAwards()
	{
		String output ="";
		output+= "\nTop Finishers:\n\n";
		output+= "First Place(5 Points): " + menu.getPlayerName(ath.get(0).getPlayerID())+"\n";
		output+= "Second Place(2 Points): " + menu.getPlayerName(ath.get(1).getPlayerID())+"\n";
		output+= "Third Place(1 Point): " + menu.getPlayerName(ath.get(2).getPlayerID())+"\n";
		
		return output;	
	}

}
