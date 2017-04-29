package com.superiorcraft.api.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageIndicator implements Listener {
	
	public DamageIndicator() {
		for (Hologram h : Hologram.holograms) {
			if (h.getHologram().getScoreboardTags().contains("dindicator")) {
				h.remove();
			}
		}
	}
	
	@EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof ArmorStand) && e.getEntityType() != EntityType.ARMOR_STAND && e.getEntityType() != EntityType.DROPPED_ITEM) {
			Hologram h = new Hologram(ChatColor.RED + Double.toString(e.getDamage()), e.getEntity().getLocation());
			h.getHologram().addScoreboardTag("dindicator");
			h.setKillTimer(2);
		}
	}
	
}
