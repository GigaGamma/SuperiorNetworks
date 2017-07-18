package com.superiorcraft.api;

import org.bukkit.Color;
import org.bukkit.Material;

import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;

public class ClearGlass extends CustomTexturedBlock {
	
	public ClearGlass(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.STONE_OUTLINE, Color.WHITE, false);
		setMaterial(Material.BARRIER);
		setItemMaterial(Material.GLASS);
	}
	
}
