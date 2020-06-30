package project;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject implements EntityA {
	
	private Textures tex;
	private Game game; // initialize game
	
	public Bullet(double x, double y, Textures tex, Game game) { // constructors
		super(x, y);
		this.tex = tex;
		this.setGame(game);
		
	} // end constructors
	
	public void tick() {
		y-= 10; // controls bullet speed
		
	} // end of method
	
	public Rectangle getBounds() { // getter for bounds
		return new Rectangle((int)x, (int)y, 32, 32);
		
	} // end of getter
	
	public void render(Graphics g) { // renders missile graphics
		g.drawImage(tex.missile, (int) x, (int) y, null);;
		
	} // end of method
	
	public double getY() { // getter for Y
		return y;
		
	} // end of getter
	
	public double getX() { // getter for X
		return x;
		
	} // end of getter

	public Game getGame() { // getter for game
		return game;
		
	} // end of getter

	public void setGame(Game game) { // setter for game
		this.game = game;
		
	} // end of setter

} // end of class
