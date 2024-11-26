package com.grouposrs.panel;

import com.grouposrs.GroupOSRSPlugin;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
public class PanelHelper {
  public enum PanelKey {
      LOGIN,
      PLAYER,
  }

  public final Map<PanelKey, PanelContainer> panelMap = new EnumMap<>(PanelKey.class);

  public PanelHelper(GroupOSRSPlugin plugin) {
    log.info(plugin.getConfig().loginToken());
    this.panelMap.put(PanelKey.LOGIN, new LoginPanel(plugin));
    this.panelMap.put(PanelKey.PLAYER, new PlayerPanel(plugin));
  }

  public PanelContainer getPanel(PanelKey panelKey) {
    return this.panelMap.get(panelKey);
  }
}
