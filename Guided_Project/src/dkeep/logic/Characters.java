package dkeep.logic;

/**
 * Characters.java - a class that handles everything in common between every character
 */
public class Characters {

	protected CellPosition cell_position;
	protected char ch;
	
	/**
	 * Create a new Character object
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param ch representative char
	 */
	public Characters(int x, int y, char ch) {
		cell_position = new CellPosition(x,y);
		this.ch = ch;
	}
	
	/**
	 * Move the character to a given position
	 * @param x new x
	 * @param y new y
	 */
	public void move(int x, int y) {
		cell_position.set_positionX(x);
		cell_position.set_positionY(y);
	}
	
	/**
	 * Move the character one position up
	 */
	public void move_up() {
		cell_position.set_positionX(cell_position.get_positionX() - 1);
	}
	
	/**
	 * Move the character one position down
	 */
	public void move_down() {
		cell_position.set_positionX(cell_position.get_positionX() + 1);
	}
	
	/**
	 * Move the character one position left
	 */
	public void move_left() {
		cell_position.set_positionY(cell_position.get_positionY() - 1);
	}
	
	/**
	 * Move the character one position right
	 */
	public void move_right() {
		cell_position.set_positionY(cell_position.get_positionY() + 1);
	}
	
	/**
	 * Set a new representative char
	 * @param new char
	 */
	public void set_char(char ch) {
		this.ch = ch;
	}
	
	/**
	 * Get the representative char
	 * @return representative char
	 */
	public char get_char() {
		return ch;
	}
	
	/**
	 * Get x coordinate
	 * @return x coordinate
	 */
	public int get_X() {
		return cell_position.get_positionX();
	}
	
	/**
	 * Get y coordinate
	 * @return y coordinate
	 */
	public int get_Y() {
		return cell_position.get_positionY();
	}
	
	/**
	 * Set a new x coordinate
	 * @param x new x coordinate
	 */
	public void set_X(int x) {
		cell_position.set_positionX(x);
	}
	
	/**
	 * Set a new y coordinate
	 * @param y new y coordinate
	 */
	public void set_Y(int y) {
		cell_position.set_positionY(y);
	}
	
	/**
	 * Get the CellPosition object
	 * @return CellPosition object
	 */
	public CellPosition getPosition() {
		return cell_position;
	}
	
	/**
	 * Check if a given position is adjacent
	 * @param pos position to check
	 * @return true if adjacent, false otherwise
	 */
	public boolean checkNear(CellPosition pos) {
		return this.cell_position.checkNear(pos);
	}

}
