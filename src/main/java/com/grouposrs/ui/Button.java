package com.grouposrs.ui;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Callable;

@Getter
@Slf4j
public class Button {
  private static final EmptyBorder PANEL_BORDER = new EmptyBorder(5, 0, 5, 0);

  private final JPanel panel;
  private final JButton button;

  public Button(String buttonText, Callable<Void> onLeftClick) {
    this.panel = new JPanel();
    this.panel.setBorder(PANEL_BORDER);
    this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

    this.button = new JButton(buttonText);
    this.button.setBackground(ColorScheme.DARK_GRAY_COLOR);
    this.button.setFont(FontManager.getRunescapeSmallFont());

    this.button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (e.getButton() == MouseEvent.BUTTON1) {
          try {
            onLeftClick.call();
          } catch (Exception ignored) {}
        }
      }

      @Override
      public void mouseEntered(java.awt.event.MouseEvent mouseEvent)
      {
        Button.this.button.setBackground(ColorScheme.DARK_GRAY_HOVER_COLOR);
      }

      @Override
      public void mouseExited(java.awt.event.MouseEvent mouseEvent)
      {
        Button.this.button.setBackground(ColorScheme.DARK_GRAY_COLOR);
      }
    });

    this.panel.add(this.button);
  }
}
