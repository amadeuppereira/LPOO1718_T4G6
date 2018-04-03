package dkeep.logic;

import java.util.Random;

public class Rookie extends Guard {

	public Rookie(int x, int y, char[] mov) {
		super(x, y, mov);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		normal_movement();
	}


}
