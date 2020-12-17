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
	
	private static SkillManager instance = null;
	
	public SkillRender Mini_Skill = new SkillRender();
	public static ArrayList<SkillRender> List_Skill = new ArrayList<SkillRender>();
	
	private SkillManager() {}
	
	public static SkillManager getInstance() {
		if (instance == null) 
			instance = new SkillManager();
		return instance;
	}
	
	public static void addSkill(SkillRender skill) {
		List_Skill.add(skill);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int i = 0; i < List_Skill.size(); ++i) {
			SkillRender mem = List_Skill.get(i);
			
			//Check intersects of skill - player
			
			// bound of skill
			Rectangle skillBound = mem.getBound();
			
			// bound of opposite player
			Rectangle playerBound = PlayerManager.List_Player.get(mem.id % 2).getBound();
			if (skillBound.intersects(playerBound)) {
				
				// Decrease HP base one shield and damage
				int tmp = List_Skill.get(i).damage;
				tmp -= PlayerManager.List_Player.get(mem.id % 2).sheild;
				
				if (tmp < 0) {
					PlayerManager.List_Player.get(mem.id % 2).sheild -= List_Skill.get(i).damage;
					tmp = 0;
				}
				else
					PlayerManager.List_Player.get(mem.id % 2).sheild = 0;
				
				PlayerManager.List_Player.get(mem.id % 2).hp -= tmp;
				
				mem.destroy();
			}
			
			// Check intersects of skill - skill
			/*
			for (int i = 0; i < PlayerManager.List_Player.get(mem.id % 2).List_Skill.size(); ++i) {
				Rectangle mem_2 = PlayerManager.List_Player.get(mem.id % 2).List_Skill.get(i).getBound();
				if (skillBound.intersects(mem_2)) {
					PlayerManager.List_Player.get(mem.id % 2).List_Skill.get(i).status = false;
					mem.destroy();
				}
			}
			*/
			
			// Check the status of skill
			if (mem.status == false)
				List_Skill.remove(i);
		}
		
	}
}
