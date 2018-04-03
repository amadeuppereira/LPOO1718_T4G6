package dkeep.logic;
import dkeep.logic.Characters;

public class Ogre extends Characters {

	CellPosition club_position;
	char ch_club;
	
	boolean ogreOnKey; //true if the Ogre is above the key
	boolean clubOnKey; //true if the club is above the key
	boolean stun;
	int stun_timer;
	
	
	public Ogre(int x, int y) {
		
		super(x, y, 'O');
		ogreOnKey = false;
		stun = false;
		stun_timer = 0;
		
		club_position = new CellPosition(x + 1,y);
		ch_club = '*';
		clubOnKey = false;
	}
	

	public CellPosition getClub_position() {
		return club_position;
	}


	public char getClubChar() {
		return ch_club;
	}


	public void club_up() {
		club_position.set_positionX(cell_position.get_positionX() - 1);
		club_position.set_positionY(cell_position.get_positionY());
	}

	public void club_down() {
		club_position.set_positionX(cell_position.get_positionX() + 1);
		club_position.set_positionY(cell_position.get_positionY());
	}

	public void club_left() {
		club_position.set_positionX(cell_position.get_positionX());
		club_position.set_positionY(cell_position.get_positionY() - 1);
	}

	public void club_right() {
		club_position.set_positionX(cell_position.get_positionX());
		club_position.set_positionY(cell_position.get_positionY() + 1);
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
	
	public void reduce_stun() {
		stun_timer--;
		if(stun_timer == 0) {
			stunned(false);
		}
	}
	
	public int get_club_X() {
		return club_position.get_positionX();
	}
	
	public int get_club_Y() {
		return club_position.get_positionY();
	}
	
	public CellPosition clubPos() {
		return club_position;
	}
}
