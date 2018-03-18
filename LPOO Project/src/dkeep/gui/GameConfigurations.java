package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;

import dkeep.logic.GameState;
import dkeep.logic.GameState.State;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.Font;

public class GameConfigurations {

	private JFrame frameConfig;
	private JTextField textFieldOgresNumber;
	JComboBox<?> comboBoxGuardType;
	JTextPane txtGameStatus;
	JButton btnNewGame;	
	
	GameState game;
	GameWindow windowGame;
	
	public GameConfigurations() {
		initialize();
	}

	private void initialize() {
		frameConfig = new JFrame("Game Configurations");
		frameConfig.setBounds(100, 100, 400, 364);
		frameConfig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] options = { "","Rookie", "Drunken", "Suspicious" };
		
		JLabel lblNewLabel = new JLabel("Number of Ogres");
		
		JButton btnExit = new JButton("Exit Game");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		textFieldOgresNumber = new JTextField();
		textFieldOgresNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatusText();
			}
		});
		textFieldOgresNumber.setColumns(10);
		
		comboBoxGuardType = new JComboBox(options);
		comboBoxGuardType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatusText();
			}
		});
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setEnabled(false);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGameButtonHandler();
				windowGame.getFrame().requestFocusInWindow();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Guard personality");
		
		txtGameStatus = new JTextPane();
		txtGameStatus.setEditable(false);
		txtGameStatus.setFont(new Font("Courier New", txtGameStatus.getFont().getStyle(), txtGameStatus.getFont().getSize()));
		txtGameStatus.setText("Introduce number of Ogres and Guard personality.");
		
		
		GroupLayout groupLayout = new GroupLayout(frameConfig.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(145)
					.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(138))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtGameStatus, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldOgresNumber, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnNewGame, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
									.addComponent(comboBoxGuardType, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
							.addGap(135))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldOgresNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBoxGuardType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewGame)
					.addGap(30)
					.addComponent(txtGameStatus, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnExit)
					.addGap(49))
		);
		frameConfig.getContentPane().setLayout(groupLayout);
	}
	
	public JFrame getFrameConfig() {
		return frameConfig;
	}
	
	private void updateStatusText() {
		int numOgres = 0;
		if (!textFieldOgresNumber.getText().equals("")) {
			try {
				numOgres = Integer.parseInt(textFieldOgresNumber.getText());
			} catch (NumberFormatException e) {
				numOgres = 0;
			}
		}

		if (textFieldOgresNumber.getText().equals("") && (String) comboBoxGuardType.getSelectedItem() == "") {
			txtGameStatus.setText("Introduce number of Ogres and Guard personality.");
			if (btnNewGame.isEnabled())
				btnNewGame.setEnabled(false);
		} else if ((numOgres > 5 || numOgres <= 0) && (String) comboBoxGuardType.getSelectedItem() == "") {
			txtGameStatus.setText("Introduce a valid number of Ogres (1-5) and Guard personality.");
			btnNewGame.setEnabled(false);
		} else if (numOgres <= 5 && numOgres > 0 && (String) comboBoxGuardType.getSelectedItem() == "") {
			txtGameStatus.setText("Introduce a Guard personality.");
			btnNewGame.setEnabled(false);
		} else if ((numOgres > 5 || numOgres <= 0) && (String) comboBoxGuardType.getSelectedItem() != "") {
			txtGameStatus.setText("Introduce a valid number of Ogres (1-5).");
			btnNewGame.setEnabled(false);
		} else if (numOgres <= 5 && numOgres > 0 && (String) comboBoxGuardType.getSelectedItem() != "") {
			if(windowGame !=  null) { 
				if(windowGame.getFrame().isVisible() == true) {
					txtGameStatus.setText("A game is already running, finish or close it before starting a new game.");
					btnNewGame.setEnabled(false);
				}
				else {
					txtGameStatus.setText("You can start a new game.");
					btnNewGame.setEnabled(true);
				}
			}
			else {
				txtGameStatus.setText("You can start a new game.");
				btnNewGame.setEnabled(true);
			}
		}
	}
	
	private void newGameButtonHandler() {
		game = new GameState(Integer.parseInt(textFieldOgresNumber.getText()),(String)comboBoxGuardType.getSelectedItem());		
		btnNewGame.setEnabled(false);
		windowGame = new GameWindow(game);
		windowGame.getFrame().setVisible(true);
		windowGame.updateMovementButtons(true);
		windowGame.updateGame();	
		updateStatusText();
		textFieldOgresNumber.setText("");
		comboBoxGuardType.setSelectedItem((String)"");
	}
}
