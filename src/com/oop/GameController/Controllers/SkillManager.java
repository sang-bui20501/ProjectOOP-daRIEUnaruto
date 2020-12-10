package com.oop.GameController.Controllers;

import java.util.ArrayList;

import com.oop.GameController.Skill.SkillRender;

public class SkillManager {
	// Manage the list of existing skill
	public SkillRender Mini_Skill = new SkillRender();
	public ArrayList<SkillRender> List_Skill = new ArrayList<SkillRender>();
	
	public SkillManager() {};
	
	public void update() {
		int i = 0;
		while (i < List_Skill.size()) {
			if (List_Skill.get(i).status == false) {
				List_Skill.remove(i);
				--i;
			}
			++i;
		}
	}
}
