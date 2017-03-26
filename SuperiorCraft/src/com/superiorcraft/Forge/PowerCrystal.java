package com.superiorcraft.Forge;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class PowerCrystal extends CustomItemLoader {

	public PowerCrystal(ItemStack item, String id) {
		super(item, id);
		
		ShapedRecipe pcrysr = new ShapedRecipe(item);
    	pcrysr.shape("=-=",
    			  	 "-#-",
    			  	 "=-=");
    	pcrysr.setIngredient('#', Material.DIAMOND);
    	pcrysr.setIngredient('-', Material.GOLD_INGOT);
    	pcrysr.setIngredient('=', Material.REDSTONE);
    	Bukkit.getServer().addRecipe(pcrysr);
	}

}