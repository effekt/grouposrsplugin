package com.grouposrs.ui;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.FlatTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter
@Slf4j
public class LabeledInput {
  private static final Color LABEL_FOREGROUND_COLOR = ColorScheme.LIGHT_GRAY_COLOR;
  private static final Color TEXT_FIELD_BACKGROUND_COLOR = ColorScheme.DARKER_GRAY_COLOR;
  private static final Dimension TEXT_FIELD_MIN_SIZE = new Dimension(0, 30);
  private static final Dimension TEXT_FIELD_SIZE = new Dimension(PluginPanel.PANEL_WIDTH - 20, 30);
  private static final EmptyBorder LABEL_BORDER = new EmptyBorder(0, 0, 1, 0);
  private static final EmptyBorder PANEL_BORDER = new EmptyBorder(5, 0, 5, 0);

  private final JLabel label;
  private final FlatTextField textField;
  private final JPanel panel;

  public LabeledInput(String labelText) {
    this.label = new JLabel(labelText);
    this.label.setBorder(LABEL_BORDER);
    this.label.setForeground(LABEL_FOREGROUND_COLOR);
    this.label.setFont(FontManager.getRunescapeSmallFont());

    this.textField = new FlatTextField();
    this.textField.setPreferredSize(TEXT_FIELD_SIZE);
    this.textField.setMinimumSize(TEXT_FIELD_MIN_SIZE);
    this.textField.setBackground(TEXT_FIELD_BACKGROUND_COLOR);

    this.panel = new JPanel();
    this.panel.setBorder(PANEL_BORDER);
    this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

    this.panel.add(label);
    this.panel.add(textField);
  }
}
