package dkeep.logic;

public class Ogre {

	int ogre_x;
	int ogre_y;
	int club_x;
	int club_y;
	
	char ch;
	char ch_club;
	
	boolean ogreOnKey; //true if the Ogre is above the key
	boolean clubOnKey; //true if the club is above the key
	
	
	public Ogre(int x, int y, int x1, int x2) {
		ogre_x = x;
		ogre_y = y;
		ch = 'O';
		ogreOnKey = false;
		
		club_x = x1;
		club_y = x2;
		ch_club = '*';
		clubOnKey = false;
	}
	
	public void ogre_up() {
		ogre_x--;
	}

	public void ogre_down() {
		ogre_x++;
	}

	public void ogre_left() {
		ogre_y--;
	}

	public void ogre_right() {
		ogre_y++;
	}
	
	public void club_up() {
		club_x = ogre_x - 1;
		club_y = ogre_y;
	}

	public void club_down() {
		club_x = ogre_x + 1;
		club_y = ogre_y;
	}

	public void club_left() {
		club_y = ogre_y - 1;
		club_x = ogre_x;
	}

	public void club_right() {
		club_y = ogre_y + 1;
		club_x = ogre_x;
	}
	
	public void move(int x, int y, int x1, int y1) {
		this.ogre_x = x;
		this.ogre_y = y;
		this.club_x = x1;
		this.club_y = y1;
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
