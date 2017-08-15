package com.superiorcraft.api.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Button {
	
	private ItemStack item;
	
	public Button(ItemStack item) {
		setItem(item);
	}
	
	public void onClick(Player p, Inventory i) {
		
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
	
}
