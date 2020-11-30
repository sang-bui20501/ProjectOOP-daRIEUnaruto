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
		
	public void paint(Graphics g, int width, int height, String name1, String name2) {
		int wP = 0;
		int hP = 0;
		
		BufferedImage i1 = null;
		BufferedImage i2 = null;		
		
		// Depend on name1 and name2 to get pictures of 2 characters				
		
		// draw Player 1
		try {
			i1 = ImageIO.read(new File("src/resource/characters/" + name1 + "1.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		wP = i1.getWidth() / 3;
		hP = i1.getHeight() / 3;
		g.drawImage(i1, width / scale, height - 2 * height / scale, wP, hP, null);
		
		
		
		// draw Player 2
		try {
			i2 = ImageIO.read(new File("src/resource/characters/" + name2 + "2.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		wP = i2.getWidth() / 3;
		hP = i2.getHeight() / 3;
		g.drawImage(i2, width - 2 * width / scale, height - 2 * height / scale, wP, hP, null);
	}
}
