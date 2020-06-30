package project;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityA { // EntitAs dont collide with other entityA's. Entity a and entit bs do collide however
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();

} // end of class
