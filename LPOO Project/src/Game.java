import java.util.Scanner;
import java.util.Random;

public class Game {

	static char[][] map1 = {{'X','X','X','X','X','X','X','X','X','X'},
					{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'X',' ','I',' ','I',' ','X',' ',' ','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X','X','X',' ','X','X','X','X',' ','X'},
					{'X',' ','I',' ','I',' ','X','k',' ','X'},
					{'X','X','X','X','X','X','X','X','X','X'}};
	
	static char[][] map2 = {{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ',' ',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}};
	
	static char[] guard = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	
	static int hero_x = 1;
	static int hero_y = 1;
	static int guard_x = 1;
	static int guard_y = 8;
	static int ogre_x = 1;
	static int ogre_y = 4;
	static int it = 0;
	static int level = 1;
	
	static boolean hasKey = false; //true if the hero has the key
	static boolean ogreOnKey = false; //true if the Ogre is above the key
	static boolean lose = false;
	
	public static char[][] getMap() {
		switch(level) {
		case 1:
			return map1;
		case 2:
			return map2;
		}
		return map2; //this needs to be here or else it won't work, even though it doesn't make sense
	}
	
	public static void printMap(char[][] map) {
		
		if(hasKey == false) {
			map[hero_x][hero_y] = 'H';
		}
		else {
			map[hero_x][hero_y] = 'K';
		}
		
		if(level == 1) {
			map[guard_x][guard_y] = 'G';
		}
		else if (level == 2) {
			if(ogreOnKey) {
				map[ogre_x][ogre_y] = '$';
			}
			else {
				map[ogre_x][ogre_y] = 'O';
			}
		}
		
		for(int i = 0; i<map.length; i++) {
			System.out.print("|");
			for(int j = 0; j< map[i].length; j++) {
				System.out.print(map[i][j]+"|");
			}
			System.out.println();
		}
		
		map[hero_x][hero_y] = ' ';
		if(level == 1) {
			map[guard_x][guard_y] = ' ';
		}
		else if (level == 2) {
			if(ogreOnKey) {
				map[ogre_x][ogre_y] = 'k';
				ogreOnKey = false;
			}
			else {
				map[ogre_x][ogre_y] = ' ';
			}
		}
	}
	
	public static void readInput() {
		System.out.print("\nEnter direction: ");
		
		Scanner s = new Scanner(System.in);
		char option = s.next().charAt(0);
		
		switch(option) {
		case 'w': 
			if(moveHero(hero_x-1,hero_y,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		case 'a':
			if(moveHero(hero_x,hero_y-1,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		case 's':
			if(moveHero(hero_x+1,hero_y,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		case 'd':
			if(moveHero(hero_x,hero_y+1,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		default:
			break;
				
		}
	}
	
	public static boolean moveHero(int x, int y, char[][] map) {
		
		char ch = map[x][y];
		if(ch == ' ') {
			hero_x = x;
			hero_y = y;
			return true;
		}
		else if(ch == 'k') {
			hero_x = x;
			hero_y = y;
			hasKey = true;
			return true;
		}
		else if(ch == 'I' && hasKey) {
			map[x][y] = 'S';
			hasKey = false;
			return true;
		}
		else if(ch == 'S' && (x == 0 || y == 0)) {
			level++;
			if (level == 2) {
				hero_x = 8;
				hero_y = 1;
			}
			return true;
		}
		else if(ch == 'S') { //to move the hero through the doors even though it won't lvl up
			hero_x = x;
			hero_y = y;
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void moveGuardOgre(char[][] map) {
		//moving guard
		if(level == 1) {
			switch(guard[it]) {
			case 'w': 
				guard_x -= 1;
				break;
			case 'a':
				guard_y -= 1;
				break;
			case 's':
				guard_x += 1;
				break;
			case 'd':
				guard_y += 1;
				break;
			default:
				break;	
			}
			it++;
			if (it == guard.length)
				it = 0;
		}
		//moving ogre
		else if(level == 2) {
			boolean moved = false;
			Random randomGenerator = new Random();
			int option;
			
			while(moved == false) {
				option = randomGenerator.nextInt(4); //generating a random number between 0 and 3;
				switch(option) {
				case 0: //w
					if(map[ogre_x-1][ogre_y] == ' ') {
						ogre_x -= 1;
						moved = true;
					}
					else if(map[ogre_x-1][ogre_y] == 'k') {
						ogreOnKey = true;
						ogre_x -= 1;
						moved = true;
					}
					break;
				case 1: //a
					if(map[ogre_x][ogre_y-1] == ' ') {
						ogre_y -= 1;
						moved = true;
					}
					else if(map[ogre_x][ogre_y-1] == 'k') {
						ogreOnKey = true;
						ogre_y -= 1;
						moved = true;
					}
					break;
				case 2: //s
					if(map[ogre_x+1][ogre_y] == ' ') {
						ogre_x += 1;
						moved = true;
					}
					else if(map[ogre_x+1][ogre_y] == 'k') {
						ogreOnKey = true;
						ogre_x += 1;
						moved = true;
					}
					break;
				case 3: //d
					if(map[ogre_x][ogre_y+1] == ' ') {
						ogre_y += 1;
						moved = true;
					}
					else if(map[ogre_x][ogre_y+1] == 'k') {
						ogreOnKey = true;
						ogre_y += 1;
						moved = true;
					}
					break;
				}
			}
		}
	}
	
	public static boolean checkGuardOgre() {
		//checking guard
		if(level == 1) {
			if(((guard_x == hero_x +1) && (guard_y == hero_y)) || 
					((guard_x == hero_x -1)&& (guard_y == hero_y)) ||
					((guard_x == hero_x)&& (guard_y == hero_y -1)) ||
					((guard_x == hero_x)&& (guard_y == hero_y +1))) {
				return true;
			}
			else
				return false;
		}
		//checking ogre
		else if (level == 2) {
			if(((ogre_x == hero_x +1) && (ogre_y == hero_y)) || 
					((ogre_x == hero_x -1)&& (ogre_y == hero_y)) ||
					((ogre_x == hero_x)&& (ogre_y == hero_y -1)) ||
					((ogre_x == hero_x)&& (ogre_y == hero_y +1))) {
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public static void main(String[] args) {
			
		while(level < 3 && !lose) { //for now reaching lvl 3 is winning the game
			printMap(getMap());
			readInput();
		}
		
		if(lose) {
			printMap(getMap());
			System.out.println("\nPerdeu o jogo.");
		}
		else
			System.out.println("\nParabéns! Ganhou o jogo.");
		
	}
	
}
