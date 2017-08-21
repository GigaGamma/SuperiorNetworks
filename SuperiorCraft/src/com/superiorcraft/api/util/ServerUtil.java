package com.superiorcraft.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerUtil {
	
	public static Collection<? extends Player> getPlayers() {
		return Bukkit.getServer().getOnlinePlayers();
	}
	
	public static List<String> getPlayerNames() {
		ArrayList<String> f = new ArrayList<String>();
		for (Player p : getPlayers()) {
			f.add(p.getCustomName());
		}
		return f;
	}
	
}
