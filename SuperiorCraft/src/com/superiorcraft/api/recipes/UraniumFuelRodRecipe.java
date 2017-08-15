package com.superiorcraft.api.recipes;

import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.crafting.CustomCraftingRecipe;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.more.UraniumFuelRod;
import com.superiorcraft.api.more.UraniumIngot;

public class UraniumFuelRodRecipe extends CustomCraftingRecipe {
	
	public static ItemStack[] recipe = {CustomItem.getItem("uranium_ingot"), new ItemStack(Material.IRON_BLOCK)};
	
	public UraniumFuelRodRecipe(ItemStack[] a) {
		super(a, CustomBlock.getBlock("uranium_fuel_rod"));
	}
	
	@Override
	public void craft(InventoryCloseEvent e) {
		e.getInventory().clear();
		e.getInventory().setItem(4, CustomBlock.getBlock("uranium_fuel_rod"));
	}
	
	@Override
	public void craft(InventoryClickEvent e) {
		e.getInventory().clear();
		e.getInventory().setItem(4, CustomBlock.getBlock("uranium_fuel_rod"));
	}
	
}
