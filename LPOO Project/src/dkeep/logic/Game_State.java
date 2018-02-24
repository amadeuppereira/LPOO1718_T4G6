package dkeep.logic;
import java.util.Random;
import dkeep.logic.*;

public class Game_State {
	
	Hero hero;
	Game_Level game_level;
	Guard guard;
	Ogre ogre;
	
	public boolean win;
	public boolean lose;
	
	public Game_State() {
		win = false;
		lose = false;
		create_Level(1);
	}
	
	public int get_status() {
		if(win) {
			return 1;
		}
		if(lose) {
			return 2;
		}
		
		return 0;
	}
	
	public void create_Level(int n) {
		switch(n) {
		
		case 1: 
			hero = new Hero(1, 1);
			game_level = new Game_Level(1);
			
			char[] guard_mov = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
			guard = new Guard(1, 8, guard_mov);
			break;
		
		case 2:
			hero = new Hero(8, 1);
			game_level = new Game_Level(2);
			
			ogre = new Ogre(1, 4, 2, 4);
			break;
		
		default:
			win = true;
			break;
		}
			
	}
	
	public boolean move_Hero(int x, int y) {
		
		char ch = game_level.getChar(x, y);
		if(ch == ' ') {
			hero.move(x, y);
			return true;
		}
		else if(ch == 'k') {
			hero.move(x, y);
			hero.set_key(true);
			return true;
		}
		else if(ch == 'I' && hero.check_key()) {
			game_level.setChar(x, y, 'S');
			return true;
		}
		else if(ch == 'S') {
			hero.move(x, y);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean check_Level_End() {
		if (hero.x == 0 || hero.y == 0) {
			return true;
		}
		
		return false;
	}
	
	public void move_Ogre() {
		boolean moved = false;
		Random randomGenerator = new Random();
		int option;
		
		while(moved == false) {
			option = randomGenerator.nextInt(4); //generating a random number between 0 and 3;
			switch(option) {
			case 0: //w
				if(game_level.getChar(ogre.ogre_x - 1, ogre.ogre_y) == ' ') {
					ogre.ogre_up();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x-1, ogre.ogre_y) == 'k') {
					ogre.ogre_set_key(true);
					ogre.ogre_up();
					moved = true;
				}
				break;
			case 1: //a
				if(game_level.getChar(ogre.ogre_x, ogre.ogre_y - 1) == ' ') {
					ogre.ogre_left();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x, ogre.ogre_y - 1) == 'k') {
					ogre.ogre_set_key(true);
					ogre.ogre_left();
					moved = true;
				}
				break;
			case 2: //s
				if(game_level.getChar(ogre.ogre_x+1, ogre.ogre_y) == ' ') {
					ogre.ogre_down();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x+1, ogre.ogre_y) == 'k') {
					ogre.ogre_set_key(true);
					ogre.ogre_down();
					moved = true;
				}
				break;
			case 3: //d
				if(game_level.getChar(ogre.ogre_x, ogre.ogre_y + 1) == ' ') {
					ogre.ogre_right();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x, ogre.ogre_y + 1) == 'k') {
					ogre.ogre_set_key(true);
					ogre.ogre_right();
					moved = true;
				}
				break;
			}
		}
		
		moved = false;
		while(moved == false) {
			option = randomGenerator.nextInt(4); //generating a random number between 0 and 3;
			switch(option) {
			case 0: //w
				if(game_level.getChar(ogre.ogre_x - 1, ogre.ogre_y) == ' ') {
					ogre.club_up();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x - 1, ogre.ogre_y) == 'k') {
					ogre.club_set_key(true);
					ogre.club_up();
					moved = true;
				}
				break;
			case 1: //a
				if(game_level.getChar(ogre.ogre_x, ogre.ogre_y - 1) == ' ') {
					ogre.club_left();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x, ogre.ogre_y - 1) == 'k') {
					ogre.club_set_key(true);
					ogre.club_left();
					moved = true;
				}
				break;
			case 2: //s
				if(game_level.getChar(ogre.ogre_x + 1, ogre.ogre_y) == ' ') {
					ogre.club_down();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x + 1, ogre.ogre_y) == 'k') {
					ogre.club_set_key(true);
					ogre.club_down();
					moved = true;
				}
				break;
			case 3: //d
				if(game_level.getChar(ogre.ogre_x, ogre.ogre_y + 1) == ' ') {
					ogre.club_right();
					moved = true;
				}
				else if(game_level.getChar(ogre.ogre_x, ogre.ogre_y + 1) == 'k') {
					ogre.club_set_key(true);
					ogre.club_right();
					moved = true;
				}
				break;
			}
		}
	
	}
	
	public void move_GuardOgre() {
		if(game_level.level == 1) {
			guard.move();
		}
		if(game_level.level > 1) {
			move_Ogre();
		}
	}
	
	public void check_gameover() {
		// checking guard
		if (game_level.level == 1) {
			if (((guard.x == hero.x + 1) && (guard.y == hero.y)) 
					|| ((guard.x == hero.x - 1) && (guard.y == hero.y))
					|| ((guard.x == hero.x) && (guard.y == hero.y - 1))
					|| ((guard.x == hero.x) && (guard.y == hero.y + 1))) {
				lose = true;
			}
		}
		// checking ogre
		else if (game_level.level == 2) {
			if (((ogre.ogre_x == hero.x + 1) && (ogre.ogre_y == hero.y))
					|| ((ogre.ogre_x == hero.x - 1) && (ogre.ogre_y == hero.y))
					|| ((ogre.ogre_x == hero.x) && (ogre.ogre_y == hero.y - 1))
					|| ((ogre.ogre_x == hero.x) && (ogre.ogre_y == hero.y + 1))) {
				lose = true;
			} else if (((ogre.club_x == hero.x + 1) && (ogre.club_y == hero.y))
					|| ((ogre.club_x == hero.x - 1) && (ogre.club_y == hero.y))
					|| ((ogre.club_x == hero.x) && (ogre.club_y == hero.y - 1))
					|| ((ogre.club_x == hero.x) && (ogre.club_y == hero.y + 1))) {
				lose = true;
			}
		}
	}
	
	public int getLevel() {
		return game_level.level;
	}
	
	public String getMapString() {
		String ret = "";
		if(hero.hasKey == false) {
			game_level.setChar(hero.x, hero.y, 'H');
		}
		else {
			game_level.setChar(hero.x, hero.y, 'K');
		}
		
		if(game_level.level == 1) {
			game_level.setChar(guard.x, guard.y, 'G');
		}
		else if (game_level.level == 2) {
			if(ogre.ogreOnKey) {
				game_level.setChar(ogre.ogre_x, ogre.ogre_y, '$');
			}
			else {
				game_level.setChar(ogre.ogre_x, ogre.ogre_y, 'O');
			}
			
			if(ogre.clubOnKey) {
				game_level.setChar(ogre.club_x, ogre.club_y, '$');
			}
			else {
				game_level.setChar(ogre.club_x, ogre.club_y, '*');
			}
			
		}
		
		for(int i = 0; i<game_level.map.length; i++) {
			System.out.print("|");
			for(int j = 0; j< game_level.map[i].length; j++) {
				System.out.print(game_level.map[i][j]+"|");
				//ret += game_level.map[i][j] + '|';
			}
			System.out.println();
			//ret += "\n";
		}
		
		game_level.setChar(hero.x, hero.y, ' ');
		if(game_level.level == 1) {
			game_level.setChar(guard.x, guard.y, ' ');
		}
		else if (game_level.level == 2) {
			if(ogre.ogreOnKey) {
				game_level.setChar(ogre.ogre_x, ogre.ogre_y, 'k');
				ogre.ogreOnKey = false;
			}
			else {
				game_level.setChar(ogre.ogre_x, ogre.ogre_y, ' ');
			}
			
			if(ogre.clubOnKey) {
				game_level.setChar(ogre.club_x, ogre.club_y, 'k');
				ogre.clubOnKey = false;
			}
			else {
				game_level.setChar(ogre.club_x, ogre.club_y, ' ');
			}
		}
		return ret;
	}
	
	public void nextMove(String option) {
		switch(option) {
		case "up": 
			if(move_Hero(hero.x-1,hero.y)) {
				move_GuardOgre();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_gameover();
			break;
		case "left":
			if(move_Hero(hero.x,hero.y-1)) {
				move_GuardOgre();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_gameover();
			break;
		case "down":
			if(move_Hero(hero.x+1,hero.y)) {
				move_GuardOgre();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_gameover();
			break;
		case "right":
			if(move_Hero(hero.x,hero.y+1)) {
				move_GuardOgre();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_gameover();
			break;
		default:
			break;	
		}
	}
	
}
