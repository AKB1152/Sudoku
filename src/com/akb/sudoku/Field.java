package com.akb.sudoku;

import java.util.Random;

import com.akb.sudoku.App.Difficulty;

public class Field {
	
	int[][] solution, user_known;

	public Field () {
		solution = generate();


	}

	private int[] give (Difficulty d) {
		int n = (d == Difficulty.Easy)? 25 : (d == Difficulty.Medium)? 16 : 9;
		int[] amt = new int[n];
		for (int i = 0; i < n; i++)
			amt[i] = new Random().nextInt(81); 
		return amt;
	}

	private int[][] generate () {
		int seed = 0, tmp;
		String $seed = "";

		Random g = new Random();
		int[] used = {};

		while ($seed.length() < 9) {
			tmp = g.nextInt(9);
			for (int excludedIndex : used)
				if (tmp == excludedIndex) continue;
			
			seed = 10 * seed + tmp + 1;
			$seed = seed + "";

			int[] usd = new int[used.length+1];
			for (int i = 0; i < used.length; i++)
				usd[i] = used[i];
			usd[used.length] = tmp;
		} 

		return generate(seed);
	}

	public static int[][] generate (int seed) {
		int[] first = new int[9];
		int[][] field = new int[9][9];
		for (int i = 1; i <= 9; i++)
			first[i-1] = (int)((seed % Math.pow(10, i) - seed % Math.pow(10, i-1))/Math.pow(10, i-1));

		field[0] = first;
		for (int i = 1; i < 9; i++)
			field[i] = (i % 3 == 0) ? shift(field[i-1], 1) : shift(field[i-1], 3);
		
		return field;
	}	

	private static int[] shift (int[] in, int shift) {
		int[] out = new int[in.length];
		for (int i = 0; i < out.length; i++)
			out [i] = in [(i+shift)%9];
		return out;
	}

	protected boolean check (int x, int y) {
		// check block
		int v = user_known[x][y], xOff = (int)Math.floor(x/3), yOff = (int)Math.floor(y/3);
		for (int ix=0; ix<3; ix++) 
			for (int iy=0; iy<3; iy++)
				if (x%3 == ix && y%3 == iy)	continue;
				else if (solution[xOff+ix][yOff+iy] == v) return false;
				else continue;
		// check lines [Horizontal] [Vertical]
		for (int ix = 0; ix < 9; ix++)
			if (ix == x) continue;
			else if (solution[ix][y] == v) return false;
			else continue;
		for (int iy = 0; iy < 9; iy++)
			if (iy == y) continue;
			else if (solution[x][iy] == v) return false;
			else continue;
		return true;
	}
}
