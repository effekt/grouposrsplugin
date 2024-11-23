package com.grouposrs.player;

import com.grouposrs.GroupOSRSConfig;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Singleton
public class Player {
  @Inject
  private Client client;
  @Inject
  private Skills skills;
  @Inject
  private Quests quests;
  @Inject
  private GroupOSRSConfig config;

  private transient String playerName = "";

  public Object getPlayer() {
    Map<String, Object> player = new HashMap<>();
    player.put("player_name", playerName);
    player.put("skills", skills.update());
    player.put("quests", quests.get());

    return player;
  }

  public void init() {
    String playerName = client.getLocalPlayer().getName();

    if (!isLoggedIn() || this.playerName.equals(playerName))
      return;

    this.playerName = playerName;
  }

  public boolean isLoggedIn() {
    return client.getGameState() == GameState.LOGGED_IN && client.getLocalPlayer() != null;
  }
}
