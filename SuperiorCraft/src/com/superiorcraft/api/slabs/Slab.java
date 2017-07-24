package com.superiorcraft.api.slabs;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;
import com.superiorcraft.main.Main;

public class Slab extends CustomTexturedBlock {

	public Slab(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.SAND, Color.fromRGB(194, 178, 128));
		setMaterial(Material.AIR);
		setItemMaterial(Material.SAND);
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		getTexture().placeBlock(e.getLocation().add(0, -0.40, -0.375));
		//p.sendMessage(e.getLocation().getBlock().getLocation().toString());
		
		if (getMaterial().equals(Material.MOB_SPAWNER)) {
			CreatureSpawner cs = (CreatureSpawner) e.getLocation().getBlock().getState();
			cs.setSpawnedType(EntityType.DROPPED_ITEM);
			cs.update();
		}
		setLe(e);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				e.getLocation().getBlock().setType(Material.STONE_SLAB2);
				e.getLocation().getBlock().setData((byte) 2);
				e.getLocation().getBlock().getWorld().dropItemNaturally(e.getLocation(), new ItemStack(e.getLocation().add(0, -1, 0).getBlock().getType()));
				e.getLocation().add(0, -1, 0).getBlock().setType(Material.GLOWSTONE);
			}
		}, (1 * 20));
		return true;
	}

}
