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

	public void printGame(String gamestring) {
		System.out.println(gamestring);
	}
	
}
