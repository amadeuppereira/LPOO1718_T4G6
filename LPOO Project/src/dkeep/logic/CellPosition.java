package dkeep.logic;

public class CellPosition {
	
	private int x;
	private int y;
	
	CellPosition(int x, int y){
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
	
	public boolean equals (CellPosition obj) {
		return (this.x == obj.get_positionX() && this.y == obj.get_positionY());
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
