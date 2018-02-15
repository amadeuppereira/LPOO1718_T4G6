import java.util.Scanner;

public class Game {

	static char[][] map = {{'X','X','X','X','X','X','X','X','X','X'},
					{'X',' ',' ',' ','I',' ','X',' ','G','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'X',' ','I',' ','I',' ','X',' ',' ','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X','X','X',' ','X','X','X','X',' ','X'},
					{'X',' ','I',' ','I',' ','X','k',' ','X'},
					{'X','X','X','X','X','X','X','X','X','X'}};
	
	static int hero_x = 1;
	static int hero_y = 1;
	static boolean lose = false;
	
	public static void printMap() {
		
		map[hero_x][hero_y] = 'H';
		
		for(int i = 0; i<map.length; i++) {
			System.out.print("|");
			for(int j = 0; j< map[i].length; j++) {
				System.out.print(map[i][j]+"|");
			}
			System.out.println();
		}
		
		map[hero_x][hero_y] = ' ';
	}
	
	public static void readInput() {
		System.out.print("\nEnter direction: ");
		
		Scanner s = new Scanner(System.in);
		char option = s.next().charAt(0);
		
		switch(option) {
		case 'w': 
			checkKey(hero_x-1,hero_y);
			moveHero(hero_x-1,hero_y);
			if(checkGuard()) {
				lose = true;
			}
			break;
		case 'a':
			checkKey(hero_x,hero_y-1);
			moveHero(hero_x,hero_y-1);
			if(checkGuard()) {
				lose = true;
			}
			break;
		case 's':
			checkKey(hero_x+1,hero_y);
			moveHero(hero_x+1,hero_y);
			if(checkGuard()) {
				lose = true;
			}
			break;
		case 'd':
			checkKey(hero_x,hero_y+1);
			moveHero(hero_x,hero_y+1);
			if(checkGuard()) {
				lose = true;
			}
			break;
		default:
			break;
				
		}
	}
	
	public static boolean moveHero(int x, int y) {
		
		char ch = map[x][y];
		if(ch == ' '|| ch == 'k' || ch == 'S') {
			hero_x = x;
			hero_y = y;
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean checkKey(int x, int y) {
		char ch = map[x][y];
		if(ch == 'k') {
			for(int i = 0; i<map.length; i++) {
				for(int j = 0; j< map[i].length; j++) {
					if(map[i][j] == 'I') {
						map[i][j] = 'S';
					}
				}
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean checkGuard() {
		char up = map[hero_x-1][hero_y];
		char down = map[hero_x+1][hero_y];
		char left = map[hero_x][hero_y-1];
		char right = map[hero_x][hero_y+1];
		
		if(up == 'G' || down == 'G' || left == 'G' || right == 'G') {
			return true;
		}
		else
			return false;
	}
	
	public static void main(String[] args) {
		
		printMap();
		
		while(hero_x > 0 && hero_y > 0 && !lose) {
			readInput();
			printMap();
		}
		
		if(lose) {
			System.out.println("\nPerdeu o jogo.");
		}
		else
			System.out.println("\nParabéns! Ganhou o jogo.");
		
	}
	
}
