package com.superiorcraft.api;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Elevator extends CustomBlockLoader {

	public Elevator(String name, String id) {
		super(name, id);
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		e.getWorld().getBlockAt(e.getLocation()).setType(Material.WOOL);
		
		return true;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		for (Entity en : e.getPlayer().getWorld().getEntities()) {
			if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().distance(e.getTo()) <= 1.5 && en.getLocation().getY() < e.getTo().getY()) {
				if (e.getTo().getY() - en.getLocation().getY() >= 1.2) {
					e.getPlayer().sendMessage("up");
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent e) {
		// Sneaking = !sneaking because of badly timed events
		
		for (Entity en : e.getPlayer().getWorld().getEntities()) {
			if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().distance(e.getPlayer().getLocation()) <= 1.5 && en.getLocation().getY() < e.getPlayer().getLocation().getY()) {
				if (!e.getPlayer().isSneaking()) {
					e.getPlayer().sendMessage("down");
				}
			}
		}
	}

}
