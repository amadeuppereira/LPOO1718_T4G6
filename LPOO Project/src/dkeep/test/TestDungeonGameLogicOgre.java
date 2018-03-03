package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.GameLevel;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;

public class TestDungeonGameLogicOgre {

	char [][] map = {{'X','X','X','X','X'},
			{'X','H',' ','O','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}};
	
	boolean lever = false;
	
	@Test
	public void testOgreRandomMovement() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
	}
}
