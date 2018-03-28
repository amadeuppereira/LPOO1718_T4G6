package dkeep.logic;
import java.util.Random;

import java.util.ArrayList;

public class GameState {
	
	public enum State {WIN, DEFEAT, PLAYING}
	public enum Movement {UP, DOWN, LEFT, RIGHT}
	private State state;
	
	private Hero hero;
	private GameLevel game_level;
	private Guard guard;
	private String guard_type;
	private char[] guard_mov = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	private ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	private int numOgres;

	
	private int lvl;

	public GameState() {
		Random r = new Random();
		lvl = 1;
		numOgres = r.nextInt(3) + 1;
		game_level = new GameLevel(lvl);
		guard_type = "";
		state = State.PLAYING;
		create_Level();
	}
	
	public GameState(GameLevel g) {
		guard_mov = new char[1];
		numOgres = 1;
		game_level = g;
		guard_type = "";
		state = State.PLAYING;
		create_Level();
	}
	
	public GameState(int numOgres, String guardtype) {
		lvl = 1;
		this.numOgres = numOgres;
		game_level = new GameLevel(lvl);
		state = State.PLAYING;
		guard_type = guardtype;
		create_Level();
	}
	
	public State get_status() {
		return state;
	}
	
	public Movement getRandomAdjancentPos(int x, int y) {
		Movement m = null;
		boolean available = false;
		Random randomGenerator = new Random();
		int option;

		while (available == false) {
			option = randomGenerator.nextInt(4); // generating a random number between 0 and 3;
			switch (option) {
			case 0: // w
				if (game_level.getChar(x - 1, y) == ' ' || game_level.getChar(x - 1, y) == 'k') {
					m = Movement.UP;
					available = true;
				}
				break;
			case 1: // a
				if (game_level.getChar(x, y - 1) == ' ' || game_level.getChar(x, y - 1) == 'k') {
					m = Movement.LEFT;
					available = true;
				}
				break;
			case 2: // s
				if (game_level.getChar(x + 1, y) == ' ' || game_level.getChar(x + 1, y) == 'k') {
					m = Movement.DOWN;
					available = true;
				}
				break;
			case 3: // d
				if (game_level.getChar(x, y + 1) == ' ' || game_level.getChar(x, y + 1) == 'k') {
					m = Movement.RIGHT;
					available = true;
				}
				break;
			}
		}
		
		return m;
	}
	
	public void create_Level() {
		if(game_level.getMaps() != null) {
			if(lvl > game_level.getMaps().size()) {
				state = State.WIN;
				return;
			}
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
					if(guard_type == "") {
						guard = new Guard(i, j, guard_mov);
					}
					else {
						guard = new Guard(i, j, guard_mov, guard_type);
					}
					game_level.setChar(i, j, ' ');
				}
				if(map[i][j] == 'O') {
					for (int n = 0; n < numOgres; n++) {
						ogres.add(new Ogre(i, j));
					}
					game_level.setChar(i, j, ' ');
				}
			}
		}	
	}
	
	public boolean move_Hero(Movement m) {
		int x = hero.get_X();
		int y = hero.get_Y();
		
		switch(m) {
		case UP:
			x = hero.get_X()-1;
			y = hero.get_Y();
			break;
		case DOWN:
			x = hero.get_X()+1;
			y = hero.get_Y();
			break;
		case LEFT:
			x = hero.get_X();
			y = hero.get_Y()-1;
			break;
		case RIGHT:
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
		if (hero.get_X() == 0 || hero.get_Y() == 0 || hero.get_X() == game_level.getMap().length - 1 || hero.get_Y() == game_level.getMap()[0].length - 1) {
			return true;
		}
		
		return false;
	}
	
	public void checkOgreAtKey(Ogre ogre) {
		if(game_level.getChar(ogre.get_X(), ogre.get_Y()) == 'k') {
			ogre.ogre_set_key(true);
		}
		else {
			ogre.ogre_set_key(false);
		}
	}
	
	public void checkOgreClubAtKey(Ogre ogre) {
		if(game_level.getChar(ogre.get_club_X(), ogre.get_club_Y()) == 'k') {
			ogre.club_set_key(true);
		}
		else {
			ogre.club_set_key(false);
		}
	}
	
	public void move_Ogre(Ogre ogre) {
		
		if(ogre.stun) {
			ogre.reduce_stun();
			return;
		}
		
		Movement move = getRandomAdjancentPos(ogre.get_X(), ogre.get_Y());
		
		switch(move) {
		case UP:
			ogre.move_up();
			checkOgreAtKey(ogre);
			break;
		case DOWN:
			ogre.move_down();
			checkOgreAtKey(ogre);
			break;
		case RIGHT:
			ogre.move_right();
			checkOgreAtKey(ogre);
			break;
		case LEFT:
			ogre.move_left();
			checkOgreAtKey(ogre);
			break;
		}
	}

	public void move_OgreClub(Ogre ogre) {
		
		Movement move = getRandomAdjancentPos(ogre.get_X(), ogre.get_Y());
		
		switch(move) {
		case UP:
			ogre.club_up();
			checkOgreClubAtKey(ogre);
			break;
		case DOWN:
			ogre.club_down();
			checkOgreClubAtKey(ogre);
			break;
		case RIGHT:
			ogre.club_right();
			checkOgreClubAtKey(ogre);
			break;
		case LEFT:
			ogre.club_left();
			checkOgreClubAtKey(ogre);
			break;
		}
	}
	
	public void move_Enemy() {
		if(guard != null) {
			guard.move();
		}
		for (Ogre ogre : ogres) {
			move_Ogre(ogre);
			move_OgreClub(ogre);
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
			
	public void nextMove(Movement option) {
		if(move_Hero(option)) {
			move_Enemy();
			if(isLevelEnd()) {
				game_level = new GameLevel(++lvl);
				create_Level();
			}
		}
		isGameover();
	}	
	
	public String getGameString() {
		String ret = "";
		char[][] map = this.getMap();
		boolean flag = true;
		
		
		for(int i = 0; i<map.length; i++) {
			ret += "|";
			for(int j = 0; j< map[i].length; j++) {
				flag = true;
				
				if(this.check_hero(i, j) && flag) {
					ret += this.get_hero_char() + "|";
					flag = false;
				}
				
				if(this.check_guard(i, j) && flag) {
					ret += this.get_guard_char() + "|";
					flag = false;
				}
				for (Ogre ogre : this.get_ogres()) {
					if (this.check_ogre(i, j, ogre) && flag) {
						ret += this.get_ogre_char(ogre) + "|";
						flag = false;
					} else if (this.check_ogre_club(i, j, ogre) && flag) {
						ret += this.get_ogre_club_char(ogre) + "|";
						flag = false;
					}
				}
				
				if(flag) {
					ret += map[i][j]+"|";
				}
			}
			ret += "\n";
		}
		return ret;
	}
	
	public char[][]  mapclone(char [][] map){
		char[][] newmap = new char[map.length][];
		for(int i = 0; i < map.length; i++) {
			newmap[i] = map[i].clone();
		}
		return newmap;
	}
	
	public char[][] getGameMap(){
		char[][] ret = mapclone(this.game_level.getMap());
		ret[this.hero.get_X()][this.hero.get_Y()] = hero.get_char();
		if(this.guard != null) {
			ret[this.guard.get_X()][this.guard.get_Y()] = guard.get_char();
		}
		for(Ogre ogre : ogres) {	
			ret[ogre.get_club_X()][ogre.get_club_Y()] = ogre.getClubChar();
			ret[ogre.get_X()][ogre.get_Y()] = ogre.get_char();
		}
		return ret;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
