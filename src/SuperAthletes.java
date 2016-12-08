
public class SuperAthletes extends Athletes 
{
    //Constructor to pass data to parent athletes class
	public SuperAthletes(String ID, String name, int age, String state) 
	{
		super(ID, name, age, state);		
	}
    
	//overidden method to deduct 2 points if Athletes are SuperAthletes
	public void lessPoints() 
	{
		super.lessPoints();		
	}
	
}
