package project;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable { // When a thread is called or started it will take that thread and call the run method
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320; // Static final = width and height never/cant be changed
	public static final int HEIGHT = WIDTH / 12 * 9; // Can set to whatever but this gives us a nice aspect ratio
	public static final int SCALE = 2;
	public final String TITLE = "APOCALYPSE";
	public static int SCORE = 0;

	private boolean running = false;
			private Thread thread;
			
			private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
			private BufferedImage spriteSheet = null;
			private BufferedImage background = null;
			
			private boolean is_shooting = false; // prevents a continuous stream of shooting by holding shoot key. Dont want player to be over powered
			
			private int enemy_count = 10; // Enemy count tells the game how many spaceships to spawn
			private int enemy_killed = 0;
			
			public int getEnemy_count() { // getter for enemy count
				return enemy_count;
				
			} // end of getter

			public void setEnemy_count(int enemy_count) { // setter for enemy count
				this.enemy_count = enemy_count;
				
			} // end of setter

			public int getEnemy_killed() { // getter for enemy killed
				return enemy_killed;
				
			} // end of getter

			public void setEnemy_killed(int enemy_killed) {
				this.enemy_killed = enemy_killed;
				
			} // end of method

			private Player p;
			private Controller c;
			private Textures tex;
			private Menu menu;
			
			public LinkedList<EntityA> ea;
			public LinkedList<EntityB> eb;
			
			public static int HEALTH = 100 * 2; // set player health
			
			public static enum STATE {
				
				MENU,
				GAME
				
			} // end of enum
			
			public static STATE State = STATE.MENU;
			
			public void init() {
				requestFocus(); // Means we dont have to click the screen to begin movement
				BufferedImageLoader loader = new BufferedImageLoader();
				
				try {
					
					spriteSheet = loader.loadImage("/SpriteSheet.png"); // If this image does not load send us an error report
					background = loader.loadImage("/Galaxy.png"); // sets background
					
				} // end of try
				
				catch(IOException e) {
					e.printStackTrace(); // this is the error report
					
				} // end of catch
				
				tex = new Textures(this); // Must come before Controller and player otherwise they wont know what image to get
				c = new Controller(tex, this);
				p = new Player(200, 200, tex, this, c); // in our game class we refer to itself as "this"
				menu = new Menu();
				
				ea = c.getEntityA(); // for colllisions 
				eb = c.getEntityB();
				
				
				this.addKeyListener(new KeyInput(this)); // KeyListner is invoked when a key has been pressed to allow movement e.g. 7
				this.addMouseListener(new MouseInput());
				
				c.createEnemy(enemy_count); // creates enemies. change amount in enemy_count
				
			} // end of method
			
			private synchronized void start() { // Initialize thread
				if (running)
					return;
				
				running = true;
				thread = new Thread(this);
				thread.start(); // Actual starting of the thread A buffered image loads the image before you can see it.
				
			} // end of method
			
			private synchronized void stop() { // Use synchronized if using threads
				if (!running)
					return; // if running is running get out of this method
				
				running = false; // Stops our game loop
				
				try { // Joining all the threads together could fail so we need to surround with try and catch. If there's any possibility of failure use try catch
					
					thread.join();
					
				} // end of try
				
				catch (InterruptedException e) {
					e.printStackTrace();
					
				} // end of catch
				
				System.exit(1);
				
			} // end of method
	
	public void run() { // This is the game loop. This is the heart of the game essentially. Code reiterates
		
		init(); // Will be called after thread has started
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; // If our fps is falling behind, this number catches everything up
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) { // pretty much an infinite game loop
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
				
			} // end of if
			
			render();
			
			frames++; // FPS stand for frames per second, a measurement for how many unique consecutive images a camera can handle each second.
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Tickes, Fps " + frames);
				updates = 0;
				frames = 0;
				
			} // end of if
			
		} // end of while
		
		stop();
		
	} // end of method
	
	private void tick() { // Everything in the game that updates will go here
		
		if (State == STATE.GAME) {
		p.tick();
		c.tick();
		
		} // end of if
		
		if (enemy_killed >= enemy_count) { // Allows creation of enemies after they have been killed
			enemy_count += 5;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
			
		} // end of if
		
	} // end of method
	
	private void render() { // Takes care of everything that renders i.e getting data. Also is where our bufferstrategy takes place.
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) { 
			createBufferStrategy(3); // This means were buffering 3 images at a time. We could load for example 30 images has cpu isnt fast enough
			return; //So were implementing triple buffering here. 30 buffers wouldnt work (try it as an example)
			
		} // end of if
		
		Graphics g = bs.getDrawGraphics(); // Basically just draws out our bufferes which will loading accordingly via the buffer strategy
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		g.drawImage(background, 0, 0, this); // selects img from row and column on sprite sheet
		
		if (State == STATE.GAME) {
			p.render(g);
			c.render(g);
			
			g.setColor(Color.gray); // set color of health bar
			g.fillRect(5, 5, 200, 20); // set health bar position on frame
			
			g.setColor(Color.green); // set color of health bar
			g.fillRect(5, 5, HEALTH, 20); // set health bar position on frame
			
			g.setColor(Color.white); // set color of health bar
			g.drawRect(5, 5, 200, 20); // set health bar position on frame
			
			g.setColor(Color.white); // set color of Score
			g.drawString("Score: " + SCORE, 10, 40); // set score position
		
		} // end of if
		
		else if (State == STATE.MENU) {
			menu.render(g);
			
		} // end of else if
		
		g.dispose(); // Get rid of image once were done with it
		bs.show(); // Show our image
		
	} // end of method
	
	public void keyPressed(KeyEvent e) { // events for keys when pressed
		
		int key = e.getKeyCode();
		
		if (State == STATE.GAME) {
		
		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(5);
			
		} // end of if
		
		else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(-5);
			
		} // end of else if
		
		else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(5);
			
		} // end of else if
		
		else if (key == KeyEvent.VK_UP) {
			p.setVelY(-5);
			
		}// end of else if
		
		else if (key == KeyEvent.VK_SPACE && !is_shooting) {
			c.addEntity(new Bullet(p.getX(), p.getY(), tex, this)); // Ensures the bullet is shooting from the sprite and not from top to bottom of frame
			is_shooting = true; // forces player to release shoot key before being able to shoot again
			
		} // end of else if
		
		} // end of if else
			
	} // end of method
	
	public void keyReleased(KeyEvent e) { // events for keys when released
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
			
		} // end of if
		
		else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
			
		} // end of else if
		
		else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
			
		} // end of else if
		
		else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
			
		} // end of else if
		
		else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;
					
		} // end of else if
		
	} // end of method
	
	public static void main(String args[]) { // Main method required to allow our program to run
		Game game = new Game(); // Creating new instance of our game class here
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // Dimension is part of java AWT
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // Must use setmax size and set min size with set preffered size. We are using set preffedr size due to layout manager
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		
		frame.add(game); // Takes in our pref,min,max sizes
		frame.pack(); // takes all of the components in it and sizes the frame according to the sizes of its components. Has to be there
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows us to exit i.e allows our x button to work basically
		frame.setResizable(false); // Forbids any resizing of dimensions
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // Allows us to see frame
		
		game.start(); // Starts the program. Program wont do anything without it.
		
	} // end of main method
	
	public BufferedImage getSpriteSheet() { // gets spritesheet
		
		return spriteSheet;
		
	} // end of buffered inmage

} // end of class
