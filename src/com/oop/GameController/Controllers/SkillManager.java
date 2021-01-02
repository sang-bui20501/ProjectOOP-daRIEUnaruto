package com.oop.GameController.Controllers;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.oop.GameController.Skill.SkillRender;


/*
 * Manage the list of existing skill, and skill lifetime
 * Working with the intersects of skill - skill, skill - player
 */

// Singleton
public class SkillManager implements ActionListener {
	// Singleton
	private static SkillManager instance = null;
	
	// Define the id of main player
	public static int player_id;
	
	// "An chu"
	public static ArrayList<SkillRender> Mini_Skill = new ArrayList<SkillRender>(2);
	
	// Save 2 ArrayList of skill of 2 players into an ArrayList
	public static ArrayList<ArrayList<SkillRender>> List_Skill = new ArrayList<ArrayList<SkillRender>>(2);
	
	
	private SkillManager(int id) {
		player_id = id;
		
		List_Skill.add(new ArrayList<SkillRender>());
		List_Skill.add(new ArrayList<SkillRender>());
		Mini_Skill.add(new SkillRender());
		Mini_Skill.add(new SkillRender());
	}
	
	public static SkillManager getInstance(int id) {
		if (instance == null) 
			instance = new SkillManager(id);
		return instance;
	}
	
	
	public static ArrayList<SkillRender> getMainListSkill() {
		return List_Skill.get(player_id - 1);
	}

	public static void addSkill(SkillRender skill) {
		getMainListSkill().add(skill);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// get existing skill of player
		for (SkillRender mem : getMainListSkill()) 
		{
			
			// bound of skill
			Rectangle skillBound = mem.getBound();
			
			
			//Check intersects of skill - player
						
			// bound of opposite player
			Rectangle playerBound = PlayerManager.List_Player.get(mem.id % 2).getBound();
			if (skillBound.intersects(playerBound))
			{	
				// Decrease HP base one shield and damage
				int tmp = mem.damage;
				tmp -= PlayerManager.List_Player.get(mem.id % 2).shield;
				
				if (tmp < 0) {
					PlayerManager.List_Player.get(mem.id % 2).shield -= mem.damage;
					tmp = 0;
				}
				else
					PlayerManager.List_Player.get(mem.id % 2).shield = 0;
				
				PlayerManager.List_Player.get(mem.id % 2).hp -= tmp;
				
				if (PlayerManager.List_Player.get(mem.id % 2).hp <= 0)
					PlayerManager.List_Player.get(mem.id % 2).hp = -1;
				
				mem.destroy();
			}
			
			// Check intersects of skill - skill
			
			// bound of skill of opposite player
			for (SkillRender mem_2 : List_Skill.get(player_id % 2)) 
			{
				Rectangle skillBound_2 = mem_2.getBound();
				
				if (skillBound.intersects(skillBound_2)) 
				{
					mem.status = false;
					mem_2.status = false;
					mem.destroy();
				}
			}
		}
		
		
		int i = 0;
		while (i < SkillManager.getMainListSkill().size()) {
			// Check the status of skill
			if (SkillManager.getMainListSkill().get(i).status == false) 
				getMainListSkill().remove(i);
			else
				++i;
		}
		
	}
}
