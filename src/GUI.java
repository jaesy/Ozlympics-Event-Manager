import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener
{
    
    Menu console = new Menu(); //Object reference to Person Details
	GamesSession pointer = new GamesSession();//Object reference for games detail
	
	
	//Container Panels:
	JPanel mainPanel = new JPanel();
	JPanel topMenuP = new JPanel();
	JPanel bottomMenuP = new JPanel();
	JMenuBar bar = new JMenuBar();
	Label brk = new Label("");
	
	
	//Page Panels For mainPanel:
	JPanel middlePanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JPanel addPOPanel1 = new JPanel();
	JPanel addPOPanel = new JPanel();
	JPanel runGamesPanel = new JPanel();
	JPanel AllGamesPanel = new JPanel();
	JPanel AllAthPanel = new JPanel();
	Container c;
	
	//topMenu Objects:
	public Label footer = new Label("                     "
			+ "                                                                ");
	JButton runGames = new JButton("Run Games");
	Color menuCol = new Color(214, 211, 196);
	
	public JTextArea txtArea = new JTextArea(40,30);
	public GUI() throws Exception
	{
		super("Ozlympics Program");//Title
		c = getContentPane();
		
		topMenuP.add(bar);
		bottomMenuP.add(footer);
		
		
		
		c.setLayout(new BorderLayout());
		c.add(topMenuP, BorderLayout.NORTH); //Menu Bars will be located north
		c.add(mainPanel, BorderLayout.CENTER);
		c.add(bottomMenuP, BorderLayout.SOUTH);//used for footer and messages

		//invoke all pages/methods when program starts
		addNewPerson1();
		addGame();
		addAthToGame();
		addOffToGame();
		resultsAllGames();
		resultsAllAth();
		runGames();
		
		makeBar();

		footer.setText("Welcome to Ozlympics! Choose a menu!");
        //Needed for closing confirm window:
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CheckOnExit());

	}

	//METHODS FOR DROP DOWN MENUS
	
	public void makeBar()
	{
		bar.removeAll();
		newMenu();
		addMenu();
		runGamesMenu();
		displayMenu();
		
	}
	
    public void newMenu()
    {
		JMenu newMenu = new JMenu("Create New");
		newMenu.setBackground(menuCol);
		
		JMenuItem gameSel = new JMenuItem("Game");
		//an event handled when a menu is selected
		gameSel.addActionListener(this);
		newMenu.add(gameSel);
		
		JMenuItem athlSel = new JMenuItem("Athlete/Official");
		athlSel.addActionListener(this);
		newMenu.add(athlSel);
		
		bar.add(newMenu);
	}
	

	
	public void addMenu()
	{
		
		JMenu addMenu = new JMenu("Add to Game");
		addMenu.setBackground(menuCol);
		

		JMenuItem athlSel = new JMenuItem("Athlete");
		athlSel.addActionListener(this);
		addMenu.add(athlSel);
		
		JMenuItem offSel = new JMenuItem("Official");
		offSel.addActionListener(this);
		addMenu.add(offSel);
		
		bar.add(addMenu);

	}
	
	public void displayMenu()
	{
		
		JMenu displayMenu = new JMenu("Show Results");
		displayMenu.setBackground(menuCol);
		
		JMenuItem athlSel = new JMenuItem("All Games");
		athlSel.addActionListener(this);
		displayMenu.add(athlSel);
		
		JMenuItem offSel = new JMenuItem("All Athletes");
		offSel.addActionListener(this);
		displayMenu.add(offSel);
		
		bar.add(displayMenu);
	}
	
	public void runGamesMenu()
	{
		JMenu runGamesMenu = new JMenu("Run Games");
		runGamesMenu.setBackground(menuCol);
		
		JMenuItem allGames = new JMenuItem("Open Games");
		allGames.addActionListener(this);
		runGamesMenu.add(allGames);
		
		bar.add(runGamesMenu);
	}


    //PAGE METHODS
    public void addGame()
    {
    	//When a page method is called, everything from the
    	//page panel will be removed for refreshing updated data
	    gamePanel.removeAll();

		JButton submit1 = new JButton("Add/Submit");
		submit1.addActionListener(this);
		
		Label namelabel1 = new Label("Create Game Type: ", Label.CENTER);
	    String[] typeA = {"Swimming", "Cycling", "Running" };
		
		final JComboBox gameType = new JComboBox(typeA);
		gameType.setSelectedIndex(0);
		gameType.addActionListener(this);
		
		gamePanel.add(namelabel1);
		gamePanel.add(gameType);
		gamePanel.add(submit1);
		
		mainPanel.add(gamePanel);
		gamePanel.setVisible(false);

		submit1.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 
	        {   
	        	
	             int gameTypeInt = -1;
	        	 
		         if(gameType.getSelectedItem().equals("Swimming"))
		         {   	 
		             gameTypeInt = 1;
		         }
		         else if(gameType.getSelectedItem().equals("Cycling"))
		         {   	 
		        	 gameTypeInt = 2;
		         }
		         else if(gameType.getSelectedItem().equals("Running"))
		         {   	 
		        	 gameTypeInt = 3;
		         }
	        	 

		         if(gameTypeInt != -1)
		         {
		             pointer.createGame(gameTypeInt);
		             footer.setText("Game Added!");
		         }
		         else 
		         {
		        	 footer.setText("Something Went Wrong");
		         }
		         //after a button has been pressed in a page, the page method
		         //needs to be called again to avoid objects in the page panel
		         //overlapping the menu boxes
		         addGame();
		         //the page needs to stay visible after a button is clicked.
		         gamePanel.setVisible(true);
	         }
		 });
		
  
	}
	
	
	
    public void addNewPerson1()
	{
		middlePanel.removeAll();
		
		final Label typelabel= new Label("Type: ",Label.CENTER);
		Label namelabel= new Label("Name: ",Label.CENTER);
		final JTextField nameTxt = new JTextField(20);
		Label agelabel= new Label("Age: ",Label.CENTER);
		final JTextField ageTxt = new JTextField(20);
		Label statelabel= new Label("State: ",Label.CENTER);
		final JTextField stateTxt = new JTextField(20);
		
		JButton submit = new JButton("Add/Submit");
		middlePanel.setLayout(new GridLayout(5, 2));
			
		String[] typeA = {"Athlete", "Official" };
		final JComboBox typeList = new JComboBox(typeA);
		typeList.setSelectedIndex(0);
		typeList.addActionListener(this);
		
        nameTxt.setText("");
        ageTxt.setText("");
        stateTxt.setText("");
        
		middlePanel.add(typelabel);
		middlePanel.add(typeList);
		middlePanel.add(namelabel);
		middlePanel.add(nameTxt);
		middlePanel.add(agelabel);
		middlePanel.add(ageTxt);
		middlePanel.add(statelabel);
		middlePanel.add(stateTxt);
		middlePanel.add(brk);
        middlePanel.add(submit);
		mainPanel.add(middlePanel);
	
		middlePanel.setVisible(false);
	
        submit.addActionListener(new ActionListener() 
        {
	        public void actionPerformed(ActionEvent e) 
	        {     
	        	 //used to find the person type
	             boolean athTrue = false;
	         
	             if(typeList.getSelectedItem().equals("Athlete"))
	             {   	 
	        	     athTrue = true;
	             }
	         
	         
	         try
	         {
	        	 int r = Integer.parseInt(ageTxt.getText());
	             console.addPersonGUI(athTrue, nameTxt.getText(), r ,stateTxt.getText());
	             footer.setText("Person Added!");
	             addNewPerson1();
			     middlePanel.setVisible(true);
			     
	         }catch(Exception e1)
	         {
	        	 
	        	 footer.setText("Invalid Age. Enter a Valid Age");
	        	 String tempName = nameTxt.getText();
	        	 String tempState = stateTxt.getText();
	        	 
	        	 addNewPerson1();
	        	 nameTxt.setText(tempName);
	        	 stateTxt.setText(tempState);
				 middlePanel.setVisible(true);  	 
	         }
	       }     
	     });

	}
	
	
    public void addAthToGame()
    {

        addPOPanel.removeAll();
		addPOPanel.setLayout(new GridLayout(4, 5));
		final Label typelabel= new Label("Athlete Name: ",Label.CENTER);
		final Label gameLabel= new Label("Game Type: ",Label.CENTER);
		//get all added athletes names and add to ComboBox
		final JComboBox nameAth = new JComboBox();
		for(int i = 0; i < console.athl.size();i++)
		{
		    nameAth.addItem(console.athl.get(i).getName());	
		}
		

		JButton submitAdd = new JButton("Add To Game");
		submitAdd.addActionListener(this);
		
		String[] typeA = {"Swimming", "Cycling","Running" };

		final JComboBox gameList = new JComboBox(typeA);
		gameList.setSelectedIndex(0);
		gameList.addActionListener(this);
		
		addPOPanel.add(gameLabel);
		addPOPanel.add(gameList);
		
		
		addPOPanel.add(typelabel);
		addPOPanel.add(nameAth);

		addPOPanel.add(submitAdd);
		
		mainPanel.add(addPOPanel);
		
		addPOPanel.setVisible(false);
		
		submitAdd.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 
	        {   

		         String name = nameAth.getSelectedItem().toString();
		         
		         String id = console.getPlayerID(name);
		         String footerTxt = "";
		         int athIndex = console.getPlayerIndex(id);

	        	 int gameTypeInt = -1;
	        	 
		         if(gameList.getSelectedItem().equals("Swimming"))
		         {   	 
		        	 gameTypeInt = 1;
		        	 console.athl.get(athIndex).setTypeS(true);
		        	 
		         }
		         else if(gameList.getSelectedItem().equals("Cycling"))
		         {   	 
		        	 gameTypeInt = 2;
		        	 console.athl.get(athIndex).setTypeC(true);
		         }
		         else if(gameList.getSelectedItem().equals("Running"))
		         {   	 
		        	 gameTypeInt = 3;
		        	 console.athl.get(athIndex).setTypeR(true);
		         }
	        	 
		         try 
		         {
					console.makeSuperAthletes();
				 } 
		         catch (Exception e1) 
				 {
					
					e1.printStackTrace();
				 }
   
		         //Check if athletes already registered
		         //for a game in this session
		         if(pointer.checkAthInGame(id)==true)
		         {
		        	 footerTxt += "Already registered in game.";   	
		         }
		         else
		         {
		         	 //check games if athlete can join by checking
		        	 //if there are under 8 participants.
		             if(pointer.checkOpenGames(gameTypeInt, id)==true)
		             {
		            	
		             	if(pointer.checkFoundGames(id)==false)
		             	{
		             		
		             		int x = pointer.createGame(gameTypeInt); 	             		
		             		pointer.addAthWithID(x,id);
		             		
		             	}
		             	         
		             }  
		             footerTxt += "Athlete Added to a game";	
		         }		          	         
	        	 footer.setText(footerTxt);
	        	 addAthToGame();
	        	 addPOPanel.setVisible(true);
	        	 makeBar();
	        	 
	         }
		 });
		
	}
	

	public void addOffToGame()
	{

		addPOPanel1.removeAll();
		addPOPanel1.setLayout(new GridLayout(4, 5));
		final Label typelabel= new Label("Official Name: ",Label.CENTER);
			
		
		final JComboBox nameOff = new JComboBox();
		for(int i = 0; i < console.offCount;i++)
		{
			nameOff.addItem(console.offi[i].getName());	
		}
				
		
		JButton submitAdd = new JButton("Add To Game");
			
		
		
		addPOPanel1.add(typelabel);
		addPOPanel1.add(nameOff);
		addPOPanel1.add(submitAdd);
		mainPanel.add(addPOPanel1);
		
		addPOPanel1.setVisible(false);
		

		 submitAdd.addActionListener(new ActionListener() 
		 {
	         public void actionPerformed(ActionEvent e) 
	         {   

                 String name = nameOff.getSelectedItem().toString();
		         String id = console.getOffID(name);
		         String footerTxt = "";
		         

		         if(pointer.checkOpenGamesRef(id)==false)
		         {
		        	 footerTxt += "No Games Require any Officials at the moment";
		         }
		         else
		         {
		        	 footerTxt += "Official Added to a game.";
		         }
		         
		         footer.setText(footerTxt);
		         
		         //Refresh (Prevented main panels overlapping top menu bars)
		         addOffToGame();
		         addPOPanel1.setVisible(true);
	         }
		 });
		
	}
	
	

	public void resultsAllGames()
	{
		JTextArea txtAGG = new JTextArea(40,30);
		
		AllGamesPanel.removeAll();
		String op ="";
		
		op += pointer.showGameHistory();
		
		txtAGG.setText(op);
		
		JScrollPane scrollPane = new JScrollPane(txtAGG);
		final Label resultslabel= new Label("All Completed Games:",Label.CENTER);

		AllGamesPanel.setLayout(new BorderLayout());

        AllGamesPanel.setPreferredSize(new Dimension(290,370));
		AllGamesPanel.add(resultslabel, BorderLayout.NORTH );
		AllGamesPanel.add(scrollPane, BorderLayout.CENTER  );
		makeBar();
		mainPanel.add(AllGamesPanel);
		AllGamesPanel.setVisible(false);
		
	}
	

	
	public void resultsAllAth()
	{
		JTextArea txtAGA = new JTextArea(40,30);
		JScrollPane scrollPane = new JScrollPane(txtAGA);
		
		AllAthPanel.removeAll();
		final Label resultslabel= new Label("All Athletes: ",Label.CENTER);
		String op ="";
		AllAthPanel.setLayout(new BorderLayout());
		AllAthPanel.setPreferredSize(new Dimension(290,370));
		console.sortAthletes();
		op += console.DisplayPoints();
		
		txtAGA.setText(op);
		AllAthPanel.add(resultslabel, BorderLayout.NORTH );
		AllAthPanel.add(scrollPane, BorderLayout.CENTER  );

		
		mainPanel.add(AllAthPanel);
		AllAthPanel.setVisible(false);

	}


	public void runGames()
	{
		runGamesPanel.removeAll();
		
		
		
		final String op ="";
		
		final Label resultslabel= new Label("Start Games:",Label.CENTER);
		JButton runGamesBtn = new JButton("Run All Games");
		runGamesBtn.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane(txtArea);
		runGamesPanel.setLayout(new BorderLayout());
		
		runGamesPanel.setPreferredSize(new Dimension(290,370));
		runGamesPanel.add(runGamesBtn, BorderLayout.NORTH );
		runGamesPanel.add(scrollPane, BorderLayout.CENTER  );
		
		mainPanel.add(runGamesPanel);
		runGamesPanel.setVisible(false);
		runGamesBtn.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 
	        {   
	            txtArea.setText("");
	        	footer.setText("Games Started.");
	     		String op = "";
	     		op += pointer.StartAllGames();
	     		txtArea.setText(op);
	    		pointer.addGamesToHistory();
	    		pointer.restartGameSession();
	    		runGames();
	    		runGamesPanel.setVisible(true);
	    		makeBar();
	         }
		 });
	}
	
	
	
	

	//Event handlers for choosing a drop down menu
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	    String cmd = e.getActionCommand();
		
		if(cmd.equals("Game"))
		{	
			//We need to invoke the page method again for
			//new data refreshing
			footer.setText("");
		    addGame();
		    //each page method has a new panel, we will then
		    //switch off pages that are not in the page methods
            gamePanel.setVisible(true);
  		    middlePanel.setVisible(false);
  		    addPOPanel.setVisible(false);
  		    AllGamesPanel.setVisible(false);
  		    runGamesPanel.setVisible(false);
  		    makeBar();
		}
		else if (cmd.equals("Athlete/Official"))
		{
			footer.setText("");
			addNewPerson1();
			middlePanel.setVisible(true);
			gamePanel.setVisible(false);
			addPOPanel.setVisible(false);
			addPOPanel1.setVisible(false);
			AllGamesPanel.setVisible(false);
			AllAthPanel.setVisible(false);
			runGamesPanel.setVisible(false);
			makeBar();
		}
		else if (cmd.equals("Athlete"))
		{
			footer.setText("");
			addAthToGame();
			addPOPanel.setVisible(true);
			middlePanel.setVisible(false);
			gamePanel.setVisible(false);
			addPOPanel1.setVisible(false);
			AllGamesPanel.setVisible(false);
			AllAthPanel.setVisible(false);
			runGamesPanel.setVisible(false);
			makeBar();
			
		}
		
		else if (cmd.equals("Official"))
		{
			footer.setText("");
			addOffToGame();
			addPOPanel1.setVisible(true);
			addPOPanel.setVisible(false);
			middlePanel.setVisible(false);
			gamePanel.setVisible(false);
			AllGamesPanel.setVisible(false);
			AllAthPanel.setVisible(false);
			runGamesPanel.setVisible(false);
			makeBar();
		}
		else if (cmd.equals("All Games"))
		{
			footer.setText("");
			resultsAllGames();		
			AllGamesPanel.setVisible(true);
			addPOPanel1.setVisible(false);
			addPOPanel.setVisible(false);
			middlePanel.setVisible(false);
			gamePanel.setVisible(false);
			AllAthPanel.setVisible(false);
			runGamesPanel.setVisible(false);
			makeBar();
		}
		else if (cmd.equals("All Athletes"))
		{
			footer.setText("");
			resultsAllAth();
			AllAthPanel.setVisible(true);
			AllGamesPanel.setVisible(false);
			addPOPanel1.setVisible(false);
			addPOPanel.setVisible(false);
			middlePanel.setVisible(false);
			gamePanel.setVisible(false);
			runGamesPanel.setVisible(false);
			makeBar();
	
		}
		else if (cmd.equals("Open Games"))
		{
			footer.setText("");
			runGames();
			runGamesPanel.setVisible(true);
			AllAthPanel.setVisible(false);
			AllGamesPanel.setVisible(false);
			addPOPanel1.setVisible(false);
			addPOPanel.setVisible(false);
			middlePanel.setVisible(false);
			gamePanel.setVisible(false);	
			makeBar();
		}

	}
	

	//closing confirm window
	private class CheckOnExit extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			ConfirmWindow checkers = new ConfirmWindow();
			checkers.setVisible(true);
		}
	}
	
	private class ConfirmWindow extends JFrame implements ActionListener
	{
		public ConfirmWindow()
		{
			setSize(250,150);
			getContentPane().setBackground(Color.WHITE);
			setLayout(new BorderLayout());
			
			
			JLabel confirmLabel= new JLabel(
					"Are you sure you want to exit?");
			add(confirmLabel, BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.WHITE);
			buttonPanel.setLayout(new FlowLayout());
			
			JButton exitButton = new JButton("Yes");
			exitButton.addActionListener(this);
			buttonPanel.add(exitButton);
			
			JButton cancelButton = new JButton("No");
			cancelButton.addActionListener(this);
			buttonPanel.add(cancelButton);
			
			add(buttonPanel, BorderLayout.SOUTH);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();
			
			if(actionCommand.equals("Yes"))
				System.exit(0);
			else if(actionCommand.equals("No"))
				dispose();
			else
				System.out.println("Unexpected Error");			
		}

	}

}