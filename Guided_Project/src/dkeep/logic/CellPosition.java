package dkeep.logic;

import java.util.Random;

import dkeep.logic.GameState.Movement;

/**
 * CellPosition.java - a class that handles the actions envolving characters positions
 */
public class CellPosition {
	
	private int x;
	private int y;
	
	/**
	 * Create a new CellPosition object
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public CellPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Retrieve the x coordinate
	 * @return x coordinate
	 */
	public int get_positionX() {
		return x;
	}
	
	/**
	 * Retrieve the y coordinate
	 * @return y y coordinate
	 */
	public int get_positionY() {
		return y;
	}
	
	/**
	 * Set x coordinate
	 * @param x new x coordinate
	 */
	public void set_positionX(int x) {
		this.x = x; 
	}
	
	/**
	 * Set y coordinate
	 * @param y new y coordinate
	 */
	public void set_positionY(int y) {
		this.y = y; 
	}
	
	/**
	 * Tell if the given object is at the same position
	 * @param obj object to check
	 * @return true if the objects are at same position, false otherwise
	 */
	public boolean equals(CellPosition obj) {
		return (this.x == obj.get_positionX() && this.y == obj.get_positionY());
	}
	
	/**
	 * Tell if the given object is at an adjacent position
	 * @param obj object to check
	 * @return true if the object is at an adjacent position, false otherwise
	 */
	public boolean checkNear(CellPosition obj) {
		int dx = Math.abs(this.x - obj.get_positionX());
		int dy = Math.abs(this.y - obj.get_positionY());
		if((dy != 0 && dy != 1) || (dx != 0 && dx != 1)) return false;
		if(dx == 0 & dy == 0) return true;
		if(dy != dx) return true;
		return false;
	}
	
	/**
	 * Returns a new object at an adjacent position
	 * @return the new object
	 */
	public CellPosition getRandomAdjPos() {
		Random randomGenerator = new Random();
		int option = randomGenerator.nextInt(4);
		switch(option) {
		case 0:
			return new CellPosition(x - 1, y);
		case 1:
			return new CellPosition(x, y - 1);
		case 2:
			return new CellPosition(x + 1, y);
		case 3:
			return new CellPosition(x , y + 1);
		default:
			return null;
		}
	}
	
	/**
	 * Analysis about the movement made between two objects
	 * @param newPos object to check
	 * @return the movement from an object to the given one
	 */
	public Movement analyseMovement(CellPosition newPos) {
		int dx = newPos.get_positionX() - x;
		int dy = newPos.get_positionY() - y;
		if(dy == 0) {
			if(dx > 0) {
				return Movement.DOWN;
			} else return Movement.UP;
		}
		else {
			if (dy > 0) {
				return Movement.RIGHT;
			} else return Movement.LEFT;
		}
	}
}
