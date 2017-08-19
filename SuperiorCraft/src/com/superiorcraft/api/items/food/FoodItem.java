package com.superiorcraft.api.items.food;

import org.bukkit.entity.Player;

public interface FoodItem {
	
	public void onEat(Player player);
	public int getNutritionLevel();
	public int getSaturationLevel();
	public default boolean hasToBeHungry() {return true;};
	
}
