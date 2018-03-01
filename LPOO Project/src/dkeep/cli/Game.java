package dkeep.cli;
import dkeep.logic.GameState;

public class Game {

	
	public static void main(String[] args) {
			
		GameState game = new GameState();
		UserOutput user_output = new UserOutput();
		UserInput user_input = new UserInput();
		
		while (game.get_status() == 0) {
			user_output.printGame(game);
			user_input.readInput(game);
		}
		
		while(!game.win && !game.lose) { //for now reaching lvl 3 is winning the game
			user_output.printGame(game);
			user_input.readInput(game);
		}
		
		if(game.lose) {
			user_output.printGame(game);
			user_output.perdeu();
		}
		else
			user_output.ganhou();
		
		user_input.close_scanner();
	}
}
