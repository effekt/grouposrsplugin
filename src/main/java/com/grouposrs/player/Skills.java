package com.grouposrs.player;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Slf4j
@Singleton
public class Skills {
  @Inject
  Client client;

  public Map<Skill, Integer> getSkillExperience() {
    final Map<Skill, Integer> skillXpMap = new HashMap<>();
    for (Skill skill : Skill.values()) {
      skillXpMap.put(skill, this.client.getSkillExperience(skill));
    }
    return skillXpMap;
  }

  public Map<Skill, Integer> getSkillLevels() {
    final Map<Skill, Integer> skillLevelMap = new HashMap<>();
    for (Skill skill : Skill.values()) {
      skillLevelMap.put(skill, this.client.getRealSkillLevel(skill));
    }
    return skillLevelMap;
  }
}