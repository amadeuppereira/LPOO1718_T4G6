package dkeep.logic;
import dkeep.logic.Characters;

public class Hero extends Characters{
	
	boolean hasKey; //true if the hero has the key
	boolean armed;
	
	public Hero(int x, int y) {
		super(x, y, 'H');
		hasKey = false;
		armed = false;
	}
	
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
	
	public boolean check_key() {
		return hasKey;
	}
	
	public void hero_armed(boolean b) {
		armed = b;
		if(b) {
			ch = 'A';
		}
		else {
			ch = 'H';
		}
	}
	
	public boolean has_arm() {
		return armed;
	}
	
}
