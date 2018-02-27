package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.Game_State;

public class User_Input {

	public User_Input() {
		
	}
	
	public void readInput(Game_State game) {
		System.out.print("\nEnter direction: ");
		
		Scanner s = new Scanner(System.in);
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
}
