package com.superiorcraft.api;

import org.bukkit.block.Dropper;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomCrafter extends CustomCraftingRecipe {
	
	public static String name = "&6Custom Crafter".replace('&', '§');
	
	public CustomCrafter(ItemStack[] a) {
		super(a);
		ncon = "container.dropper";
	}
	
	@Override
	public void craft(InventoryCloseEvent e) {
		e.getPlayer().sendMessage(name + "&r created!".replace('&', '§'));
		Dropper a = (Dropper) e.getInventory().getLocation().getBlock().getState();
		a.setCustomName(name);
		e.getInventory().clear();
	}
	
}
