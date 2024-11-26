package com.grouposrs;

import com.google.inject.Inject;
import com.google.inject.Provides;

import com.grouposrs.api.Api;
import com.grouposrs.player.Player;
import com.grouposrs.player.Quests;
import com.grouposrs.player.Skills;
import com.grouposrs.ui.Icon;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.task.Schedule;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

import java.time.temporal.ChronoUnit;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "GroupOSRS"
)
public class GroupOSRSPlugin extends Plugin
{
	@Inject
	@Getter
	private Api api;

	@Inject
	@Getter
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	@Getter
	private ConfigManager configManager;

	@Inject
	@Getter
	private GroupOSRSConfig config;

	@Getter
	private GroupOSRSPanel panel;

	@Inject
	@Getter
	private Player player;

	@Inject
	@Getter
	private Quests quests;

	@Inject
	@Getter
	private Skills skills;

	private static final String GROUP_OSRS_TITLE = "Group OSRS";

	public static final String CONFIG_KEY = "grouposrs";

	private NavigationButton navigationButton;

	@Override
	protected void startUp() throws Exception {
		log.info("Group OSRS started!");

    this.configManager.setConfiguration(CONFIG_KEY, "loginToken", "");

    this.initPanel(this);
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Group OSRS stopped!");

    this.destroyPanel();
	}

	private void initPanel(GroupOSRSPlugin plugin)
	{
    this.panel = new GroupOSRSPanel(plugin);

		final BufferedImage navigationButtonIcon = Icon.TOOLBAR.getImage();

    this.navigationButton = NavigationButton.builder()
				.tooltip(GROUP_OSRS_TITLE)
				.icon(navigationButtonIcon)
				.panel(this.panel)
				.priority(10)
				.build();

    this.clientToolbar.addNavigation(this.navigationButton);
	}

	private void destroyPanel()
	{
    this.panel = null;
    this.clientToolbar.removeNavigation(this.navigationButton);
	}

	@Schedule(
			asynchronous = true,
			period = 3,
			unit = ChronoUnit.SECONDS
	)
	public void postToApi() {
		if (this.config.loginToken().isBlank())
			return;

    this.player.init();

		if (this.player.isLoggedIn())
      this.player.postUpdates();
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged) {
//		log.info(statChanged.)
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {

	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
    this.quests.update();
	}

	@Provides
	GroupOSRSConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GroupOSRSConfig.class);
	}
}
