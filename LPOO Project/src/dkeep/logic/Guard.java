package dkeep.logic;

public class Guard {

	char[] movement;
	int it;
	
	int x;
	int y;
	
	public Guard(int x, int y, char[] mov) {
		movement = mov;
		it = 0;
		
		this.x = x;
		this.y = y;
		
	}
	
	
	public void move() {

		switch (movement[it]) {
		case 'w':
			x -= 1;
			break;
		case 'a':
			y -= 1;
			break;
		case 's':
			x += 1;
			break;
		case 'd':
			y += 1;
			break;
		default:
			break;
		}
		it++;
		if (it == movement.length)
			it = 0;

	}
	
}
