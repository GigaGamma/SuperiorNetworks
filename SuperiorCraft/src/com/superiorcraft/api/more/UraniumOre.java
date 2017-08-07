package com.superiorcraft.api.more;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.superiorcraft.Main;
import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;
import com.superiorcraft.api.items.CustomItem;

public class UraniumOre extends CustomTexturedBlock {
	
	public UraniumOre(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.STONE, Color.GRAY);
		getTexture().setLayerSecondary(CustomBlockTexture.LAPIS_ORE, Color.GREEN);
	
		setMaterial(Material.MOB_SPAWNER);
	}
	
}
