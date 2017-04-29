package com.superiorcraft.api.recipes;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.CustomBlockLoader;
import com.superiorcraft.api.CustomCraftingRecipe;

public class InOutRecipe extends CustomCraftingRecipe {
	
	public ItemStack output;
	
	public InOutRecipe(ItemStack[] a, ItemStack o) {
		super(a);
		output = o;
	}
	
	@Override
	public void craft(InventoryCloseEvent e) {
		e.getInventory().clear();
		e.getInventory().setItem(4, output);
	}
	
	@Override
	public void craft(InventoryMoveItemEvent e) {
		e.getDestination().clear();
		e.getDestination().setItem(4, output);
	}

}
