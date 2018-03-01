package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Characters;
import dkeep.logic.GameState;
import dkeep.logic.GameLevel;
import dkeep.cli.UserInput;
import java.util.Scanner;

public class TestDungeonGameLogic {
	
	char [][] map = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		GameLevel game_level = new GameLevel(map);
		GameState game_state = new GameState(game_level);
		assertTrue(game_state.check_hero(1, 1));
		Scanner s = new Scanner("s");
		UserInput user_input = new UserInput(s);
		user_input.readInput(game_state);
		assertTrue(game_state.check_hero(2, 1));
	}
	
	@Test
	public void testHeroIsCaptureByGuard() {
		GameLevel game_level = new GameLevel(map);
	}
}
