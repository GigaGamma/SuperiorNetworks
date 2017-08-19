package com.superiorcraft.api.items.food;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class ChocolateMilk extends CustomFood {

	public ChocolateMilk(ItemStack item, String id) {
		super(item, id);
	}

	@Override
	public void onEat(Player player) {
		for (PotionEffect ef : player.getActivePotionEffects()) {
			player.removePotionEffect(ef.getType());
		}
	}

	@Override
	public int getNutritionLevel() {
		return 4;
	}

	@Override
	public int getSaturationLevel() {
		return 1;
	}
	
	@Override
	public boolean hasToBeHungry() {
		return false;
	}

}
