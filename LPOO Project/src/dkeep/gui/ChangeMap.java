package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;

import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.logic.GameLevel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.ImageIcon;

public class ChangeMap {
	
	private enum Option {NONE, WALL, PATH, HERO, OGRE, KEY, DOOR};
	Option op = Option.NONE;
	

	private JFrame frameChangeMap;
	private JTextField textSizeX;
	private JTextField textSizeY;
	private JGamePanel mapPanel;
	private JButton btnDone;
	JButton btnCreateMap;
	private JFrame frameMenu;
	ImageIcon minionhappyicon = new ImageIcon("resources/happyminion.png");
	ImageIcon minionsadicon = new ImageIcon("resources/sadminion.png");
	ImageIcon hero = new ImageIcon(new ImageIcon("resources/hero.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon ogre = new ImageIcon(new ImageIcon("resources/ogre.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon key = new ImageIcon(new ImageIcon("resources/key.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon door = new ImageIcon(new ImageIcon("resources/doorclose.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    BufferedImage wall = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
    ImageIcon path = new ImageIcon(new ImageIcon("resources/white.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

	JLabel lblWall;
	JLabel lblPath;
	JLabel lblKey;
	JLabel lblOgre;
	JLabel lblHero;
	JLabel lblDoor;

	/**
	 * Create the application.
	 */
	public ChangeMap(JFrame frameMenu) {
		this.frameMenu = frameMenu;	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameChangeMap = new JFrame();
		frameChangeMap.getContentPane().setFont(new Font("Courier New", Font.PLAIN, 11));
		frameChangeMap.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frameMenu.setVisible(true);
				super.windowClosing(e);
			}
		});
		
		JPanel newMapConfig = new JPanel();
		
		mapPanel = new JGamePanel();
		mapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gamePanelHandler(arg0);
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setToolTipText("");
		GroupLayout groupLayout = new GroupLayout(frameChangeMap.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(newMapConfig, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
							.addGap(433))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(mapPanel, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(newMapConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
							.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(mapPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		lblWall = new JLabel("Wall");
		lblWall.setIcon(new ImageIcon(wall));
		lblWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.WALL;
				setOptionsEnabled();
			}
		});
		
		lblPath = new JLabel("Path");
		lblPath.setIcon(path);
		lblPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.PATH;
				setOptionsEnabled();
			}
		});
		
		lblKey = new JLabel("Key");
		lblKey.setIcon(key);
		lblKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.KEY;
				setOptionsEnabled();
			}
		});
		
		lblHero = new JLabel("Hero");
		lblHero.setIcon(hero);
		lblHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.HERO;
				setOptionsEnabled();
			}
		});
		
		lblOgre = new JLabel("Ogre");
		lblOgre.setIcon(ogre);
		lblOgre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.OGRE;
				setOptionsEnabled();
			}
		});
		
		lblDoor = new JLabel("Door");
		lblDoor.setIcon(door);
		lblDoor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.DOOR;
				setOptionsEnabled();
			}
		});
		GroupLayout gl_optionsPanel = new GroupLayout(optionsPanel);
		gl_optionsPanel.setHorizontalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblWall)
						.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblOgre)
							.addComponent(lblKey)))
					.addGap(44)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPath)
						.addComponent(lblDoor)
						.addComponent(lblHero))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_optionsPanel.setVerticalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWall)
						.addComponent(lblPath))
					.addGap(50)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKey)
						.addComponent(lblDoor))
					.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOgre)
						.addComponent(lblHero))
					.addGap(33))
		);
		optionsPanel.setLayout(gl_optionsPanel);
		
		btnCreateMap = new JButton("Create Map");
		btnCreateMap.setEnabled(false);
		btnCreateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapHandler();
			}
		});
		
		JButton btnCancel = new JButton("Return to Menu");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChangeMap.dispose();
				frameMenu.setVisible(true);
			}
		});
		GroupLayout gl_buttonsPanel = new GroupLayout(buttonsPanel);
		gl_buttonsPanel.setHorizontalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(btnCreateMap)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		gl_buttonsPanel.setVerticalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_buttonsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateMap)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		buttonsPanel.setLayout(gl_buttonsPanel);
		
		JLabel lblSize = new JLabel("Size:");
		
		textSizeX = new JTextField();
		textSizeX.setColumns(10);
		
		JLabel lblX = new JLabel("x");
		
		textSizeY = new JTextField();
		textSizeY.setColumns(10);
		
		btnDone = new JButton("Set Size");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSizeHandler();
			}
		});
		GroupLayout gl_newMapConfig = new GroupLayout(newMapConfig);
		gl_newMapConfig.setHorizontalGroup(
			gl_newMapConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newMapConfig.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSize)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textSizeX, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblX)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textSizeY, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnDone)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_newMapConfig.setVerticalGroup(
			gl_newMapConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newMapConfig.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_newMapConfig.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSize)
						.addComponent(textSizeX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX)
						.addComponent(textSizeY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDone))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		newMapConfig.setLayout(gl_newMapConfig);
		frameChangeMap.getContentPane().setLayout(groupLayout);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frameChangeMap.setBounds((screen.width-733)/2, (screen.height-550)/2, 733, 550);	
	}
	
	void createMapHandler() {
		Set<Integer> errors = mapPanel.acceptable();
		if(errors.size() == 0) {
			GameLevel a = new GameLevel(0);
			a.setMap2(mapPanel.getMap());
			JOptionPane.showMessageDialog(frameChangeMap, "Map number 2 changed!","Map Changed",JOptionPane.INFORMATION_MESSAGE,minionhappyicon);
			frameChangeMap.dispose();
			frameMenu.setVisible(true);
		}
		else {
			String errormessage = "";
			for(Integer i: errors) {
				switch(i) {
				case 1:
					errormessage += "-Invalid number of Heroes. There can only be one hero!\n";
					break;
				case 2:
					errormessage += "-Invalid number of Ogres. There can only be one start position for the Ogres!\n";
					break;
				case 3:
					errormessage += "-Missing key. You need to have at least one key\n";
					break;
				case 4:
					errormessage += "-No door in a valid position. You need to have at least one door in the borders of the map!\n";
					break;
				default:
					break;
				}
			}
			JOptionPane.showMessageDialog(frameChangeMap,errormessage,"Map Error",JOptionPane.INFORMATION_MESSAGE,minionsadicon);
		}
		
	}
	
	void setMapSize() {
		int x = -1, y = -1;
		if (!textSizeX.getText().equals("")) {
			try {
				x = Integer.parseInt(textSizeX.getText());
			} catch (NumberFormatException e) {
				x = -1;
			}
		}
		
		if (!textSizeY.getText().equals("")) {
			try {
				y = Integer.parseInt(textSizeY.getText());
			} catch (NumberFormatException e) {
				y = -1;
			}
		}
		
		if(x > 0 && y > 0) {
			mapPanel.createMap(x, y);
			btnDone.setEnabled(false);
			textSizeX.setEditable(false);
			textSizeY.setEditable(false);
		}
		else {
			JOptionPane.showMessageDialog(frameChangeMap,"Erro no tamanho!","Error",JOptionPane.INFORMATION_MESSAGE,minionsadicon);
		}
		
	}
	
	private void setSizeHandler() {
		setMapSize();
		mapPanel.repaint();
		btnCreateMap.setEnabled(true);
		
	}
	
	private void setOptionsEnabled() {
		lblWall.setEnabled(true);
		lblPath.setEnabled(true);
		lblDoor.setEnabled(true);
		lblKey.setEnabled(true);
		lblHero.setEnabled(true);
		lblOgre.setEnabled(true);
		switch(op) {
		case WALL:
			lblWall.setEnabled(false);
			break;
		case PATH:
			lblPath.setEnabled(false);
			break;
		case DOOR:
			lblDoor.setEnabled(false);
			break;
		case KEY:
			lblKey.setEnabled(false);
			break;
		case HERO:
			lblHero.setEnabled(false);
			break;
		case OGRE:
			lblOgre.setEnabled(false);
			break;
		default:
			break;
		}
		mapPanel.requestFocus();
	}
	
	private void gamePanelHandler(MouseEvent arg0) {
		if(mapPanel.getMap() == null) return;
		int tempx = mapPanel.getWidth()/mapPanel.getMap()[0].length;
		int tempy = mapPanel.getHeight()/mapPanel.getMap().length;
		
		int x = arg0.getX()/tempx;
		int y = arg0.getY()/tempy;
	
		switch(op) {
		case WALL:
			mapPanel.setChar(x, y, 'X');
			break;
		case PATH:
			mapPanel.setChar(x, y, ' ');
			break;
		case DOOR:
			mapPanel.setChar(x, y, 'I');
			break;
		case KEY:
			mapPanel.setChar(x, y, 'k');
			break;
		case HERO:
			mapPanel.setChar(x, y, 'H');
			break;
		case OGRE:
			mapPanel.setChar(x, y, 'O');
			break;
		default:
			break;
		}
		
		mapPanel.repaint();
		
	}
	
	public JFrame getFrame() {
		return frameChangeMap;
	}
	
}
