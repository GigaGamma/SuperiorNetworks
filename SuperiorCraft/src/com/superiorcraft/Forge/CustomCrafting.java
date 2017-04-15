package com.superiorcraft.Forge;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class CustomCrafting implements Listener {
	
	public String name;
	
	public ItemStack[] makeCrafter = new ItemStack[9];
	public CustomCraftingRecipe customCrafter;
	
	public CustomCrafting(String name) {
		this.name = name;
		
		for (CustomItemLoader cil : CustomItemLoader.items) {
			if (cil instanceof PowerCrystal) {
				ItemStack its = cil.item.clone();
				its.setAmount(1);
				makeCrafter[4] = its;
			}
		}
	}
	
	public void load() {
		customCrafter = new CustomCrafter(makeCrafter);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onCraft(InventoryCloseEvent e) {
		if (e.getInventory().getName().equals("container.dropper")) {
			if (customCrafter.check(e.getInventory().getContents())) {
				customCrafter.onCraft(e);
			}
			/*if (true) {
				e.getPlayer().sendMessage(name + "&r created!".replace('&', '§'));
				Dropper a = (Dropper) e.getInventory().getLocation().getBlock().getState();
				a.setCustomName(name);
				e.getInventory().clear();
			}*/
		}
		else if (e.getInventory().getName().contains("Custom Craft")) {
			if (customCrafter.check(e.getInventory().getContents())) {
				e.getPlayer().sendMessage("This is already a Custom Crafter");
			}
		}
 	}
	
}
