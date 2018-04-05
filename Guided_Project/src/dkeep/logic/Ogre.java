package dkeep.logic;
import dkeep.logic.Characters;

/**
 * Ogre.java - a class that handles an ogre
 */
/**
 * @author amade
 *
 */
/**
 * @author amade
 *
 */
public class Ogre extends Characters {

	CellPosition club_position;
	char ch_club;
	
	boolean ogreOnKey; //true if the Ogre is above the key
	boolean clubOnKey; //true if the club is above the key
	boolean stun;
	int stun_timer;
	
	
	/**
	 * Create a new Ogre object
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Ogre(int x, int y) {
		
		super(x, y, 'O');
		ogreOnKey = false;
		stun = false;
		stun_timer = 0;
		
		club_position = new CellPosition(x + 1,y);
		ch_club = '*';
		clubOnKey = false;
	}
	
	/**
	 * Get the CellPosition object
	 * @return CellPosition object
	 */
	public CellPosition getClub_position() {
		return club_position;
	}

	/**
	 * Get the club's representative char
	 * @return club's representative char
	 */
	public char getClubChar() {
		return ch_club;
	}

	/**
	 * Moves the club one position up from the ogre
	 */
	public void club_up() {
		club_position.set_positionX(cell_position.get_positionX() - 1);
		club_position.set_positionY(cell_position.get_positionY());
	}

	/**
	 * Moves the club one position down from the ogre
	 */
	public void club_down() {
		club_position.set_positionX(cell_position.get_positionX() + 1);
		club_position.set_positionY(cell_position.get_positionY());
	}

	/**
	 * Moves the club one position left from the ogre
	 */
	public void club_left() {
		club_position.set_positionX(cell_position.get_positionX());
		club_position.set_positionY(cell_position.get_positionY() - 1);
	}

	/**
	 * Moves the club one position right from the ogre
	 */
	public void club_right() {
		club_position.set_positionX(cell_position.get_positionX());
		club_position.set_positionY(cell_position.get_positionY() + 1);
	}
	
	/**
	 * Set a new value to the on_key flag of the ogre and changes the representative char accordingly
	 * @param b new value
	 */
	public void ogre_set_key(boolean b) {
		ogreOnKey = b;
		
		if(b) {
			ch = '$';
		}
		else {
			ch = 'O';
		}
	}
	
	/**
	 * Set a new value to the on_key flag for the club the and changes the representative char accordingly
	 * @param b new value
	 */
	public void club_set_key(boolean b) {
		clubOnKey = b;
		
		if(b) {
			ch_club = '$';
		}
		else {
			ch_club = '*';
		}
	}
	
	/**
	 * Set a new value to the stunned flag of the ogre  
	 * @param b new value
	 */
	public void stunned(boolean b) {
		stun = b;
		stun_timer = 3;
		
		if(b) {
			ch = '8';
		}
		else {
			ch = 'O';
		}
	}
	
	
	/**
	 * Reduce the stun timer by one
	 */
	public void reduce_stun() {
		stun_timer--;
		if(stun_timer == 0) {
			stunned(false);
		}
	}
	
	/**
	 * Get club's x position
	 * @return x position
	 */
	public int get_club_X() {
		return club_position.get_positionX();
	}
	
	
	/**
	 * Get club's y position
	 * @return y position
	 */
	public int get_club_Y() {
		return club_position.get_positionY();
	}
	
	/**
	 * Get club's CellPosition object
	 * @return club's position
	 */
	public CellPosition clubPos() {
		return club_position;
	}
	
	/**
	 * Update stun timer
	 * @return true if ogre is stunned, false otherwise
	 */
	public boolean updateStunTime() {
		if(stun) {
			reduce_stun();
			return true;
		}
		return false;
	}
}
