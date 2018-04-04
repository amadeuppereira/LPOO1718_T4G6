package dkeep.logic;

import java.util.Random;

public class Drunken extends Guard {
	
	public Drunken(int x, int y, char[] mov) {
		super(x, y, mov);
		reverse = false;
	}

	@Override
	public void move() {
		Random randomGenerator = new Random();
		int option = randomGenerator.nextInt(2);
			
		if (option == 0) { // he will sleep in the same position
			setAsleep(true);
		} else {
			changeMovement();
			if (reverse == false)
				normal_movement();
			else
				reverse_movement();
			setAsleep(false);
		}
	}
	
	private void setAsleep(boolean b) {
		if(b) {
			set_char('g');
			asleep = true;
		} else {
			set_char('G');
			asleep = false;
		}
	}
	
	private void changeMovement() {
		Random randomGenerator = new Random();
		int option = randomGenerator.nextInt(11);
		if (asleep == true && option < 3) {
			if (reverse == false)
				reverse = true;
			else
				reverse = false;
		}
	}
	

}
