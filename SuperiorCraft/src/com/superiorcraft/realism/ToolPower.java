package com.superiorcraft.realism;

import org.bukkit.inventory.ItemStack;

public enum ToolPower {
	WOOD (1),
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
		ToolPower converted = null;
		for (ToolPower p : ToolPower.values()) {
			if (item.getType().name().contains(p.name())) {
				 converted = p;
			}
		}
		
		return canBreakWith(converted);
	}
}
