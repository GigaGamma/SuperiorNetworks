package com.superiorcraft.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu implements Listener {
	
	public Inventory inv;
	
	public Menu(String name, int size) {
		inv = Bukkit.getServer().createInventory(null, size, name);
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.plugin);
	}
	
	public void addItem(ItemStack item, int place) {
		inv.setItem(place, item);
	}
	
	public void show(Player p) {
		/*inv.clear();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null) {
				inv.setItem(i, items.get(i));
			}
		}*/
		p.openInventory(inv);
	}
}
