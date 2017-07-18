package com.superiorcraft.api.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import com.superiorcraft.main.Main;

public class Hologram {
	
	public static ArrayList<Hologram> holograms = new ArrayList<Hologram>();
	
	public static void registerHolograms() {
		holograms.clear();
		for (World w : Bukkit.getWorlds()) {
			for (ArmorStand e : w.getEntitiesByClass(ArmorStand.class)) {
				if (e.getScoreboardTags().contains("hologram") && !e.getScoreboardTags().contains("dindicator")) {
					holograms.add(new Hologram(e));
					System.out.println("Hologram Init: " + e.getCustomName());
				}
				else if (e.getScoreboardTags().contains("dindicator")) {
					e.remove();
				}
			}
		}
	}
	
	private ArmorStand hologram;
	
	public Hologram(ArmorStand a) {
		hologram = a;
	}
	
	public Hologram(String t, Location l) {
		hologram = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		
		hologram.setGravity(false);
		hologram.setVisible(false);
		hologram.setCustomName(t);
		hologram.setCustomNameVisible(true);
		hologram.setMarker(true);
		hologram.setAI(false);
		hologram.addScoreboardTag("hologram");
		
		holograms.add(this);
	}
	
	public void remove() {
		hologram.remove();
		holograms.remove(this);
	}
	
	public void setKillTimer(int time) {
		Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
			@Override
			public void run() {
				remove();
			}
		}, time * 20);
	}
	
	public ArmorStand getHologram() {
		return hologram;
	}
	
	public void setText(String t) {
		hologram.setCustomName(t);
	}
	
}
