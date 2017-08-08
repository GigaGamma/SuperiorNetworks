package com.superiorcraft.api;

import org.bukkit.event.Listener;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.blocks.CustomPanel;
import com.superiorcraft.api.crafting.CustomCrafting;
import com.superiorcraft.api.crafting.CustomCraftingRecipe;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.realism.BlockBreakRule;

public class Registry {
	
	public static void registerListener(Listener l) {
		SuperiorCraft.plugin.getServer().getPluginManager().registerEvents(l, SuperiorCraft.plugin);
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
	
}
