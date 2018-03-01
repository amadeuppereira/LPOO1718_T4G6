package dkeep.logic;

public class Characters {

	protected CellPosition cell_position;
	protected char ch;
	
	public Characters(int x, int y, char ch) {
		cell_position = new CellPosition(x,y);
		this.ch = ch;
	}
	
	public void move(int x, int y) {
		cell_position.set_positionX(x);
		cell_position.set_positionX(y);
	}
	
	public void move_up() {
		cell_position.set_positionX(cell_position.get_positionX() - 1);
	}
	
	public void move_down() {
		cell_position.set_positionX(cell_position.get_positionX() + 1);
	}
	
	public void move_left() {
		cell_position.set_positionY(cell_position.get_positionY() - 1);
	}
	
	public void move_right() {
		cell_position.set_positionY(cell_position.get_positionY() + 1);
	}
	
	public void set_char(char ch) {
		this.ch = ch;
	}
	
	public char get_char() {
		return ch;
	}
	
	public int get_X() {
		return cell_position.get_positionX();
	}
	
	public int get_Y() {
		return cell_position.get_positionY();
	}
	
	public void set_X(int x) {
		cell_position.set_positionX(x);
	}
	
	public void set_Y(int y) {
		cell_position.set_positionY(y);
	}
	
	public CellPosition getPosition() {
		return cell_position;
	}

}
