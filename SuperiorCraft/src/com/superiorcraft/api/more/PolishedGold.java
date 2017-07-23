package com.superiorcraft.api.more;

import org.bukkit.Color;
import org.bukkit.Material;

import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;

public class PolishedGold extends CustomTexturedBlock {

	public PolishedGold(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.DIAMOND_BLOCK, Color.fromRGB(224, 197, 20), true);
		getTexture().setLayerSecondary(CustomBlockTexture.QUARTZ_MAZE, Color.fromRGB(173, 152, 14), true);
		
		setMaterial(Material.MOB_SPAWNER);
		setItemMaterial(Material.GOLD_BLOCK);
	}

}
