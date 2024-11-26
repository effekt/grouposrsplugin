package com.grouposrs.panel;

import com.google.gson.JsonObject;
import com.grouposrs.GroupOSRSPlugin;
import com.grouposrs.ui.Button;
import com.grouposrs.ui.LabeledInput;
import com.grouposrs.ui.TitledPanel;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class LoginPanel extends PanelContainer {
  private final transient LabeledInput secretPhrase;
  private final transient LabeledInput uuid;

  public LoginPanel(GroupOSRSPlugin plugin) {
    super(plugin);

    JPanel loginPanel = new TitledPanel("Login").getPanel();
    this.uuid = new LabeledInput("Code");
    this.secretPhrase = new LabeledInput("Phrase");
    JPanel loginButton = new Button("Log In", () -> {
      JsonObject response = plugin.getApi().logIn(this.uuid.getTextField().getText(), this.secretPhrase.getTextField().getText());
      String token = response.get("data").getAsJsonObject().get("token").getAsString();
      log.info(token);

      if (!token.isBlank()) {
        plugin.getConfigManager().setConfiguration(GroupOSRSPlugin.CONFIG_KEY, "uuid", this.uuid.getTextField().getText());
        plugin.getConfigManager().setConfiguration(GroupOSRSPlugin.CONFIG_KEY, "phrase", this.secretPhrase.getTextField().getText());
        plugin.getConfigManager().setConfiguration(GroupOSRSPlugin.CONFIG_KEY, "loginToken", token);
        plugin.getConfigManager().sendConfig();

        plugin.getPanel().removeAll();
        plugin.getPanel().add(new PlayerPanel(plugin));
        plugin.getPanel().revalidate();
        plugin.getPanel().repaint();
      }
      return null;
    }).getPanel();

    this.uuid.getTextField().setText(plugin.getConfig().uuid());
    this.secretPhrase.getTextField().setText(plugin.getConfig().phrase());

    loginPanel.add(this.uuid.getPanel());
    loginPanel.add(this.secretPhrase.getPanel());
    loginPanel.add(loginButton);

    this.add(loginPanel);
  }

  public boolean shouldUpdate() {
    return false;
  }
}
