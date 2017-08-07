package com.superiorcraft.city;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.superiorcraft.Main;
import com.superiorcraft.api.blocks.CustomBlock;

public class StopBlock extends CustomBlock {

	public StopBlock(String name, String id) {
		super(name, id);
		
		Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
    		@Override
    		public void run() {
    			loop();
    		}
    	}, 0L, 2L);
	}
	
	public boolean placeBlock(ArmorStand e, Player p) {
		//e.setHelmet(new ItemStack(Material.STONE, 1));
		
		e.getWorld().getBlockAt(e.getLocation()).setType(Material.WOOL);
		e.getWorld().getBlockAt(e.getLocation()).setData((byte) 14);
		
		return true;
	}
	
	public void loop() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			for (Entity bl : p.getNearbyEntities(0.24, 2, 0.24)) {
				if (bl.getCustomName() != null && bl.getCustomName().equals(getName())) {
					for (Entity ent : p.getNearbyEntities(0.2, 0.2, 0.2)) {
						if (ent.getCustomName() != null && ent.getCustomName().equals("HoverBike")) {
							for (String tag : ent.getScoreboardTags()) {
								if (tag.startsWith("speed:") && Integer.valueOf(tag.split(":")[1]) > 18) {
									Police.create(p.getLocation().add(-10, 2, -10), p);
									p.sendMessage("&6[Warning] Don't go past stops!".replace('&', '§'));
									p.sendMessage("&9[Police] Pull over!".replace('&', '§'));
									
									for (Entity pli : p.getNearbyEntities(50, 50, 50)) {
										if (pli.getCustomName() != null && pli.getCustomName().equals("Officer")) {
											((PigZombie) pli).setTarget(p);
										}
									}
								}
								else {
									if (!ent.getScoreboardTags().contains("stopcooldown")) {
										((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
										((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255, true, false));
										((Pig) ent).setVelocity(new Vector(0, 0, 0));
									} else {
										ent.getScoreboardTags().remove("stopcooldown");
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
