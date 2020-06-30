package project;

import java.awt.image.BufferedImage;

public class SpriteSheet { // spritesheet class 
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		
		this.image = image;
		
	} // end od spritesheet
	
	public BufferedImage grabImage(int col, int row, int width, int heght) { // This method is actually grabbing and cropping out our spritesheet
		
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, heght); // 32, 32 is the width that we want
		return img;
		
	} // end of BufferedImage

} // end of class
