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
	private Option op = Option.NONE;
	
	private JFrame frameChangeMap;
	private JTextField textSizeX;
	private JTextField textSizeY;
	private JGamePanel mapPanel;
	private JButton btnDone;
	private JButton btnCreateMap;
	private JButton btnCancel;
	private JFrame frameMenu;
	private JLabel lblSize;
	private JLabel lblX;
	private JLabel lblSelectLevel;
	private JComboBox LvlcomboBox;
	private GameLevel a;
	private ImageIcon minionhappyicon = new ImageIcon("resources/happyminion.png");
	private ImageIcon minionsadicon = new ImageIcon("resources/sadminion.png");
	private ImageIcon hero = new ImageIcon(new ImageIcon("resources/hero.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	private ImageIcon ogre = new ImageIcon(new ImageIcon("resources/ogre.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	private ImageIcon key = new ImageIcon(new ImageIcon("resources/key.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	private ImageIcon door = new ImageIcon(new ImageIcon("resources/doorclose.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	private  BufferedImage wall = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
	private  ImageIcon path = new ImageIcon(new ImageIcon("resources/white.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

	private JPanel newMapConfig;
	private JPanel buttonsPanel;
	private JPanel optionsPanel;
	private GroupLayout groupLayout;
	private GroupLayout gl_optionsPanel;
	private GroupLayout gl_buttonsPanel;
	private GroupLayout gl_newMapConfig;
	
	private JLabel lblWall;
	private JLabel lblPath;
	private JLabel lblKey;
	private JLabel lblOgre;
	private JLabel lblHero;
	private JLabel lblDoor;

	/**
	 * Create the application.
	 */
	public ChangeMap(JFrame frameMenu) {
		this.frameMenu = frameMenu;
		a = new GameLevel(0);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameInitialize();
		gamePanelInitialize();
		labelsInitialize();
		labels2Initialize();
		labels3Initialize();
		labels4Initialize();
		labels5Initialize();
		labels6Initialize();
		buttonsInitialize();
		moreInitializes();
		panelHandler();
	}
	
	private void frameInitialize() {
		frameChangeMap = new JFrame();
		frameChangeMap.getContentPane().setFont(new Font("Courier New", Font.PLAIN, 11));
		frameChangeMap.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frameMenu.setVisible(true);
				super.windowClosing(e);
			}
		});
	}
	
	private void gamePanelInitialize() {
		mapPanel = new JGamePanel();
		mapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				gamePanelHandler(arg0);
			}
		});
	}
	
	private void labelsInitialize() {
		lblWall = new JLabel("Wall");
		lblWall.setIcon(new ImageIcon(wall));
		lblWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.WALL;
				setOptionsEnabled();
			}
		});
	}
	
	private void labels6Initialize() {
		lblPath = new JLabel("Path");
		lblPath.setIcon(path);
		lblPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.PATH;
				setOptionsEnabled();
			}
		});
	}
	
	private void labels2Initialize() {
		lblHero = new JLabel("Hero");
		lblHero.setIcon(hero);
		lblHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.HERO;
				setOptionsEnabled();
			}
		});
	}
	
	private void labels5Initialize() {
		lblOgre = new JLabel("Ogre");
		lblOgre.setIcon(ogre);
		lblOgre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.OGRE;
				setOptionsEnabled();
			}
		});
	}
	
	private void labels3Initialize() {
		lblDoor = new JLabel("Door");
		lblDoor.setIcon(door);
		lblDoor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.DOOR;
				setOptionsEnabled();
			}
		});
	}
	
	private void labels4Initialize() {
		lblKey = new JLabel("Key");
		lblKey.setIcon(key);
		lblKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op = Option.KEY;
				setOptionsEnabled();
			}
		});
	}
	
	private void buttonsInitialize() {
		btnCreateMap = new JButton("Create Map");
		btnCreateMap.setEnabled(false);
		btnCreateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapHandler();
			}
		});
		btnCancel = new JButton("Return to Menu");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChangeMap.dispose();
				frameMenu.setVisible(true);
			}
		});
		btnDone = new JButton("Set Size");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSizeHandler();
			}
		});
	}
	
	private void moreInitializes() {
		lblSize = new JLabel("Size:");
		textSizeX = new JTextField();
		textSizeX.setColumns(10);
		lblX = new JLabel("x");
		textSizeY = new JTextField();
		textSizeY.setColumns(10);
		lblSelectLevel = new JLabel("Select Level:");	
		LvlcomboBox = new JComboBox(getLevelsString());
	}
	
	private void panelHandler() {
		newMapConfig = new JPanel();
		buttonsPanel = new JPanel();
		optionsPanel = new JPanel();
		optionsPanel.setToolTipText("");
		newMapConfigLayoutHandler();
		gl_optionsLayoutHandler();
		gl_buttonsLayoutHandler();
		gl_newMapConfigLayoutHandler();
	}
	
	private void newMapConfigLayoutHandler(){
		groupLayout = new GroupLayout(frameChangeMap.getContentPane());
		newMapConfigLayoutHandler_Horizontal();
		newMapConfigLayoutHandler_Vertical();
		frameChangeMap.getContentPane().setLayout(groupLayout);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frameChangeMap.setBounds((screen.width-733)/2, (screen.height-550)/2, 733, 550);	
	}
	
	private void newMapConfigLayoutHandler_Horizontal() {
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(newMapConfig, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(mapPanel, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
									.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
			);
	}
	
	private void newMapConfigLayoutHandler_Vertical() {
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
	}
	
	private void gl_optionsLayoutHandler() {
		gl_optionsPanel = new GroupLayout(optionsPanel);
		gl_optionsLayoutHandler_Horizontal();
		gl_optionsLayoutHandler_Vertical();
		optionsPanel.setLayout(gl_optionsPanel);
	}
	
	private void gl_optionsLayoutHandler_Horizontal() {
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
	}
	
	private void gl_optionsLayoutHandler_Vertical() {
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
	}
	
	private void gl_buttonsLayoutHandler() {
		gl_buttonsPanel = new GroupLayout(buttonsPanel);
		gl_buttonsLayoutHandler_Horizontal();
		gl_buttonsLayoutHandler_Vertical();
		buttonsPanel.setLayout(gl_buttonsPanel);
	}
	
	private void gl_buttonsLayoutHandler_Horizontal() {
		gl_buttonsPanel.setHorizontalGroup(
				gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonsPanel.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(btnCreateMap)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addGap(49))
			);
	}
	
	private void gl_buttonsLayoutHandler_Vertical() {
		gl_buttonsPanel.setVerticalGroup(
				gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonsPanel.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addGroup(gl_buttonsPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCreateMap)
							.addComponent(btnCancel))
						.addContainerGap())
			);
	}

	private void gl_newMapConfigLayoutHandler() {
		gl_newMapConfig = new GroupLayout(newMapConfig);
		gl_newMapConfigLayoutHandler_Horizontal();
		gl_newMapConfigLayoutHandler_Vertical();
		newMapConfig.setLayout(gl_newMapConfig);
	}
	
	private void gl_newMapConfigLayoutHandler_Horizontal() {
		gl_newMapConfig.setHorizontalGroup(
				gl_newMapConfig.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_newMapConfig.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblSelectLevel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(LvlcomboBox, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addGap(28)
						.addComponent(lblSize)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textSizeX, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblX)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textSizeY, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDone)
						.addContainerGap(109, Short.MAX_VALUE))
			);
	}
	
	private void gl_newMapConfigLayoutHandler_Vertical() {
		gl_newMapConfig.setVerticalGroup(
				gl_newMapConfig.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_newMapConfig.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_newMapConfig.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSelectLevel)
							.addComponent(LvlcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSize)
							.addComponent(textSizeX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblX)
							.addComponent(textSizeY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnDone))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
	}
	
	private void createMapHandler() {
		Set<Integer> errors = mapPanel.acceptable();
		if(errors.size() == 0) {
			String option = (String) LvlcomboBox.getSelectedItem();
			if(option.equals("New Level")) {
				a.setMap(a.getMaps().size() + 1,mapPanel.getMap());
				JOptionPane.showMessageDialog(frameChangeMap, "New Map created!","Map Changed",JOptionPane.INFORMATION_MESSAGE,minionhappyicon);
			}
			else {
				a.setMap(Integer.parseInt(option),mapPanel.getMap());
				JOptionPane.showMessageDialog(frameChangeMap, "Map number " + Integer.parseInt(option) + " changed!","Map Changed",JOptionPane.INFORMATION_MESSAGE,minionhappyicon);
			}
			frameChangeMap.dispose();
			frameMenu.setVisible(true);
		}
		else {
			String errormessage = getErrorMessage(errors);
			JOptionPane.showMessageDialog(frameChangeMap,errormessage,"Map Error",JOptionPane.INFORMATION_MESSAGE,minionsadicon);
		}
	}
	
	private String getErrorMessage(Set<Integer> errors) {
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
		return errormessage;
	}
	
	private void setMapSize() {
		int x = -1, y = -1;
		if (!textSizeX.getText().equals("") && !textSizeY.getText().equals("")) {
			try {
				x = Integer.parseInt(textSizeX.getText());
				y = Integer.parseInt(textSizeY.getText());
			} catch (NumberFormatException e) {
				x = -1;
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
	
	private void enableAllOptions(boolean b) {
		lblWall.setEnabled(b);
		lblPath.setEnabled(b);
		lblDoor.setEnabled(b);
		lblKey.setEnabled(b);
		lblHero.setEnabled(b);
		lblOgre.setEnabled(b);
	}
	
	private void setOptionsEnabled() {
		enableAllOptions(true);
		switch(op) {
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
		setOptionsEnabled2();
		mapPanel.requestFocus();
	}
	
	private void setOptionsEnabled2() {
		switch (op) {
		case WALL:
			lblWall.setEnabled(false);
			break;
		case PATH:
			lblPath.setEnabled(false);
			break;
		case DOOR:
			lblDoor.setEnabled(false);
			break;
		default:
			break;
		}
	}
	
	private void changeMapChar(int x, int y) {
		switch(op) {
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
		changeMapChar2(x, y);
	}
	
	private void changeMapChar2(int x, int y) {
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
		default:
			break;
		}
	}
	
	private void gamePanelHandler(MouseEvent arg0) {
		if(mapPanel.getMap() == null) return;
		int tempx = mapPanel.getWidth()/mapPanel.getMap()[0].length;
		int tempy = mapPanel.getHeight()/mapPanel.getMap().length;
		int x = arg0.getX()/tempx;
		int y = arg0.getY()/tempy;
		changeMapChar(x, y);
		mapPanel.repaint();
	}
	
	public JFrame getFrame() {
		return frameChangeMap;
	}
	
	private String[] getLevelsString() {
		String[] ret = new String[a.getMaps().size()];
		for(int i = 0; i < a.getMaps().size() - 1; i++) {
			ret[i] = Integer.toString(i + 2);
		}
		ret[a.getMaps().size() - 1]= "New Level";
		return ret;
	}
}
