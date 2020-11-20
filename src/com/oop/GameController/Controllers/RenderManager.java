package com.oop.GameController.Controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * @author CongTrung
 * @Conclusion background is jpg, character and skill is png
 * 
 * background chia thanh luoi 5x5
 * player 1 stand in 2,2
 * player 2 stand in 4,4
 */

public class RenderManager extends JPanel{
	private static final long serialVersionUID = 1L;
	
	final int scale = 7;
    
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		int wP = 0;
		int hP = 0;
		
		BufferedImage i0 = null;
		BufferedImage i1 = null;
		BufferedImage i2 = null;
		String basePath = new File("").getAbsolutePath();
		System.out.println(basePath);
		// draw background
		try {
			i0 = ImageIO.read(new File("src/resource/maps/konoha.jpg"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(i0, 0, 0, width, height, null);
		
		
		// get list of character from PlayerManager to here first
		// assume that choose these 2 character.
		//String[] players = {"sasuke", "itachi"};
		String[] players = {"itachi", "itachi"};
				
		
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
				
		
		
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
	}
}
