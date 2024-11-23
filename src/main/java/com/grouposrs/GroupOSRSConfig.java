package com.grouposrs;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("grouposrs")
public interface GroupOSRSConfig extends Config
{
	@ConfigSection(
			name = "Group Config",
			description = "Group details",
			position = 0
	)
	String groupSection = "GroupSection";

	@ConfigItem(
			keyName = "groupName",
			name = "Group Name (on GroupOSRS)",
			description = "Group name provided on GroupOSRS website.",
			section = groupSection
	)
	default String groupName() {
		return "";
	}

	@ConfigItem(
			description = "JWT to authenticate with API.",
			keyName = "loginToken",
			name = "Login Token",
			secret = true
	)
	default String loginToken() {
		return "";
	}
}
