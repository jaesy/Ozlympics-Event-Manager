import java.util.InputMismatchException;
import java.util.Scanner;


public class Program {

	
	
    public static int choice;
    public static Menu pointer = new Menu(); 
    public static Scanner sc = new Scanner(System.in);
    
    public void start(){
	//public static void main(String[] args) 
	//{

		
		 try
	        {
	    	    //Scanner sc = new Scanner(System.in);
					
	            System.out.println("Ozlympic Game");
	            System.out.println("==========================================");
	            do
	            {
	                   	
	        	    choice = 0;
				
	                try
	                {
	            	
	                    System.out.println("1. Add an athele");   
	                    System.out.println("2. Appoint official");
	                    System.out.println("3. Start the games");
	                    System.out.println("4. Display the final results");
	                    System.out.println("5. Display the points of athletes");
	                    System.out.println("6. Exit");
	                    System.out.print("\nEnter an option: ");
	                    choice = sc.nextInt();
	                    //String flush = sc.nextLine();
	                    System.out.println("");
	                    //Valdation input of only an integer between 1 and 5.			
	                    if(choice > 6 || choice <= 0)
					    {
					        System.out.println("Invalid Choice. Please Choose a Valid Option");
	                    }
	                    else
	                    {
						    
		                    switch (choice)
		                    {
		                        case 1: 
		                            pointer.AddAthlete();
		                            break;
		                 	            
		                        case 2: 
		                           pointer.AppOfficial();
		                            break;

		                        case 3: 
		                            pointer.StartGames();
		                            break;

		                        case 4: 
		                            pointer.DisplayResults();
		                            break;
		                            
		                        case 5: 
		                        	pointer.sortAthletes();
		                            System.out.println(pointer.DisplayPoints());
		                            break;
		                    }				
					    }
									
			        }catch(InputMismatchException e)
			        {
				        System.out.println("You entered an invalid option. Try Again.");
				        String flush =  sc.nextLine();
			        }
	        }while(choice != 6); //Program exits when you choose option 5
	        
	        }catch(Exception e)
	        {
	        	System.out.println("Something Went Wrong. Exiting Program..." + e);
	        }
		
		
		
		
		
	//}
    }
}
