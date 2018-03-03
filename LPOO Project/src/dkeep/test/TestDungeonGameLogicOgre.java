package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.UserInput;
import dkeep.logic.CellPosition;
import dkeep.logic.GameLevel;
import dkeep.logic.GameState;
import dkeep.logic.GameState.State;
import dkeep.logic.Ogre;

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
		UserInput user_input = new UserInput();
		Ogre ogre = game.get_ogres().get(0);
		
		int x = 1;
		int y = 3;
		assertTrue(game.check_ogre(x, y, ogre));
		
		int it = 0;
		while(user_input.readInput(game) && it < 5) {
			if(game.check_ogre(x - 1, y, ogre)) {
				x--;
			}
			else if (game.check_ogre(x + 1, y, ogre)) {
				x++;
			}
			else if (game.check_ogre(x, y - 1, ogre)) {
				y--;
			}
			else if (game.check_ogre(x, y + 1, ogre)) {
				y++;
			}
			else {
				fail("Error moving ogre!");
			}
			assertNotEquals("X", game_level.getChar(x, y));
			it++;
		}
		
	}
	
	@Test
	public void testOgreClubRandomMovement() {
		GameLevel game_level = new GameLevel(map, lever);
		GameState game = new GameState(game_level);
		UserInput user_input = new UserInput();
		Ogre ogre = game.get_ogres().get(0);
		
		int ogre_x = 1;
		int ogre_y = 3;
		assertTrue(game.check_ogre(ogre_x, ogre_y, ogre));
		int club_x = 2;
		int club_y = 3;
		assertTrue(game.check_ogre_club(club_x, club_y, ogre));
		
		int it = 0;
		while(user_input.readInput(game) && it < 5) {
			ogre_x = ogre.get_X();
			ogre_y = ogre.get_Y();
			
			if(game.check_ogre_club(ogre_x - 1, ogre_y, ogre)) {
				club_x = ogre_x - 1;
				club_y = ogre_y;
			}
			else if(game.check_ogre_club(ogre_x + 1, ogre_y, ogre)) {
				club_x = ogre_x + 1;
				club_y = ogre_y;
			}
			else if(game.check_ogre_club(ogre_x, ogre_y - 1, ogre)) {
				club_x = ogre_x;
				club_y = ogre_y - 1;
			}
			else if(game.check_ogre_club(ogre_x, ogre_y + 1, ogre)) {
				club_x = ogre_x;
				club_y = ogre_y + 1;
			}
			else {
				fail("Club moving error!");
			}
			
			assertNotEquals("X", game_level.getChar(club_x, club_y));
			it++;
		}
		
	}
}
