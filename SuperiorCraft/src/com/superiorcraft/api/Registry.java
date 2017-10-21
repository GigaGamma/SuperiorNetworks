package com.superiorcraft.api;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.blocks.CustomPanel;
import com.superiorcraft.api.commands.CustomCommand;
import com.superiorcraft.api.crafting.CustomCrafting;
import com.superiorcraft.api.crafting.CustomCraftingRecipe;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.nms.NMSAdapter;
import com.superiorcraft.realism.BlockBreakRule;
import com.superiorcraft.server.Server;
import com.superiorcraft.server.ServerSelector;

public class Registry {
	
	public static void registerListener(Listener l) {
		SuperiorCraft.plugin.getServer().getPluginManager().registerEvents(l, SuperiorCraft.plugin);
	}
	
	public static void registerCommand(CustomCommand command) {
		try {
			Field f = NMSAdapter.getCraftBukkitClass("CraftServer").getDeclaredField("commandMap");
			f.setAccessible(true);
			CommandMap cmap = (CommandMap) f.get(Bukkit.getServer());
			cmap.register("", command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void registerBlock(CustomBlock b) {
		SuperiorCraft.plugin.getServer().getPluginManager().registerEvents(b, SuperiorCraft.plugin);
	}
	
	public static void registerBlockBreakRule(BlockBreakRule r) {
		BlockBreakRule.rules.add(r);
	}
	
	public static void registerPanel(CustomPanel p) {
		SuperiorCraft.plugin.getServer().getPluginManager().registerEvents(p, SuperiorCraft.plugin);
	}
	
	public static void registerItem(CustomItem i) {
		SuperiorCraft.plugin.getServer().getPluginManager().registerEvents(i, SuperiorCraft.plugin);
	}
	
	public static void addRecipe(CustomCraftingRecipe r) {
		CustomCrafting.recipes.add(r);
	}
	
	public static void addServer(Server s) {
		ServerSelector.getServers().add(s);
	}
	
}
