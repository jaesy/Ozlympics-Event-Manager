import java.util.*;
//import java.awt.List;

public class Menu 
{
	
    public static ArrayList<Athletes> athl = new ArrayList<Athletes>();
    public static Officials[] offi = new Officials[20];
    GamesSession pointer = new GamesSession();

    private static final Scanner sc = new Scanner(System.in);	
    public static int offCount = 0;//Number of Officials added
	
    
    //Method for creating new Athletes and adding
    //athletes to a game
	public void AddAthlete() throws Exception
	{
		
	    AtheleteLabel:
		try
	    {
		
		    String name, state;
		    int age = 0, ID=-1;
		    //a copy of index number of athletes added for parameters
		    String IDpara="";
		    int athIndex = -1;
		    //Validation of Y/N characters. if invalid, will loop 
		    //so user can re-enter rather than exiting method
		    boolean exitChoice = false;
		    do
		    {
		    	//Users can add a new athlete or search athl array for existing athletes
		    	//via name input.
		        System.out.println("Is this for a new athelete or an existing athlete?(Y/N)");
		        String type = sc.nextLine().toUpperCase();
		        char selection = type.charAt(0);
		        switch (selection)
		        {
		        	case 'Y': 
		                exitChoice = true;
		                System.out.println("New athlete:");        	
		                System.out.println("Enter the Athletes Name: ");
		                name = sc.nextLine();
		                boolean choice = true; 
        				
		                do
		                {
		                	try
		                	{			
		                	    System.out.println("Enter Age: ");
		                	    age = sc.nextInt();
		                	    String flush = sc.nextLine();
		                	    choice = false;
		                	}
		                		catch(InputMismatchException e)
		                		{
		                	    System.out.println("invalid age. Try again");
		                	    String flush = sc.nextLine();
		                		}
		                }
		                while(choice == true);

		                System.out.println("Enter the State: ");
		                state = sc.nextLine();
        		
        		
        		        //ID creation is created by starting with a character
		                //of type (e.g. A for athlete) followed by an increment
		                //number every time an athlete is added.
		                ID = athl.size() + 1;
		                StringBuilder sb = new StringBuilder();
		                sb.append("A");
		                sb.append(ID);
		                String newID  = sb.toString();
		                IDpara = newID;       		
		                athIndex = athl.size();
        		        //new object create of Athletes type
		                athl.add(new Athletes(newID, name, age, state));     	            	       	
		                break;
     	       
                    case 'N': 
                    	exitChoice =true;
                    	boolean choice1 = false;
                    	System.out.println("Enter the Athletes Name:");
                    	String tempName = sc.nextLine();
                	
                    	if(getPlayerID(tempName)!="")
                    	{
                    		IDpara = getPlayerID(tempName);//store ID of found name
                    		athIndex = getPlayerIndex(IDpara);//store index number
                    	}
                    	else
                    	{
                    		System.out.println("Athlete Not found");
                    		break AtheleteLabel;	
                    	}               	           	        	
                    	break;
                    	
                    default:
                    	System.out.println("Invalid Choice.");       
                }	
		
		    }
		    while(exitChoice ==false);	 
        
       
        
		    pointer.resetfoundGameList();//clear all arrays from previous inputs
		    boolean exitSwitch = false;
		    int requestGame;
		    String type1;
		    char selection1;
		    do
		    {
		    	System.out.println("Register to which game? ");
		    	System.out.println("\na. Swimming");
		    	System.out.println("b. Cycling");
		    	System.out.println("c. Running\n");
		    	System.out.print("Enter Your Choice? ");
        
		    	requestGame = 0;
		    	type1 = null;
		    	type1 = sc.nextLine().toUpperCase();
		    	selection1 = type1.charAt(0);
		
		
		        //requestGame int to define the game type athlete
		    	//would like to register (1 for Swimming, 2 - Cycling
		    	//3 - Running)
		    	switch (selection1)
		    	{
		    		case 'A': 
		    			requestGame = 1;
		    			//set athletes types (A runner, swimmer etc.)
		    			athl.get(athIndex).setTypeS(true);
		    			exitSwitch = true;
		    			break;    	       
		    		case 'B': 
		    			requestGame = 2;
		    			athl.get(athIndex).setTypeC(true);
		    			exitSwitch = true;
		    			break;
	            
		    		case 'C': 
		    			requestGame = 3;
		    			athl.get(athIndex).setTypeR(true);
		    			exitSwitch = true;
		    			break;
		    			
		    		default:
		    			System.out.println("Invalid choice");
		    	}	
		    }while(exitSwitch==false);
		    makeSuperAthletes();

             
		    //After We have found the ID (that is after adding new athlete 
		    //or retrieving ID from existing ath).
	        // We then move to adding this ID to a game and creating a game if none open. 
		    //If block will check if an athlete is already registered for a game.
		    if(pointer.checkAthInGame(IDpara)==true)
		    {
		    	System.out.println("Athlete Already in a game.\n");    	
		    }
		    else
		    {
        	    //look will first check any games that are open, store them in
		    	//an array, then check those games if there is free space for
		    	// the athlete to join the game(under 8 participants).
		    	if(pointer.checkOpenGames(requestGame, IDpara)==true)
		    	{
		    		if(pointer.checkFoundGames(IDpara)==false)
		    		{
		    			int x = pointer.createGame(requestGame);            		
		    			pointer.addAthWithID(x,IDpara);
		    		}     
		    	}        	
		    }	  
		    System.out.println("Athlete has been added to a game.\n");
		    
		}catch(Exception e)
		{		
		}       
	}

    //Similar method AddAthlete
	public void AppOfficial() throws Exception
	{
		OfficialLabel:
		try
		{
			String name, state;
			int age = 0, ID = -1;
			String IDpara="";
		
			boolean exitChoice = false;
			do
			{
				System.out.println("Is this for a new Official or an existing Official?(Y/N)");
				String type = sc.nextLine().toUpperCase();
				char selection = type.charAt(0);
				switch (selection)
				{
					case 'Y': 
						exitChoice = true;
						System.out.println("New official:");	
						System.out.println("Enter the Officials Name: ");
						name = sc.nextLine();
        		
						boolean choice = true;
						do
						{
							try
							{
        			
								System.out.println("Enter Age: ");
								age = sc.nextInt();
								String flush = sc.nextLine();
								choice = false;
							}
							catch(InputMismatchException e)
							{
								System.out.println("invalid age. Try again");
								String flush = sc.nextLine();
							}
						}
						while(choice == true);
 		
						System.out.println("Enter the State: ");
						state = sc.nextLine();
						
						ID = offCount + 1;
						StringBuilder sb = new StringBuilder();
						sb.append("O");
						sb.append(ID);
						String newID  = sb.toString();
						IDpara = newID;

						offi[offCount] = new Officials(newID, name, age, state);
						offCount++;
        		
           	
						;
						break;
     	       
					case 'N': 
						exitChoice = true;           	
						System.out.println("Enter the Officials Name:");
						String tempName = sc.nextLine();
            	
						if(getOffID(tempName)!="")
						{
							IDpara = getOffID(tempName);
						}
						else
						{
							System.out.println("Official Not found");
							break OfficialLabel;           		
						}	
						break;
					default:
						System.out.println("Invalid Choice");
				}	
    
			}
			while(exitChoice==false);
        
			//Check games if an official has been appointed
			if(pointer.checkOpenGamesRef(IDpara)==false)
			{
				System.out.println("No Games Require any Officials at the moment.");
			}
			else
			{
				System.out.println("Official has been appointed to a game.");
			}
			
		}
		catch(Exception e)
		{
		}
		
	}
	

	//loops through each athl, to check if they have registered
	//for all 3 sports, if true, make a new SuperAthletes class
	public void makeSuperAthletes() throws Exception
	{	
		for(int i = 0; i < athl.size(); i++)
		{			
			if(athl.get(i).allTypes()==true)
			{
				//temp store all details to pass to new constructor
				String tempID = athl.get(i).getID();
			    String tempName = athl.get(i).getName();
			    int tempAge = athl.get(i).getAge();
			    String tempState = athl.get(i).getState();
				int points = athl.get(i).getPoints();
		
				athl.remove(i);//remove Athletes obj and create new superAthlete		
				athl.add(new SuperAthletes(tempID, tempName, tempAge, tempState));
				athl.get(athl.size()-1).setPoints(points);						
			}
		}	
	}
	
	
	//global method to return a players name from id parameter.
    public static String getPlayerName(String x) 
    {
	    String name="";
	    Found:
	    for(int i = 0; i < athl.size(); i++)
	    {	
			if(athl.get(i).getID().equals(x))
			{
				name = athl.get(i).getName();
				break Found;
			}		
		}	
		return name;
	}
	
	
	//get player index from ID parameter. not found if returns -1
	public static int getPlayerIndex(String x) 
	{
	    int index = -1;
		Found:
		for(int i = 0; i < athl.size(); i++)
		{		
			if(athl.get(i).getID().equals(x))
			{
				index = i;
				break Found;
			}		
		}	
		return index;
	}
	
	
	//get Player ID from name parameter
	public static String getPlayerID(String x) 
	{
		String id="";
		Found:
		for(int i = 0; i < athl.size(); i++)
		{	
			if(athl.get(i).getName().equals(x))
			{
				id = athl.get(i).getID();
				break Found;
			}		
		}	
		return id;
	}
	
	// get Official ID from name parameter
	public static String getOffID(String x) 
	{
		String id="";
		Found:
		for(int i = 0; i < offCount; i++)
		{
			if(offi[i].getName().equals(x))
			{
				id = offi[i].getID();
				break Found;
			}		
		}	
		return id;
	}
	
	//set points of player using an ID parameter and points awarded
	public static void awardPlayerPoints(String x, int y)
	{
		for(int i = 0; i < athl.size(); i++)
		{		
			if(athl.get(i).getID().equals(x))
			{
				athl.get(i).addPoints(y);
			}	
		}	
		
	}
	
	//loop through each athlete and check if a SuperAthlete type
	//if SuperAthlete, deduct points. deductPoints method will only
	//send non-top finishers through the parameter. So points deducted
	// for superAthletes not finishing not 3.
	public static void deductPoints(String x)
	{
		Loop:
		for(int i = 0; i < athl.size(); i++)
		{		
			if(athl.get(i).getID().equals(x))
			{
				
				if(athl.get(i) instanceof SuperAthletes)
				{
					athl.get(i).lessPoints();
				}		
				break Loop;
			}	
		}	
		
	}
	
	
	//Method used for passing information from GUI to create athletes/officials
	public void addPersonGUI(boolean a, String x, int y, String z) throws Exception
	{
			
		//boolean a defines if creating a athlete or official
		//true for athlete, false for official
		if(a == true)
		{
    		int ID = athl.size() + 1;
    		StringBuilder sb = new StringBuilder();
    		sb.append("A");
    		sb.append(ID);
    		String newID  = sb.toString();
    		String IDpara = newID;       		
    		//athIndex = athl.size(); 		
    		athl.add(new Athletes(newID, x, y, z));
    		//athCount++;	
		}
		else
		{		
    		int ID = offCount + 1;
    		StringBuilder sb = new StringBuilder();
    		sb.append("O");
    		sb.append(ID);
    		String newID  = sb.toString();
        	String IDpara = newID;
	
			offi[offCount] = new Officials(newID, x, y, z);
			offCount++;
		}
			
	}
	



	public void StartGames()
	{   
		StartGames:
		try
		{
		    System.out.println("StartGames Active");		
		    if(pointer.CheckAllGamesReady()==false)
		    {		
		    }
		    else
		    {//Allow Users to go back to main menu because a game(s) gets cancelled
		    	boolean offLoop = false;
		    	do{
			    System.out.println("Games Without an Official or over 4 paticipants"
					    + "will be canceled.\n"
					    + "Would you like to go back to main menu to add athlete"
					    + "/Official?(Y/N)");
			    String x = sc.nextLine().toUpperCase();
			    char sel = x.charAt(0);
			    
			    switch(sel){
			    
			    case 'Y':
			    	break StartGames; 	

			    case 'N':
			       offLoop = true;
			    default:
			    	System.out.println("Invalid choice");
			    
			    }
		    	}while(offLoop==false);
			    /*
			    if(x=="Y")
			    {
				    break StartGames;
			    }
			    else if(x=="N")
			    {
				
			    }
			    else
			    {
			        System.out.println("Invalid Input.");
			    }
			    */
		    }
		    System.out.println(pointer.StartAllGames());
		    pointer.addGamesToHistory(); //add all completed games to history
		    pointer.restartGameSession(); //Clear game session variables
		    //We could try creating/resetting new session rather than
	        //terminating the program
		    }catch(Exception e)
		    {
			    String x = "";
			    System.out.println("Games Could not be complete. GameSession has reset");
			    pointer.restartGameSession();
		    }	
	}
	
    public void DisplayResults()
	{		
		System.out.println(pointer.showGameHistory());
	}
	
	
	//Sort Athletes from most points to lowest points to
	//rank highest players to starting index of athlete arraylist.
	public void sortAthletes()
	{
		Collections.sort(athl, new Comparator<Athletes>() {
	        public int compare(Athletes p1, Athletes p2) {
	            return (int) (p2.getPoints() - p1.getPoints()); // Ascending
	        }	        
	    });	
	}
	
	//display all athletes details
	public String DisplayPoints(){

        String op="";
        op += "================================\n";
        op += "          Player Rankings         \n";
        op += "================================\n";
		for(int i = 0; i < athl.size(); i++){
			
			    op += "Rank: " + (i + 1)+ "\n"; 
				op += "Name: " + athl.get(i).getName()+"\n";
				
				//Output athlete type according to which class they belong to
				// and getters of game types.
				String type ="";
                if(athl.get(i) instanceof SuperAthletes){
					type += "Super Athlete";
				}
                else
                {
                	if(athl.get(i).getTypeS()==true){
                		type += " Swimmer";
                	}
                	if(athl.get(i).getTypeR()==true){
                		type += " Runner";
                	}
                	if(athl.get(i).getTypeC()==true){
                		type += " Cycler";
                	}
                	
                }
                
				op += "Type: " + type + "\n";			
				op += "Points: " + athl.get(i).getPoints()+"\n";
    	        op += "================================\n";
		}	  
    	  return op; 	  
	}	
	
	
	
}
