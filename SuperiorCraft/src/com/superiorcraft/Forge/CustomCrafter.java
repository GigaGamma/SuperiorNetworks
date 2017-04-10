package com.superiorcraft.Forge;

import org.bukkit.block.Dropper;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomCrafter extends CustomCraftingRecipe {

	public CustomCrafter(ItemStack[] a) {
		super(a);
	}
	
	@Override
	public void onCraft(InventoryCloseEvent e) {
		e.getPlayer().sendMessage("&6Custom Crafter".replace('&', '§') + "&r created!".replace('&', '§'));
		Dropper a = (Dropper) e.getInventory().getLocation().getBlock().getState();
		a.setCustomName("&6Custom Crafter".replace('&', '§'));
		e.getInventory().clear();
		
		
	}
	
}
