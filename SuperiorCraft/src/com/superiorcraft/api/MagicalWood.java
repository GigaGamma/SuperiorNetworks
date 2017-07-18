package com.superiorcraft.api;

import org.bukkit.Color;
import org.bukkit.Material;

import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;

public class MagicalWood extends CustomTexturedBlock {

	public MagicalWood(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.WOOD, Color.fromRGB(70, 255, 168), true);
		setMaterial(Material.GLASS);
		setItemMaterial(Material.LOG);
	}

}
