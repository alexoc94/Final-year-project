package project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter { // Whenever we use a key from the keyboard this class is called. When we use a key the KeyAdapter calls methods below
	
	Game game;
	
	public KeyInput(Game game) { // This allows us to call our keyInput class in our game class. Must be done in game not player to allow menu to work
		this.game = game;
		
	} // end of constructor
	
	public void keyPressed(KeyEvent e) { //Do not capitalize k. // keypressed and key released copied and pasted into game class
		game.keyPressed(e); // When we press a key it goes from this method to mirrored method in game class. Same with KeyReleased
		
	} // end of method
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
		
	} // end of method

} // end of class
