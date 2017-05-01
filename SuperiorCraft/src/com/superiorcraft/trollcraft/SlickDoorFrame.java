package com.superiorcraft.trollcraft;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.main.Main;

public class SlickDoorFrame extends CustomBlock implements Listener {

	public SlickDoorFrame(String name, String id) {
		super(name, id);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		e.getWorld().getBlockAt(e.getLocation()).setType(Material.SMOOTH_BRICK);
		
		return true;
	}

}
