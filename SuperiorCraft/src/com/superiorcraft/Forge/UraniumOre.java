package com.superiorcraft.Forge;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.superiorcraft.main.Main;

public class UraniumOre extends CustomBlockLoader {

	public UraniumOre(String name, String id) {
		super(name, id);
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		CustomBlockTexture t = new CustomBlockTexture();
    	
    	t.setLayerPrimary(CustomBlockTexture.STONE, "7237230", "");
    	t.setLayerSecondary(CustomBlockTexture.LAPIS_ORE, "6291238", ",ench:[{id:64}]");
    	
    	Location l = new Location(e.getWorld(), e.getLocation().getBlockX(), e.getLocation().getBlockY(), e.getLocation().getBlockZ());
    	
    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {

			@Override
			public void run() {
				t.placeBlock(e.getLocation());
			}
    		
    	}, 2);

		return true;
	}

}
