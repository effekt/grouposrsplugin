package com.grouposrs.ui;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Slf4j
public class LongText extends JTextArea {
  public LongText(String text) {
    this.setRows(2);
    this.setColumns(24);
    this.setText(text);
    this.setWrapStyleWord(true);
    this.setLineWrap(true);
    this.setOpaque(false);
    this.setEditable(false);
    this.setFocusable(false);
    this.setBackground(ColorScheme.DARK_GRAY_COLOR);
    Font textAreaFont = FontManager.getRunescapeSmallFont();
    textAreaFont = textAreaFont.deriveFont(textAreaFont.getStyle(), (float)textAreaFont.getSize() - (float)0.1);
    this.setFont(textAreaFont);
    this.setBorder(new EmptyBorder(0, 0, 0, 0));
  }
}
