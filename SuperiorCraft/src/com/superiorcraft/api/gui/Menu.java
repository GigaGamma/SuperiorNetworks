package com.superiorcraft.api.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.SuperiorCraft;

public class Menu implements Listener {
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	public Inventory inv;
	
	private ArrayList<Button> abuttons = new ArrayList<Button>();
	private ArrayList<Button> rbuttons = new ArrayList<Button>();
	
	public Menu(String name, int size) {
		inv = Bukkit.getServer().createInventory(null, size, name);
		Bukkit.getServer().getPluginManager().registerEvents(this, SuperiorCraft.plugin);
	}
	
	public Menu setBackground(ItemStack item) {
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, item);
		}
		
		return this;
	}
	
	public Menu addItem(ItemStack item) {
		inv.addItem(item);
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
	
	public ArrayList<Button> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<Button> buttons) {
		this.buttons = buttons;
	}
	
	public Menu addButton(Button button) {
		abuttons.add(button);
		return this;
	}
	
	public Menu removeButton(Button button) {
		rbuttons.add(button);
		return this;
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equals(inv.getName())) {
			onInventoryClick(e);
		}
		ListIterator<Button> badd = abuttons.listIterator();
		while (badd.hasNext()) {
			getButtons().add(badd.next());
		}
		ListIterator<Button> it = getButtons().listIterator();
		while (it.hasNext()) {
			Button b = it.next();
			if (rbuttons.contains(b)) {
				it.remove();
			}
			if (b.getItem().equals(e.getCurrentItem()) && e.getInventory().equals(inv)) {
				b.onClick((Player) e.getWhoClicked(), e.getInventory());
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent e) {
		
	}
	
	public void onInventoryClick(InventoryClickEvent e) {
		
	}
	
}
