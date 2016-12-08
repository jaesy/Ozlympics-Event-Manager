/* Software Architecture: Design and Implementation
 * Assignment 2 Semester 2 2014
 * 
 * Student Name: JanCarlo Abagatnan
 * Student Number: s3083090 
 */

public class Ozlympic 
{

	public static void main(String[] args) 
	{
		
	    if(args.length == 0)
	    {   
	        Program prog = new Program();
			prog.start();
	        System.exit(0);
	    }
	    else if (args[0].equals("[-gui]"))
	    {
	    	GUIDriver gui = new GUIDriver();
	    	//gui.guiStart();
	    }
	
	}
}
