package dkeep.logic;

import java.util.Random;

public class Suspicious extends Guard {

	public Suspicious(int x, int y, char[] mov) {
		super(x, y, mov);
		reverse = false;
	}

	@Override
	public void move() {
		
		changeMovement();
		if (reverse == false)
			normal_movement();
		else
			reverse_movement();
		
	}
	
	private void changeMovement() {
		Random randomGenerator = new Random();
		int option = randomGenerator.nextInt(11);
		if (option < 3) {
			if (reverse == false)
				reverse = true;
			else
				reverse = false;
		}	
	}

}
