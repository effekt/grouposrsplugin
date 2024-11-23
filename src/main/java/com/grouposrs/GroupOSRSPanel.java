package com.grouposrs;

import com.grouposrs.api.Api;
import com.grouposrs.panel.LoginPanel;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

import javax.swing.border.EmptyBorder;
import java.awt.*;

@Slf4j
public class GroupOSRSPanel extends PluginPanel {
  private final GroupOSRSConfig config;
  private final ConfigManager configManager;

  private static final EmptyBorder DEFAULT_BORDER = new EmptyBorder(3, 3, 3, 3);

  public GroupOSRSPanel(GroupOSRSConfig config, ConfigManager configManager, Api api) {
    super(false);
    this.config = config;
    this.configManager = configManager;

    this.setBackground(ColorScheme.DARK_GRAY_COLOR);
    this.setLayout(new BorderLayout());
    this.setBorder(DEFAULT_BORDER);

    if (config.loginToken().isEmpty()) {
      PluginPanel loginPanel = new LoginPanel(config, configManager, api);
      this.add(loginPanel, BorderLayout.NORTH);
    }
  }
}
