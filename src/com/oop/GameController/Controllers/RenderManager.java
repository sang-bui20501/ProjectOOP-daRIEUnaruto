 package com.oop.GameController.Controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.oop.GameController.Player.Player;
import com.oop.GameController.Player.PlayerRender;


/**
 * 
 * @author CongTrung
 * @Conclusion background is jpg, character and skill is png
 * 
 * background chia thanh luoi 5x5
 * player 1 stand in 2,2
 * player 2 stand in 4,4
 * 
 * player size: 205 px * 2xx px
 */

public class RenderManager extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Graphics save_g;
	
	final int scale = 7;
	String name1;
	String name2;
	PlayerManager List;
	
	public RenderManager() {};
	
	public RenderManager(PlayerManager List) {
		// Pipe the list of player;
		this.List = List;
	}
	
	public void paint(Graphics g) {
		
		this.save_g = g;
		
		// draw background
		BufferedImage i0 = null;
		try {
			i0 = ImageIO.read(new File("src/resource/maps/konoha.jpg"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(i0, 0, 0, getWidth(), getHeight(), null);
		
		
		// Draw 2 player
		ArrayList<Player> tmp = List.List_Player;
		PlayerRender player = new PlayerRender();
		
		player.paint(g, getWidth(), getHeight(), 1, tmp.get(0));
		add(player);		
		
		player.paint(g, getWidth(), getHeight(), 2, tmp.get(1));
		add(player);
	}
    
	public void paintComponent(Graphics g) { 		
		
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
	}
	

}

