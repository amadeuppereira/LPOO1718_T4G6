package dkeep.logic;
import java.util.Random;

import java.util.ArrayList;

public class GameState {
	
	public enum State {WIN, DEFEAT, PLAYING}
	private State state;
	
	private Hero hero;
	private GameLevel game_level;
	private Guard guard;
	private char[] guard_mov = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	private ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	private int maxOgres;

	
	private int lvl;

	
	public GameState() {
		lvl = 1;
		maxOgres = 3;
		game_level = new GameLevel(lvl);
		state = State.PLAYING;
		create_Level();
	}
	
	public GameState(GameLevel g) {
		guard_mov = new char[1];
		maxOgres = 1;
		game_level = g;
		state = State.PLAYING;
		create_Level();
	}
	
	public State get_status() {
		return state;
	}
	
	public void create_Level() {
		if(lvl > 2 ) {
			state = State.WIN;
			return;
		}
		guard = null;
		ogres.clear();
		
		char[][] map = game_level.getMap();
		for(int i = 0; i < map.length; i++) {
			for(int j  = 0; j < map[i].length; j++) {
				if(map[i][j] == 'H') {
					hero = new Hero(i, j);
					if (lvl == 2) {
						hero.armed(true);;
					}
					game_level.setChar(i, j, ' ');
				}
				if(map[i][j] == 'G') {
					guard = new Guard(i, j, guard_mov);
					game_level.setChar(i, j, ' ');
				}
				if(map[i][j] == 'O') {
					Random r = new Random();
					for (int n = 0; n < (r.nextInt(maxOgres) + 1); n++) {
						ogres.add(new Ogre(i, j));
					}
					game_level.setChar(i, j, ' ');
				}
			}
		}	
	}
	
	public boolean move_Hero(String m) {
		int x = hero.get_X();
		int y = hero.get_Y();
		
		switch(m) {
		case "up":
			x = hero.get_X()-1;
			y = hero.get_Y();
			break;
		case "down":
			x = hero.get_X()+1;
			y = hero.get_Y();
			break;
		case "left":
			x = hero.get_X();
			y = hero.get_Y()-1;
			break;
		case "right":
			x = hero.get_X();
			y = hero.get_Y()+1;
			break;
		default:
			break;
		}
		
		
		char ch = game_level.getChar(x, y);
		if(ch == ' ') {
			hero.move(x, y);
			return true;
		}
		else if(ch == 'k') {
			hero.move(x, y);
			if(!game_level.lever()) {
				hero.set_key(true);
				game_level.setChar(x, y, ' ');
			}
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
	
	public boolean isLevelEnd() {
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
	
	public boolean isGameover() {
		if(guard != null) {
			if (!guard.asleep) {
				if ((guard.get_X() == hero.get_X() + 1) && (guard.get_Y() == hero.get_Y())
						|| ((guard.get_X() == hero.get_X() - 1) && (guard.get_Y() == hero.get_Y()))
						|| ((guard.get_X() == hero.get_X()) && (guard.get_Y() == hero.get_Y() - 1))
						|| ((guard.get_X() == hero.get_X()) && (guard.get_Y() == hero.get_Y() + 1))
						|| ((guard.get_X() == hero.get_X()) && (guard.get_Y() == hero.get_Y()))) {
					state = State.DEFEAT;
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
						state = State.DEFEAT;
					}
				} else if (((ogre.get_club_X() == hero.get_X() + 1) && (ogre.get_club_Y() == hero.get_Y()))
						|| ((ogre.get_club_X() == hero.get_X() - 1) && (ogre.get_club_Y() == hero.get_Y()))
						|| ((ogre.get_club_X() == hero.get_X()) && (ogre.get_club_Y() == hero.get_Y() - 1))
						|| ((ogre.get_club_X() == hero.get_X()) && (ogre.get_club_Y() == hero.get_Y() + 1))
						|| ((ogre.get_club_X() == hero.get_X()) && (ogre.get_club_Y() == hero.get_Y()))) {
					state = State.DEFEAT;
				}
			}
		}
		
		if (state == State.DEFEAT) { return true; }
		else { return false; }
	}
	
	public char[][] getMap(){
		return game_level.getMap();
	}
	
	public int getLvl(){
		return lvl;
	}
	
	public boolean check_hero(int x, int y) {
		return hero.get_X() == x && hero.get_Y() == y;
	}
	
	public boolean check_guard(int x, int y) {
		if(guard == null)
			return false;
		
		return guard.get_X() == x && guard.get_Y() == y;
	}
	
	public ArrayList<Ogre> get_ogres() {
		return ogres;
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
			if(move_Hero(option)) {
				move_Enemy();
				if(isLevelEnd()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			isGameover();
			break;
		case "left":
			if(move_Hero(option)) {
				move_Enemy();
				if(isLevelEnd()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			isGameover();
			break;
		case "down":
			if(move_Hero(option)) {
				move_Enemy();
				if(isLevelEnd()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			isGameover();
			break;
		case "right":
			if(move_Hero(option)) {
				move_Enemy();
				if(isLevelEnd()) {
					game_level = new GameLevel(++lvl);
					create_Level();
				}
			}
			isGameover();
			break;
		default:
			break;	
		}
	}	
}
