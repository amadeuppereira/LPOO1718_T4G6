package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dkeep.logic.GameState;
import dkeep.logic.GameState.Guard_Type;
import dkeep.logic.GameState.Movement;
import dkeep.logic.GameState.State;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Rookie;
import dkeep.logic.Suspicious;
import dkeep.logic.CellPosition;
import dkeep.logic.Drunken;
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
	
	@Test
	public void testCloneMap() {
		GameLevel game_level = new GameLevel(map, lever);
		assertFalse(map == game_level.mapclone(game_level.getMap()));
	}
	
	@Test
	public void testSamePosition() {
		CellPosition cell1 = new CellPosition(0,0);
		CellPosition cell2 = new CellPosition(1,2);
		CellPosition cell3 = new CellPosition(1,2);
		assertFalse(cell1.equals(cell2));
		assertTrue(cell2.equals(cell3));
	}
	
	@Test
	public void testHeroChar() {
		Hero hero = new Hero(0,0);
		assertEquals('H',hero.get_char());
		hero.set_key(true);
		assertEquals('K',hero.get_char());
		hero.armed(true);
		assertEquals('A',hero.get_char());
		assertTrue(hero.has_arm());
		hero.set_key(false);
		assertEquals('A',hero.get_char());
		hero.armed(false);
		assertEquals('H',hero.get_char());
	}
	
	@Test
	public void testGuardMovement() {
		char[] mov = {'a','s','d','w'};
		Guard guard = new Rookie(10,10,mov);
		assertEquals(10,guard.get_X());
		assertEquals(10,guard.get_Y());
		guard.normal_movement();
		assertEquals(10,guard.get_X());
		assertEquals(9,guard.get_Y());
		guard.normal_movement();
		assertEquals(11,guard.get_X());
		assertEquals(9,guard.get_Y());
		guard.normal_movement();
		assertEquals(11,guard.get_X());
		assertEquals(10,guard.get_Y());
		guard.normal_movement();
		assertEquals(10,guard.get_X());
		assertEquals(10,guard.get_Y());
		guard.reverse_movement();
		assertEquals(11,guard.get_X());
		assertEquals(10,guard.get_Y());
		guard.reverse_movement();
		assertEquals(11,guard.get_X());
		assertEquals(9,guard.get_Y());
		guard.reverse_movement();
		assertEquals(10,guard.get_X());
		assertEquals(9,guard.get_Y());
		guard.reverse_movement();
		assertEquals(10,guard.get_X());
		assertEquals(10,guard.get_Y());
	}
	
	@Test
	public void testGuardSleep() {
		char[] mov = {'a','s','d','w'};
		Guard guard = new Drunken(10,10,mov);
		boolean sleep = false;
		boolean awake = false;
		int i = 0;
		while(i < 100) {
			guard.move();
			if(guard.isAsleep()) {
				sleep = true;
			}
			if(sleep && !guard.isAsleep()) {
				awake = true;
				break;
			}
			i++;
		}
		assertTrue(sleep);
		assertTrue(awake);
	}
	
	@Test
	public void testGuardDrunkenReverse() {
		char[] mov = {'a','s','d','w'};
		Guard guard = new Drunken(10,10,mov);
		boolean reverse = false;
		int i = 0;
		while(i < 100) {
			guard.move();
			if(guard.isReverse()) {
				reverse = true;
				break;
			}
			i++;
		}
		assertTrue(reverse);
	}
	
	@Test
	public void testGuardSuspiciousReverse() {
		char[] mov = {'a','s','d','w'};
		Guard guard = new Suspicious(10,10,mov);
		boolean reverse = false;
		int i = 0;
		while(i < 100) {
			guard.move();
			if(guard.isReverse()) {
				reverse = true;
				break;
			}
			i++;
		}
		assertTrue(reverse);
	}
	
	@Test
	public void testGuardTypeMovement() {
		char[] mov = {'a','s','d','w'};
		Guard guard = new Rookie(10,10,mov);
		assertEquals(10,guard.get_X());
		assertEquals(10,guard.get_Y());
		guard.move();
		assertEquals(10,guard.get_X());
		assertEquals(9,guard.get_Y());
		guard.move();
		assertEquals(11,guard.get_X());
		assertEquals(9,guard.get_Y());
		guard = new Drunken(10,10,mov);
		guard.move();
		if(!(guard.get_X() == 10 && guard.get_Y() == 9) && !(guard.get_X() == 11 && guard.get_Y() == 10) && !(guard.get_X() == 10 && guard.get_Y() == 10))
			fail("Error moving Drunken!");
		guard = new Suspicious(10,10,mov);
		guard.move();
		if(!(guard.get_X() == 10 && guard.get_Y() == 9) && !(guard.get_X() == 11 && guard.get_Y() == 10))
			fail("Error moving Suspicious!");
		guard.move();
		if(!(guard.get_X() == 11 && guard.get_Y() == 9) && !(guard.get_X() == 10 && guard.get_Y() == 10))
			fail("Error moving Suspicious!");
	}
	
	@Test
	public void testGameStringAndClone() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		assertEquals(0,game.getLvl());
		char[][] map2 = game.mapclone(game.getGameMap());
		GameLevel game_level2 = new GameLevel(map2,lever);
		GameState game2 = new GameState(game_level2);
		assertEquals(game2.getGameString(),game.getGameString());
	}
	
	@Test
	public void testCharactersMove() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		game.nextMove(Movement.DOWN);
		assertTrue(game.check_hero(2, 1));
		assertTrue(game.check_guard(1, 3));
	}
	
	@Test
	public void testNewGame() {
		GameState game = new GameState(3, "Rookie");
		assertEquals(3,game.getNumOgres());
		assertEquals(Guard_Type.ROOKIE,game.getGuard_type());
		game = new GameState(3, "Drunken");
		assertEquals(Guard_Type.DRUNKEN,game.getGuard_type());
		game = new GameState(3, "Suspicious");
		assertEquals(Guard_Type.SUSPICIOUS,game.getGuard_type());
		game = new GameState();
		if(game.getNumOgres() > 5 || game.getNumOgres()< 1)
			fail("Wrong number of Ogres");
	}
	
	
}
