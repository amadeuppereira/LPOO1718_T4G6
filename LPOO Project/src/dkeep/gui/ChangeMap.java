package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.MouseInfo;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeMap {

	private JFrame frame;
	private JTextField textSizeX;
	private JTextField textSizeY;
	private JGamePanel mapPanel;
	private JButton btnDone;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeMap windowConfig = new ChangeMap();
					windowConfig.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangeMap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Courier New", Font.PLAIN, 11));
		
		JPanel newMapConfig = new JPanel();
		
		mapPanel = new JGamePanel();
		mapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int tempx = mapPanel.getWidth()/mapPanel.getMap()[0].length;
				int tempy = mapPanel.getHeight()/mapPanel.getMap().length;
				System.out.println(arg0.getX()/tempx);
				System.out.println(arg0.getY()/tempy);
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setToolTipText("");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(newMapConfig, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addComponent(mapPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(newMapConfig, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(mapPanel, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JButton btnCreateMap = new JButton("Create Map");
		btnCreateMap.setEnabled(false);
		btnCreateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMapHandler();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_buttonsPanel = new GroupLayout(buttonsPanel);
		gl_buttonsPanel.setHorizontalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(btnCreateMap)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel)
					.addGap(22))
		);
		gl_buttonsPanel.setVerticalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addGap(0, 1, Short.MAX_VALUE)
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
		
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMapSize();
			}
		});
		GroupLayout gl_newMapConfig = new GroupLayout(newMapConfig);
		gl_newMapConfig.setHorizontalGroup(
			gl_newMapConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newMapConfig.createSequentialGroup()
					.addComponent(lblSize)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textSizeX, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblX)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textSizeY, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDone)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_newMapConfig.setVerticalGroup(
			gl_newMapConfig.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newMapConfig.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_newMapConfig.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSize)
						.addComponent(textSizeX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX)
						.addComponent(textSizeY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDone))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		newMapConfig.setLayout(gl_newMapConfig);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 486, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	void createMapHandler() {
		
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
			System.out.println("Erro no tamanho!\n");
		}
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
