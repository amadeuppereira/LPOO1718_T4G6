package dkeep.cli;
import java.util.Scanner;

import dkeep.logic.GameState;

public class UserInput {

	private Scanner s;
	
	public UserInput() {
		s = new Scanner(System.in);
	}
	
	public UserInput(Scanner scan) {
		s = scan;
	}
	
	public void readInput(GameState game) {
		System.out.print("\nEnter direction: ");
		
		char option = s.next().charAt(0);
		
		switch(option) {
		case 'w': 
			game.nextMove("up");
			break;
		case 'a':
			game.nextMove("left");
			break;
		case 's':
			game.nextMove("down");
			break;
		case 'd':
			game.nextMove("right");
			break;
		default:
			break;
				
		}
	}
	
	public void close_scanner() {
		s.close();
	}
}
