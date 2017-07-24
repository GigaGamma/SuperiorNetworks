package com.superiorcraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandConstruct {
	
	private static Player getPlayer(String player) {
		for(Player ps : Bukkit.getOnlinePlayers()){
			if (ps.getName().equalsIgnoreCase(player)) {
				return ps;
			}
		}
		
		return null;
	}
	
	public static boolean match(String[] args, String[] pattern) {
		if (args.length == pattern.length) {
			for (int i = 0; i < args.length; i++) {
				if (pattern[i].equalsIgnoreCase("string")) {
					try {
						Float.valueOf(args[i]);
						return false;
					}
					catch (Exception e) {
						System.out.println(e);
					}
				}
				
				if (pattern[i].equalsIgnoreCase("player") && getPlayer(args[i]) == null) {
					return false;
				}
				
				else if (args[i].equalsIgnoreCase(pattern[i])) {
					
				}
				
				else {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public static String getNoArgsMessage() {
		return ChatColor.RED + "Please, get some /help";
	}
	
}
