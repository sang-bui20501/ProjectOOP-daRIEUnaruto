package com.oop.GameController.Player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlayerRender extends JPanel {
	private static final long serialVersionUID = 1L;
	
	final int scale = 7;
		
	public void paint(Graphics g, int width, int height) {
		//System.out.println("Hello");

		int wP = 0;
		int hP = 0;
		
		BufferedImage i1 = null;
		BufferedImage i2 = null;
		
		// get list of character from PlayerManager to here first
		// assume that choose these 2 character.
		String[] players = {"itachi", "sasuke"};
		
		
										
		// draw Player 1
		try {
			i1 = ImageIO.read(new File("src/resource/characters/" + players[0] + "1.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		wP = i1.getWidth() / 3;
		hP = i1.getHeight() / 3;
		g.drawImage(i1, width / scale, height - 2 * height / scale, wP, hP, null);
					
		
		
		// draw Player 2
		try {
			i2 = ImageIO.read(new File("src/resource/characters/" + players[1] + "2.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		wP = i2.getWidth() / 3;
		hP = i2.getHeight() / 3;
		g.drawImage(i2, width - 2 * width / scale, height - 2 * height / scale, wP, hP, null);
	}
}
