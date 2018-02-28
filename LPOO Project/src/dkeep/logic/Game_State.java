package dkeep.logic;
import java.util.Random;
import java.util.ArrayList;
import dkeep.logic.*;

public class Game_State {
	
	Hero hero;
	Game_Level game_level;
	Guard guard;
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	
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
			String[] guard_type = {"Rookie","Drunken","Suspicious"};
			Random randomGenerator = new Random();
			guard = new Guard(1, 8, guard_mov, guard_type[randomGenerator.nextInt(3)]);
			break;
		
		case 2:
			hero = new Hero(8, 1);
			hero.hero_armed(true);
			game_level = new Game_Level(2);
			
			Random random = new Random();
			int nOgres = random.nextInt(3) + 1;
			for(int i = 0; i < nOgres; i++) {
				ogres.add(new Ogre(1 + 2*i, 4, 2+2*i, 4));
			}
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
			game_level.setChar(x, y, ' ');
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
	
	public void move_Ogre(Ogre ogre) {
		boolean moved;
		if(ogre.stun) {
			moved = true;
			ogre.reduce_stun();
		}
		else {
			moved = false;
		}
		Random randomGenerator = new Random();
		int option;
			
		while (moved == false) {
			option = randomGenerator.nextInt(4); // generating a random number between 0 and 3;
			switch (option) {
			case 0: // w
				if (game_level.getChar(ogre.x - 1, ogre.y) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_up();
					moved = true;
				} else if (game_level.getChar(ogre.x - 1, ogre.y) == 'k') {
					ogre.ogre_set_key(true);
					ogre.move_up();
					moved = true;
				}
				break;
			case 1: // a
				if (game_level.getChar(ogre.x, ogre.y - 1) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_left();
					moved = true;
				} else if (game_level.getChar(ogre.x, ogre.y - 1) == 'k') {
					ogre.ogre_set_key(true);
					ogre.move_left();
					moved = true;
				}
				break;
			case 2: // s
				if (game_level.getChar(ogre.x + 1, ogre.y) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_down();
					moved = true;
				} else if (game_level.getChar(ogre.x + 1, ogre.y) == 'k') {
					ogre.ogre_set_key(true);
					ogre.move_down();
					moved = true;
				}
				break;
			case 3: // d
				if (game_level.getChar(ogre.x, ogre.y + 1) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_right();
					moved = true;
				} else if (game_level.getChar(ogre.x, ogre.y + 1) == 'k') {
					ogre.ogre_set_key(true);
					ogre.move_right();
					moved = true;
				}
				break;
			}
		}

		moved = false;
		while (moved == false) {
			option = randomGenerator.nextInt(4); // generating a random number between 0 and 3;
			switch (option) {
			case 0: // w
				if (game_level.getChar(ogre.x - 1, ogre.y) == ' ') {
					ogre.club_set_key(false);
					ogre.club_up();
					moved = true;
				} else if (game_level.getChar(ogre.x - 1, ogre.y) == 'k') {
					ogre.club_set_key(true);
					ogre.club_up();
					moved = true;
				}
				break;
			case 1: // a
				if (game_level.getChar(ogre.x, ogre.y - 1) == ' ') {
					ogre.club_set_key(false);
					ogre.club_left();
					moved = true;
				} else if (game_level.getChar(ogre.x, ogre.y - 1) == 'k') {
					ogre.club_set_key(true);
					ogre.club_left();
					moved = true;
				}
				break;
			case 2: // s
				if (game_level.getChar(ogre.x + 1, ogre.y) == ' ') {
					ogre.club_set_key(false);
					ogre.club_down();
					moved = true;
				} else if (game_level.getChar(ogre.x + 1, ogre.y) == 'k') {
					ogre.club_set_key(true);
					ogre.club_down();
					moved = true;
				}
				break;
			case 3: // d
				if (game_level.getChar(ogre.x, ogre.y + 1) == ' ') {
					ogre.club_set_key(false);
					ogre.club_right();
					moved = true;
				} else if (game_level.getChar(ogre.x, ogre.y + 1) == 'k') {
					ogre.club_set_key(true);
					ogre.club_right();
					moved = true;
				}
				break;
			}
		}
	
	}
	
	public void move_Enemy() {
		if(game_level.level == 1) {
			guard.move();
		}
		if(game_level.level == 2) {
			for(Ogre ogre : ogres) {
				move_Ogre(ogre);
			}
		}
	}
	
	public void check_Enemy() {
		switch (game_level.level) {
		case 1:
			// checking guard
			if (!guard.asleep) {
				if (((guard.x == hero.x + 1) && (guard.y == hero.y))
						|| ((guard.x == hero.x - 1) && (guard.y == hero.y))
						|| ((guard.x == hero.x) && (guard.y == hero.y - 1))
						|| ((guard.x == hero.x) && (guard.y == hero.y + 1))) {
					lose = true;
				}
			}
			break;

		case 2:
			// checking ogres
			for (Ogre ogre : ogres) {
				if (!ogre.stun) {
					if (((ogre.x == hero.x + 1) && (ogre.y == hero.y)) 
							|| ((ogre.x == hero.x - 1) && (ogre.y == hero.y))
							|| ((ogre.x == hero.x) && (ogre.y == hero.y - 1))
							|| ((ogre.x == hero.x) && (ogre.y == hero.y + 1))) {
						if(hero.has_arm()) {
							ogre.stunned(true);
						}
						else {
							lose = true;
						}
					} else if (((ogre.club_x == hero.x + 1) && (ogre.club_y == hero.y))
							|| ((ogre.club_x == hero.x - 1) && (ogre.club_y == hero.y))
							|| ((ogre.club_x == hero.x) && (ogre.club_y == hero.y - 1))
							|| ((ogre.club_x == hero.x) && (ogre.club_y == hero.y + 1))) {
						lose = true;
					}
				}
			}
			break;

		default:
			break;
		}
	}
	
	public int getLevel() {
		return game_level.level;
	}
	
	public char[][] getMap() {
		return game_level.map;
	}
	
	public boolean check_hero(int x, int y) {
		return hero.x == x && hero.y == y;
	}
	
	public boolean check_guard(int x, int y) {
		return guard.x == x && guard.y == y;
	}
	
	public boolean check_ogre(int x, int y, Ogre ogre) {
		return ogre.x == x && ogre.y == y;
	}
	
	public boolean check_ogre_club(int x, int y, Ogre ogre) {
		return ogre.club_x == x && ogre.club_y == y;
	}
	
	public char get_hero_char() {
		return hero.ch;
	}
	
	public char get_guard_char() {
		return guard.ch;
	}
	
	public char get_ogre_char(Ogre ogre) {
		return ogre.ch;
	}
	
	public char get_ogre_club_char(Ogre ogre) {
		return ogre.ch_club;
	}
		
	
	public void nextMove(String option) {
		switch(option) {
		case "up": 
			if(move_Hero(hero.x-1,hero.y)) {
				check_Enemy();
				move_Enemy();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_Enemy();
			break;
		case "left":
			if(move_Hero(hero.x,hero.y-1)) {
				check_Enemy();
				move_Enemy();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_Enemy();
			break;
		case "down":
			if(move_Hero(hero.x+1,hero.y)) {
				check_Enemy();
				move_Enemy();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_Enemy();
			break;
		case "right":
			if(move_Hero(hero.x,hero.y+1)) {
				check_Enemy();
				move_Enemy();
				if(check_Level_End()) {
					create_Level(game_level.level + 1);
				}
			}
			check_Enemy();
			break;
		default:
			break;	
		}
	}
	
}
