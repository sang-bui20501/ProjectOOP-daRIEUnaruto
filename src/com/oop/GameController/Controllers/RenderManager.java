 package com.oop.GameController.Controllers;

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
	SkillRender mem;
	
	boolean gameStatus = true;
	
	public RenderManager() {}
	
	public RenderManager(boolean gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public void paintComponent(Graphics g) {
		if (gameStatus) {
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
				
			PlayerRender playerframe2 = new PlayerRender(getWidth(), getHeight(), 2, PlayerManager.List_Player.get(1));
			playerframe2.customPaint(g);	
			PlayerRender playerframe1 = new PlayerRender(getWidth(), getHeight(), 1, PlayerManager.List_Player.get(0));
				
			playerframe1.customPaint(g);	
			// draw this "an chu"
			mem = SkillManager.Mini_Skill.get(0);
			mem.paint(g);
			add(mem);
			
			// draw enemy "an chu"
			mem = SkillManager.Mini_Skill.get(1);
			mem.paint(g);
			add(mem);
			
			// draw existed skill of this player	
			for (int i = 0; i < SkillManager.getMainListSkill().size(); ++i) {
				mem = SkillManager.getMainListSkill().get(i);
				mem.paint(g);
				add(mem);
			}
			
			// draw existed skill of enemy	
			for (int i = 0; i < SkillManager.getEnemyListSkill().size(); ++i) {
				mem = SkillManager.getEnemyListSkill().get(i);
				mem.paint(g);
				add(mem);
			}
		}
		
		else
			
		{
			BufferedImage i0 = null;
			try {
				i0 = ImageIO.read(new File("src/resource/maps/KO.jpg"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(i0, 0, 0, getWidth(), getHeight(), null);
		}
		this.repaint();

	}
	

}

