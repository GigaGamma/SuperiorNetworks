package com.superiorcraft.api.crafting;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomCraftingRecipe {
	
	public ItemStack[] recipe = new ItemStack[9];
	public String ncon = CustomCrafter.name;
	public ItemStack out;
	
	public CustomCraftingRecipe(ItemStack[] a, ItemStack out) {
		recipe = a;
		this.out = out;
	}
	
	public CustomCraftingRecipe(ItemStack[] a, ItemStack out, String ncon) {
		recipe = a;
		this.out = out;
		this.ncon = ncon;
	}
	
	public boolean check(ItemStack[] a, Inventory inv) {
		boolean match = true;
		//System.out.println(inv.getName());
		//System.out.println(ncon);
		if (inv.getSize() == 9 && nCheck(inv.getName())) {
			for (int i = 0; i < recipe.length; i++) {
				if (match) {
					System.out.println(a[i]);
					System.out.println(recipe[i]);
					if (recipe[i] != null) {
						match = recipe[i].equals(a[i]);
					}
					else {
						
					}
				}
			}
		} else {
			return false;
		}
		
		return match;
	}
	
	public boolean nCheck(String name) {
		if (name.equals(ncon)) {
			return true;
		}
		
		return false;
	}
	
	public void onCraft(InventoryClickEvent e) {
		if (nCheck(e.getInventory().getName())) {
			craft(e);
		}
	}
	
	public void craft(InventoryClickEvent e) {
		
	}
	
	public void onCraft(InventoryCloseEvent e) {
		if (nCheck(e.getInventory().getName())) {
			craft(e);
		}
	}
	
	public void craft(InventoryCloseEvent e) {
		
	}
	
	public void onCraft(InventoryMoveItemEvent e) {
		if (nCheck(e.getDestination().getName())) {
			craft(e);
		}
	}
	
	public void craft(InventoryMoveItemEvent e) {
		
	}
	
}
