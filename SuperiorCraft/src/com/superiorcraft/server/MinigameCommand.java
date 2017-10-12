package com.superiorcraft.server;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MinigameCommand implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sender is required to be a player");
			return false;
		}
		if (command.getName().equalsIgnoreCase("mg") && args.length > 0) {
			if (args[0].equalsIgnoreCase("leave")) {
				for (Minigame mg : Minigame.minigames) {
					mg.leave((Player) sender);
				}
			}
			for (Minigame mg : Minigame.minigames) {
				if (args[0].equalsIgnoreCase(mg.getName())) {
					sender.sendMessage("Joining minigame");
					mg.join((Player) sender);
				}
			}
			return true;
		}
		
		return false;
	}
	
}
