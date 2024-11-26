package com.grouposrs.panel;

import com.grouposrs.GroupOSRSPlugin;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Slf4j
public abstract class PanelContainer extends JPanel {
  protected transient GroupOSRSPlugin plugin;

  private static final EmptyBorder DEFAULT_BORDER = new EmptyBorder(3, 3, 3, 3);

  protected PanelContainer(GroupOSRSPlugin plugin) {
    this.plugin = plugin;

    this.setBackground(ColorScheme.DARK_GRAY_COLOR);
    this.setLayout(new BorderLayout());
    this.setBorder(DEFAULT_BORDER);
  }

  public abstract boolean shouldUpdate();
}
