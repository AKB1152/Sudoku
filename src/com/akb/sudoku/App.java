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
	
	private int[][] field = new int[9][9];
	private Difficulty dif;

	enum Difficulty {
		Easy, Medium, Hard
	}

	public static void main (String[] args) {
		new App();
	}


	public App () {
		popup();
	}

	// HOW IN THE GODDAMN FUCK DO YOU GENERATE A SUDOKU FIELD?!
	private int[][] generate () {
		return null;
	}


	protected boolean check (int x, int y) {
		// check block
		int v = field[x][y], xOff = (int)Math.floor(x/3), yOff = (int)Math.floor(y/3);
		for (int ix=0; ix<3; ix++) 
			for (int iy=0; iy<3; iy++)
				if (x%3 == ix && y%3 == iy)	continue;
				else if (field[xOff+ix][yOff+iy] == v) return false;
				else continue;
		// check lines [Horizontal] [Vertical]
		for (int ix = 0; ix < 9; ix++)
			if (ix == x) continue;
			else if (field[ix][y] == v) return false;
			else continue;
		for (int iy = 0; iy < 9; iy++)
			if (iy == y) continue;
			else if (field[x][iy] == v) return false;
			else continue;
		return true;
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
