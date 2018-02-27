package dkeep.cli;
import dkeep.logic.Game_State;

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
		
		
		for(int i = 0; i<map.length; i++) {
			System.out.print("|");
			for(int j = 0; j< map[i].length; j++) {
				
				if(g.check_hero(i, j)) {
					System.out.print(g.get_hero_char() + "|");
				}
				
				else if(g.getLevel() == 1 && g.check_guard(i, j)) {
					System.out.print(g.get_guard_char() + "|");
				}
				
				else if(g.getLevel() == 2 && g.check_ogre(i, j)) {
					System.out.print(g.get_ogre_char() + "|");
				}
				
				else if(g.getLevel() == 2 && g.check_ogre_club(i, j)) {
					System.out.print(g.get_ogre_club_char() + "|");
				}
				
				else {
					System.out.print(map[i][j]+"|");
				}
			}
			System.out.println();
		}
	}
	
}
