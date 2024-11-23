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

  private final Map<Skill, Integer> skillMap = new HashMap<>();

  public Object get() {
    return skillMap;
  }

  public Map<Skill, Integer> update() {
    skillMap.clear();

    for (Skill skill : Skill.values()) {
      skillMap.put(skill, client.getSkillExperience(skill));
    }

    return skillMap;
  }
}