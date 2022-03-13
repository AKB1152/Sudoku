package com.akb.sudoku;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
	
	private Field field;
	private Difficulty dif;

	enum Difficulty {
		Easy, Medium, Hard
	}

	public static void main (String[] args) {
		//new App();
		int[][] a = Field.generate(987654321);
		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++)
				System.err.printf("%d | %s", a[x][y], (y%3 == 2)? " " : "");
			System.err.printf(" x = %d \n%s", x, (x%3 == 2)? "\n" : ""); 
		}
	}


	public App () {
		popup();
	}

	private void popup () {
		JFrame frame = new JFrame("Choose a Difficulty");
		JComboBox<Difficulty> comboBox = new JComboBox<>(Difficulty.values());
		JButton enter = new JButton("Select");
		JPanel popupPanel = new JPanel(new GridLayout(1,2));

		popupPanel.add(comboBox);
		popupPanel.add(enter);
		
		comboBox.setSelectedIndex(0);

		frame.setIconImage(new ImageIcon("./img/tmp_ico.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 100);
		frame.setVisible(true);
		frame.add(popupPanel);

		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dif = Difficulty.valueOf(""+comboBox.getSelectedItem());
				frame.setVisible(false);
				frame.dispose();
				System.err.println(dif);
			}	
		});


	}
}
