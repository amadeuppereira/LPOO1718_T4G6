package dkeep.logic;

import java.util.Random;


/**
 * Suspicious.java - A class that handles a specific type of guard (Suspicious)
 */
public class Suspicious extends Guard {

	/**
	 * Create a new Suspicious object
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param mov the movement that guard follows
	 */
	public Suspicious(int x, int y, char[] mov) {
		super(x, y, mov);
		reverse = false;
	}

	/* (non-Javadoc)
	 * @see dkeep.logic.Guard#move()
	 */
	@Override
	public void move() {
		
		changeMovement();
		if (reverse == false)
			normal_movement();
		else
			reverse_movement();
		
	}
	
	/**
	 * Change the guard movement if he awakes (20% chance) 
	 */
	private void changeMovement() {
		Random randomGenerator = new Random();
		int option = randomGenerator.nextInt(11);
		if (option < 3) {
			if (reverse == false)
				reverse = true;
			else
				reverse = false;
		}	
	}

}
