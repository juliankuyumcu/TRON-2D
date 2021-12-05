package tron; //Code part of package tron

import java.awt.Color; //Imports the color class
import java.awt.Graphics; //Imports the graphics class

public class Trail //Declares class Trail
{
	int xCoor = 0; //Int to represent X-axis coordinate
	int yCoor = 0; //Int to represent Y-axis coordinate
	int gridSize = 0; //Int to represent tile size
	Color color = null; //Color to represent color of trail
	
	//Constructor, parameters for X coordinate, Y coordinate, tile size, and color
	public Trail(int xCoor, int yCoor, int gridSize, Color color)
	{
		//Set state variables to passed-in values
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.gridSize = gridSize;
		this.color = color;
	}
	
	public void paint(Graphics g) //Paint method, parameter for graphics
	{
		g.setColor(color); //Set color to passed-in color
		//Draws a trail at the appropriate location and size
		g.fillRect(xCoor * gridSize, yCoor * gridSize, gridSize, gridSize);
	}
}