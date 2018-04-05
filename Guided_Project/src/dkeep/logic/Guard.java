package dkeep.logic;
import dkeep.logic.Characters;
import java.util.Random;

/**
 * Guard.java - an abstract class that handles all types of guards 
 */
public abstract class Guard extends Characters {

	char[] movement;
	int it;
	protected boolean asleep;
	protected boolean reverse;
	
	
	/**
	 * Create a new Guard object
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param mov the movement that the guard follows
	 */
	public Guard(int x, int y, char[] mov) {
		super(x, y, 'G');
		
		movement = mov;
		asleep = false;
		it = 0;
	} 

	/**
	 * Get the value of the reverse movement flag
	 * @return reverse movement flag value
	 */
	public boolean isReverse() {
		return reverse;
	}
	
	/**
	 * Get the value of the asleep flag
	 * @return asleep flag value
	 */
	public boolean isAsleep() {
		return asleep;
	}

	/**
	 * Move the guard in a reverse order
	 */
	public void reverse_movement() {
		if(it == 0) it = movement.length - 1;
		else it--;
		switch (movement[it]) {
		case 'w':
			move_down();
			break;
		case 'a':
			move_right();
			break;
		case 's':
			move_up();
			break;
		case 'd':
			move_left();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Move the guard in a normal order
	 */
	public void normal_movement() {
		switch (movement[it]) {
		case 'w':
			move_up();
			break;
		case 'a':
			move_left();
			break;
		case 's':
			move_down();
			break;
		case 'd':
			move_right();
			break;
		default:
			break;
		}
		it++;
		if (it == movement.length) it = 0;
	}
	
	
	/**
	 * Move the guard
	 */
	public abstract void move();
	

}
