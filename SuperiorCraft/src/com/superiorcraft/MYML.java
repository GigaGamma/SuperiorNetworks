package com.superiorcraft;

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

import com.superiorcraft.api.util.Menu;

public class MYML implements CommandExecutor, Listener {
	
	public HashMap<ItemStack, String> onclick = new HashMap<ItemStack, String>();
	
	public void run(String name, Player player) {
		String title = SuperiorCraft.plugin.getConfig().getString("myml." + name + ".title").replace("(player)", player.getName());
		Menu menu = new Menu(title, SuperiorCraft.plugin.getConfig().getInt("myml." + name + ".length"));
		onclick.clear();
		for (int i = 0; i < SuperiorCraft.plugin.getConfig().getInt("myml." + name + ".length"); i++) {
			//player.sendMessage(Main.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item"));
	
			if (SuperiorCraft.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i)) != null) {
				System.out.println(SuperiorCraft.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item"));
				ItemStack item = new ItemStack(Material.getMaterial(SuperiorCraft.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item")), SuperiorCraft.plugin.getConfig().getInt("myml." + name + ".items." + Integer.toString(i) + ".amount"));
				if (SuperiorCraft.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".meta") != null) {
					if (SuperiorCraft.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".meta.data") != null) {
						item = new ItemStack(Material.getMaterial(SuperiorCraft.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".item")), SuperiorCraft.plugin.getConfig().getInt("myml." + name + ".items." + Integer.toString(i) + ".amount"), (short) SuperiorCraft.plugin.getConfig().getInt("myml." + name + ".items." + Integer.toString(i) + ".meta.data"));
					}
				}
				if (SuperiorCraft.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".events") != null) {
					if (SuperiorCraft.plugin.getConfig().get("myml." + name + ".items." + Integer.toString(i) + ".events.onclick") != null) {
						onclick.put(item, SuperiorCraft.plugin.getConfig().getString("myml." + name + ".items." + Integer.toString(i) + ".events.onclick"));
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
					SuperiorCraft.plugin.getConfig().set("myml." + args[1], args[2]);
				
					SuperiorCraft.plugin.saveConfig();
				}
			
				else if (args[0].equals("reload")) {
					SuperiorCraft.plugin.reloadConfig();
				
					SuperiorCraft.plugin.saveConfig();
				}
			
				else {
					SuperiorCraft.plugin.reloadConfig();
					
					SuperiorCraft.plugin.saveConfig();
					
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
