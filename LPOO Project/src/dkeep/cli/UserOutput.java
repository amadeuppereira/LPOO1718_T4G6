package dkeep.cli;
import dkeep.logic.GameState;
import dkeep.logic.Ogre;

public class UserOutput {

	public UserOutput() {
		
	}
	
	public void perdeu() {
		System.out.println("\nPerdeu o jogo.");
	}
	
	public void ganhou() {
		System.out.println("\nParabéns! Ganhou o jogo.");
	}

	public void printGame(GameState g) {
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
				
				if(g.check_guard(i, j)) {
					System.out.print(g.get_guard_char() + "|");
					flag = false;
				}
				for (Ogre ogre : g.get_ogres()) {
					if (g.check_ogre(i, j, ogre) && flag) {
						System.out.print(g.get_ogre_char(ogre) + "|");
						flag = false;
					} else if (g.check_ogre_club(i, j, ogre) && flag) {
						System.out.print(g.get_ogre_club_char(ogre) + "|");
						flag = false;
					}
				}
				
				if(flag) {
					System.out.print(map[i][j]+"|");
				}
			}
			System.out.println();
		}
	}
	
}
