package com.grouposrs.ui;

import net.runelite.client.ui.FontManager;

public class RSLabel extends Label {
  public enum Size {
    SMALL,
    NORMAL
  }

  public RSLabel(String labelText, Size size) {
    super(labelText);

    if (size == Size.SMALL) {
      this.setFont(FontManager.getRunescapeSmallFont());
      return;
    }
    this.setFont(FontManager.getRunescapeFont());
  }
}
