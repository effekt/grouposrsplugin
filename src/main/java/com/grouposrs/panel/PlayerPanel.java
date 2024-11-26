package com.grouposrs.panel;

import com.grouposrs.GroupOSRSPlugin;
import com.grouposrs.ui.Button;
import com.grouposrs.ui.LongText;
import com.grouposrs.ui.TitledPanel;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class PlayerPanel extends PanelContainer {
  public PlayerPanel(GroupOSRSPlugin plugin) {
    super(plugin);

    JPanel trackPanel = new TitledPanel("Track Player").getPanel();
    if (!plugin.getPlayer().isLoggedIn()) {
      LongText notLoggedInLabel = new LongText("To begin tracking you must be logged in to the game.");
      trackPanel.add(notLoggedInLabel);
    }

    if (plugin.getPlayer().isLoggedIn()) {
      JPanel trackButton = new Button("Start Tracking!", () -> {
        plugin.getPlayer().trackPlayer();

        return null;
      }).getPanel();
      trackPanel.add(trackButton);
    }

    this.add(trackPanel);
  }

  public boolean shouldUpdate() {
    return false;
  }
}
