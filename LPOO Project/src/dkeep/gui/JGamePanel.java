package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class JGamePanel extends JPanel {
	
	private char[][] map;


	public JGamePanel() {
		super();
	}
	
	public void setMap(char[][] map) {
		this.map = map;
		repaint();
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
				if(map[i][j] == 'X')
					g.setColor(Color.BLACK);
				else if(map[i][j] == ' ')
					g.setColor(Color.WHITE);
				else if(map[i][j] == 'H')
					g.setColor(Color.RED);
				else if(map[i][j] == 'K')
					g.setColor(Color.ORANGE);
				else if(map[i][j] == 'A')
					g.setColor(Color.PINK);
				else if(map[i][j] == 'G')
					g.setColor(Color.GRAY);
				else if(map[i][j] == 'g')
					g.setColor(Color.DARK_GRAY);
				else if(map[i][j] == 'O')
					g.setColor(Color.GREEN);
				else if(map[i][j] == '8')
					g.setColor(Color.BLUE);
				else if(map[i][j] == '*')
					g.setColor(Color.CYAN);
				else if(map[i][j] == 'k')
					g.setColor(Color.YELLOW);
				else if(map[i][j] == 'I')
					g.setColor(Color.BLACK);
				else if(map[i][j] == 'S')
					g.setColor(Color.MAGENTA);
				else if(map[i][j] == '$')
					g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x, y, addx -1, addy -1);
				x += addx;
			}
			x = 0;
			y += addy;
		}
	}
}
