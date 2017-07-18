package com.superiorcraft.logicrace;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;

import com.superiorcraft.api.blocks.BlockUtil;
import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.blocks.CustomBlockInstance;

public class RoomGenerator extends CustomBlock {

	public RoomGenerator(String name, String id) {
		super(name, id);
	}
	
	@Override
	public CustomBlockInstance getInstance(ArmorStand s) {
		//System.out.println(s.getLocation().add(0, -1, 0).getBlock().getType());
		Bukkit.broadcastMessage(String.valueOf(BlockUtil.getBlocksTouching(Material.WOOL, s.getLocation().add(-0.5, -1, -0.5)).size()));
		return null;
	}

}
