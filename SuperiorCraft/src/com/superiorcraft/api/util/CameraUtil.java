package com.superiorcraft.api.util;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CameraUtil {
	
	public static void goToCameraLocation(Player p, Location l) {
		if (p.getScoreboardTags().contains("viewingCamera")) {
			p.removeScoreboardTag("viewingCamera");
		}
		p.teleport(l);
		if (!p.getScoreboardTags().contains("viewingCamera")) {
			p.addScoreboardTag("viewingCamera");
		}
	}
	
	public static void goToNearestCamera(Player p) {
		for (ArmorStand entity : p.getWorld().getEntitiesByClass(ArmorStand.class)) {
			if (entity.getCustomName() != null && entity.getCustomName().equals("Camera")) {
				goToCameraLocation(p, entity.getLocation());
				return;
			}
		}
	}
	
}
