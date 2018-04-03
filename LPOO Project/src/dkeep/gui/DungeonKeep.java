package dkeep.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import dkeep.logic.GameState;
import dkeep.logic.GameState.Movement;
import dkeep.logic.GameState.State;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class DungeonKeep {

	private JFrame frameMenu;
	private ImageIcon dungeon = new ImageIcon("resources/dungeon.png");
	private ImageIcon keep = new ImageIcon("resources/keep.png");
	private JButton btnChangeMap;
	private JButton btnExitGame;
	private JButton btnStartGame;
	private JLabel keeplbl;
	private JLabel dungeonlbl;
	private GroupLayout groupLayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonKeep windowMenu = new DungeonKeep();
					windowMenu.frameMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public DungeonKeep() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMenu = new JFrame();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frameMenu.setBounds((screen.width-631)/2, (screen.height-460)/2, 631, 460);
		frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		dungeonlbl = new JLabel(dungeon);
		keeplbl = new JLabel(keep);
		
		buttonInitialize();
		layout();
	}
	
	private void buttonInitialize() {
		btnStartGame = new JButton("Start Game");
		btnStartGame.setForeground(Color.BLACK);
		btnStartGame.setFont(new Font("Courier New", Font.BOLD, 16));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				GameConfigurations windowConfig = new GameConfigurations(frameMenu);
				windowConfig.getFrameConfig().setVisible(true);
			}
		});
		
		btnExitGame = new JButton("Exit Game");
		btnExitGame.setFont(new Font("Courier New", Font.BOLD, 16));
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnChangeMap = new JButton("Create/Change Map");
		btnChangeMap.setFont(new Font("Courier New", Font.BOLD, 16));
		btnChangeMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				ChangeMap windowConfig = new ChangeMap(frameMenu);
				windowConfig.getFrame().setVisible(true);
			}
		});
	}
	
	private void layout() {
		groupLayout = new GroupLayout(frameMenu.getContentPane());
		layout_Horizontal();
		layout_Vertical();
		frameMenu.getContentPane().setLayout(groupLayout);
	}
	
	private void layout_Horizontal() {
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(29)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(121)
								.addComponent(keeplbl, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
								.addGap(122))
							.addComponent(dungeonlbl, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
						.addGap(29))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(222)
						.addComponent(btnStartGame, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addGap(222))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(222)
						.addComponent(btnChangeMap, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addGap(222))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(222)
						.addComponent(btnExitGame, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addGap(222))
			);
	}
	
	private void layout_Vertical() {
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(106)
								.addComponent(keeplbl, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(dungeonlbl, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addGap(106)))
						.addGap(24)
						.addComponent(btnStartGame, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
						.addGap(12)
						.addComponent(btnChangeMap, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
						.addGap(11)
						.addComponent(btnExitGame, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
						.addGap(28))
			);
	}
}
