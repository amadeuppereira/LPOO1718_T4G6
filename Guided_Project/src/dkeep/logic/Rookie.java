package dkeep.logic;

import java.util.Random;

/**
 * Rookie.java - A class that handles a specific type of guard (Rookie)
 */
public class Rookie extends Guard {

	/**
	 * Create a new Rookie object
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param mov the movement that guard follows
	 */
	public Rookie(int x, int y, char[] mov) {
		super(x, y, mov);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dkeep.logic.Guard#move()
	 */
	@Override
	public void move() {
		normal_movement();
	}


}
