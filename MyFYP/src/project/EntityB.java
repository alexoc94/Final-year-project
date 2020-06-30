package project;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB { // EntitBs dont collide with other entityB's. Entity A and entity B do collide however
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();

} // end of class
