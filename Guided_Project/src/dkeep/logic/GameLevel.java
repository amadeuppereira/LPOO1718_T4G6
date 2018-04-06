package dkeep.logic;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * GameLevel.java - a class that handles the game maps and levels
 */
public class GameLevel {
	
	private char[][] map;
	private boolean lever;
	private ArrayList<char[][]> maps;
	
	public GameLevel(int n) {
		maps = new ArrayList<char[][]> ();
		readFile();
		if(n == 0) return;
		if(n <= maps.size()) {
			map = mapclone(maps.get(n - 1));
			if(n == 1)
				lever = true;
			else
				lever = false;
		}	
	}
	
	/**
	 * Clones a given two dimensional array
	 * @param map given array
	 * @return new array
	 */
	public char[][]  mapclone(char [][] map){
		char[][] newmap = new char[map.length][];
		for(int i = 0; i < map.length; i++) {
			newmap[i] = map[i].clone();
		}
		return newmap;
	}
	
	/**
	 * Create a new GameLever object
	 * @param m map array
	 * @param l lever flag
	 */
	public GameLevel(char[][] m, boolean l) {
		lever = l;
		map = m.clone();
	}
	
	/**
	 * Get a given position char from the map
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return char
	 */
	public char getChar(int x, int y) {
		return map[x][y];
	}
	
	/**
	 * Set a given char to a map position
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param ch new char
	 */
	public void setChar(int x, int y, char ch) {
		map[x][y] = ch;
	}
	
	/**
	 * Get the map array
	 * @return map array
	 */
	public char[][] getMap(){
		return map;
	}
	
	/**
	 * Activate lever, if it is a level level it opens all the doors (change their representative char) 
	 * @return true if lever level, false otherwise
	 */
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
	
	/**
	 * Change a level map (or creates a new one if the level doesn't exist)
	 * @param n level
	 * @param m map
	 */
	public void setMap(int n, char[][] m) {
		if(n <= maps.size())
			maps.set(n - 1, m);
		else
			maps.add(m);
		saveFile();
	}
	
	/**
	 * Get all the maps
	 * @return arraylist with all the maps
	 */
	public ArrayList<char[][]> getMaps() {
		return maps;
	}
	
	/**
	 * Read the maps file
	 */
	public void readFile() {
		FileReader fr = null;
		BufferedReader br = null;
		String line = "";
		try {
			fr = new FileReader("maps.txt");
			br = new BufferedReader(fr);
			
			line = br.readLine();
			while(line != null) {
				analyseLine(line, br);
				line = br.readLine();
			}
			br.close();
			fr.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Transform a string into a 2 dimensional array
	 * @param line line
	 * @param br buffered reader
	 */
	void analyseLine(String line, BufferedReader br){
		int sizex = 0, sizey = 0;
		char[][] newmap;
		try {
			sizex = Integer.parseInt(line);
			line = br.readLine();
			sizey = Integer.parseInt(line);
			newmap = new char[sizex][sizey];
			for (int i = 0; i < sizex; i++) {
				for (int j = 0; j < sizey; j++) {
					line = br.readLine();
					newmap[i][j] = line.charAt(0);
				}
			}
			maps.add(newmap);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Save all the maps to the file
	 */
	public void saveFile() {
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("maps.txt"), "utf-8"));
		    for(int n = 0; n < maps.size(); n++) {
		    	writer.write(new Integer(maps.get(n).length).toString());
		    writer.newLine();
		    writer.write(new Integer(maps.get(n)[0].length).toString());
		    writer.newLine();
				for(int i = 0; i < maps.get(n).length; i++) {
					for(int j = 0; j < maps.get(n)[0].length; j++) {
						writer.write(maps.get(n)[i][j]);
						if(!(i == maps.get(n).length-1 && j == maps.get(n)[0].length-1 && n == maps.size()-1)) writer.newLine();
					}
				}
		    }
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
}