package com.superiorcraft.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MYML implements CommandExecutor, Listener {
	
	public HashMap<ItemStack, String> onclick = new HashMap<ItemStack, String>();
	
	public void run(String name, Player player) {
		String title = Main.plugin.getConfig().getString("myml." + name + ".title").replace("(player)", player.getName());
		Menu menu = new Menu(title, Main.plugin.getConfig().getInt("myml." + name + ".length"));
		onclick.clear();
		for (int i = 0; i < Main.plugin.getConfig().getInt("myml." + name + ".length"); i++) {
			//player.sendMessage(Main.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item"));
	
			if (Main.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i)) != null) {
				System.out.println(Main.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item"));
				ItemStack item = new ItemStack(Material.getMaterial(Main.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item")), Main.plugin.getConfig().getInt("myml." + name + ".items." + Integer.toString(i) + ".amount"));
				if (Main.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".meta") != null) {
					if (Main.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".meta.data") != null) {
						item = new ItemStack(Material.getMaterial(Main.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item")), Main.plugin.getConfig().getInt("myml." + name + ".items." + Integer.toString(i) + ".amount"), (short) Main.plugin.getConfig().getInt("myml." + name + ".items." + Integer.toString(i) + ".meta.data"));
					}
				}
				if (Main.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".events") != null) {
					if (Main.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".events.onclick") != null) {
						onclick.put(item, Main.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".events.onclick"));
						System.out.print("a");
					}
				}
				menu.inv.setItem(i, item);
			
				
			}
		}
		
		player.openInventory(menu.inv);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("myml")) {
			if (args.length != 0) {
				if (args[0].equals("edit")) {
					Main.plugin.getConfig().set("myml." + args[1], args[2]);
				
					Main.plugin.saveConfig();
				}
			
				else if (args[0].equals("reload")) {
					Main.plugin.reloadConfig();
				
					Main.plugin.saveConfig();
				}
			
				else {
					Main.plugin.reloadConfig();
					
					Main.plugin.saveConfig();
					
					Player player = (Player) sender;
					run(args[0], player);
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Usage: /myml <[file]:edit:reload> [...]");
			}
			
			return true;
		}
		
		return false;
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (onclick.containsKey(e.getCurrentItem())) {
			if (onclick.get(e.getCurrentItem()).equals("mclose")) {
				e.getWhoClicked().closeInventory();
				
			}
		}
	}

}
