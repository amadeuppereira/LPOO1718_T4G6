package dkeep.logic;

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
		
		if((dy != 0 && dy != 1) || (dx != 0 && dy != 1)) return false;
		
		if(dy != dx) return true;
		
		return false;
	}
}
