package com.grouposrs.player;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.client.callback.ClientThread;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Slf4j
@Singleton
public class Quests {
  @Inject
  Client client;

  private final Map<Integer, QuestState> questStateMap = new HashMap<>();
  private boolean shouldUpdateQuests = true;

  public Object get() {
    return questStateMap;
  }

  public void update() {
    if (!shouldUpdateQuests)
      return;

    shouldUpdateQuests = false;
    questStateMap.clear();

    for (Quest quest : Quest.values()) {
      QuestState state = quest.getState(client);
      questStateMap.put(quest.getId(), state);
    }
  }
}