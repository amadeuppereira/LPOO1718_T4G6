package dkeep.cli;

/**
 * UserOutput.java - class handling user output 
 */
public class UserOutput {

	/**
	 * Create a new UserOutput object
	 */
	public UserOutput() {
	}
	
	/**
	 * Print loss message
	 */
	public void perdeu() {
		System.out.println("\nPerdeu o jogo.");
	}
	
	/**
	 * Print win message
	 */
	public void ganhou() {
		System.out.println("\nParabens! Ganhou o jogo.");
	}

	/**
	 * Print a given string
	 * @param gamestring string
	 */
	public void printGame(String gamestring) {
		System.out.println(gamestring);
	}
	
}
