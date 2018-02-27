package dkeep.logic;

public class Game_Level {

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
	
	char[][] map;
	int level;
	
	public Game_Level(int n) {
		
		switch(n) {
		
		case 1:
			map = map1.clone();
			break;
		case 2:
			map = map2.clone();
			break;
		default:
			break;
		}
		
		level = n;
		
	}
	
	public char getChar(int x, int y) {
		return map[x][y];
	}
	
	public void setChar(int x, int y, char ch) {
		map[x][y] = ch;
	}
	
}
