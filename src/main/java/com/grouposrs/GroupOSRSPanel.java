package com.grouposrs;

import com.grouposrs.panel.PanelHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.PluginPanel;

import java.awt.*;

@Slf4j
public class GroupOSRSPanel extends PluginPanel {
  @Getter
  private final PanelHelper panelHelper;

  public GroupOSRSPanel(GroupOSRSPlugin plugin) {
    super(false);
    this.panelHelper = new PanelHelper(plugin);

    if (plugin.getConfig().loginToken().isEmpty()) {
      this.setPanel(PanelHelper.PanelKey.LOGIN);
    } else {
      this.setPanel(PanelHelper.PanelKey.PLAYER);
    }
  }

  public void setPanel(PanelHelper.PanelKey panelKey) {
    this.removeAll();
    this.add(this.panelHelper.getPanel(panelKey), BorderLayout.NORTH);
    this.revalidate();
    this.repaint();
  }

  public void update() {
    this.revalidate();
    this.repaint();
  }
}
