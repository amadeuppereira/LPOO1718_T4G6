package dkeep.gui;

import java.awt.EventQueue;
import dkeep.logic.GameState;
import dkeep.logic.GameState.Movement;
import dkeep.logic.GameState.State;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
//import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class GameWindow {

	private JFrame frame;
	private JTextField textFieldOgresNumber;
	GameState game;
	JComboBox comboBoxGuardType;
	JLabel lblGameStatus;
	JButton btnUp;
	JButton btnDown;
	JButton btnLeft;
	JButton btnRight;
	JButton btnNewGame;
	JGamePanel gamePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//frame.setBounds(0, 0,screen.width,screen.height - 30);
		//frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Number of Ogres");
		lblNewLabel.setBounds(22, 25, 108, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldOgresNumber = new JTextField();
		textFieldOgresNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatusText();
			}
		});
		
		textFieldOgresNumber.setBounds(159, 20, 64, 26);
		frame.getContentPane().add(textFieldOgresNumber);
		textFieldOgresNumber.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Guard personality");
		lblNewLabel_1.setBounds(22, 57, 125, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		String[] options = { "","Rookie", "Drunken", "Suspicious" };
		comboBoxGuardType = new JComboBox(options);
		comboBoxGuardType.setBounds(159, 53, 124, 27);
		frame.getContentPane().add(comboBoxGuardType);
		comboBoxGuardType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatusText();
			}
		});
		
		gamePanel = new JGamePanel();
		gamePanel.setBounds(22, 92, 389, 283);
		frame.getContentPane().add(gamePanel);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(439, 341, 102, 27);
		frame.getContentPane().add(btnExit);
		
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upButtonHandler();
			}
		});
		btnUp.setBounds(461, 171, 75, 29);
		frame.getContentPane().add(btnUp);
		
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downButtonHandler();
			}
		});
		btnDown.setBounds(461, 234, 80, 29);
		frame.getContentPane().add(btnDown);
		
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftButtonHandler();
			}
		});
		btnLeft.setBounds(423, 203, 75, 29);
		frame.getContentPane().add(btnLeft);
		
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightButtonHandler();
			}
		});
		btnRight.setBounds(497, 203, 77, 29);
		frame.getContentPane().add(btnRight);
		
		lblGameStatus = new JLabel();
		lblGameStatus.setText("Introduce number of Ogres and Guard personality.");
		lblGameStatus.setBounds(22, 390, 552, 15);
		frame.getContentPane().add(lblGameStatus);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setEnabled(false);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGameButtonHandler();
			}
		});
		btnNewGame.setBounds(439, 92, 108, 27);
		frame.getContentPane().add(btnNewGame);
	}
	
	private void newGameButtonHandler() {
		game = new GameState(Integer.parseInt(textFieldOgresNumber.getText()),(String)comboBoxGuardType.getSelectedItem());
		updateMovementButtons(true);
		updateGame();
	}
	
	private void rightButtonHandler() {
		game.nextMove(Movement.RIGHT);
		updateGame();
	}
	
	private void leftButtonHandler() {
		game.nextMove(Movement.LEFT);
		updateGame();
	}
	
	private void upButtonHandler() {
		game.nextMove(Movement.UP);
		updateGame();
	}
	
	private void downButtonHandler() {
		game.nextMove(Movement.DOWN);
		updateGame();
	}
	
	private void updateMovementButtons(boolean act) {
		btnRight.setEnabled(act);
		btnLeft.setEnabled(act);
		btnUp.setEnabled(act);
		btnDown.setEnabled(act);
	}
	
	private void updateStatusText() {
		if(game == null) {
			int numOgres = 0;
			if (!textFieldOgresNumber.getText().equals("")) {
				try {
					numOgres = Integer.parseInt(textFieldOgresNumber.getText());
				} catch (NumberFormatException e) {
					numOgres = 0;
				}
			}

			if (textFieldOgresNumber.getText().equals("") && (String) comboBoxGuardType.getSelectedItem() == "") {
				lblGameStatus.setText("Introduce number of Ogres and Guard personality.");
				if (btnNewGame.isEnabled())
					btnNewGame.setEnabled(false);
			} else if ((numOgres > 5 || numOgres <= 0) && (String) comboBoxGuardType.getSelectedItem() == "") {
				lblGameStatus.setText("Introduce a valid number of Ogres (1-5) and Guard personality.");
				btnNewGame.setEnabled(false);
			} else if (numOgres <= 5 && numOgres > 0 && (String) comboBoxGuardType.getSelectedItem() == "") {
				lblGameStatus.setText("Introduce a Guard personality.");
				btnNewGame.setEnabled(false);
			} else if ((numOgres > 5 || numOgres <= 0) && (String) comboBoxGuardType.getSelectedItem() != "") {
				lblGameStatus.setText("Introduce a valid number of Ogres (1-5).");
				btnNewGame.setEnabled(false);
			} else if (numOgres <= 5 && numOgres > 0 && (String) comboBoxGuardType.getSelectedItem() != "") {
				lblGameStatus.setText("You can start a new game.");
				btnNewGame.setEnabled(true);
			}
		}
		else {
			if (game.get_status() == State.DEFEAT) {
				lblGameStatus.setText("Better luck next time, you lost the game!");
			} else if (game.get_status() == State.WIN) {
				lblGameStatus.setText("Congratulations, you won the game!");
			} else if (game.get_status() == State.PLAYING) {
				lblGameStatus.setText("You can play now.");
			}
		}
	}
	
	private void updateGame() {
		updateStatusText();
		if(game.get_status() == State.DEFEAT || game.get_status() == State.WIN) {
			updateMovementButtons(false);
			if(game.get_status() == State.DEFEAT ) {
				gamePanel.setMap(game.getGameMap());
			}
		}
		else {
			gamePanel.setMap(game.getGameMap());
		}
	}
}
