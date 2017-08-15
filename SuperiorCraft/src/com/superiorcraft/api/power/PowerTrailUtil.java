package com.superiorcraft.api.power;

import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

import com.superiorcraft.SuperiorCraft;

public class PowerTrailUtil {
	
	public static void makePowerGridMap(World world) {
		for (ArmorStand s : world.getEntitiesByClass(ArmorStand.class)) {
			if (s.getCustomName() != null && s.getCustomName().contains("cable")) {
				SuperiorCraft.getPlayer("SuperAuguste").sendMessage(s.getLocation().getBlock().getLocation().toString());
			}
		}
	}
	
}
