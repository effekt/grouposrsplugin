package com.grouposrs;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.grouposrs.api.Api;
import com.grouposrs.player.Player;
import com.grouposrs.player.Quests;
import com.grouposrs.ui.Icon;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.PlayerSpawned;
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
	@Getter
	@Inject
	private Client client;
	@Inject
	private ClientToolbar clientToolbar;
	@Inject
	private GroupOSRSConfig config;
	@Inject
	private Player player;
	@Inject
	private Api api;
	@Inject
	private Quests quests;
	@Inject
	private ConfigManager configManager;

	private static final String GROUP_OSRS_TITLE = "Group OSRS";

	private GroupOSRSPanel groupOSRSPanel;
	private NavigationButton navigationButton;

	@Override
	protected void startUp() throws Exception {
		log.info("Group OSRS started!");

		initPanel();
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Group OSRS stopped!");

		destroyPanel();
	}

	private void initPanel()
	{
		groupOSRSPanel = new GroupOSRSPanel(config, configManager, api);

		final BufferedImage navigationButtonIcon = Icon.TOOLBAR.getImage();

		navigationButton = NavigationButton.builder()
				.tooltip(GROUP_OSRS_TITLE)
				.icon(navigationButtonIcon)
				.panel(groupOSRSPanel)
				.priority(10)
				.build();

		clientToolbar.addNavigation(navigationButton);
	}

	private void destroyPanel()
	{
		groupOSRSPanel = null;
		clientToolbar.removeNavigation(navigationButton);
	}

	@Schedule(
			asynchronous = true,
			period = 3,
			unit = ChronoUnit.SECONDS
	)
	public void postToApi() {
		if (config.loginToken().isBlank())
			return;

		if (player.isLoggedIn())
			api.postToApi();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			player.init();
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		quests.update();
	}

	@Provides
	GroupOSRSConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GroupOSRSConfig.class);
	}
}
