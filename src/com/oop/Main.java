package com.oop;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.oop.GameController.Controllers.PlayerManager;
import com.oop.GameController.Controllers.RenderManager;
import com.oop.GameController.Controllers.SkillManager;
import com.oop.GameController.Player.Player;
import com.oop.GameController.Skill.SkillRender;

public class Main implements ActionListener{
	
	private static Main mainInstance = null;
	
	public String name1;
	public String name2;
	
	public JFrame j;
	
	public final int w = 1000, h = 650;
	
	public RenderManager gameframe;
	public SkillRender renSkill;
	public PlayerManager List_Player;
	public SkillManager List_Skill;
	
	public Timer t_Skill;
	public Timer t_Player;
	public Timer t_game;
	
	public boolean gameStatus = true;
	
	//int mainPlayerID = 1;
	int mainPlayerID = 2;
	Player mainPlayer;
	
	StringBuilder List_Key = new StringBuilder();
	StringBuilder miniKey = new StringBuilder();
	

    private KeyListener kbListener = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			// Press key
			if (e.getKeyCode() != 0) {	
				char key = (char) e.getKeyCode();
				
				// Skill after rendered
				renSkill = new SkillRender();
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE) 
				{
					// If space then generate skill and adding to list of existing skill
					renSkill = mainPlayer.generateSkill(List_Key.toString());
					
					if (renSkill != null) 
						SkillManager.addSkill(renSkill);
					
					// Reset list of key pressed
					List_Key = new StringBuilder();
					
					// Reset "an chu"
					SkillManager.Mini_Skill.set(mainPlayerID - 1, new SkillRender());
				}
				else	// else generate "an chu" and add to Mini_Skill in order to generate. 
				{
					// A single "an chu"
					miniKey.append(key);
					
					// A complete chain of "an chu"
					List_Key.append(key);
					
					// generate and save in Mini_Skill
					renSkill = mainPlayer.generateSkill(miniKey.toString());
					
					if (renSkill != null) 
						SkillManager.Mini_Skill.set(mainPlayerID - 1, renSkill);
				}
				
				// Reset list of key pressed
				miniKey = new StringBuilder();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {}
	};
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		j.repaint();
		
		if (gameStatus && this.mainPlayer.dead) {
			System.out.println("You dead!");
			endGame(1);
		}
		
		if (gameStatus && PlayerManager.List_Player.get(mainPlayerID % 2).dead) {
			System.out.println("You won!");
			endGame(2);
		}
	}
	private void reRender(){
		j.repaint();
		
		if (gameStatus && this.mainPlayer.dead) {
			System.out.println("You dead!");
			endGame(1);
		}
		
		if (gameStatus && PlayerManager.List_Player.get(mainPlayerID % 2).dead) {
			System.out.println("You won!");
			endGame(2);
		}
	}
	
	public void init() {

		this.name1 = "itachi";
		this.name2 = "sasuke";
		
		// Form the Frame
		j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(w,h);
		j.setResizable(true);
		j.getContentPane().add(new RenderManager());
	
		
		if (gameStatus) 
		{
			// Generate information of 2 characters and save in List
			// For render and other
			// 1 = host , 2 = connect <---
			Player player1 = new Player(name1, 1);
			Player player2 = new Player(name2, 2);
			List_Player = new PlayerManager(player1, player2);
			
			// mainPlayer: only generates skill for this player
			if (mainPlayerID == 1) 
				mainPlayer = player1;
			else 
				mainPlayer = player2;
			
			// Get a list store the existing skill on one frame
			List_Skill = SkillManager.getInstance(this.mainPlayerID);
					
			// Add Keyboard Interactive 
			j.addKeyListener(kbListener);
			
			// create timer for automatic skill on frame and player attributed
			t_Skill = new Timer(5, List_Skill);
			t_Player = new Timer(500, List_Player);
			
			t_Skill.start();
			t_Player.start();
		}
		
		// Create gameframe
		gameframe = new RenderManager(gameStatus);
		j.add(gameframe);
		
		t_game = new Timer(5, this);
		t_game.start();
		j.setVisible(true);
	}
	
	public void endGame(int term) {
		t_game.stop();
		t_Player.stop();
		t_Skill.stop();
		
		this.gameStatus = false;
		j.dispose();
		init();
		
		String name;		
		if (term == 1)
			if (mainPlayerID == 1)
				name = name2;
			else
				name = name1;
		else
			if (mainPlayerID == 1)
				name = name1;
			else
				name = name2;
		JOptionPane.showMessageDialog(null, name + " VICTORY !!!!!!!!!!" , "", JOptionPane.CLOSED_OPTION);
	}
	
	private Main() {
		//Must be identify the present player is id 1 or 2
		//this.mainPlayerID = 1;
		
		// Pipe to init() in oder to initial
		this.init();
	}

	public static Main getInstance(){
		if(mainInstance == null)
			mainInstance = new Main();
		return mainInstance;
	}

	public static void main(String[] args){
		// Pipe names of 2 characters after connect and choose
		
		//name1 = args[0];
		//name2 = args[1];
        new Main();
    }
	
}
