package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.GameState;
import dkeep.logic.GameState.State;
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
		GameState game = new GameState(game_level);
		assertTrue(game.check_hero(1, 1));
//		Scanner s = new Scanner("s");
//		UserInput user_input = new UserInput(s);
//		user_input.readInput(game);
		game.move_Hero("down");
		assertTrue(game.check_hero(2, 1));
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		GameLevel game_level = new GameLevel(map);
		GameState game = new GameState(game_level);
		assertTrue(game.check_hero(1, 1));
//		Scanner s = new Scanner("a");
//		UserInput user_input = new UserInput(s);
//		user_input.readInput(game);
		game.move_Hero("left");
		assertTrue(game.check_hero(1, 1));
	}
	
	@Test
	public void testHeroIsCaptureByGuard() {
		GameLevel game_level = new GameLevel(map);
		GameState game = new GameState(game_level);
		assertFalse(game.isGameover());
//		Scanner s = new Scanner("d");
//		UserInput user_input = new UserInput(s);
//		user_input.readInput(game);
		game.move_Hero("right");
		assertTrue(game.isGameover());
		assertEquals(State.DEFEAT, game.get_status());
	}
	
	@Test
	public void testHeroFailsToLeave() {
		GameLevel game_level = new GameLevel(map);
		GameState game = new GameState(game_level);
		game.move_Hero("down");
		assertTrue(game.check_hero(2, 1));
		game.move_Hero("left");
		assertTrue(game.check_hero(2, 1));
	}
	
	@Test
	public void testHeroMovesIntoLeverCell() {
		GameLevel game_level = new GameLevel(map);
		GameState game = new GameState(game_level);
		game.move_Hero("down");
		assertTrue(game.check_hero(2, 1));
		game.move_Hero("down");
		assertTrue(game.check_hero(3, 1));
		assertEquals('S', game_level.getChar(2, 0));
		assertEquals('S', game_level.getChar(3, 0));
	}
	
	@Test
	public void testHeroSuccessToLeave() {
		GameLevel game_level = new GameLevel(map);
		GameState game = new GameState(game_level);
		game.move_Hero("down");
		assertTrue(game.check_hero(2, 1));
		game.move_Hero("down");
		assertTrue(game.check_hero(3, 1));
		assertEquals('S', game_level.getChar(2, 0));
		assertEquals('S', game_level.getChar(3, 0));
		game.move_Hero("left");
		assertTrue(game.check_hero(3, 0));
		assertTrue(game.isLevelEnd());
	}
}
