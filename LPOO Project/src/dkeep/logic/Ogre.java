package dkeep.logic;
import dkeep.logic.Characters;

public class Ogre extends Characters {


	int club_x;
	int club_y;
	char ch_club;
	
	boolean ogreOnKey; //true if the Ogre is above the key
	boolean clubOnKey; //true if the club is above the key
	
	
	public Ogre(int x, int y, int x1, int x2) {
		
		super(x, y, 'O');
		ogreOnKey = false;
		
		club_x = x1;
		club_y = x2;
		ch_club = '*';
		clubOnKey = false;
	}
	
	
	public void club_up() {
		club_x = x - 1;
		club_y = y;
	}

	public void club_down() {
		club_x = x + 1;
		club_y = y;
	}

	public void club_left() {
		club_y = y - 1;
		club_x = x;
	}

	public void club_right() {
		club_y = y + 1;
		club_x = x;
	}
	
	public void ogre_set_key(boolean b) {
		ogreOnKey = b;
		
		if(b) {
			ch = '$';
		}
		else {
			ch = 'O';
		}
	}
	
	public void club_set_key(boolean b) {
		clubOnKey = b;
		
		if(b) {
			ch_club = '$';
		}
		else {
			ch_club = '*';
		}
	}
	
}
