package com.grouposrs.ui;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@Getter
@Slf4j
public class TitledPanel {
  private static final LineBorder ROUNDED_LINE_BORDER = new LineBorder(ColorScheme.BORDER_COLOR, 3, true);
  private static final EmptyBorder DEFAULT_BORDER = new EmptyBorder(0, 5, 0, 5);

  private final JPanel panel;

  public TitledPanel(String panelTitle) {
    TitledBorder titledBorder = BorderFactory.createTitledBorder(ROUNDED_LINE_BORDER, panelTitle);
    titledBorder.setTitleJustification(TitledBorder.CENTER);
    titledBorder.setTitleFont(FontManager.getRunescapeSmallFont());

    this.panel = new JPanel();
    this.panel.setBorder(BorderFactory.createCompoundBorder(titledBorder, DEFAULT_BORDER));
    this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
  }
}
