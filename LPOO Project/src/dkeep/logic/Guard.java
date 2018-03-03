package dkeep.logic;
import dkeep.logic.Characters;
import java.util.Random;

public class Guard extends Characters {

	char[] movement;
	String type;
	int it;
	boolean asleep;
	boolean reverse;
	
	
	public Guard(int x, int y, char[] mov) {
		super(x, y, 'G');
		
		movement = mov;
		String[] guard_type = {"Rookie","Drunken","Suspicious"};
		Random randomGenerator = new Random();
		this.type = guard_type[randomGenerator.nextInt(3)];
		it = 0;
		asleep = false;
		reverse = false;
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
	
	
	public void move() {
		Random randomGenerator = new Random();
		int option;
		
		switch(type) {
		case "Rookie":
			normal_movement();
			break;
			
		case "Drunken":
			option = randomGenerator.nextInt(2);
			if(option == 0) { //he will sleep in the same position
				set_char('g');
				asleep = true;
			}
			else {
				set_char('G');
				option = randomGenerator.nextInt(11);
				if(asleep == true && option < 3) {
					if(reverse == false)
						reverse = true;
					else
						reverse = false;
				}
				if(reverse == false)
					normal_movement();
				else
					reverse_movement();
				asleep = false;
			}
			break;
			
		case "Suspicious":
			option = randomGenerator.nextInt(15);
			if(option < 3) {
				if(reverse == false) {
					reverse = true;
					reverse_movement();
				}
				else {
					reverse = false;
					normal_movement();
				}
			}
			else {
				if(reverse == false) {
					normal_movement();
				}
				else {
					reverse_movement();
				}
			}
			break;
		default:
			break;	
		}

	}	
	
}
