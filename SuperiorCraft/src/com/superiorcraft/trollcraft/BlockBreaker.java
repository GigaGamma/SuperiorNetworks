package com.superiorcraft.trollcraft;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.main.Main;

public class BlockBreaker extends CustomBlockLoader implements Listener {

	public BlockBreaker(String name, String id) {
		super(name, id);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		e.getWorld().getBlockAt(e.getLocation()).setType(Material.DISPENSER);
		e.getWorld().getBlockAt(e.getLocation()).setData((byte) 0);
		
		return true;
	}
	
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getClickedBlock().getLocation())) {
					Location l = e.getClickedBlock().getLocation();
					l.add(0, -1, 0);
					en.getWorld().getBlockAt(l).breakNaturally();
					e.setCancelled(true);
				}
			}
		}
	}
}
