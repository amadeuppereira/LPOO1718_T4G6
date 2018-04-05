package dkeep.logic;

import java.util.Random;

/**
 * Drunken.java - A class that handles a specific type of guard (Drunken)
 */
public class Drunken extends Guard {
	
	/**
	 * Create a new Drunken object
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param mov the movement that guard follows
	 */
	public Drunken(int x, int y, char[] mov) {
		super(x, y, mov);
		reverse = false;
	}

	/* (non-Javadoc)
	 * @see dkeep.logic.Guard#move()
	 */
	@Override
	public void move() {
		Random randomGenerator = new Random();
		int option = randomGenerator.nextInt(2);
			
		if (option == 0) { // he will sleep in the same position
			setAsleep(true);
		} else {
			changeMovement();
			if (reverse == false)
				normal_movement();
			else
				reverse_movement();
			setAsleep(false);
		}
	}
	
	/**
	 * Set a new value to the asleep flag and set the representative char accordingly
	 * @param b new boolean
	 */
	private void setAsleep(boolean b) {
		if(b) {
			set_char('g');
			asleep = true;
		} else {
			set_char('G');
			asleep = false;
		}
	}
	
	
	/**
	 * Change the guard movement if he awakes (20% chance) 
	 */
	private void changeMovement() {
		Random randomGenerator = new Random();
		int option = randomGenerator.nextInt(11);
		if (asleep == true && option < 3) {
			if (reverse == false)
				reverse = true;
			else
				reverse = false;
		}
	}
	

}
