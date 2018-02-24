package dkeep.cli;

public class User_Output {

	public User_Output() {
		
	}

	public void printMap(String output) {
		System.out.print(output);
	}
	
	public void perdeu() {
		System.out.println("\nPerdeu o jogo.");
	}
	
	public void ganhou() {
		System.out.println("\nParabéns! Ganhou o jogo.");
	}
	
}
