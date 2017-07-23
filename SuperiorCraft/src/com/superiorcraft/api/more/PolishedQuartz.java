package com.superiorcraft.api.more;

import org.bukkit.Color;
import org.bukkit.Material;

import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;

public class PolishedQuartz extends CustomTexturedBlock {

	public PolishedQuartz(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.DIAMOND_BLOCK, Color.fromRGB(224, 197, 200), true);
		getTexture().setLayerSecondary(CustomBlockTexture.QUARTZ_MAZE, Color.fromRGB(173, 152, 200), true);
		setMaterial(Material.GLASS);
		setItemMaterial(Material.QUARTZ_BLOCK);
	}

}
