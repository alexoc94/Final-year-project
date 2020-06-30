package project;

import java.awt.image.BufferedImage;

public class Textures { // critical for reduction of lag as spritesheet wont have to load everytime theres an image needed.
	
	public BufferedImage player, missile, enemy;
	
	private SpriteSheet ss;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
		
	} // end of textures
	
	private void getTextures() { // grabs images of the spritesheet
		player = ss.grabImage(1, 1, 32, 32);
		missile = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3, 1, 32, 32);
		
	} // end of method

} // end of class
