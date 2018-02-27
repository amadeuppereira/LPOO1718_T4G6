import dkeep.logic.Game_State;
import dkeep.cli.*;

public class Game {

	
	public static void main(String[] args) {
			
		Game_State game = new Game_State();
		User_Output user_output = new User_Output();
		User_Input user_input = new User_Input();
		
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
		
	}
}
