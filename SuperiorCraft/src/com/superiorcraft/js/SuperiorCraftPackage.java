package com.superiorcraft.js;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.Registry;
import com.superiorcraft.api.commands.CustomCommand;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.util.ServerUtil;
import com.superiorcraft.api.util.item.ItemConstruct;

public class SuperiorCraftPackage {
	
	public List<Player> getPlayers() {
		return ((List<Player>) ServerUtil.getPlayers());
	}
	
	public Registry getRegistry() {
		return new Registry();
	}
	
	public CustomItem item(String id, String name, ItemStack item) {
		return new CustomItem(item, id, name);
	}
	
	public ItemConstruct itemConstruct(Material mat) {
		return new ItemConstruct(mat);
	}
	
	public Server getServer() {
		return Bukkit.getServer();
	}
	
	public CustomCommand command(String name, Function4<CommandSender, Command, String, String[], Boolean> function) {
		CustomCommand command = new CustomCommand(name);
		command.setExecutor(new CommandExecutor() {
			
			@Override
			public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
				return function.apply(sender, command, label, args);
			}
			
		});
		Registry.registerCommand(command);
		return command;
	}
	
	public Player asPlayer(Entity entity) {
		return (Player) entity;
	}
	
	public ChatColor chatColor(String color) {
		return ChatColor.valueOf(color.toUpperCase().replace(" ", "_"));
	}
	
}
