package com.superiorcraft.Forge;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomCraftingRecipe {
	
	public ItemStack[] recipe = new ItemStack[9];
	
	public CustomCraftingRecipe(ItemStack[] a) {
		recipe = a;
	}
	
	public boolean check(ItemStack[] a) {
		boolean match = true;
		
		for (int i = 0; i < recipe.length; i++) {
			if (match) {
				if (recipe[i] != null) {
					match = recipe[i].equals(a[i]);
				}
				else {
					
				}
			}
		}
		
		return match;
	}
	
	public void onCraft(InventoryCloseEvent e) {
		
	}
	
}
