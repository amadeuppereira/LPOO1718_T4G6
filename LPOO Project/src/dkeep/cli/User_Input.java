package dkeep.cli;

import java.util.Scanner;

public class User_Input {

	public static void readInput() {
		System.out.print("\nEnter direction: ");
		
		Scanner s = new Scanner(System.in);
		char option = s.next().charAt(0);
		
		switch(option) {
		case 'w': 
			if(moveHero(hero_x-1,hero_y,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		case 'a':
			if(moveHero(hero_x,hero_y-1,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		case 's':
			if(moveHero(hero_x+1,hero_y,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		case 'd':
			if(moveHero(hero_x,hero_y+1,getMap())) {
				moveGuardOgre(getMap());
			}
			if(checkGuardOgre()) {
				lose = true;
			}
			break;
		default:
			break;
				
		}
	}
}
