package project;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy  extends GameObject implements EntityB {
	
	Random r = new Random();
	private Textures tex;
	private Game game; // import game
	private Controller c; // import controller as they contain entities
	
	private int speed = r.nextInt(3) + 1; // speed of enemy
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game) { // constructors
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		
	} // end of constructors
	
	public void tick() { // If our object moves at all we need tick method for updating object
		y += speed;
		
		if (y > (Game.HEIGHT * Game.SCALE)) { // once enemies get to the bottom of the screen they will go back to top and come down again
			speed = r.nextInt(3) + 1;
			y = -10;
			x = r.nextInt(640); // when enemies go from top to bottow have them appear randomly at top
			
		} // end of if
		
		for (int i = 0; i < game.ea.size(); i++) {
			
			EntityA tempEnt = game.ea.get(i);
			
			if (Physics.Collision(this, tempEnt)) { // it hits enemy remove bullet after
				
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				Game.SCORE += 1;
				
			} // end of if
			
		} // end of for loop
			
		} // end of method
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int) x, (int) y, null); // must cast int as draw image only handles integers
		
	} // end of method
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
		
	} // end
	
	public double getY() { // getter for Y
		return y;
		
	} // end of getter
	
	public void setY(double y) { // setter for Y
		this.y = y;
		
	} // end of setter

	
	public double getX() { // getter for X
		return x;
				
	} // end of getter

} // end of class
