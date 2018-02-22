package dkeep.logic;

public class Hero {

	int x;
	int y;
	
	boolean hasKey; //true if the hero has the key
	
	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		hasKey = false;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public void set_key(boolean b) {
		hasKey = b;
	}
	
	public boolean check_key() {
		return hasKey;
	}
	
}
