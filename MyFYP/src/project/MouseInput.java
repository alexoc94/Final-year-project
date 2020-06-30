package project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener { // mouse input class

	
	public void mouseClicked(MouseEvent e) {
		
	} // end of method

	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if ( mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) { // play button
			
			if (my >= 150 && my <= 200) {
				Game.State = Game.STATE.GAME; // pressed play button
				
			} // end of if
			
		} // end of if
		
		if ( mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) { // quit button
			
			if (my >= 350 && my <= 400) {
				
				System.exit(1); // pressed quit button
				
			} // end of if
			
		} // end of if
		 
	} // end of method

	public void mouseReleased(MouseEvent e) { // event for mouse release
			
	} // end of method

	public void mouseEntered(MouseEvent e) { // event for mouse entered
		
	} // end of method

	public void mouseExited(MouseEvent e) { // event for mouse exited
		
	} // end of method

} // end of class
