package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

/**
 * GameConfigurations.java - class handling the map configurations interface 
 */
public class GameConfigurations {

	private JFrame frameConfig;
	private JTextField textFieldOgresNumber;
	private JComboBox<?> comboBoxGuardType;
	private JTextPane txtGameStatus;
	private JButton btnNewGame;
	private JFrame frameMenu;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JButton btnReturn;
	private GameState game;
	private GameWindow windowGame;
	private GroupLayout groupLayout;

	
	/**
	 * Create the application
	 * @param frameMenu menu frame
	 */
	public GameConfigurations(JFrame frameMenu) {
		this.frameMenu = frameMenu;
		initialize();
	}

	private void initialize() {
		frameInitialize();
		buttonInitialize();
		textInitialize();
		comboInitialize();
		layout();
	}

	private void frameInitialize() {
		frameConfig = new JFrame("Game Configurations");
		frameConfig.setBounds(100, 100, 384, 333);
		frameConfig.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frameMenu.setVisible(true);
				if (windowGame != null) {
					windowGame.getFrame().dispose();
				}
				super.windowClosing(e);
			}
		});
	}

	private void buttonInitialize() {
		btnReturn = new JButton("Return to Menu");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameConfig.dispose();
				frameMenu.setVisible(true);
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
	}

	private void textInitialize() {
		textFieldOgresNumber = new JTextField();
		textFieldOgresNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatusText();
			}
		});
		textFieldOgresNumber.setColumns(10);
		lblNewLabel = new JLabel("Number of Ogres");
		lblNewLabel_1 = new JLabel("Guard personality");
		txtGameStatus = new JTextPane();
		txtGameStatus.setEditable(false);
		txtGameStatus.setFont(
				new Font("Courier New", txtGameStatus.getFont().getStyle(), txtGameStatus.getFont().getSize()));
		txtGameStatus.setText("Introduce number of Ogres and Guard personality.");
	}

	private void comboInitialize() {
		String[] options = { "", "Rookie", "Drunken", "Suspicious" };
		comboBoxGuardType = new JComboBox(options);
		comboBoxGuardType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatusText();
			}
		});
	}

	private void layout() {
		groupLayout = new GroupLayout(frameConfig.getContentPane());
		layout_Horizontal();
		layout_Vertical();
		frameConfig.getContentPane().setLayout(groupLayout);
	}

	private void layout_Horizontal() {
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(11).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addComponent(txtGameStatus, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup().addGap(108).addComponent(
												btnNewGame, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 120,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 108,
																GroupLayout.PREFERRED_SIZE))
												.addGap(12)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textFieldOgresNumber, GroupLayout.DEFAULT_SIZE,
																122, Short.MAX_VALUE)
														.addComponent(comboBoxGuardType, 0, 122, Short.MAX_VALUE))))
								.addGap(109)))
						.addGap(10))
				.addGroup(groupLayout.createSequentialGroup().addGap(125)
						.addComponent(btnReturn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(119)));
	}

	private void layout_Vertical() {
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						textFieldOgresNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(
						comboBoxGuardType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(30).addComponent(btnNewGame).addGap(18)
				.addComponent(txtGameStatus, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(btnReturn).addGap(34)));
	}

	/**
	 * Get the frame
	 * @return frame
	 */
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
		} else if ((numOgres > 5 || numOgres <= 0) && (String) comboBoxGuardType.getSelectedItem() == "") {
			txtGameStatus.setText("Introduce a valid number of Ogres (1-5) and Guard personality.");
		} else if (numOgres <= 5 && numOgres > 0 && (String) comboBoxGuardType.getSelectedItem() == "") {
			txtGameStatus.setText("Introduce a Guard personality.");
		} else if ((numOgres > 5 || numOgres <= 0) && (String) comboBoxGuardType.getSelectedItem() != "") {
			txtGameStatus.setText("Introduce a valid number of Ogres (1-5).");
		} else if (numOgres <= 5 && numOgres > 0 && (String) comboBoxGuardType.getSelectedItem() != "") {
			updateStatusText_helper();
			return;
		}
		btnNewGame.setEnabled(false);
	}
	
	private void updateStatusText_helper() {
		if (windowGame != null) {
			if (windowGame.getFrame().isVisible() == true) {
				txtGameStatus.setText("A game is already running, finish or close it before starting a new game.");
				btnNewGame.setEnabled(false);
			} else {
				txtGameStatus.setText("You can start a new game.");
				btnNewGame.setEnabled(true);
			}
		} else {
			txtGameStatus.setText("You can start a new game.");
			btnNewGame.setEnabled(true);
		}
	}

	private void newGameButtonHandler() {
		game = new GameState(Integer.parseInt(textFieldOgresNumber.getText()),
				(String) comboBoxGuardType.getSelectedItem());
		btnNewGame.setEnabled(false);
		windowGame = new GameWindow(game);
		windowGame.getFrame().setVisible(true);
		windowGame.updateMovementButtons(true);
		windowGame.updateGame();
		updateStatusText();
		textFieldOgresNumber.setText("");
		comboBoxGuardType.setSelectedItem((String) "");
	}
}