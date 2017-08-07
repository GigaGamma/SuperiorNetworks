package com.superiorcraft.api.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.Main;

public class Menu implements Listener {
	
	public Inventory inv;
	
	public Menu(String name, int size) {
		inv = Bukkit.getServer().createInventory(null, size, name);
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.plugin);
	}
	
	public Menu setBackground(ItemStack item) {
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, item);
		}
		
		return this;
	}
	
	public Menu addItem(ItemStack item, int place) {
		inv.setItem(place, item);
		return this;
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
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equals(inv.getName())) {
			onInventoryClick(e);
		}
	}
	
	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent e) {
		
	}
	
	public void onInventoryClick(InventoryClickEvent e) {
		
	}
	
}
