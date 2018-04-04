package dkeep.logic;
import java.util.Random;

import java.util.ArrayList;

public class GameState {
	
	public enum State {WIN, DEFEAT, PLAYING}
	public enum Movement {UP, DOWN, LEFT, RIGHT}
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

	public GameState() {
		Random r = new Random();
		lvl = 1;
		numOgres = r.nextInt(3) + 1;
		game_level = new GameLevel(lvl);
		guard_type = Guard_Type.RANDOM;
		state = State.PLAYING;
		create_Level();
	}
	
	public GameState(GameLevel g) {
		guard_mov = new char[1];
		numOgres = 1;
		game_level = g;
		guard_type = Guard_Type.RANDOM;
		state = State.PLAYING;
		create_Level();
	}
	
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
	
	public State get_status() {
		return state;
	}
	
	public CellPosition getRandomAvailableAdjacentPos(CellPosition pos) {
		CellPosition newPos = null;
		boolean available = false;
		while(!available) {
			newPos = pos.getRandomAdjPos();
			available = isPossiblePos(newPos.get_positionX(), newPos.get_positionY());
		}
		return newPos;
	}
	
	//the flag indicates if it is possible to go to coordinates 0 (x or y)
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
		if(ogres.size() > 0) hero.armed(true);
	}
	
	private boolean isPossiblePos(int x, int y) {
		char ch = game_level.getChar(x, y);
		if(ch == 'X' || (ch == 'I' && !hero.check_key())) {
			return false;
		}
		
		return true;
	}
	
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
	
	public void move_Enemy() {
		if(guard != null) {
			guard.move();
		}
		for (Ogre ogre : ogres) {
			move_Ogre(ogre);
			move_OgreClub(ogre);
		}
	}
	
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
	
	private boolean updateOgreState(Ogre ogre) {
		if (hero.has_arm()) {
			ogre.stunned(true);
		} else {
			return true;
		}
		return false;
	}
	
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
	
	public boolean isGameover() {
		if(isGameoverGuard()) state = State.DEFEAT;
		if(isGameoverOgres()) state = State.DEFEAT;
		if (state == State.DEFEAT) return true;
		else return false;
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
			ret += "|" + getGameLineString(map, i, flag, ret) + "\n";
		}
		return ret;
	}
	
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
