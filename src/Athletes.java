
public class Athletes extends Members 
{

    private int points= 0 ;
    //booleans value if Athletes are a type of athlete (e.g a swimmer and/or 
    //runner etc.)
	private boolean swimmer = false;
	private boolean cycler = false;
	private boolean runner = false;
	private final int nonFinish = 2; //will use for super athletes deducting 
	
	//Constructor to pass to super class (Members Abstract Class)
	public Athletes(String ID, String name, int age, String state) 
	{
	    super(ID, name, age, state);	
	}

    //Setters to add/subtract from current point
	public void addPoints(int points) 
	{
		this.points += points;
	}
	
	public void lessPoints() 
	{
		this.points -= nonFinish;
	}
	
	
	public int getPoints() 
	{
	    return points;
	}


	public void setPoints(int points) 
	{
		this.points = points;
	}
	
	//Getters/Setters for athlete type
	public void setTypeS(boolean x)
	{
		swimmer = x;
		
	}
	
	public boolean getTypeS()
	{
		return swimmer;		
	}
	
	public void setTypeC(boolean x)
	{
		cycler = x;
	}

	public boolean getTypeC()
	{
		return cycler;
	}
	
	public void setTypeR(boolean x)
	{
		runner = x;	
	}
	
	public boolean getTypeR(){
		return runner;
		
	}
	
	//If athlete is all types, return true to make a Super Athlete
	public boolean allTypes()
	{
		boolean x = false;
		
		if(swimmer==true && cycler ==true && runner == true)
		{
			x = true;
		}
		return x;
	}
	
}
