package dkeep.logic;

public class Characters {

	int x;
	int y;
	char ch;
	
	public Characters(int x, int y, char ch) {
		this.x = x;
		this.y = y;
		this.ch = ch;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move_up() {
		x--;
	}
	
	public void move_down() {
		x++;
	}
	
	public void move_left() {
		y--;
	}
	
	public void move_right() {
		y++;
	}
	
	public void set_char(char ch) {
		this.ch = ch;
	}
	
	public char get_char() {
		return ch;
	}

}
