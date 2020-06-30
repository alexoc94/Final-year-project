package project;

public class Physics { // This classes handles possible collissions or not
	
	public static boolean Collision(EntityA enta, EntityB entb) { // call collision method
		
			if (enta.getBounds().intersects(entb.getBounds())) {
				return true; // if true then a collision happens
				
			} // end of if
			
			return false;
			
	} // end of collision
	
	public static boolean Collision(EntityB entb, EntityA enta) { // call collision method
	
			if (entb.getBounds().intersects(enta.getBounds())) {
				
				return true;
				
			} // end of if
			
			return false;
		
	} // end of collision

} // end of class
