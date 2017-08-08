package com.superiorcraft.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import com.superiorcraft.SuperiorCraft;

public class AddMin implements CommandExecutor, TabCompleter {
	
	public String[] troll_fakechat = {"troll", "fakechat", "player"};
	public String[] troll_fakechat2 = {"troll", "fakechat", "string"};
	
	public String[] troll_noreload = {"troll", "noreload"};
	public static HashMap<String, Boolean> noreload = new HashMap<String, Boolean>();
	
	public AddMin() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.isOp()) {
				noreload.put(player.getName(), true);
			}
		}
	}
	
	private static Player getPlayer(String player) {
		for(Player ps : Bukkit.getOnlinePlayers()){
			if (ps.getName().equalsIgnoreCase(player)) {
				return ps;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("addmin")) {
			if (!(sender instanceof Player)) return false;
			
			Player player = (Player) sender;
			
			if (CommandConstruct.match(args, troll_fakechat)) {
				String name = getPlayer(args[2]).getName();
				
		        if (player.isOp()) {
		        	name = SuperiorCraft.plugin.getConfig().getString("players." + getPlayer(args[2]).getName() + ".name") + SuperiorCraft.plugin.getConfig().getString("players." + getPlayer(args[2]).getName() + ".suffix");
		        }
				
		        SuperiorCraft.plugin.getConfig().set("players." + player.getName() + ".name", SuperiorCraft.plugin.getConfig().getString("players." + getPlayer(args[2]).getName() + ".name"));
		        SuperiorCraft.plugin.getConfig().set("players." + player.getName() + ".suffix", SuperiorCraft.plugin.getConfig().getString("players." + getPlayer(args[2]).getName() + ".suffix"));
				player.sendMessage(("&7You are now fakechatting under " +name + "&r&7's name").replace('&', '§'));
			}
			
			else if (CommandConstruct.match(args, troll_fakechat2)) {
		        SuperiorCraft.plugin.getConfig().set("players." + player.getName() + ".name", args[2].replace('&', '§'));
		        SuperiorCraft.plugin.getConfig().set("players." + player.getName() + ".suffix", " ");
				player.sendMessage(("&7You are now fakechatting under " + args[2].replace('&', '§') + "&r&7's name").replace('&', '§'));
			}
			
			else if (CommandConstruct.match(args, troll_noreload)) {
				if (!noreload.containsKey(player.getName())) {
					noreload.put(player.getName(), true);
				}
				else {
					noreload.replace(player.getName(), !noreload.get(player.getName()));
				}
				
				if (noreload.get(player.getName()) == true) {
					player.sendMessage(ChatColor.GREEN + "NoReload enabled!");
				}
				else {
					player.sendMessage(ChatColor.RED + "NoReload disabled!");
				}
			} else {
				player.sendMessage(CommandConstruct.getNoArgsMessage());
			}
			
			return true;
		}
	
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<String> a = null;
		
		if (command.getName().equalsIgnoreCase("addmin")) {
			a = new ArrayList<String>();
			if (args.length <= 1) {
				if ("troll".startsWith(args[0])) {
					a.add("troll");
				}
			}
			else if (args.length == 2) {
				if ("fakechat".startsWith(args[1])) {
					a.add("fakechat");
				}
				if ("noreload".startsWith(args[1])) {
					a.add("noreload");
				}
			}
		}
		
		return a;
	}

}
