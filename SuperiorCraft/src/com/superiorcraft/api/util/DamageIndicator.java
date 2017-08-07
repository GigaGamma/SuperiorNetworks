package com.superiorcraft.api.util;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.superiorcraft.Main;
import com.superiorcraft.api.crafting.CustomCraftingRecipe;

public class DamageIndicator implements Listener {
	
	@EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof ArmorStand) && e.getEntityType() != EntityType.ARMOR_STAND && e.getEntityType() != EntityType.DROPPED_ITEM) {
			Hologram h = new Hologram(ChatColor.RED + "-" + Double.toString(e.getDamage()) + " \u2764", e.getEntity().getLocation());
			h.getHologram().addScoreboardTag("dindicator");
			h.setKillTimer(2);
		}
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			player.sendMessage(ChatColor.GRAY + "[DamageIndicator] " + e.getCause().name() + " did " + new DecimalFormat("#0.0").format(((double) Math.round(e.getDamage())) / 2) + ChatColor.RED + " \u2764");
		}
	}
	
	@EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && !(e.getEntity() instanceof ArmorStand)) {
			((Player) e.getDamager()).sendMessage(ChatColor.GRAY + "You did " + Math.round(e.getDamage()) + ChatColor.RED + " \u2764 " + ChatColor.GRAY + "to " + e.getEntity().getName());
		}
		if (e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player) {
			((Player) (((Projectile) e.getDamager()).getShooter())).sendMessage(ChatColor.GRAY + "You did " + Math.round(e.getDamage()) + ChatColor.RED + " \u2764 " + ChatColor.GRAY + "to " + e.getEntity().getName());
		}
	}
	
}
