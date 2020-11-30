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
		
	public void paint(Graphics g, int width, int height, int order, Player player) {
		int wP = 0;
		int hP = 0;
		BufferedImage i = null;
		
		// save position to fix and deploy health
		if (order == 1)
			player.posX = width / scale; 
		else
			player.posX = width - 2 * width / scale;
		
		player.posY = height - 2 * height / scale;
		
		
		// draw player depend on name and player object				
		try {
			i = ImageIO.read(new File("src/resource/characters/" + player.name + order + ".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		wP = i.getWidth() / 3;
		hP = i.getHeight() / 3;
		g.drawImage(i, player.posX, player.posY, wP, hP, null);
	}
}
