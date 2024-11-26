package com.grouposrs.ui;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Slf4j
public class Label extends JLabel {
  private static final Color LABEL_FOREGROUND_COLOR = ColorScheme.LIGHT_GRAY_COLOR;
  private static final EmptyBorder LABEL_BORDER = new EmptyBorder(0, 0, 1, 0);

  public Label(String labelText) {
    this.setText(labelText);
    this.setBorder(LABEL_BORDER);
    this.setForeground(LABEL_FOREGROUND_COLOR);
    this.setFont(FontManager.getDefaultFont());
  }



  public Label(String labelText, Boolean bold) {
    this.setText(labelText);
    this.setBorder(LABEL_BORDER);
    this.setForeground(LABEL_FOREGROUND_COLOR);

    if (bold) {
      this.setFont(FontManager.getRunescapeBoldFont());
    }
  }
}
