package dkeep.cli;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;;

/**
 * Game.jave - main class for playing in the console 
 */
public class Game {
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
			
		GameState game = new GameState();
		UserOutput user_output = new UserOutput();
		UserInput user_input = new UserInput();
		
		while (game.get_status() == State.PLAYING) {
			user_output.printGame(game.getGameString());
			user_input.readInput(game);
		}
	
		
		if(game.get_status() == State.DEFEAT) {
			user_output.printGame(game.getGameString());
			user_output.perdeu();
		}
		else
			user_output.ganhou();
		
		user_input.close_scanner();
	}
}
