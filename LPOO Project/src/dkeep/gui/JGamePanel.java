package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JGamePanel extends JPanel {
	
	private char[][] map;
	Image hero = new ImageIcon("resources/hero.png").getImage();
	Image herokey = new ImageIcon("resources/herokey.png").getImage();
	Image heroarmed = new ImageIcon("resources/heroarmed.png").getImage();
	Image ogre = new ImageIcon("resources/ogre.png").getImage();
	Image ogrestuned = new ImageIcon("resources/ogrestuned.png").getImage();
	Image ogreonkey = new ImageIcon("resources/ogreonkey.png").getImage();
	Image ogreclub = new ImageIcon("resources/ogreclub.png").getImage();
	Image guard = new ImageIcon("resources/guard.png").getImage();
	Image guardsleep = new ImageIcon("resources/guardsleep.png").getImage();
	Image key = new ImageIcon("resources/key.png").getImage();
	Image dooropen = new ImageIcon("resources/dooropen.png").getImage();
	Image doorclose = new ImageIcon("resources/doorclose.png").getImage();
	


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
	
	public void createMap(int x, int y) {
		this.map = new char[y][x];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(i == 0 || i == map.length - 1) {
					map[i][j] = 'X';
				}
				else if(j == 0 || j == map[i].length - 1) {
					map[i][j] = 'X';
				}
			}
		}
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
