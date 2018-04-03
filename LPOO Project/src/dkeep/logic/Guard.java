package dkeep.logic;
import dkeep.logic.Characters;
import java.util.Random;

public abstract class Guard extends Characters {

	char[] movement;
	int it;
	protected boolean asleep;
	
	
	public Guard(int x, int y, char[] mov) {
		super(x, y, 'G');
		
		movement = mov;
		asleep = false;
		it = 0;
	} 
	
	

	public boolean isAsleep() {
		return asleep;
	}

	public void reverse_movement() {
		if(it == 0)
			it = movement.length - 1;
		else
			it--;
		switch (movement[it]) {
		case 'w':
			move_down();
			break;
		case 'a':
			move_right();
			break;
		case 's':
			move_up();
			break;
		case 'd':
			move_left();
			break;
		default:
			break;
		}
	}
	
	public void normal_movement() {
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
	
	
	public abstract void move();
	

}
