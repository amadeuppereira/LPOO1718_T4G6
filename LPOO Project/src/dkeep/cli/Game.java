package dkeep.cli;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;;


public class Game {

	
	public static void main(String[] args) {
			
		GameState game = new GameState();
		UserOutput user_output = new UserOutput();
		UserInput user_input = new UserInput();
		
		while (game.get_status() == State.PLAYING) {
			user_output.printGame(game.printGameString());
			user_input.readInput(game);
		}
	
		
		if(game.get_status() == State.DEFEAT) {
			user_output.printGame(game.printGameString());
			user_output.perdeu();
		}
		else
			user_output.ganhou();
		
		user_input.close_scanner();
	}
}
