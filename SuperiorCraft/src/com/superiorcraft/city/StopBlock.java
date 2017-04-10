package com.superiorcraft.city;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.superiorcraft.Forge.CustomBlockLoader;
import com.superiorcraft.main.Main;

public class StopBlock extends CustomBlockLoader {

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
			for (Entity bl : p.getNearbyEntities(0.5, 2, 0.5)) {
				if (bl.getCustomName() != null && bl.getCustomName().equals(name)) {
					for (Entity ent : p.getNearbyEntities(0.2, 0.2, 0.2)) {
						if (ent.getCustomName() != null && ent.getCustomName().equals("HoverBike")) {
							for (String tag : ent.getScoreboardTags()) {
								if (tag.startsWith("speed:") && Integer.valueOf(tag.split(":")[1]) > 18) {
									p.sendMessage("BEEP BOP");
								}
								else {
									((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
									((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255, true, false));
								}
							}
						}
					}
				}
			}
		}
	}
}
