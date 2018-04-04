package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dkeep.logic.GameState;
import dkeep.logic.GameState.Movement;
import dkeep.logic.GameState.State;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GameWindow {

	private JFrame frameWindow;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JGamePanel gamePanel;
	private ImageIcon minionhappyicon = new ImageIcon("resources/happyminion.png");
	private ImageIcon minionsadicon = new ImageIcon("resources/sadminion.png");
	private GroupLayout groupLayout;
	public GameState game;

	/**
	 * Create the application.
	 */
	public GameWindow(GameState game) {
		this.game = game;
		initialize();
		
		frameWindow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(game != null && game.get_status() == State.PLAYING) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						upButtonHandler();
						break;
					case KeyEvent.VK_LEFT:
						leftButtonHandler();
						break;
					case KeyEvent.VK_RIGHT:
						rightButtonHandler();
						break;
					case KeyEvent.VK_DOWN:
						downButtonHandler();
						break;
					default:
						break;
					}
				}
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameWindow = new JFrame("Dungeon Keep");
		frameWindow.setBounds(484, 100, 626, 463);
		frameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gamePanel = new JGamePanel();
		buttonUpDown();
		buttonLeftRight();
		layout();
	}
	
	private void buttonUpDown() {
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upButtonHandler();
			}
		});
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downButtonHandler();
			}
		});
	}
	
	private void buttonLeftRight() {
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftButtonHandler();
			}
		});
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightButtonHandler();
			}
		});
	}
	
	private void layout() {
		groupLayout = new GroupLayout(frameWindow.getContentPane());
		layout_Horizontal();
		layout_Vertical();
		frameWindow.getContentPane().setLayout(groupLayout);
	}
	
	private void layout_Horizontal() {
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(21)
						.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnLeft)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRight)
									.addGap(18))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnDown)
									.addGap(54)))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(btnUp)
								.addGap(59))))
			);
	}
	
	private void layout_Vertical() {
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(160)
								.addComponent(btnUp)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnRight)
									.addComponent(btnLeft))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnDown))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(26)
								.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)))
						.addGap(37))
			);
	}
	
	private void rightButtonHandler() {
		game.nextMove(Movement.RIGHT);
		updateGame();
		frameWindow.requestFocusInWindow();
	}
	
	private void leftButtonHandler() {
		game.nextMove(Movement.LEFT);
		updateGame();
		frameWindow.requestFocusInWindow();
	}
	
	private void upButtonHandler() {
		game.nextMove(Movement.UP);
		updateGame();
		frameWindow.requestFocusInWindow();
	}
	
	private void downButtonHandler() {
		game.nextMove(Movement.DOWN);
		updateGame();
		frameWindow.requestFocusInWindow();
	}
	
	public void updateMovementButtons(boolean act) {
		btnRight.setEnabled(act);
		btnLeft.setEnabled(act);
		btnUp.setEnabled(act);
		btnDown.setEnabled(act);
	}
	
	public void updateGame() {
		if(game.get_status() == State.DEFEAT || game.get_status() == State.WIN) {
			updateMovementButtons(false);
			if(game.get_status() == State.DEFEAT ) {
				gamePanel.setMap(game.getGameMap());
				JOptionPane.showMessageDialog(frameWindow, "Better luck next time, you lost the game!","Game Lost",JOptionPane.INFORMATION_MESSAGE,minionsadicon);
				frameWindow.setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(frameWindow, "Congratulations, you won the game!", "Game Won",JOptionPane.INFORMATION_MESSAGE,minionhappyicon);
				frameWindow.setVisible(false);
			}
		}
		else {
			gamePanel.setMap(game.getGameMap());
		}
	}
	
	public JFrame getFrame() {
		return this.frameWindow;
	}

}
