package com.superiorcraft.api.recipes;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.crafting.CustomCraftingRecipe;

public class InOutRecipe extends CustomCraftingRecipe {

	public InOutRecipe(ItemStack[] a, ItemStack o) {
		super(a, o);
	}
	
	public InOutRecipe(ItemStack[] a, ItemStack o, String ncon) {
		super(a, o, ncon);
	}
	
	@Override
	public void craft(InventoryCloseEvent e) {
		e.getInventory().clear();
		e.getInventory().setItem(4, out);
	}
	
	@Override
	public void craft(InventoryClickEvent e) {
		e.getInventory().clear();
		e.getInventory().setItem(4, out);
	}
	
	@Override
	public void craft(InventoryMoveItemEvent e) {
		e.getDestination().clear();
		e.getDestination().setItem(4, out);
	}

}
