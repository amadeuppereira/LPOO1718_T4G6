package dkeep.logic;

import java.util.Random;

import dkeep.logic.GameState.Movement;

public class CellPosition {
	
	private int x;
	private int y;
	
	public CellPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int get_positionX() {
		return x;
	}
	
	public int get_positionY() {
		return y;
	}
	
	public void set_positionX(int x) {
		this.x = x; 
	}
	
	public void set_positionY(int y) {
		this.y = y; 
	}
	
	public boolean equals(CellPosition obj) {
		return (this.x == obj.get_positionX() && this.y == obj.get_positionY());
	}
	
	public boolean checkNear(CellPosition obj) {
		int dx = Math.abs(this.x - obj.get_positionX());
		int dy = Math.abs(this.y - obj.get_positionY());
		if((dy != 0 && dy != 1) || (dx != 0 && dx != 1)) return false;
		if(dx == 0 & dy == 0) return true;
		if(dy != dx) return true;
		return false;
	}
	
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
