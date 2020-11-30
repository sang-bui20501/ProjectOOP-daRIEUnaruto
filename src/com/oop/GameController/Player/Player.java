package com.oop.GameController.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Player {
	int hp;
	int mana;
	int sheld;
	String element;
	
	public Player(String name) {
		try {
			// Open file and imple information of player into Object
			
			String path = "src/resource/characters/" + name + "_info.txt";
			File obj = new File(path);
			Scanner sc = new Scanner(obj);
			
			this.hp = sc.nextInt();
			this.mana = sc.nextInt();
			this.sheld= sc.nextInt();
			sc.nextLine();
			this.element = sc.nextLine();
			
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Nope");
		}
	}
	
	public void generateSkill(String inputKey) {
		// Check that if press more than 5 key
		if (inputKey.length() < 5)
			return;
		
		System.out.println(inputKey);
	}
}
