package dkeep.cli;

public class UserOutput {

	public UserOutput() {
		
	}
	
	public void perdeu() {
		System.out.println("\nPerdeu o jogo.");
	}
	
	public void ganhou() {
		System.out.println("\nParabens! Ganhou o jogo.");
	}

	public void printGame(String gamestring) {
		System.out.println(gamestring);
	}
	
}
