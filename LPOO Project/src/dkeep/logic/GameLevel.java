package dkeep.logic;

public class GameLevel {

	private static char[][] map1 = {{'X','X','X','X','X','X','X','X','X','X'},
					{'X','H',' ',' ','I',' ','X',' ','G','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'X',' ','I',' ','I',' ','X',' ',' ','X'},
					{'X','X','X',' ','X','X','X',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X','X','X',' ','X','X','X','X',' ','X'},
					{'X',' ','I',' ','I',' ','X','k',' ','X'},
					{'X','X','X','X','X','X','X','X','X','X'}};

	private static char[][] map2 = {{'X','X','X','X','X','X','X','X','X'},
					{'I',' ',' ',' ','O',' ',' ','k','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ',' ',' ','X'},
					{'X','H',' ',' ',' ',' ',' ',' ','X'},
					{'X','X','X','X','X','X','X','X','X'}};
	
	private char[][] map;
	private boolean lever;
	
	public GameLevel(int n) {
		
		switch(n) {
		case 1:
			map = map1.clone();
			lever = true;
			break;
		case 2:
			map = map2.clone();
			lever = false;
			break;
		default:
			break;
		}		
	}
	
	public GameLevel(char[][] m, boolean l) {
		lever = l;
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
	
	public boolean lever() {
		if(!lever) {
			return false;
		}
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 'I') {
					map[i][j] = 'S';
				}
			}
		}
			
		return true;
	}
	
}
