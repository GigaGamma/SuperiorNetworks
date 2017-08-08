package com.superiorcraft.api.recipes.food;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.crafting.FoodCrafter;
import com.superiorcraft.api.recipes.InOutRecipe;

public class SaladRecipe extends InOutRecipe {
	
	public static ItemStack[] recipe = {new ItemStack(Material.POTATO_ITEM), new ItemStack(Material.CARROT_ITEM), new ItemStack(Material.LEAVES), new ItemStack(Material.BOWL)};
	
	public SaladRecipe(ItemStack[] a, ItemStack o) {
		super(a, o);
		ncon = FoodCrafter.name;
	}

}
