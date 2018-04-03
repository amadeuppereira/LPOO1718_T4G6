package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JGamePanel extends JPanel {
	
	private char[][] map;
	private Image hero = new ImageIcon("resources/hero.png").getImage();
	private Image herokey = new ImageIcon("resources/herokey.png").getImage();
	private Image heroarmed = new ImageIcon("resources/heroarmed.png").getImage();
	private Image ogre = new ImageIcon("resources/ogre.png").getImage();
	private Image ogrestuned = new ImageIcon("resources/ogrestuned.png").getImage();
	private Image ogreonkey = new ImageIcon("resources/ogreonkey.png").getImage();
	private Image ogreclub = new ImageIcon("resources/ogreclub.png").getImage();
	private Image guard = new ImageIcon("resources/guard.png").getImage();
	private Image guardsleep = new ImageIcon("resources/guardsleep.png").getImage();
	private Image key = new ImageIcon("resources/key.png").getImage();
	private Image dooropen = new ImageIcon("resources/dooropen.png").getImage();
	private Image doorclose = new ImageIcon("resources/doorclose.png").getImage();
	


	public JGamePanel() {
		super();
	}
	
	public void setMap(char[][] map) {
		this.map = map;
		repaint();
	}
	
	public char[][] getMap() {
		return map;
	}
	
	public void setChar(int x, int y, char ch) {
		map[y][x] = ch;
	}
	
	public void createMap(int x, int y) {
		this.map = new char[y][x];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if((i == 0 || i == map.length - 1) || (j == 0 || j == map[i].length - 1)) {
					map[i][j] = 'X';
				}
				else {
					map[i][j] = ' ';
				}
			}
		}
	}
	
	public Set<Integer> acceptable() {
		Set<Integer> ret = new HashSet<Integer>();
		
		boolean hero = false, ogre = false, door = false, key = false;
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				switch(map[i][j]) {
				case 'I':
					if(i == 0 || j == 0 || i == map.length - 1 || j == map[i].length - 1) {
						door = true;
					}
					break;
				case 'H':
					if(hero) {
						ret.add(1); // invalid number of heros
					} else {
						hero = true;
					}
					break;
				case 'O':
					if(ogre) {
						ret.add(2); // invalid number of ogres
					} else {
						ogre = true;
					}
					break;
				case 'k':
					key = true;
					break;
				default:
					break;
				}
					
			}
			
		}
		
		if(!hero) {
			ret.add(1);
		}
		if(!ogre) {
			ret.add(2);
		}
		if(!key) {
			ret.add(3); // no keys
		}
		if(!door) {
			ret.add(4); // no valid door
		}
		
		
		return ret;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (map == null) return;
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		int addx = this.getWidth() / map[0].length;
		int addy = this.getHeight() / map.length;
		int x = 0, y = 0;
		
		for(int i = 0; i<map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 'X') {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, addx -1, addy -1);
				}
				else if(map[i][j] == ' ') {
					g.setColor(Color.WHITE);
					g.fillRect(x, y, addx -1, addy -1);
				}
				else if(map[i][j] == 'H') {
					g.drawImage(hero, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'K') {
					g.drawImage(herokey, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'A') {
					g.drawImage(heroarmed, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'G') {
					g.drawImage(guard, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'g') {
					g.drawImage(guardsleep, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'O') {
					g.drawImage(ogre, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == '8') {
					g.drawImage(ogrestuned, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == '*') {
					g.drawImage(ogreclub, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'k') {
					g.drawImage(key, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'I') {
					g.drawImage(doorclose, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == 'S') {
					g.drawImage(dooropen, x, y, addx-1, addy-1, this);
				}
				else if(map[i][j] == '$') {
					g.drawImage(ogreonkey, x, y, addx-1, addy-1, this);
				}
				x += addx;
			}
			x = 0;
			y += addy;
		}
	}
}
