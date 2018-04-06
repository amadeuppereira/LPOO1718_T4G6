package dkeep.logic;
import java.util.Random;

import java.util.ArrayList;

/**
 * GameState.java - a class that handles all the game mechanics
 */
public class GameState {
	
	/**
	 * Represents the different states of the game
	 */
	public enum State {WIN, DEFEAT, PLAYING}
	
	/**
	 * Represents the different types of movement
	 */
	public enum Movement {UP, DOWN, LEFT, RIGHT}
	
	/**
	 * Represents the different types of guard
	 */
	public enum Guard_Type {RANDOM, ROOKIE, DRUNKEN, SUSPICIOUS}
	private State state;
	
	private Hero hero;
	private GameLevel game_level;
	private Guard guard;
	private Guard_Type guard_type;
	private char[] guard_mov = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	private ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	private int numOgres;

	
	private int lvl;

	/**
	 * Create a new GameState object
	 */
	public GameState() {
		Random r = new Random();
		lvl = 1;
		numOgres = r.nextInt(3) + 1;
		game_level = new GameLevel(lvl);
		guard_type = Guard_Type.RANDOM;
		state = State.PLAYING;
		create_Level();
	}
	
	/**
	 * Create a new GameState object with a given level
	 * @param g level
	 */
	public GameState(GameLevel g) {
		guard_mov = new char[1];
		numOgres = 1;
		game_level = g;
		guard_type = Guard_Type.RANDOM;
		state = State.PLAYING;
		create_Level();
	}
	
	/**
	 * Create a new GameState object with a given number of ogres and a given guard type
	 * @param numOgres number of ogres
	 * @param guardtype guard type
	 */
	public GameState(int numOgres, String guardtype) {
		game_level = new GameLevel(lvl = 1);
		state = State.PLAYING;
		this.numOgres = numOgres;
		switch(guardtype) {
		case "Rookie":
			guard_type = Guard_Type.ROOKIE;
			break;
		case "Drunken":
			guard_type = Guard_Type.DRUNKEN;
			break;
		case "Suspicious":
			guard_type = Guard_Type.SUSPICIOUS;
			break;
		default:
			guard_type = Guard_Type.RANDOM;
			break;
		}
		create_Level();
	}
	
	/**
	 * Get the game state
	 * @return game state
	 */
	public State get_status() {
		return state;
	}
	
	/**
	 * Get a random available position(now a wall/closed door) next to a given position 
	 * @param pos position
	 * @return new position
	 */
	public CellPosition getRandomAvailableAdjacentPos(CellPosition pos) {
		CellPosition newPos = null;
		boolean available = false;
		while(!available) {
			newPos = pos.getRandomAdjPos();
			available = isPossiblePos(newPos.get_positionX(), newPos.get_positionY());
		}
		return newPos;
	}
	

	/**
	 * Get a random possible movement
	 * @param x actual x coordinate
	 * @param y actual y coordinate
	 * @param flag true if it is possible to go to the edges, false otherwise (e.g: must be false for an ogre)
	 * @return random movement
	 */
	public Movement getRandomPossibleMove(int x, int y, boolean flag) {
		CellPosition pos = new CellPosition(x, y);
		CellPosition newPos;
		while(true) {
			newPos = getRandomAvailableAdjacentPos(pos);
			if(!flag && (newPos.get_positionX() == 0 || newPos.get_positionY() == 0)) continue;
			break;
		}
		return pos.analyseMovement(newPos);	
	}
	
	/**
	 * Update the guard type if it was meant to be random
	 */
	public void updateGuardType() {
		if(guard_type == Guard_Type.RANDOM) {
			Random r = new Random();
			int o = r.nextInt(3);
			switch(o) {
			case 0:
				guard_type = Guard_Type.ROOKIE;
				break;
			case 1:
				guard_type = Guard_Type.DRUNKEN;
				break;
			case 2:
				guard_type = Guard_Type.SUSPICIOUS;
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * Create a guard at a given position
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void createGuard(int x, int y) {
		updateGuardType();
		switch(guard_type) {
		case ROOKIE:
			guard = new Rookie(x, y, guard_mov);
			break;
		case DRUNKEN:
			guard = new Drunken(x, y, guard_mov);
			break;
		case SUSPICIOUS:
			guard = new Suspicious(x, y, guard_mov);
			break;
		default:
			break;
		}
	}
		
	/**
	 * Create a new level, setting the game state to WIN if the player passed all the levels
	 */
	public void create_Level() {
		if(game_level.getMaps() != null) {
			if(lvl > game_level.getMaps().size()) {
				state = State.WIN;
				return;
			}
		}
		guard = null;
		ogres.clear();
		create_Level_HeroGuardOgre();
	}
	
	/**
	 * Change if the hero as a weapon or not
	 * @param b new value
	 */
	public void giveHeroWeapon(boolean b) {
		hero.armed(b);
	}
	
	/**
	 * Create all the characters of the level
	 */
	private void create_Level_HeroGuardOgre() {
		char[][] map = game_level.getMap();
		for(int i = 0; i < map.length; i++) {
			for(int j  = 0; j < map[i].length; j++) {
				switch(map[i][j]) {
				case 'H':
					hero = new Hero(i, j);
					game_level.setChar(i, j, ' ');
					break;
				case 'G':
					createGuard(i, j);
					game_level.setChar(i, j, ' ');
					break;
				case 'O':
					for (int n = 0; n < numOgres; n++) {ogres.add(new Ogre(i, j));}
					game_level.setChar(i, j, ' ');
					break;
				default:
					break;
		}}}
		if(ogres.size() > 0) giveHeroWeapon(true);
	}
	
	/**
	 * Check if it is possible to go to a given position
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if possible, false otherwise
	 */
	private boolean isPossiblePos(int x, int y) {
		char ch = game_level.getChar(x, y);
		if(ch == 'X' || (ch == 'I' && !hero.check_key())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Update hero, also updating the door if he has a key
	 * @param x new x coordinate
	 * @param y new y coordinate
	 */
	private void updateHero(int x, int y) {
		char ch = game_level.getChar(x, y);
		
		if(ch == 'I') {
			game_level.setChar(x, y, 'S');
			return; 
		}
		if(ch == 'k') {
			if(!game_level.lever()) {
				hero.set_key(true);
				game_level.setChar(x, y, ' ');
			}
		}
		hero.move(x, y);
	}
	
	/**
	 * Move the hero to a given possition
	 * @param m type of movement
	 * @return true if the movement was possible, false otherwise
	 */
	public boolean move_Hero(Movement m) {
		int x = hero.get_X();
		int y = hero.get_Y();
		boolean possible = false;
		switch(m) {
		case UP:
			possible = isPossiblePos(--x, y);
			break;
		case DOWN:
			possible = isPossiblePos(++x, y);
			break;
		case LEFT:
			possible = isPossiblePos(x, --y);
			break;
		case RIGHT:
			possible = isPossiblePos(x, ++y);;
			break;
		default:
			break;
		}
		if(!possible) return false;
		updateHero(x, y);
		return true;
	}
	
	/**
	 * Check if the player reached the end of the level
	 * @return true if reached the end of level, false otherwise
	 */
	public boolean isLevelEnd() {
		if (hero.get_X() == 0 || hero.get_Y() == 0 || hero.get_X() == game_level.getMap().length - 1 || hero.get_Y() == game_level.getMap()[0].length - 1) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if an ogre is at a key, updating it's status
	 * @param ogre ogre to check
	 */
	public void checkOgreAtKey(Ogre ogre) {
		if(game_level.getChar(ogre.get_X(), ogre.get_Y()) == 'k') {
			ogre.ogre_set_key(true);
		}
		else {
			ogre.ogre_set_key(false);
		}
	}
	
	/**
	 * Check if an ogre's club is at a key, updating it's status
	 * @param ogre ogre to check
	 */
	public void checkOgreClubAtKey(Ogre ogre) {
		if(game_level.getChar(ogre.get_club_X(), ogre.get_club_Y()) == 'k') {
			ogre.club_set_key(true);
		}
		else {
			ogre.club_set_key(false);
		}
	}
	
	/**
	 * Move an ogre randomly
	 * @param ogre ogre to move
	 */
	public void move_Ogre(Ogre ogre) {
		if(ogre.updateStunTime()) return;
		Movement move = getRandomPossibleMove(ogre.get_X(), ogre.get_Y(), false);
		if(move == Movement.UP)
			ogre.move_up();
		else if(move == Movement.DOWN)
			ogre.move_down();
		else if(move == Movement.LEFT)
			ogre.move_left();
		else if(move == Movement.RIGHT)
			ogre.move_right();
		checkOgreAtKey(ogre);
	}

	/**
	 * Move an ogre's club randomly
	 * @param ogre ogre
	 */
	public void move_OgreClub(Ogre ogre) {
		Movement move = getRandomPossibleMove(ogre.get_X(), ogre.get_Y(), true);
		if(move == Movement.UP)
			ogre.club_up();
		else if(move == Movement.DOWN)
			ogre.club_down();
		else if(move == Movement.LEFT)
			ogre.club_left();
		else if(move == Movement.RIGHT)
			ogre.club_right();
		checkOgreClubAtKey(ogre);
	}
	
	/**
	 * Move all the enemies from the level (guard/ogres)
	 */
	public void move_Enemy() {
		if(guard != null) {
			guard.move();
		}
		for (Ogre ogre : ogres) {
			move_Ogre(ogre);
			move_OgreClub(ogre);
		}
	}
	
	/**
	 * Check if it is gameover for a guard level
	 * @return true if gameover, false otherwise
	 */
	private boolean isGameoverGuard() {
		if(guard != null) {
			if (!guard.asleep) {
				if (guard.checkNear(hero.getPosition())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Update an ogre state
	 * @param ogre ogre to update
	 * @return true if ogre gets stunned, false otherwise 
	 */
	private boolean updateOgreState(Ogre ogre) {
		if (hero.has_arm()) {
			ogre.stunned(true);
		} else {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if it is gameover for an ogre level
	 * @return true if gameover, false otherwise
	 */
	private boolean isGameoverOgres() {
		for (Ogre ogre : ogres) {
			if (!ogre.stun) {
				if (ogre.checkNear(hero.getPosition())) {
					return updateOgreState(ogre);
				} else if (ogre.getClub_position().checkNear(hero.getPosition())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if it is gameover updating the game state accordingly
	 * @return true if gameover, false otherwise
	 */
	public boolean isGameover() {
		if(isGameoverGuard()) state = State.DEFEAT;
		if(isGameoverOgres()) state = State.DEFEAT;
		if (state == State.DEFEAT) return true;
		else return false;
	}
	
	/**
	 * Get the level map
	 * @return level map
	 */
	public char[][] getMap(){
		return game_level.getMap();
	}
	
	/**
	 * Get the game level
	 * @return game level
	 */
	public int getLvl(){
		return lvl;
	}
	
	/**
	 * Check if the hero is at a given position
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if the hero is at the given position, false otherwise
	 */
	public boolean check_hero(int x, int y) {
		return hero.get_X() == x && hero.get_Y() == y;
	}
	
	/**
	 * Check if the guard is at a given position
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if the guard is at the given position, false otherwise
	 */
	public boolean check_guard(int x, int y) {
		if(guard == null)
			return false;
		
		return guard.get_X() == x && guard.get_Y() == y;
	}
	
	/**
	 * Get all the ogres
	 * @return arraylist with all the ogres
	 */
	public ArrayList<Ogre> get_ogres() {
		return ogres;
	}
	
	/**
	 * Check if a given ogre is at a given position
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param ogre ogre
	 * @return true if the ogre is at the given position, false otherwise
	 */
	public boolean check_ogre(int x, int y, Ogre ogre) {
		return ogre.get_X() == x && ogre.get_Y() == y;
	}
	
	/**
	 * Check if a given ogre's club is at a given position
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param ogre ogre
	 * @return true if the ogre's club is at the given position, false otherwise
	 */
	public boolean check_ogre_club(int x, int y, Ogre ogre) {
		return ogre.get_club_X() == x && ogre.get_club_Y() == y;
	}
	
	/**
	 * Get hero representative char
	 * @return hero representative char
	 */
	public char get_hero_char() {
		return hero.get_char();
	}
	
	/**
	 * Get guard representative char
	 * @return guard representative char
	 */
	public char get_guard_char() {
		return guard.get_char();
	}
	
	/**
	 * Get a given ogre representative char
	 * @param ogre ogre
	 * @return ogre representative char
	 */
	public char get_ogre_char(Ogre ogre) {
		return ogre.get_char();
	}
	
	/**
	 * Get a given ogre's club representative char
	 * @param ogre ogre
	 * @return ogre's club representative char
	 */
	public char get_ogre_club_char(Ogre ogre) {
		return ogre.ch_club;
	}
			
	/**
	 * Update the game based on a player move
	 * @param option player move
	 */
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
	
	/**
	 * Convert the level to a string
	 * @return level string
	 */
	public String getGameString() {
		String ret = "";
		char[][] map = this.getMap();
		boolean flag = true;
		for(int i = 0; i<map.length; i++) {
			ret += "|" + getGameLineString(map, i, flag, ret) + "\n";
		}
		return ret;
	}
	
	/**
	 * Converts map line to a string
	 * @param map map
	 * @param i line index
	 * @param flag flag indicating if something has already been drawn at a given position 
	 * @param ret string to update
	 * @return new string
	 */
	public String getGameLineString(char[][] map, int i, boolean flag, String ret) {
		for(int j = 0; j< map[i].length; j++) {
			flag = true;
			if(this.check_hero(i, j) && flag) {
				ret += this.get_hero_char() + "|";
				flag = false;}
			if(this.check_guard(i, j) && flag) {
				ret += this.get_guard_char() + "|";
				flag = false;}
			for (Ogre ogre : this.get_ogres()) {
				if (this.check_ogre(i, j, ogre) && flag) {
					ret += this.get_ogre_char(ogre) + "|";
					flag = false;
				} else if (this.check_ogre_club(i, j, ogre) && flag) {
					ret += this.get_ogre_club_char(ogre) + "|";
					flag = false;
			}}
			if(flag) ret += map[i][j]+"|";}
		return ret;
	}
	
	/**
	 * Clone a given map
	 * @param map map to clone
	 * @return new map
	 */
	public char[][]  mapclone(char [][] map){
		char[][] newmap = new char[map.length][];
		for(int i = 0; i < map.length; i++) {
			newmap[i] = map[i].clone();
		}
		return newmap;
	}
	
	/**
	 * Get a map with every character at is current position
	 * @return new map
	 */
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
	
	/**
	 * Set a new level
	 * @param lvl new level 
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	/**
	 * Get the guard type
	 * @return guard type
	 */
	public Guard_Type getGuard_type() {
		return guard_type;
	}

	/** 
	 * Get the number of ogres
	 * @return number of ogres
	 */
	public int getNumOgres() {
		return numOgres;
	}
	
	
}
