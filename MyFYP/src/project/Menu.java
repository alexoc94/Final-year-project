package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu { // main menu when game starts
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50); // play button
	
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50); // quit button

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("APOCALYPSE", Game.WIDTH / 2, 100); // heading
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		
		g.drawString("Play", playButton.x + 20, playButton.y + 35);
		g2d.draw(playButton);
		
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 35);
		g2d.draw(quitButton);
		
	} // end of method
	
} // end of class
