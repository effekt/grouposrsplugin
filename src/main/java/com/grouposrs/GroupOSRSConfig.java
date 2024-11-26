package com.grouposrs;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("grouposrs")
public interface GroupOSRSConfig extends Config
{
	@ConfigSection(
			description = "Group details",
			name = "Group Config",
			position = 0
	)
	String groupSection = "GroupSection";

	@ConfigItem(
			description = "Group name provided on GroupOSRS website.",
			keyName = "groupName",
			name = "Group Name (on GroupOSRS)",
			section = groupSection
	)
	default String groupName() {
		return "";
	}

	@ConfigItem(
			description = "JWT to authenticate with API.",
			hidden = true,
			keyName = "loginToken",
			name = "Login Token",
			secret = true
	)
	default String loginToken() {
		return "";
	}

	@ConfigItem(
			description = "UUID",
			hidden = true,
			keyName = "uuid",
			name = "uuid",
			secret = true
	)
	default String uuid() {
		return "";
	}

	@ConfigItem(
			description = "Phrase",
			hidden = true,
			keyName = "phrase",
			name = "Phrase",
			secret = true
	)
	default String phrase() {
		return "";
	}
}
