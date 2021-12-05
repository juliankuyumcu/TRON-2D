package tron; //Code part of package tron

import java.awt.Color; //Imports color class
import java.awt.Graphics; //Imports Graphics class
import java.awt.Image; //Imports Image class

import javax.swing.ImageIcon; //Imports ImageIcon class

public class Lightcycle //Declares class Lightcycle
{
	private final int gridSize = 10; //Constant int to represent tile size
	int xCoor = 0; //Int to represent X-axis coordinate
	int yCoor = 0; //Int to represent Y-axis coordinate
	String direction = ""; //String to represent direction
	boolean crash = false; //Boolean to represent crash status
	Color color = null; //Color to represent color of bike
	
	//ImageIcons for directions of bike
    ImageIcon up = null;
	ImageIcon down = null;
	ImageIcon left = null;
	ImageIcon right = null;
	
	//ImageIcons for animations of bike
    ImageIcon upCrash = null;
	ImageIcon downCrash = null;
	ImageIcon leftCrash = null;
	ImageIcon rightCrash = null;
	
	Image bike = null; //Image to represent image of bike
	
	//Constructor, parameters for X coordinate, Y coordinate, direction, crash status, and color
	public Lightcycle(int xCoor, int yCoor, String direction, boolean crash, Color color)
	{
		//Set state variables to passed-in values
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.direction = direction;
		this.crash = crash;
		this.color = color;
	}
	
	public void colorChange() //colorChange method
	{
		if(color == Color.CYAN) //If color is cyan:
		{
			//Update all direction pictures to the cyan bike
			up = new ImageIcon("res/TRON Lightcycles/cyanUP.png");
			down = new ImageIcon("res/TRON Lightcycles/cyanDOWN.png");
			left = new ImageIcon("res/TRON Lightcycles/cyanLEFT.png");
			right = new ImageIcon("res/TRON Lightcycles/cyanRIGHT.png");
			//Update all derez animations to the cyan bike
		    upCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/cyanDerezUP.gif");
			downCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/cyanDerezDOWN.gif");
			leftCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/cyanDerezLEFT.gif");
			rightCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/cyanDerezRIGHT.gif");
		}
		else if(color == Color.YELLOW) //If color is yellow:
		{
			//Update all direction pictures to the yellow bike
			up = new ImageIcon("res/TRON Lightcycles/yellowUP.png");
			down = new ImageIcon("res/TRON Lightcycles/yellowDOWN.png");
			left = new ImageIcon("res/TRON Lightcycles/yellowLEFT.png");
			right = new ImageIcon("res/TRON Lightcycles/yellowRIGHT.png");
			//Update all derez animations to the yellow bike
			upCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/yellowDerezUP.gif");
			downCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/yellowDerezDOWN.gif");
			leftCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/yellowDerezLEFT.gif");
			rightCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/yellowDerezRIGHT.gif");
		}
		else if(color == Color.GREEN) //If color is green:
		{
			//Update all direction pictures to the green bike
			up = new ImageIcon("res/TRON Lightcycles/greenUP.png");
			down = new ImageIcon("res/TRON Lightcycles/greenDOWN.png");
			left = new ImageIcon("res/TRON Lightcycles/greenLEFT.png");
			right = new ImageIcon("res/TRON Lightcycles/greenRIGHT.png");
			//Update all derez animations to the green bike
			upCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/greenDerezUP.gif");
			downCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/greenDerezDOWN.gif");
			leftCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/greenDerezLEFT.gif");
			rightCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/greenDerezRIGHT.gif");
		}
		else if(color == Color.RED) //If color is red:
		{
			//Update all direction pictures to the red bike
			up = new ImageIcon("res/TRON Lightcycles/redUP.png");
			down = new ImageIcon("res/TRON Lightcycles/redDOWN.png");
			left = new ImageIcon("res/TRON Lightcycles/redLEFT.png");
			right = new ImageIcon("res/TRON Lightcycles/redRIGHT.png");
			//Update all derez animations to the red bike
			upCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/redDerezUP.gif");
			downCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/redDerezDOWN.gif");
			leftCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/redDerezLEFT.gif");
			rightCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/redDerezRIGHT.gif");
		}
		else if(color == Color.MAGENTA) //If color is magenta:
		{
			//Update all direction pictures to the magenta bike
			up = new ImageIcon("res/TRON Lightcycles/magUP.png");
			down = new ImageIcon("res/TRON Lightcycles/magDOWN.png");
			left = new ImageIcon("res/TRON Lightcycles/magLEFT.png");
			right = new ImageIcon("res/TRON Lightcycles/magRIGHT.png");
			//Update all derez animations to the magenta bike
			upCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/magDerezUP.gif");
			downCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/magDerezDOWN.gif");
			leftCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/magDerezLEFT.gif");
			rightCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/magDerezRIGHT.gif");
		}
		else if(color == Color.WHITE) //If oclor is white:
		{
			//Update all direction pictures to the white bike
			up = new ImageIcon("res/TRON Lightcycles/whiteUP.png");
			down = new ImageIcon("res/TRON Lightcycles/whiteDOWN.png");
			left = new ImageIcon("res/TRON Lightcycles/whiteLEFT.png");
			right = new ImageIcon("res/TRON Lightcycles/whiteRIGHT.png");
			//Update all derez animations to the white bike
			upCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/whiteDerezUP.gif");
			downCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/whiteDerezDOWN.gif");
			leftCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/whiteDerezLEFT.gif");
			rightCrash = new ImageIcon("res/TRON Lightcycles/Derez GIFs/whiteDerezRIGHT.gif");
		}
	}
	
	public void paint(Graphics g) //Paint method, parameter for graphics
	{
		if(direction.equals("down")) //If direction is down
		{
			if(!crash) //If the bike is alive:
			{
				bike = down.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 7, yCoor * gridSize - 51, 24, 62, null);
			}
			else if(crash) //If the bike crashed:
			{
				bike = downCrash.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 32, yCoor * gridSize - 59, 73, 73, null);
			}
		}
		else if(direction.equals("up"))
		{
			if(!crash) //If the bike is alive:
			{
				bike = up.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 7, yCoor * gridSize - 1, 24, 62, null);
			}
			else if(crash) //If the bike crashed:
			{
				bike = upCrash.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 31, yCoor * gridSize - 5, 73, 73, null);
			}
		}
		else if(direction.equals("left"))
		{
			if(!crash) //If the bike is alive:
			{
				bike = left.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 1, yCoor * gridSize - 7, 62, 24, null);
			}
			else if(crash) //If the bike crashed:
			{
				bike = leftCrash.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 5, yCoor * gridSize - 32, 73, 73, null);
			}
		}
		else if(direction.equals("right"))
		{
			if(!crash) //If the bike is alive:
			{
				bike = right.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 51, yCoor * gridSize - 7, 62, 24, null);
			}
			else if(crash) //If the bike crashed:
			{
				bike = rightCrash.getImage(); //Update bike image
				//Draw bike image in appropriate location
				g.drawImage(bike, xCoor * gridSize - 59, yCoor * gridSize - 31, 73, 73, null);
			}
		}
	}
}