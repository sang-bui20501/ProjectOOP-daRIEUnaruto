 package com.oop.GameController.Controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.oop.GameController.Player.PlayerRender;
import com.oop.GameController.Skill.SkillRender;


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
		
	String name1;
	String name2;
	PlayerManager List_Player;
	SkillManager List_Skill;
	
	public RenderManager() {};
	
	public RenderManager(PlayerManager List_Player, SkillManager List_Skill) {
		// Pipe the list of player and list of skill
		this.List_Player = List_Player;
		this.List_Skill= List_Skill;
	}
	
	public void paintComponent(Graphics g) {
		// draw background
		BufferedImage i0 = null;
		try {
			i0 = ImageIO.read(new File("src/resource/maps/konoha.jpg"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(i0, 0, 0, getWidth(), getHeight(), null);
		
		
		// draw 2 player
		PlayerRender playerframe = new PlayerRender();
				
		playerframe = new PlayerRender(getWidth(), getHeight(), 1, List_Player.List_Player.get(0));
		playerframe.paint(g);
		this.add(playerframe);
				
		playerframe = new PlayerRender(getWidth(), getHeight(), 2, List_Player.List_Player.get(1));
		playerframe.paint(g);
		this.add(playerframe);
		
		
		// draw "an chu"
		SkillRender mem = List_Skill.Mini_Skill;
		mem.paint(g);
		add(mem);
		
		
		// draw existed skill		
		if (List_Skill.List_Skill.size() != 0) {
			int i = 0;
			
			while (i < List_Skill.List_Skill.size()) {
				mem = List_Skill.List_Skill.get(i);
				mem.paint(g);
				
				if (mem.status == false) {
					List_Skill.List_Skill.remove(i);
					--i;
				}
				else
					add(mem);
				
				++i;
			}
		}
		
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
	}
	

}

