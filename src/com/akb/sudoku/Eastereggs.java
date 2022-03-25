package com.akb.sudoku;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Eastereggs {
	
	private boolean enabled; 

	private static boolean isEnabled () {
		try {
			Scanner sc = new Scanner (new File ("./.config/.achievements"), "utf-8");
			if (sc.nextLine().matches(".*eee.*"))
				return true; 
		} catch(IOException t) {
			t.printStackTrace();
			return false;
		}
		return false;
	}

	public static boolean isCode (int...code) {

	}

}
