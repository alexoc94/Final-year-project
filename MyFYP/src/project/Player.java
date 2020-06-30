package project;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject implements EntityA {
	
	private double velX = 0; // cornets also known as axis  . . . x 100 is 100 to right y 100 is 100 down. down is positive however. its flipped
	private double velY = 0; // Velocity x and y allows for smoother movement with no delayed response when a key is pressed or released
	private Textures tex;
	
	public Game game;
	Controller controller;

	public Player(double x, double y, Textures tex, Game game, Controller controller) { // Game game initializes our game classConstructor is called once the player is intialized in the game class
		super(x, y); // super constructor which lets us use variables for player enemy and bullet. super refers to super class
		this.tex = tex; // private tex variable = tex in constructor parameter
		this.game = game;
		this.controller = controller;
		
	} // end of constructors
	
	public void tick() { // tick is the update method here
		x+=velX;
		y+=velY;
		
		if (x <= 0) // These statements ensure player does not move off the screen. -32 ensures that no part of the player goes off screen
			x = 0; // Try -32 then -15 as an example
		if (x >= 640 - 32)
			x = 640 - 32;
		if (y <= 0)
			y = 0;
		if (y >= 480 - 44)
			y = 480 - 44;
		
		for (int i = 0; i < game.eb.size(); i++) { // removes health from player if collision detected
			
			EntityB tempEnt = game.eb.get(i);
			
			if (Physics.Collision(this, tempEnt)) {
				
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 10;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			
			} // end of if
		
		} // end of for loop
		
	} // end of method
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
		
	}
	
	public void render(Graphics g) { // render is drawing out the image
		
		g.drawImage(tex.player, (int)x, (int)y, null);
		
	} // end of method
	
	public double getX() { // Allows us to pass private variables in game class
		return x;
		
	} // end of getter
	
	public double getY() { // getter for getY
		return y;
		
	} // end of getter
	
	public void setX(double x) { // Allows us to change velx and vely variables
		this.x = x;
		
	} // end of setter
	
	public void setY(double y) { // setter for setY
		this.y = y;
			
	} // end of setter
	
	public void setVelX(double velX) { // setter for VelX
		this.velX = velX;
		
	} // end of setter
	
	public void setVelY(double velY) { // setter for VelY
		this.velY = velY;
		
	} // end of setter
	
} // end of class
