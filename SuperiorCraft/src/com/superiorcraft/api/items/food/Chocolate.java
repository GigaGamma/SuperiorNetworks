package com.superiorcraft.api.items.food;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Chocolate extends CustomFood {

	public Chocolate(ItemStack item, String id) {
		super(item, id);
	}

	@Override
	public void onEat(Player player) {
		
	}

	@Override
	public int getNutritionLevel() {
		return 4;
	}

	@Override
	public int getSaturationLevel() {
		return 1;
	}

}
