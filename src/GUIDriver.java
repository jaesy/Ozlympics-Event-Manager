
public class GUIDriver {

	public static void main(String[] args) 
	{

		
		//public void guiStart()
		//{
			try
			{
				GUI gui = new GUI();
				gui.setSize(500, 500);
				gui.setResizable(false);
				gui.setVisible(true);
				
			}
			catch(Exception e)
			{
				System.exit(0);//close GUI from thrown exceptions
			}
		//}

	}

}
