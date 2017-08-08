package com.superiorcraft.api.crafting;

import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class FoodCrafter extends CustomCraftingRecipe {
	
	public static String name = "&rFood Crafter".replace('&', '§');
	public static ItemStack[] recipe = {new ItemStack(Material.APPLE), new ItemStack(Material.IRON_BLOCK)};
	
	public FoodCrafter(ItemStack[] a) {
		super(a);
	}
	
	@Override
	public void craft(InventoryClickEvent e) {
		e.getWhoClicked().closeInventory();
		e.getWhoClicked().sendMessage("Bacon pancakes, makin' bacon pancakes (Milhorn)".replace('&', '§'));
		Dropper a = (Dropper) e.getInventory().getLocation().getBlock().getState();
		a.setCustomName(name);
		a.update();
		e.getInventory().clear();
	}
	
}
