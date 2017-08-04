package com.superiorcraft.api;

import org.bukkit.event.Listener;

import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.blocks.CustomPanel;
import com.superiorcraft.api.crafting.CustomCrafting;
import com.superiorcraft.api.crafting.CustomCraftingRecipe;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.main.Main;

public class Registry {
	
	public static void registerListener(Listener l) {
		Main.plugin.getServer().getPluginManager().registerEvents(l, Main.plugin);
	}
	
	public static void registerBlock(CustomBlock b) {
		Main.plugin.getServer().getPluginManager().registerEvents(b, Main.plugin);
	}
	
	public static void registerPanel(CustomPanel p) {
		Main.plugin.getServer().getPluginManager().registerEvents(p, Main.plugin);
	}
	
	public static void registerItem(CustomItem i) {
		Main.plugin.getServer().getPluginManager().registerEvents(i, Main.plugin);
	}
	
	public static void addRecipe(CustomCraftingRecipe r) {
		CustomCrafting.recipes.add(r);
	}
	
}
