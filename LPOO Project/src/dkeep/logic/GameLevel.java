package dkeep.logic;

public class GameLevel {

	static char[][] map1 = {{'X','X','X','X','X','X','X','X','X','X'},
					{'X','H',' ',' ','I',' ','X',' ','G','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'X',' ','I',' ','I',' ','X',' ',' ','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X','X','X',' ','X','X','X','X',' ','X'},
					{'X',' ','I',' ','I',' ','X','k',' ','X'},
					{'X','X','X','X','X','X','X','X','X','X'}};

	static char[][] map2 = {{'X','X','X','X','X','X','X','X','X'},
					{'I',' ',' ',' ','O',' ',' ','k','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X','H',' ',' ',' ',' ',' ',' ','X'},
					{'X','X','X','X','X','X','X','X','X'}};
	
	char[][] map;
	
	public GameLevel(int n) {
		
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
	}
	
	public GameLevel(char[][] m) {
		map = m.clone();
	}
	
	public char getChar(int x, int y) {
		return map[x][y];
	}
	
	public void setChar(int x, int y, char ch) {
		map[x][y] = ch;
	}
	
	public char[][] getMap(){
		return map;
	}
	
}
