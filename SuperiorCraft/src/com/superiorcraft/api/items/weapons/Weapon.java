package com.superiorcraft.api.items.weapons;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public interface Weapon extends Listener {
	
	public String getName();
	public double getDamage();
	public void useSpecial(Player player);
	public ItemStack getItem();
	
}
