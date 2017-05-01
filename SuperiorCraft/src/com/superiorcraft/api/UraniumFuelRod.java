package com.superiorcraft.api;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;

public class UraniumFuelRod extends CustomTexturedBlock {
	
	public UraniumFuelRod(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.DIAMOND_BLOCK, Color.fromRGB(0, 20, 0));
		getTexture().setLayerSecondary(CustomBlockTexture.DAYLIGHT_TOP, Color.GREEN, true);
		
		setMaterial(Material.GLOWSTONE);
	}
	
}
