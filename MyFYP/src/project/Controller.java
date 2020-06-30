package project;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<EntityA>(); // Always create lists if we want multiple entities/objects
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	
	EntityA enta;
	EntityB entb;
	
	Textures tex;
	Random r = new Random(); // allows us to get random numbers which will be used to randomize the spawning on enemies
	private Game game;
	
	public Controller(Textures tex, Game game) { // constructors
		this.tex = tex;
		this.game = game;
		
	} // end constructors
	
	public void createEnemy(int enemy_count) { // generate enemies to begin with
		
		for (int i = 0; i < enemy_count; i++) {
			addEntity(new Enemy(r.nextInt(640), -10, tex, this, game)); // positions at random points. When an enemy is created its placed as a b class
			
		} // end of for loop
		
	} // end of method
	
	public void tick() { // A class
		
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			
			enta.tick();
			
		} // end of for loop
			
			for (int i = 0; i < eb.size(); i++) { // B class
				entb = eb.get(i);
				
				entb.tick();
				
			} // end of for loop
		
	} // end of method
	
	public void render(Graphics g) { // A class
		
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			
			enta.render(g);
			
		} // end of for loop 
			
			for (int i = 0; i < eb.size(); i++) { // B class
				entb = eb.get(i);
				
				entb.render(g);
				
			} // end of for loop 
		
	} // end of method
	
	public void addEntity(EntityA block) { // A class
		ea.add(block);
		
	} // end of method
	
	public void removeEntity(EntityA block) { // A class
		ea.remove(block);
		
	} // end of method
	
	public void addEntity(EntityB block) { // B class.  Note to self we can use same names as parameters are different
		eb.add(block);
		
	} // end of method
	
	public void removeEntity(EntityB block) { // B class
		eb.remove(block);
		
	} // end of method
	
	public LinkedList<EntityA> getEntityA() {
		return ea;
		
	} // end 
	
	public LinkedList <EntityB> getEntityB() {
		return eb;
		
	} // end

} // end of class
