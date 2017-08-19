package com.superiorcraft.realism;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.items.CustomTool;

public enum ToolPower {
	WOOD (1),
	FLINT (2),
	STONE (2), 
	IRON (3), 
	GOLD (4), 
	DIAMOND (5);
	
	private final int power;
	
	private int power() { return power; }
	
	ToolPower(int power) {
		this.power = power;
	}
	
	boolean canBreakWith(ToolPower curr) {
		return power() <= curr.power();
	}
	
	boolean canBreakWith(ItemStack item) {
		ToolPower converted = ToolPower.WOOD;
		for (CustomItem it : CustomItem.items) {
			if (it instanceof CustomTool) {
				if (item != null && item.getType() != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && it.getItem().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
					return canBreakWith(((CustomTool) it).getPower());
				}
			}
		}
		
		for (ToolPower p : ToolPower.values()) {
			if (item != null && item.getType() != null && item.getType().name().contains(p.name())) {
				converted = p;				}
		}
		
		return canBreakWith(converted);
	}
}
