package com.superiorcraft.api.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.superiorcraft.api.crafting.CustomCraftingRecipe;
import com.superiorcraft.main.Main;

public class DamageIndicator implements Listener {
	
	@EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof ArmorStand) && e.getEntityType() != EntityType.ARMOR_STAND && e.getEntityType() != EntityType.DROPPED_ITEM) {
			Hologram h = new Hologram(ChatColor.RED + Double.toString(e.getDamage()), e.getEntity().getLocation());
			h.getHologram().addScoreboardTag("dindicator");
			h.setKillTimer(2);
		}
	}
	
}
