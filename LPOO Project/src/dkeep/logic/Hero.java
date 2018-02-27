package dkeep.logic;
import dkeep.logic.Characters;

public class Hero extends Characters{
	
	boolean hasKey; //true if the hero has the key
	
	public Hero(int x, int y) {
		super(x, y, 'H');
		hasKey = false;
	}
	
	public void set_key(boolean b) {
		hasKey = b;
		if(b) {
			ch = 'K';
		}
		else {
			ch = 'H';
		}
	}
	
	public boolean check_key() {
		return hasKey;
	}
	
}
