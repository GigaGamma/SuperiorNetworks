package com.superiorcraft.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.superiorcraft.SuperiorCraft;

public class ConfigTranslater {
	
	public static boolean isCloaked(Player player) {
		if (SuperiorCraft.plugin.getConfig().getString("players." + player.getName() + ".cloaked") != null) {
			return SuperiorCraft.plugin.getConfig().getString("players." + player.getName() + ".cloaked").equals("yes");
		} else {
			return false;
		}
	}
	
	public static void setCloak(Player player, boolean state) {
		setCloakState(player, state);
		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (isCloaked(player)) {
				ps.hidePlayer(player);
			} else {
				ps.showPlayer(player);
			}
		}
	}
	
	public static void setCloakState(Player player, boolean state) {
		SuperiorCraft.plugin.getConfig().set("players." + player.getName() + ".cloaked", state ? "yes" : "no");
	}
	
	public static boolean hasRank(Player player) {
		return getRank(player) != null;
	}
	
	public static String getRank(Player player) {
		return SuperiorCraft.plugin.getConfig().getString("players." + player.getName() + ".rankpath");
	}
	
}
