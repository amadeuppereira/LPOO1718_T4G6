package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.GameState;
import dkeep.logic.GameState.State;
import dkeep.logic.GameLevel;
import dkeep.cli.UserInput;
import java.util.Scanner;

public class TestDungeonGameLogicKeep {

	char [][] map = {{'X','X','X','X','X'},
			{'X','H',' ','O','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}};
	
	boolean lever = false;
	
	@Test
	public void testHeroIsCaptureByOgre() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		assertFalse(game.isGameover());
		game.move_Hero("right");
		assertTrue(game.isGameover());
		assertEquals(State.DEFEAT, game.get_status());
	}
	
	@Test
	public void testHeroGetsKey() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		assertEquals('H', game.get_hero_char());
		game.move_Hero("down");
		game.move_Hero("down");
		assertTrue(game.check_hero(3, 1));
		assertEquals('K', game.get_hero_char());
	}
	
	@Test
	public void testHeroFailsToOpenDoor() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		assertEquals('H', game.get_hero_char());
		game.move_Hero("down");
		assertTrue(game.check_hero(2, 1));
		game.move_Hero("left");
		assertTrue(game.check_hero(2, 1));
		assertEquals('I', game_level.getChar(2, 0));
	}
	
	@Test
	public void testHeroSuccessToOpenDoor() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		assertEquals('H', game.get_hero_char());
		game.move_Hero("down");
		game.move_Hero("down");
		assertTrue(game.check_hero(3, 1));
		assertEquals('K', game.get_hero_char());
		assertEquals('I', game_level.getChar(3, 0));
		game.move_Hero("left");
		assertTrue(game.check_hero(3, 1));
		assertEquals('S', game_level.getChar(3, 0));
	}
	
	@Test
	public void testHeroSuccessToExitDoor() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		game.move_Hero("down");
		game.move_Hero("down");
		game.move_Hero("left");
		game.move_Hero("left");
		assertTrue(game.check_hero(3, 0));
		assertTrue(game.isLevelEnd());
	}
	
	

}
