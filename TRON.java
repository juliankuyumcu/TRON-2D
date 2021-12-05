package tron; //Code part of package tron

import java.awt.*; //Imports java.awt package to be used in code
import java.awt.event.KeyEvent; //Imports KeyEvent class
import java.awt.event.KeyListener; //Imports KeyListener class
import java.awt.event.MouseEvent; //Imports MouseEvent class
import java.awt.event.MouseListener; //Imports MouseListener class

import java.util.Random; //Imports Random class
import java.util.TimerTask; //Imports TimerTask class
import java.util.Timer; //Imports Timer class

import javax.swing.*; //Imports Swing package


public class TRON extends JPanel implements Runnable //Declares class TRON, extends JPanel, implements Thread
{
	private static final long serialVersionUID = 1L; //Adds serial version to class
	
	//[JFRAME VARIABLES]
	JFrame frame = null; //Declares JFrame object for window of game
	public final int WIDTH = 1210; //Constant int to represent width of window
	public final int HEIGHT = 810; //Constant int to represent height of window
	
	//[GLOBAL IMAGE VARIABLES]
	ImageIcon background1 = new ImageIcon("res/Titlescreen.png"); //ImageIcon for titlescreen
	Image title = background1.getImage(); //Image from background1
	ImageIcon background2 = new ImageIcon("res/grid.png"); //ImageIcon for The Grid
	Image grid = background2.getImage(); //Image from background2
	ImageIcon background3 = new ImageIcon("res/Customization/custom.png"); //ImageIcon for customization screen
	Image custom = background3.getImage(); //Image from background3
	ImageIcon background4 = new ImageIcon("res/winner1.png"); //ImageIcon for player 1 winner screen
	Image winner1 = background4.getImage(); //Image from background4
	ImageIcon background5 = new ImageIcon("res/winner2.png"); //ImageIcon for player 2 winner screen
	Image winner2 = background5.getImage(); //Image from background5
	ImageIcon background6 = new ImageIcon("res/winner3.png"); //ImageIcon for stalemate screen
	Image winner3 = background6.getImage(); //Image from backgroud6
	
    ImageIcon colourbar = new ImageIcon("res/Customization/colourbar.png"); //ImageIcon for colour bar
    Image bar = colourbar.getImage(); //Image from colourbar

    ImageIcon cyanDISC = new ImageIcon("res/Customization/Discs/cyanDISC.png"); //ImageIcon for cyan disc
    ImageIcon yellowDISC = new ImageIcon("res/Customization/Discs/yellowDISC.png"); //ImageIcon for yellow disc
    ImageIcon magDISC = new ImageIcon("res/Customization/Discs/magDISC.png"); //ImageIcon for magenta disc
    ImageIcon redDISC = new ImageIcon("res/Customization/Discs/redDISC.png"); //ImageIcon for red disc
    ImageIcon greenDISC = new ImageIcon("res/Customization/Discs/greenDISC.png"); //ImageIcon for green disc
    ImageIcon whiteDISC = new ImageIcon("res/Customization/Discs/whiteDISC.png"); //ImageIcon for white disc
    Image disc1 = cyanDISC.getImage(); //Image for player 1's disc
    Image disc2 = yellowDISC.getImage(); //Image for player 2's disc
	
	ImageIcon count3 = new ImageIcon("res/Countdown/3.png"); //ImageIcon for countdown 3
	ImageIcon count2 = new ImageIcon("res/Countdown/2.png"); //ImageIcon for countdown 2
	ImageIcon count1 = new ImageIcon("res/Countdown/1.png"); //ImageIcon for countdown 1
	ImageIcon countGO = new ImageIcon("res/Countdown/go.png"); //ImageIcon for countdown GO
	Image count = null; //Image for countdown icons
	
	//[THREAD VARIABLES]
	Thread thread = null; //Creates Thread object for main thread
	boolean running = false; //Boolean to represent game loop
	
	//[GRID VARIABLES]
	public int[][] location = new int[121][81]; //Int array to represent locations on grid
	int xCoor1 = 60; //Int to represent X-axis coordinate for player 1
	int yCoor1 = 3; //Int to represent Y-axis coordinate for player 1
	int xCoor2 = 60; //Int to represent X-axis coordinate for player 2
	int yCoor2 = 74; //Int to represent Y-axis coordinate for player 2
	final int gridSize = 10; //Constant int to represent tile size of grid
	
	//[GAME-STATE VARIABLES]
	boolean titlescreen = false; //Boolean to represent titlescreen of game
	boolean customization = false; //Boolean to represent customization screen of game
	boolean playerVsComputer = false; //Boolean to represent gamemode of Player vs. Computer
	boolean playerVsPlayer = false; //Boolean to represent gamemode of Player vs. Player
	boolean battle = false; //Boolean to represent grid battle of game
	boolean crash1 = false; //Boolean to represent crash status of player 1
	boolean crash2 = false; //Boolean to represent crash status of player 2
	boolean delay = false; //Boolean to represent delayed end of game
	boolean winner = false; //Boolean to represent winner screen of game
	Timer timer = null; //Creates timer for delayed end of game
	int countdown = 0; //Int to represent countdown increment
	
	//[DIRECTION VARIABLES]
	boolean right1 = false; //Boolean to represent right direction of player 1
	boolean left1 = false; //Boolean to represent left direction of player 1
	boolean up1 = false; //Boolean to represent up direction of player 1
	boolean down1 = false; //Boolean to represent down direction of player 1
	String direction1 = "down"; //String to represent current direction of player 1
	
	boolean right2 = false; //Boolean to represent right direction of player 2
	boolean left2 = false; //Boolean to represent left direction of player 2
	boolean up2 = false; //Boolean to represent up direction of player 2
	boolean down2 = false; //Boolean to represent down direction of player 2
	String direction2 = "up"; //String to represent current direction of player 2
	
	//[LIGHTCYCLE VARIABLES]
	Color color1 = Color.CYAN; //Color to represent color of player 1's bike/trail
	Lightcycle lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1); //Creates lightcycle for player 1's bike
	Trail trail1 = null; //Creates trail object for player 1's trail
	
	Color color2 = Color.YELLOW; //Color to represent color of player 1's bike/trail
	Lightcycle lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2); //Creates lightcycle for player 2's bike
	Trail trail2 = null; //Creates trail object for player 2's trail
	
	//[EVENT VARIABLES]
	Key key = new Key(); //Creates Key object for KeyEvents
	Mouse mouse = new Mouse(); //Creates Mouse object for MouseEvents

	public TRON() //Constructor for main class
	{
		frame = new JFrame("TRON"); //Creates JFrame named "TRON"
		frame.setSize(WIDTH, HEIGHT); //Sets dimensions of JFrame
		frame.setVisible(true); //Makes JFrame visible
		frame.setResizable(false); //Makes JFrame non-resizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Program will stop upon exit of frame
		frame.setLocationRelativeTo(null); //Makes JFrame appear in middle of screen
		
		frame.setFocusable(true); //Makes JFrame available for KeyEvents/MouseEvents
		frame.addKeyListener(key); //Adds KeyListener to JFrame
		frame.addMouseListener(mouse); //Adds MouseListener to JFrame
		
		frame.add(this); //Adds everything to the frame

		titlescreen = true; //Sets up titlescreen
		countdown = 3; //Prepares countdown for game to start
		
		running = true; //Prepares game loop
		thread = new Thread(this); //Creates thread for everything in TRON class
		thread.start(); //Starts thread
	}
	
	public void tick() //Tick method, updates game
	{
				if(countdown == -1) //If countdown is finished:
				{
					down1 = true; //Set player 1's direction to down
					up2 = true; //Set player 2's direction to up
					countdown = -2; //Set countdown to -2 (unusable)
				}
				if(battle) //If game-state is battle:
				{
					//If countdown is unusable and both bikes are alive:
					if(countdown == -2 && !crash1 && !crash2)
					{
							if(right1) //If player 1's direction is right
								xCoor1++; //Move player 1 right
							else if(left1) //If player 1's direction is left
								xCoor1--; //Move player 1 left
							else if(up1) //If player 1's direction is up
								yCoor1--; //Move player 1 up
							else if(down1) //If player 1's direction is down
								yCoor1++; //Move player 1 down
						trail1 = new Trail(xCoor1, yCoor1, gridSize, color1); //Create trail at player 1's location
						location[xCoor1][yCoor1] += 2; //Add 2 to player 1's location in array
						lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1); //Create lightcycle at player 1's location
						
							if(playerVsComputer) //If gamemode is Player vs. Computer:
							{
								artificialIntelligence(); //Let computer decide player 2's move
							}
						
							if(right2) //If player 2's direction is right
								xCoor2++; //Move player 2 right
							else if(left2) //If player 2's direction is left
								xCoor2--; //Move player 2 left
							else if(up2) //If player 2's direction is up
								yCoor2--; //Move player 2 up
							else if(down2) //If player 2's direction is down
								yCoor2++; //Move player 2 down
						trail2 = new Trail(xCoor2, yCoor2, gridSize, color2); //Create trail at player 2's location
						location[xCoor2][yCoor2] += 3; //Add 3 to player 2's location in array
						lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2); //Create lightcycle at player 2's location
							
							//If any bike runs into boundary:
							if(xCoor1 == 0 || xCoor1 == 120 || yCoor1 == 0 || yCoor1 == 78
							|| xCoor2 == 0 || xCoor2 == 120 || yCoor2 == 0 || yCoor2 == 78)
							{
									crash(); //Run crash method
							}
							
							//If any location in array is greater than 3 (any bike crashed):
							for(int r = 0; r < 120; r++)
							{
								for(int c = 0; c < 80; c++)
								{
									if(location[r][c] > 3)
									{
										crash(); //Run crash method
									}
								}
							}
					}
					if(crash1 || crash2) //If any bike has crashed:
					{
						//Create lightcycle with player 1's location and crash status
						lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
						//Create lightcycle with player 2's location and crash status
						lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
						
						if(delay) //If game-state is delayed end
						{
							delay = false; //Set delay to false
							timer = new Timer(); //Create a new timer
							timer.schedule(new WinDelay(), 3000); //Run WinDelay() after 3 seconds
						}
					}
				}
	}
	
	public void paint(Graphics g) //Paint method, takes in graphics parameter
	{
		g.clearRect(0, 0, WIDTH, HEIGHT); //Clears the screen
			if(titlescreen) //If game-state is titlescreen:
			{
				g.drawImage(title, 0, 0, WIDTH, HEIGHT, null); //Draw titlescreen
			}
			else if(customization) //If game-state is customization screen
			{
				g.drawImage(custom, 0, 0, WIDTH, HEIGHT, null); //Draw customization screen
                g.drawImage(bar, 57, 600, null); //Draw color bar for player 1
                g.drawImage(disc1, 40, 125, null); //Draw disc for player 1
                g.drawImage(bar, 630, 660, null); //Draw color bar for player 2
                g.drawImage(disc2, 610, 185, null); //Draw disc for player 2
			}
			else if(battle) //If game-state is battle
			{
				g.drawImage(grid, 0, 0, WIDTH, HEIGHT, null); //Draw The Grid as background
					for(int r = 0; r < 120; r++)
					{
						for(int c = 0; c < 80; c++)
						{
							if(location[r][c] == 2) //If any location in array is equal to 2
							{
								trail1 = new Trail(r, c, gridSize, color1); //Create player 1's trail at that location
								trail1.paint(g); //Draw player 1's trail
							}
							else if(location[r][c] == 3) //If any location in array is equal to 3
							{
								trail2 = new Trail(r, c, gridSize, color2); //Create player 2's trail at that location
								trail2.paint(g); //Draw player 2's trail
							}
						}
					}
					
				lightcycle1.colorChange(); //Check for a color change for player 1
				lightcycle1.paint(g); //Draw player 1's lightcycle
				lightcycle2.colorChange(); //Checks for a colo change for player 2
				lightcycle2.paint(g); //Draw player 2's lightcycle
				
				if(countdown == 3) //If countdown is equal to 3:
				{
					count = count3.getImage(); //Set countdown image to 3
					g.drawImage(count, 560, 350, null); //Draw countdown image
				}
				else if(countdown == 2) //If countdown is equal to 2:
				{
					count = count2.getImage(); //Set countdown image to 2
					g.drawImage(count, 560, 350, null); //Draw countdown image
				}
				else if(countdown == 1) //If countdown is equal to 1:
				{
					count = count1.getImage(); //Set countdown image to 1
					g.drawImage(count, 560, 350, null); //Draw countdown image
				}
				else if(countdown == 0) //If countdown is equal to 0:
				{
					count = countGO.getImage(); //Set countdown image to GO
					g.drawImage(count, 560, 350, null); //Draw countdown image
				}
			}
			else if(winner) //If game-state is winning screen
			{
				if(!crash1 && crash2) //If player 2 crashed:
					g.drawImage(winner1, 0, 0, WIDTH, HEIGHT, null); //Draw player 1 winning screen
				else if(crash1 && !crash2) //If player 1 crashed:
					g.drawImage(winner2, 0, 0, WIDTH, HEIGHT, null); //Draw player 2 winning screen
				else if(crash1 && crash2) //If both players crashed:
					g.drawImage(winner3, 0, 0, WIDTH, HEIGHT, null); //Draw stalemate screen
			}
	}
	
	public void run() //Run method
	{
		while(running) //If game is running:
		{
			try 
			{
				Thread.sleep(29); //Delay game loop for 28 milliseconds
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			tick(); //Run tick method
			repaint(); //Run paint method
			
			if(battle) //If game-state is battle:
			{
				if(countdown > -1) //If countdown is usable:
				{
					try 
					{
						Thread.sleep(1000); //Delay game loop for one second
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
					countdown--; //Subtract 1 from countdown
				}
			}
		}
	}
	
	public void stop() //Stop method
	{	
		running = false; //Sets game to not running
			try 
			{
				thread.join(); //Kills thread
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		System.exit(0); //Closes JFrame
	}
	
	class WinDelay extends TimerTask //Declares class WinDelay, extends TimerTask
	{
		public void run() //Run method
		{
			winner = true; //Set game-state to winner screen
			battle = false; //Stop battle
			timer.cancel(); //Stop timer
		}
	}
	
	public void artificialIntelligence() //Artificial Intelligence method
	{
		Random rand = new Random(); //Creates new Random object
		int turn = rand.nextInt(100) + 1; //Creates random integer from 1-100
		
		boolean north = false; //Boolean to represent tiles north of player
		boolean west = false; //Boolean to represent tiles west of player
		boolean east = false; //Boolean to represent tiles east of player
		boolean south = false; //Boolean to represent tiles south of player
		
		//If tiles west of player are a trail or boundary:
		if(xCoor2 - 3 <= 0 || (location[xCoor2 - 3][yCoor2] != 0 
		|| location[xCoor2 - 2][yCoor2] != 0 || location[xCoor2 - 1][yCoor2] != 0))
		{
			west = true; //Set west to true
		}
		//If tiles east of player are a trail or boundary:
		if(xCoor2 + 3 >= 120 || (location[xCoor2 + 3][yCoor2] != 0 
		|| location[xCoor2 + 2][yCoor2] != 0 || location[xCoor2 + 1][yCoor2] != 0))
		{
			east = true; //Set east to true
		}
		//If tiles north of player are a trail or boundary:
		if(yCoor2 - 3 <= 0 || (location[xCoor2][yCoor2 - 3] != 0 
		|| location[xCoor2][yCoor2 - 2] != 0 || location[xCoor2][yCoor2 - 1] != 0))
		{
			north = true; //Set north to true
		}
		//If tiles south of player are a trail or boundary:
		if(yCoor2 + 3 >= 78 || (location[xCoor2][yCoor2 + 3] != 0 
		|| location[xCoor2][yCoor2 + 2] != 0 || location[xCoor2][yCoor2 + 1] != 0))
		{
			south = true; //Set south to true
		}
		
		if(up2) //If player is going up:
		{
			if(!north && !west && !east) //If all directions are clear:
			{
				//1% chance of turning right
				if(turn == 50) 
					turnRight(2);
				//1% chance of turning left
				else if(turn == 51)
					turnLeft(2);
			}
			else if(north && !west && !east) //If only north is blocked:
			{
				//45% chance of turning right
				if(turn <= 45)
					turnRight(2);
				//45% chance of turning left
				else if(turn > 45 && turn <= 90)
					turnLeft(2);
				//10% chance of crashing
			}
			else if(north && west && !east) //If only north and west are blocked:
			{
				//90% chance of turning right
				if(turn <= 90)
					turnRight(2);
			}
			else if(north && !west && east) //If only north and east are blocked:
			{
				//90% chance of turning left
				if(turn <= 90)
					turnLeft(2);
			}
			else if(!north && !west && east) //If only east is blocked:
			{
				//1% chance of turning left
				if(turn == 50)
					turnLeft(2);
			}
			else if(!north && west && !east) //If only west is blocked:
			{
				//1% chance of turning right
				if(turn == 50)
					turnRight(2);
			}
		}
		else if(down2) //If player is going down:
		{
			if(!south && !west && !east) //If all directions are clear:
			{
				//1% chance of turning right
				if(turn == 50)
					turnRight(2);
				//1% chance of turning left
				else if(turn == 51)
					turnLeft(2);
			}
			else if(south && !west && !east) //If only south is blocked:
			{
				//45% chance of turning right
				if(turn <= 45)
					turnRight(2);
				//45% chance of turning left
				else if(turn > 45 && turn <= 90)
					turnLeft(2);
				//10% chance of crashing
			}
			else if(south && west && !east) //If only south and west are blocked:
			{
				//90% chance of turning right
				if(turn <= 90)
					turnRight(2);
			}
			else if(south && !west && east) //If only south and east are blocked:
			{
				//90% chance of turning left
				if(turn <= 90)
					turnLeft(2);
			}
			else if(!south && !west && east) //If only east is blocked:
			{
				//1% chance of turning left
				if(turn == 50)
					turnLeft(2);
			}
			else if(!south && west && !east) //If only west is blocked:
			{
				//1% chance of turning right
				if(turn == 50)
					turnRight(2);
			}
		}
		else if(right2) //If player is going right:
		{
			if(!east && !north && !south) //If all directions are clear:
			{
				//1% chance of turning up
				if(turn == 50)
					turnUp(2);
				//1% chance of turning down
				else if(turn == 51)
					turnDown(2);
			}
			else if(east && !north && !south) //If only east is blocked:
			{
				//45% chance of turning up
				if(turn <= 45)
					turnUp(2);
				//45% chance of turning down
				else if(turn > 45 && turn <= 90)
					turnDown(2);
				//10% of crashing
			}
			else if(east && !north && south) //If only east and south are blocked:
			{
				//90% chance of turning up
				if(turn <= 90)
					turnUp(2);
			}
			else if(east && north && !south) //If only east and north are blocked:
			{
				//90% chance of turning down
				if(turn <= 90)
					turnDown(2);
			}
			else if(!east && !north && south) //If only south is blocked:
			{
				//1% chance of turning up
				if(turn == 50)
					turnUp(2);
			}
			else if(!east && north && !south) //If only north is blocked:
			{
				//1% chance of turning down
				if(turn == 50)
					turnDown(2);
			}
		}
		else if(left2) //If player is going left:
		{
			if(!west && !north && !south) //If all directions are clear:
			{
				//1% chance of turning up
				if(turn == 50)
					turnUp(2);
				//1% chance of turning down
				else if(turn == 51)
					turnDown(2);
			}
			else if(west && !north && !south) //If only west is blocked:
			{
				//45% chance of turning up
				if(turn <= 45)
					turnUp(2);
				//45% chance of turning down
				else if(turn > 45 && turn <= 90)
					turnDown(2);
				//10% chance of crashing
			}
			else if(west && !north && south) //If only west and south are blocked:
			{
				//90% chance of turning up
				if(turn <= 90)
					turnUp(2);
			}
			else if(west && north && !south) //If only west and north are blocked:
			{
				//90% chance of turning down
				if(turn <= 90)
					turnDown(2);
			}
			else if(!west && !north && south) //If only south is blocked:
			{
				//1% chance of turning up
				if(turn == 50)
					turnUp(2);
			}
			else if(!west && north && !south) //If only north is blocked:
			{
				//1% chance of turning down
				if(turn == 50)
					turnDown(2);
			}
		}
	}
	
	public void crash() //Crash method
	{	
		//Stop player 1 from moving
		down1 = false;
		up1 = false;
		right1 = false;
		left1 = false;
		//Stop player 2 from moving
		down2 = false;
		up2 = false;
		right2 = false;
		left2 = false;
		
			//If both bikes hit a trail or boundary:
			if((location[xCoor1][yCoor1] == 5
			&& location[xCoor2][yCoor2] == 5)
			|| ((xCoor1 == 0 || xCoor1 == 120 || yCoor1 == 0 || yCoor1 == 78)
			&& (xCoor2 == 0 || xCoor2 == 120 || yCoor2 == 0 || yCoor2 == 78)))
			{
				crash1 = true; //Set player 1's crash status to true
				crash2 = true; //Set player 2's crash status to true
			}
			//If player 1's bike hit a trail or a boundary:
			else if((location[xCoor1][yCoor1] == 5 || location[xCoor1][yCoor1] == 4)
			|| ((xCoor1 == 0 || xCoor1 == 120 || yCoor1 == 0 || yCoor1 == 78)
			&& !(xCoor2 == 0 || xCoor2 == 120 || yCoor2 == 0 || yCoor2 == 78)))
			{
					if(location[xCoor1][yCoor1] == 5) //If player 1's bike hit a trail:
						location[xCoor1][yCoor1] = 3; //Set location of player 1 to player 2's trail
				crash1 = true; //Sets player 1's crash status to true
			}	
			//If player 2's bike hit a trail or boundary:
			else if((location[xCoor2][yCoor2] == 5 || location[xCoor2][yCoor2] == 6)
			|| (!(xCoor1 == 0 || xCoor1 == 120 || yCoor1 == 0 || yCoor1 == 78)
			&& (xCoor2 == 0 || xCoor2 == 120 || yCoor2 == 0 || yCoor2 == 78)))
			{	
					if(location[xCoor2][yCoor2] == 5) //If player 2's bike hit a trail:
						location[xCoor2][yCoor2] = 2; //Set location of player 2 to player 1's trail
				crash2 = true; //Sets player 2's crash status to true
			}
		
			for(int r = 0; r < 120; r++)
			{
				for(int c = 0; c < 80; c++)
				{
					if(crash1 && crash2) //If both bikes crashed:
					{
						location[r][c] = 0; //Set all locations in array to 0
					}
					else if(crash1 && !crash2) //If player 1 crashed:
					{
						if(location[r][c] == 2) //If location in array is equal to 2:
						{
							location[r][c] = 0; //Clear all of player 1's trail
						}
					}
					else if(!crash1 && crash2) //If player 2 crashed:
					{
						if(location[r][c] == 3) //If location in array is equal to 3:
						{
							location[r][c] = 0; //Clear all of player 2's trail
						}
					}
				}
			}
			
			delay = true; //Set game-state to delay end
	}
	
	public void turnUp(int player) //turnUp method, parameter for player #
	{
		if(player == 1) //If player is equal to 1:
		{
			//Set player 1's direction to up
			up1 = true;
			left1 = false;
			right1 = false;
			direction1 = "up";

		}
		else if(player == 2) //If player is equal to 2:
		{
			//Set player 2's direction to up
			up2 = true;
			left2 = false;
			right2 = false;
			direction2 = "up";
		}
	}
	public void turnDown(int player) //turnDown method, parameter for player #
	{
		if(player == 1) //If player is equal to 1:
		{
			//Set player 1's direction to down
			down1 = true;
			left1 = false;
			right1 = false;
			direction1 = "down";
		}
		else if(player == 2) //If player is equal to 2:
		{
			//Set player 2's direction to down
			down2 = true;
			left2 = false;
			right2 = false;
			direction2 = "down";
		}
	}
	public void turnLeft(int player)
	{
		if(player == 1) //If player is equal to 1:
		{
			//Set player 1's direction to left
			up1 = false;
			down1 = false;
			left1 = true;
			direction1 = "left";
		}
		else if(player == 2) //If player is equal to 2:
		{
			//Set player 2's direction to left
			up2 = false;
			down2 = false;
			left2 = true;
			direction2 = "left";
		}
	}
	public void turnRight(int player)
	{
		if(player == 1) //If player is equal to 1:
		{
			//Set player 1's direction to right
			up1 = false;
			down1 = false;
			right1 = true;
			direction1 = "right";
		}
		else if(player == 2) //If player is equal to 2:
		{
			//Set player 2's direction to right
			up2 = false;
			down2 = false;
			right2 = true;
			direction2 = "right";
		}
	}
	
	public void reset() //Reset method
	{
		//Resets all game variables
		countdown = 3; //Resets countdown back to 3
	
		//Resets starting position and direction for player 1
		xCoor1 = 60;
		yCoor1 = 3;
		right1 = false;
		left1 = false;
		up1 = false;
		down1 = false;
		direction1 = "down";
		crash1 = false;
		lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
		
		//Resets starting position and direction for player 2
		xCoor2 = 60;
		yCoor2 = 74;
		right2 = false;
		left2 = false;
		up2 = false;
		down2 = false;
		direction2 = "up";
		crash2 = false;
		lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
		
		for(int r = 0; r < 120; r++)
		{
			for(int c = 0; c < 80; c++)
			{
				location[r][c] = 0; //Sets all locations in array back to 0
			}
		}
	}
	
	class Key implements KeyListener //Declares class Key, implements KeyListener
	{
		public void keyPressed(KeyEvent k) //keyPressed method, parameter for key code
		{
			int key = k.getKeyCode(); //Int to represent key code
			
				if(!crash1 && !crash2) //If both bikes are alive:
				{
					if(key == KeyEvent.VK_RIGHT && !left1) //If right arrow is pressed:
					{
						turnRight(1); //Turn player 1 right
					}
					else if(key == KeyEvent.VK_LEFT && !right1) //If left arrow is pressed:
					{
						turnLeft(1); //Turn player 1 left
					}
					else if(key == KeyEvent.VK_UP && !down1) //If up arrow is pressed:
					{
						turnUp(1); //Turn player 1 up
					}
					else if(key == KeyEvent.VK_DOWN && !up1) //If down arrow is pressed:
					{
						turnDown(1); //Turn player 1 down
					}
					
					if(playerVsPlayer) //If gamemode is Player vs. Player:
					{
						if(key == KeyEvent.VK_D && !left2) //If D is pressed:
						{
							turnRight(2); //Turn player 2 right
						}
						else if(key == KeyEvent.VK_A && !right2) //If A is pressed:
						{
							turnLeft(2); //Turn player 2 left
						}
						else if(key == KeyEvent.VK_W && !down2) //If W is pressed:
						{
							turnUp(2); //Turn player 2 up
						}
						else if(key == KeyEvent.VK_S && !up2) //If S is pressed:
						{
							turnDown(2); //Turn player 2 down
						}
					}
				}
		}

		public void keyReleased(KeyEvent k) 
		{
		}

		public void keyTyped(KeyEvent k) 
		{	
		}
	}
	
    class Mouse implements MouseListener //Delcares class Mouse implements MouseListener
    {
        public void mouseClicked(MouseEvent m) //mouseClicked method, parameter for mouse click
        {
            int xCoor = m.getX(); //Int to represent X-axis coordinate of mouse click
            int yCoor = m.getY(); //Int to represent Y-axis coordinate of mouse click
            if(titlescreen) //If game-state is titlescreen:
            {
            	//If user clicked Player vs. Player
            	if(xCoor >= 269 && xCoor <= 523 && yCoor >= 590 && yCoor <= 660)
            	{
            		//Set gamemode to Player vs. Player
            		playerVsPlayer = true;
            		playerVsComputer = false; 
            		battle = true; //Start battle
            		titlescreen = false; //Exit titlescreen
            	}
            	//If user clicked Player vs. Computer
            	else if(xCoor >= 707 && xCoor <= 960 && yCoor >= 591 && yCoor <= 664)
            	{
            		//Set gamemode to Player vs. Computer
            		playerVsComputer = true;
            		playerVsPlayer = false;
            		battle = true; //Start battle
            		titlescreen = false; //Exit titlescreen
            	}
            	//If user clicked Customization
            	else if(xCoor >= 212 && xCoor <= 580 && yCoor >= 730 && yCoor <= 760)
            	{
            		customization = true; //Enter customization screen
            		titlescreen = false; //Exit titlescreen
            	}
            	//If user clicked Exit
            	else if(xCoor >= 786 && xCoor <= 890 && yCoor >= 732 && yCoor <= 762)
            	{
            		stop(); //Run stop method
            	}
            }
            else if(customization) //If game-state is cusomization screen:
            {
            	//If user clicked cyan square for player 1:
                if((xCoor >= 57 && xCoor <= 138 && yCoor >= 624 && yCoor <= 676) && color2 != Color.CYAN)
                {
                	//Set player 1's bike/trail color to cyan
                    disc1 = cyanDISC.getImage();
                    color1 = Color.CYAN;
                    lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
                }
                //If user clicked red square for player 1:
                else if((xCoor >= 139 && xCoor <= 217 && yCoor >= 624 && yCoor <= 676) && color2 != Color.RED)
                {
                	//Set player 1's bike/trail color to red
                    disc1 = redDISC.getImage();
                    color1 = Color.RED;
                    lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
                }
                //If user clicked on yellow square for player 1:
                else if((xCoor >= 218 && xCoor <= 296 && yCoor >= 624 && yCoor <= 676) && color2 != Color.YELLOW)
                {
                	//Set player 1's bike/trail color to yellow
                    disc1 = yellowDISC.getImage();
                    color1 = Color.YELLOW;
                    lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
                }
                //If user clicked on green square for player 1:
                else if((xCoor >= 297 && xCoor <= 376 && yCoor >= 624 && yCoor <= 676) && color2 != Color.GREEN)
                {
                	//Set player 1's bike/trail color to green
                    disc1 = greenDISC.getImage();
                    color1 = Color.GREEN;
                    lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
                }
                //If user clicked on magenta square for player 1:
                else if((xCoor >= 377 && xCoor <= 455 && yCoor >= 624 && yCoor <= 676) && color2 != Color.MAGENTA)
                {
                	//Set player 1's bike/trail color to magenta
                    disc1 = magDISC.getImage();
                    color1 = Color.MAGENTA;
                    lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
                }
                //If user clicked on white square for player 1:
                else if((xCoor >= 456 && xCoor <= 534 && yCoor >= 624 && yCoor <= 676) && color2 != Color.WHITE)
                {
                	//Set player 1's bike/trail color to white
                    disc1 = whiteDISC.getImage();
                    color1 = Color.WHITE;
                    lightcycle1 = new Lightcycle(xCoor1, yCoor1, direction1, crash1, color1);
                }
                //If user clicked on cyan square for player 2:
                else if((xCoor >= 633 && xCoor <= 711 && yCoor >= 685 && yCoor <= 737) && color1 != Color.CYAN)
                {
                	//Set player 2's bike/trail color to cyan
                	disc2 = cyanDISC.getImage();
                	color2 = Color.CYAN;
                	lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
                }
                //If user clicked on red square for player 2:
                else if((xCoor >= 712 && xCoor <= 790 && yCoor >= 685 && yCoor <= 737) && color1 != Color.RED)
                {
                	//Set player 2's bike/trail color to red
                	disc2 = redDISC.getImage();
                	color2 = Color.RED;
                	lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
                }
                //If user clicked on yellow square for player 2:
                else if((xCoor >= 791 && xCoor <= 869 && yCoor >= 685 && yCoor <= 737) && color1 != Color.YELLOW)
                {
                	//Set player 2's bike/trail color to yellow
                	disc2 = yellowDISC.getImage();
                	color2 = Color.YELLOW;
                	lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
                }
                //If user clicked on green square for player 2:
                else if((xCoor >= 870 && xCoor <= 948 && yCoor >= 685 && yCoor <= 737) && color1 != Color.GREEN)
                {
                	//Set player 2's bike/trail color to green
                	disc2 = greenDISC.getImage();
                	color2 = Color.GREEN;
                	lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
                }
                //If user clicked on magenta square for player 2:
                else if((xCoor >= 949 && xCoor <= 1028 && yCoor >= 685 && yCoor <= 737) && color1 != Color.MAGENTA)
                {
                	//Set player 2's bike/trail color to magenta
                	disc2 = magDISC.getImage();
                	color2 = Color.MAGENTA;
                	lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
                }
                //If user clicked on white square for player 2:
                else if((xCoor >= 1029 && xCoor <= 1107 && yCoor >= 685 && yCoor <= 737) && color1 != Color.WHITE)
                {
                	//Set player 2's bike/trail color to white
                	disc2 = whiteDISC.getImage();
                	color2 = Color.WHITE;
                	lightcycle2 = new Lightcycle(xCoor2, yCoor2, direction2, crash2, color2);
                }
                //If user clicked on Back
                else if(xCoor >= 88 && xCoor <= 325 && yCoor >= 737 && yCoor <= 782)
                {
                	titlescreen = true; //Enter titlescreen
                	customization = false; //Exit customization screen
                }
            }
            else if(winner) //If game-state is winner screen:
            {
            	//If user clicked on Play Again:
            	if(xCoor >= 77 && xCoor <= 613 && yCoor >= 500 && yCoor <= 600)
            	{
            		battle = true; //Start battle
            		winner = false; //Exit winner screen
            		reset(); //Run reset method
            	}
            	//If user clicked on Menu:
            	else if(xCoor >= 708 && xCoor <= 1130 && yCoor >= 620 && yCoor <= 755)
            	{
            		titlescreen = true; //Enter titlescreen
            		winner = false; //Exit winner screen
            		reset(); //Run reset method
            	}
            }
        }

        public void mousePressed(MouseEvent m)
        {
        }

        public void mouseReleased(MouseEvent m)
        {
        }

        public void mouseEntered(MouseEvent m)
        {
        }

        public void mouseExited(MouseEvent m)
        {
        }
    }
	
	public static void main(String args[]) //Declares main method
	{
		new TRON(); //Instantiates new TRON game
	}
}