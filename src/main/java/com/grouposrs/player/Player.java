package com.grouposrs.player;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.grouposrs.GroupOSRSPlugin;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GameState;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Singleton
public class Player {
  private final GroupOSRSPlugin plugin;
  private Gson gson;

  @Getter
  private String playerName = "";
  private final Map<String, Object> updates = new HashMap<>();
  private final JsonArray trackedPlayers = new JsonArray();

  @Inject
  public Player(GroupOSRSPlugin plugin) {
    this.plugin = plugin;
  }

  public Object getPlayer() {
    final Map<String, Object> player = new HashMap<>();
    player.put("player_name", this.playerName);
    player.put("skill_level", this.plugin.getSkills().getSkillLevels());
    player.put("skill_xp", this.plugin.getSkills().getSkillExperience());
    player.put("quests", this.plugin.getQuests().get());

    return player;
  }

  public void init() {
    if (this.trackedPlayers.size() == 0) {
      final JsonObject getTrackedPlayersResponse = this.plugin.getApi().getTrackedPlayers();

      this.trackedPlayers.addAll(getTrackedPlayersResponse
          .get("data")
          .getAsJsonObject()
          .get("players")
          .getAsJsonArray());
    }

    try {
      final String clientPlayerName = this.plugin.getClient().getLocalPlayer().getName();
      if (!this.isLoggedIn() || this.playerName.equals(clientPlayerName))
        return;

      this.playerName = clientPlayerName;
    } catch (Exception _exception) {}
  }

  public boolean isLoggedIn() {
    return this.plugin.getClient().getGameState() == GameState.LOGGED_IN && this.plugin.getClient().getLocalPlayer() != null;
  }

  public void addUpdate(String key, Object update) {
    this.updates.put(key, update);
  }

  public void postUpdates() {
    if (this.updates.isEmpty())
      return;

    this.plugin.getApi().updatePlayer(this.gson.toJson(this.updates));
  }

  public JsonObject trackPlayer() {
    return this.plugin.getApi().addTrackedPlayer(this.getPlayer());
  }
}
