package com.grouposrs.panel;

import com.google.gson.JsonObject;
import com.grouposrs.GroupOSRSConfig;
import com.grouposrs.api.Api;
import com.grouposrs.ui.Button;
import com.grouposrs.ui.LabeledInput;
import com.grouposrs.ui.TitledPanel;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;

@Slf4j
public class LoginPanel extends PluginPanel {
  private final GroupOSRSConfig config;
  private final ConfigManager configManager;
  private final LabeledInput secretPhrase;
  private final LabeledInput uuid;

  public LoginPanel(GroupOSRSConfig config, ConfigManager configManager, Api api) {
    super(false);
    this.config = config;
    this.configManager = configManager;

    JPanel loginPanel = new TitledPanel("Login").getPanel();
    this.uuid = new LabeledInput("Code");
    this.secretPhrase = new LabeledInput("Phrase");
    JPanel loginButton = new Button("Log In", () -> {
      JsonObject response = api.logIn(this.uuid.getTextField().getText(), this.secretPhrase.getTextField().getText());
      String token = response.get("data").getAsJsonObject().get("token").getAsString();
      log.info(token);

      if (!token.isBlank()) {
        configManager.setConfiguration("grouposrs", "loginToken", token);
        configManager.sendConfig();
      }
      return null;
    }).getPanel();

    loginPanel.add(this.uuid.getPanel());
    loginPanel.add(this.secretPhrase.getPanel());
    loginPanel.add(loginButton);

    this.add(loginPanel);
  }
}
