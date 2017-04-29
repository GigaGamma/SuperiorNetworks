package com.superiorcraft.api.recipes;

import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.CustomBlockLoader;
import com.superiorcraft.api.CustomCraftingRecipe;
import com.superiorcraft.api.CustomItemLoader;
import com.superiorcraft.api.UraniumFuelRod;
import com.superiorcraft.api.UraniumIngot;

public class UraniumFuelRodRecipe extends CustomCraftingRecipe {
	
	public static ItemStack[] recipe = {CustomItemLoader.getItem("uranium_ingot"), new ItemStack(Material.IRON_BLOCK)};
	
	public UraniumFuelRodRecipe(ItemStack[] a) {
		super(a);
	}
	
	@Override
	public void craft(InventoryCloseEvent e) {
		e.getInventory().clear();
		e.getInventory().setItem(4, CustomBlockLoader.getBlock("uranium_fuel_rod"));
	}
	
	@Override
	public void craft(InventoryMoveItemEvent e) {
		e.getDestination().clear();
		e.getDestination().setItem(4, CustomBlockLoader.getBlock("uranium_fuel_rod"));
	}
	
}
