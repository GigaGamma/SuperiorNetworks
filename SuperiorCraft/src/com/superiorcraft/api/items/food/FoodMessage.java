package com.superiorcraft.api.items.food;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.superiorcraft.nms.JsonMessage;

public class FoodMessage extends BukkitRunnable {

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			JsonMessage.sendActionBar(p, (p.getFoodLevel() > 16 ? ChatColor.GREEN : ChatColor.RED) + "Food Level: " + p.getFoodLevel() + " / " + ChatColor.GRAY + "Saturation Level: " + p.getSaturation());
		}
	}
	
}
