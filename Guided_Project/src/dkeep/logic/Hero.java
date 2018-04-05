package dkeep.logic;
import dkeep.logic.Characters;

/**
 * Hero.java - a class that handles the hero
 */
public class Hero extends Characters{
	
	boolean hasKey; //true if the hero has the key
	boolean armed;
	
	/**
	 * Create a new Hero object
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Hero(int x, int y) {
		super(x, y, 'H');
		hasKey = false;
		armed = false;
	}
	
	/**
	 * Set a new value to the has_key flag and update the representative char accordingly
	 * @param b new value
	 */
	public void set_key(boolean b) {
		hasKey = b;
		if(b) {
			ch = 'K';
		}
		else {
			if(armed) {
				ch = 'A';
			}
			else {
				ch = 'H';
			}
		}
	}
	
	/**
	 * Get the has_key flag value
	 * @return has_key flag value
	 */
	public boolean check_key() {
		return hasKey;
	}
	
	/**
	 * Set a new value to the armed flag and update the representative char accordingly
	 * @param b new value
	 */
	public void armed(boolean b) {
		armed = b;
		if(b) {
			ch = 'A';
		}
		else {
			ch = 'H';
		}
	}
	
	/**
	 * Get armed flag value
	 * @return armed flag value
	 */
	public boolean has_arm() {
		return armed;
	}
	
}
