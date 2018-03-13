package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.GameState;
import dkeep.logic.GameState.Movement;
import dkeep.logic.GameState.State;
import dkeep.logic.GameLevel;

public class TestDungeonGameLogic {
	
	char [][] map = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};

	boolean lever = true;
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		assertTrue(game.check_hero(1, 1));
		game.move_Hero(Movement.DOWN);
		assertTrue(game.check_hero(2, 1));
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level); 
		assertTrue(game.check_hero(1, 1));
		game.move_Hero(Movement.LEFT);
		assertTrue(game.check_hero(1, 1));
	}
	
	@Test
	public void testHeroIsCaptureByGuard() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		assertFalse(game.isGameover());
		game.move_Hero(Movement.RIGHT);
		assertTrue(game.isGameover());
		assertEquals(State.DEFEAT, game.get_status());
	}
	
	@Test
	public void testHeroFailsToLeave() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		game.move_Hero(Movement.DOWN);
		assertTrue(game.check_hero(2, 1));
		game.move_Hero(Movement.LEFT);
		assertTrue(game.check_hero(2, 1));
	}
	
	@Test
	public void testHeroMovesIntoLeverCell() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		game.move_Hero(Movement.DOWN);
		assertTrue(game.check_hero(2, 1));
		game.move_Hero(Movement.DOWN);
		assertTrue(game.check_hero(3, 1));
		assertEquals('S', game_level.getChar(2, 0));
		assertEquals('S', game_level.getChar(3, 0));
	}
	
	@Test
	public void testHeroSuccessToLeave() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		game.move_Hero(Movement.DOWN);
		assertTrue(game.check_hero(2, 1));
		game.move_Hero(Movement.DOWN);
		assertTrue(game.check_hero(3, 1));
		assertEquals('S', game_level.getChar(2, 0));
		assertEquals('S', game_level.getChar(3, 0));
		game.move_Hero(Movement.LEFT);
		assertTrue(game.check_hero(3, 0));
		assertTrue(game.isLevelEnd());
	}
	
}
