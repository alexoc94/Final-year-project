package project;

import java.awt.Rectangle;

public class GameObject {
	
	public double x, y; // variable
	
	public GameObject(double x, double y) { // constructor
		this.x = x;
		this.y = y;
		
	} // end of constructor
	
	public Rectangle getBounds(int width, int height) { // we use rectangles to see if theres a collision. we map a non visible rectangle around player
		return new Rectangle((int)x, (int)y, width, height); // to detect collision. Must cast as x and y are double values
		
	} 
	
} // end of class
