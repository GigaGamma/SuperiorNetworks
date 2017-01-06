package com.superiorcraft.main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Helicopter implements CommandExecutor, Listener {
	
	public void create(Location l) {
		ArmorStand heli = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		
		heli.setCustomName("Helicopter");
		heli.setGravity(false);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("helicopter")) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				create(player.getLocation());
			}
			else if (args[0].equals("steal")) {
				for (Entity ent : player.getNearbyEntities(0, 0, 0)) {
					if (ent.getCustomName().equals("Helicopter")) {
						ent.addScoreboardTag("riding");
						ent.addScoreboardTag("rider:" + player.getName());
						//player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100000, 255, true, false));
					}
				}
			}
				
			return true;
		}
		
		return false;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		for (Entity ent : e.getPlayer().getNearbyEntities(0, 0, 0)) {
			if (ent.getCustomName() != null && ent.getCustomName().equals("Helicopter") && ent.getScoreboardTags() != null && !ent.getScoreboardTags().contains("riding")) {
				ent.addScoreboardTag("riding");
				ent.addScoreboardTag("rider:" + e.getPlayer().getName());
				//e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100000, 255, true, false));
			}
			else if (ent.getCustomName() != null && ent.getCustomName().equals("Helicopter") && ent.getScoreboardTags() != null && ent.getScoreboardTags().contains("rider:" + e.getPlayer().getName())) {
				e.getPlayer().setAllowFlight(true);
				e.getPlayer().sendMessage("Going to (" + e.getTo().getX() + ", " + e.getTo().getY() + ", " + e.getTo().getZ() + ")");
				Location loc = ent.getLocation().setDirection(e.getTo().getDirection());

				if (e.getTo().getY() > e.getFrom().getY()) {
					loc.add(0, 0.15, 0);
				}
				if (e.getTo().getY() < e.getFrom().getY()) {
					loc.add(0, -0.15, 0);
				}
				if (e.getTo().getX() > e.getFrom().getX()) {
					loc.add(0.15, 0, 0);
				}
				e.setTo(loc);
				ent.teleport(loc);
			}
		}
	}
	
}
