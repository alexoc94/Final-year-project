package project;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader { // This class just loads in our buffered image

	private BufferedImage image;
	
	public BufferedImage loadImage(String path) throws IOException { // This is the path at which our spritesheet is held
		
		image = ImageIO.read(getClass().getResource(path));
		return image;
		
	} // end of throw exception
	
} // end of class
