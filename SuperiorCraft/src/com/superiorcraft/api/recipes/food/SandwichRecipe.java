package com.superiorcraft.api.recipes.food;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.crafting.FoodCrafter;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.recipes.InOutRecipe;

public class SandwichRecipe extends InOutRecipe {
	
	public static ItemStack[] recipe = {null, new ItemStack(Material.BREAD), null, null, CustomItem.getItem("salad"), null, null, new ItemStack(Material.BREAD)};
	
	public SandwichRecipe(ItemStack[] a, ItemStack o) {
		super(a, o);
		ncon = FoodCrafter.name;
	}

}
