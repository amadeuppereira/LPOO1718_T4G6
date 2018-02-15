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
			moveHero(hero_x-1,hero_y);
			break;
		case 'a':
			moveHero(hero_x,hero_y-1);
			break;
		case 's':
			moveHero(hero_x+1,hero_y);
			break;
		case 'd':
			moveHero(hero_x,hero_y+1);
			break;
		default:
			break;
				
		}
	}
	
	public static boolean moveHero(int x, int y) {
		
		char ch = map[x][y];
		if(ch == ' ') {
			hero_x = x;
			hero_y = y;
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		printMap();
		
		while(true) {
			readInput();
			printMap();
		}
		
		

	}
	
}
