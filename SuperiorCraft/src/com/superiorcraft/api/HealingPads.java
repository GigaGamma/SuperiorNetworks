package com.superiorcraft.api;

import org.bukkit.Color;
import org.bukkit.Material;

import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;

public class HealingPads extends CustomTexturedBlock {
	
	public HealingPads(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.CLAY, Color.fromRGB(14, 150, 204), true);
		getTexture().setLayerSecondary(CustomBlockTexture.PURPUR_PILLAR, Color.fromRGB(235, 2, 57), true);
		setMaterial(Material.GLASS);
		setItemMaterial(Material.GLASS);
	}
	
}
