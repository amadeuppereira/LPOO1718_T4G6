package dkeep.cli;
import java.util.Scanner;

import dkeep.logic.GameState;
import dkeep.logic.GameState.Movement;

/**
 * UserInput.java - class handling user input 
 */
public class UserInput {

	private Scanner s;
	
	/**
	 * Create a new UserInput object
	 */
	public UserInput() {
		s = new Scanner(System.in);
	}
	
	/**
	 * Create a new UserInput object with a given scanner
	 * @param scan scanner
	 */
	public UserInput(Scanner scan) {
		s = scan;
	}
	
	/**
	 * Read an input and updates the game
	 * @param game game to update
	 * @return true if the game was updated, false otherwise
	 */
	public boolean readInput(GameState game) {
		System.out.print("\nEnter direction: ");
		
		char option = s.next().charAt(0);
		
		switch(option) {
		case 'w': 
			game.nextMove(Movement.UP);
			return true;
		case 'a':
			game.nextMove(Movement.LEFT);
			return true;
		case 's':
			game.nextMove(Movement.DOWN);
			return true;
		case 'd':
			game.nextMove(Movement.RIGHT);
			return true;
		default:
			return false;				
		}
	}
	
	/**
	 * Close the scanner
	 */
	public void close_scanner() {
		s.close();
	}
}
