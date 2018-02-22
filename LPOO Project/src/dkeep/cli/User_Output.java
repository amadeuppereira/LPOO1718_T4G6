package dkeep.cli;

public class User_Output {


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
			
			if(clubOnKey) {
				map[club_x][club_y] = '$';
			}
			else {
				map[club_x][club_y] = '*';
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
			
			if(clubOnKey) {
				map[club_x][club_y] = 'k';
				clubOnKey = false;
			}
			else {
				map[club_x][club_y] = ' ';
			}
		}
	}
	
}
