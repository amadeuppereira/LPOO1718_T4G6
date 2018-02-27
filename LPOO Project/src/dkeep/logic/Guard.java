package dkeep.logic;
import dkeep.logic.Characters;

public class Guard extends Characters {

	char[] movement;
	int it;
	
	
	public Guard(int x, int y, char[] mov) {
		super(x, y, 'G');
		movement = mov;
		it = 0;
			
	}
	
	
	public void move() {

		switch (movement[it]) {
		case 'w':
			move_up();
			break;
		case 'a':
			move_left();
			break;
		case 's':
			move_down();
			break;
		case 'd':
			move_right();
			break;
		default:
			break;
		}
		it++;
		if (it == movement.length)
			it = 0;

	}
	
}
