package com.oop.GameController.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.oop.GameController.Skill.SkillRender;

public class SkillManager implements ActionListener {
	// Manage the list of existing skill
	
	
	private static SkillManager instance = null;
	
	public SkillRender Mini_Skill = new SkillRender();
	public static ArrayList<SkillRender> List_Skill = new ArrayList<SkillRender>();
	
	public SkillManager() {}
	
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
			if (mem.status == false)
				List_Skill.remove(i);
		}
	}
}
