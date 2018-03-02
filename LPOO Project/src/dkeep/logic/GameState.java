package dkeep.logic;
import java.util.Random;
import java.util.ArrayList;

public class GameState {
	
	Hero hero;
	GameLevel game_level;
	Guard guard;
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	private int lvl;
	
	public boolean win;
	public boolean lose;
	
	public GameState() {
		win = false;
		lose = false;
		lvl = 1;
		game_level = new GameLevel(lvl);
		create_Level();
	}
	
	////////
	public GameState(GameLevel g) {
		win = false;
		lose = false;
		game_level = g;
		create_Level();
	}
	////////
	
	public int get_status() {
		if(win) {
			return 1;
		}
		if(lose) {
			return 2;
		}
		
		return 0;
	}
	
	public void create_Level() {
		if(lvl > 2 ) {
			win = true;
			return;
		}
		guard = null;
		ogres.clear();
		
		char[][] mapa = game_level.getMap();
		for(int i = 0; i < mapa.length; i++) {
			for(int j  = 0; j < mapa[i].length; j++) {
				if(mapa[i][j] == 'H') {
					hero = new Hero(i, j);
					if (lvl == 2) {
						hero.armed(true);;
					}
					game_level.setChar(i, j, ' ');
				}
				if(mapa[i][j] == 'G') {
					guard = new Guard(i, j);
					game_level.setChar(i, j, ' ');
				}
				if(mapa[i][j] == 'O') {
					Random r = new Random();
					for (int n = 0; n < (r.nextInt(3) + 1); n++) {
						ogres.add(new Ogre(i, j));
					}
					game_level.setChar(i, j, ' ');
				}
			}
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
		if (hero.get_X() == 0 || hero.get_Y() == 0) {
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
				if (game_level.getChar(ogre.get_X() - 1, ogre.get_Y()) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_up();
					moved = true;
				} else if (game_level.getChar(ogre.get_X() - 1, ogre.get_Y()) == 'k') {
					ogre.ogre_set_key(true);
					ogre.move_up();
					moved = true;
				}
				break;
			case 1: // a
				if (game_level.getChar(ogre.get_X(), ogre.get_Y() - 1) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_left();
					moved = true;
				} else if (game_level.getChar(ogre.get_X(), ogre.get_Y() - 1) == 'k') {
					ogre.ogre_set_key(true);
					ogre.move_left();
					moved = true;
				}
				break;
			case 2: // s
				if (game_level.getChar(ogre.get_X() + 1, ogre.get_Y()) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_down();
					moved = true;
				} else if (game_level.getChar(ogre.get_X() + 1, ogre.get_Y()) == 'k') {
					ogre.ogre_set_key(true);
					ogre.move_down();
					moved = true;
				}
				break;
			case 3: // d
				if (game_level.getChar(ogre.get_X(), ogre.get_Y() + 1) == ' ') {
					ogre.ogre_set_key(false);
					ogre.move_right();
					moved = true;
				} else if (game_level.getChar(ogre.get_X(), ogre.get_Y() + 1) == 'k') {
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
				if (game_level.getChar(ogre.get_X() - 1, ogre.get_Y()) == ' ') {
					ogre.club_set_key(false);
					ogre.club_up();
					moved = true;
				} else if (game_level.getChar(ogre.get_X() - 1, ogre.get_Y()) == 'k') {
					ogre.club_set_key(true);
					ogre.club_up();
					moved = true;
				}
				break;
			case 1: // a
				if (game_level.getChar(ogre.get_X(), ogre.get_Y() - 1) == ' ') {
					ogre.club_set_key(false);
					ogre.club_left();
					moved = true;
				} else if (game_level.getChar(ogre.get_X(), ogre.get_Y() - 1) == 'k') {
					ogre.club_set_key(true);
					ogre.club_left();
					moved = true;
				}
				break;
			case 2: // s
				if (game_level.getChar(ogre.get_X() + 1, ogre.get_Y()) == ' ') {
					ogre.club_set_key(false);
					ogre.club_down();
					moved = true;
				} else if (game_level.getChar(ogre.get_X() + 1, ogre.get_Y()) == 'k') {
					ogre.club_set_key(true);
					ogre.club_down();
					moved = true;
				}
				break;
			case 3: // d
				if (game_level.getChar(ogre.get_X(), ogre.get_Y() + 1) == ' ') {
					ogre.club_set_key(false);
					ogre.club_right();
					moved = true;
				} else if (game_level.getChar(ogre.get_X(), ogre.get_Y() + 1) == 'k') {
					ogre.club_set_key(true);
					ogre.club_right();
					moved = true;
				}
				break;
			}
		}
	
	}
	
	public void move_Enemy() {
		if(guard != null) {
			guard.move();
		}
		for (Ogre ogre : ogres) {
			move_Ogre(ogre);
		}
	}
	
	public void check_Enemy() {
		if(guard != null) {
			if (!guard.asleep) {
				if (((guard.get_X() == hero.get_X() + 1) && (guard.get_Y() == hero.get_Y()))
						|| ((guard.get_X() == hero.get_X() - 1) && (guard.get_Y() == hero.get_Y()))
						|| ((guard.get_X() == hero.get_X()) && (guard.get_Y() == hero.get_Y() - 1))
						|| ((guard.get_X() == hero.get_X()) && (guard.get_Y() == hero.get_Y() + 1))
						|| ((guard.get_X() == hero.get_X()) && (guard.get_Y() == hero.get_Y()))) {
					lose = true;
				}
			}
		}
		for (Ogre ogre : ogres) {
			if (!ogre.stun) {
				if (((ogre.get_X() == hero.get_X() + 1) && (ogre.get_Y() == hero.get_Y()))
						|| ((ogre.get_X() == hero.get_X() - 1) && (ogre.get_Y() == hero.get_Y()))
						|| ((ogre.get_X() == hero.get_X()) && (ogre.get_Y() == hero.get_Y() - 1))
						|| ((ogre.get_X() == hero.get_X()) && (ogre.get_Y() == hero.get_Y() + 1))
						|| ((ogre.get_X() == hero.get_X()) && (ogre.get_Y() == hero.get_Y()))) {
					if (hero.has_arm()) {
						ogre.stunned(true);
					} else {
						lose = true;
					}
				} else if (((ogre.get_club_X() == hero.get_X() + 1) && (ogre.get_club_Y() == hero.get_Y()))
						|| ((ogre.get_club_X() == hero.get_X() - 1) && (ogre.get_club_Y() == hero.get_Y()))
						|| ((ogre.get_club_X() == hero.get_X()) && (ogre.get_club_Y() == hero.get_Y() - 1))
						|| ((ogre.get_club_X() == hero.get_X()) && (ogre.get_club_Y() == hero.get_Y() + 1))
						|| ((ogre.get_club_X() == hero.get_X()) && (ogre.get_club_Y() == hero.get_Y()))) {
					lose = true;
				}
			}
		}
	}
	
	public char[][] getMap(){
		return game_level.getMap();
	}
	
	public boolean check_hero(int x, int y) {
		return hero.get_X() == x && hero.get_Y() == y;
	}
	
	public boolean check_guard(int x, int y) {
		if(guard == null)
			return false;
		
		return guard.get_X() == x && guard.get_Y() == y;
	}
	
	public boolean check_ogre(int x, int y, Ogre ogre) {
		return ogre.get_X() == x && ogre.get_Y() == y;
	}
	
	public boolean check_ogre_club(int x, int y, Ogre ogre) {
		return ogre.get_club_X() == x && ogre.get_club_Y() == y;
	}
	
	public char get_hero_char() {
		return hero.get_char();
	}
	
	public char get_guard_char() {
		return guard.get_char();
	}
	
	public char get_ogre_char(Ogre ogre) {
		return ogre.get_char();
	}
	
	public char get_ogre_club_char(Ogre ogre) {
		return ogre.ch_club;
	}
		
	
	public void nextMove(String option) {
		switch(option) {
		case "up": 
			if(move_Hero(hero.get_X()-1,hero.get_Y())) {
				move_Enemy();
				if(check_Level_End()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			check_Enemy();
			break;
		case "left":
			if(move_Hero(hero.get_X(),hero.get_Y()-1)) {
				move_Enemy();
				if(check_Level_End()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			check_Enemy();
			break;
		case "down":
			if(move_Hero(hero.get_X()+1,hero.get_Y())) {
				move_Enemy();
				if(check_Level_End()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			check_Enemy();
			break;
		case "right":
			if(move_Hero(hero.get_X(),hero.get_Y()+1)) {
				move_Enemy();
				if(check_Level_End()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			check_Enemy();
			break;
		default:
			break;	
		}
	}	
}
