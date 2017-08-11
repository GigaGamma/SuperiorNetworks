package com.superiorcraft.anticheat;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.nms.JsonMessage;
import com.superiorcraft.nms.NMSAdapter;

// AKA SuperiorSecure

public class AntiCheat implements Listener {
	
	private static FileConfiguration config;
	
	public AntiCheat() {
		config = YamlConfiguration.loadConfiguration(new File(SuperiorCraft.plugin.getDataFolder(), "anticheat.yml"));
		if (config.getString("enabled").equalsIgnoreCase("true")) {
			JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage("[SuperiorCraft > SuperiorSecure] AntiCheat is now activated", "green", "If you are not a developer, you can ignore this", "light_purple", "")});
		} else {
			JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage("[SuperiorCraft > SuperiorSecure] SuperiorSecure is either not enabled or it could not find the anticheat.yml", "red", "If you are not a developer, you can ignore this", "light_purple", "")});
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (!e.getPlayer().isOnGround() && !e.getPlayer().hasPermission("superiorcraft.fly")) {
			Location moved = e.getTo().clone().subtract(e.getFrom());
			Block b = e.getPlayer().getWorld().getHighestBlockAt(e.getPlayer().getLocation());
			if ((moved.getX() > 0.2 || moved.getZ() > 0.2) && e.getPlayer().getLocation().getY() - b.getY() > 10) {
				e.setCancelled(true);
				e.getPlayer().teleport(b.getLocation().add(0, 1, 0));
				e.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', "&4[SuperiorCraft > SuperiorSecure] Birds take flight, not hackers"));
			}
		}
	}
	
}
