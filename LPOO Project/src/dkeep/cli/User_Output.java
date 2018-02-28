package dkeep.cli;
import dkeep.logic.Game_State;
import dkeep.logic.Ogre;

public class User_Output {

	public User_Output() {
		
	}
	
	public void perdeu() {
		System.out.println("\nPerdeu o jogo.");
	}
	
	public void ganhou() {
		System.out.println("\nParabéns! Ganhou o jogo.");
	}
	
	public void printGame(Game_State g) {
		char[][] map = g.getMap();
		boolean flag = true;
		
		
		for(int i = 0; i<map.length; i++) {
			System.out.print("|");
			for(int j = 0; j< map[i].length; j++) {
				flag = true;
				
				if(g.check_hero(i, j)) {
					System.out.print(g.get_hero_char() + "|");
					flag = false;
				}
				
				switch(g.getLevel()) {
				case 1:
					if(g.getLevel() == 1 && g.check_guard(i, j)) {
						System.out.print(g.get_guard_char() + "|");
						flag = false;
					}
					break;
				case 2:
					for(Ogre ogre : g.ogres) {
						if(g.check_ogre(i, j, ogre) && flag) {
							System.out.print(g.get_ogre_char(ogre) + "|");
							flag = false;
						}
						else if (g.check_ogre_club(i, j, ogre) && flag) {
							System.out.print(g.get_ogre_club_char(ogre) + "|");
							flag = false;
						}
					}
					break;
				default:
					break;
				}
				
				if(flag) {
					System.out.print(map[i][j]+"|");
				}
			}
			System.out.println();
		}
	}
	
}
