package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.GameLevel;
import dkeep.logic.GameState;
import dkeep.logic.Ogre;

/**
 * TestDungeoGameLogicOgre.java - class testing the ogre
 */
public class TestDungeonGameLogicOgre {

	char [][] map = {{'X','X','X','X','X'},
					 {'X','H',' ',' ','X'},
					 {'I',' ','O',' ','X'},
					 {'I','k',' ',' ','X'},
					 {'X','X','X','X','X'}};
	
	boolean lever = false;
	
	/**
	 * Test ogre random movement
	 */
	@Test
	public void testOgreRandomMovement() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		Ogre ogre = game.get_ogres().get(0);
		
		int x = 2;
		int y = 2;
		assertTrue(game.check_ogre(x, y, ogre));
		
		boolean up = false, down = false, left = false, right = false; 
		
		int it = 0;
		while(it < 100) {
			game.move_Ogre(ogre);
			
			if(game.check_ogre(x - 1, y, ogre)) {
				x--;
				up = true;
			}
			else if (game.check_ogre(x + 1, y, ogre)) {
				x++;
				down = true;
			}
			else if (game.check_ogre(x, y - 1, ogre)) {
				y--;
				left = true;
			}
			else if (game.check_ogre(x, y + 1, ogre)) {
				y++;
				right = true;
			}
			else {
				fail("Error moving ogre!");
			}
			
			assertNotEquals("X", game_level.getChar(x, y));
			assertNotEquals("I", game_level.getChar(x, y));
			
			if(up && down && left && right) {
				break;
			}
			it++;
		}
		
	}
	
	/**
	 * Test club random movement
	 */
	@Test
	public void testOgreClubRandomMovement() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		Ogre ogre = game.get_ogres().get(0);
		
		int ogre_x = 2;
		int ogre_y = 2;
		
		boolean up = false, down = false, left = false, right = false; 
		
		int it = 0;
		while(it < 100) {
		
			if(game.check_ogre_club(ogre_x - 1, ogre_y, ogre)) {
				up = true;
			}
			else if(game.check_ogre_club(ogre_x + 1, ogre_y, ogre)) {
				down = true;
			}
			else if(game.check_ogre_club(ogre_x, ogre_y - 1, ogre)) {
				left = true;
			}
			else if(game.check_ogre_club(ogre_x, ogre_y + 1, ogre)) {
				right = true;
			}
			else {
				fail("Club moving error!");
			}
			
			if(up && down && left && right) {
				break;
			}
			it++;
		}
		
	}
	
	/**
	 * Test club movement
	 */
	@Test
	public void testOgresClubMovement() {
		Ogre ogre = new Ogre(0,0);
		assertEquals(1,ogre.getClub_position().get_positionX());
		assertEquals(0,ogre.getClub_position().get_positionY());
		ogre.club_down();
		assertEquals(1,ogre.getClub_position().get_positionX());
		assertEquals(0,ogre.getClub_position().get_positionY());
		ogre.club_right();
		assertEquals(0,ogre.getClub_position().get_positionX());
		assertEquals(1,ogre.getClub_position().get_positionY());
		ogre.club_left();
		assertEquals(0,ogre.getClub_position().get_positionX());
		assertEquals(-1,ogre.getClub_position().get_positionY());
		ogre.club_up();
		assertEquals(-1,ogre.getClub_position().get_positionX());
		assertEquals(0,ogre.getClub_position().get_positionY());
	}
	
	/**
	 * Test ogre an club representative char
	 */
	@Test
	public void testOgresAndClubChar() {
		Ogre ogre = new Ogre(1,1);
		assertEquals('O',ogre.get_char());
		ogre.stunned(true);
		assertEquals('8',ogre.get_char());
		ogre.reduce_stun();
		assertEquals('8',ogre.get_char());
		ogre.reduce_stun();
		ogre.reduce_stun();
		assertEquals('O',ogre.get_char());
		assertEquals('*',ogre.getClubChar());
		ogre.club_set_key(true);
		assertEquals('$',ogre.getClubChar());
	}
}
