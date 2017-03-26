package com.superiorcraft.Forge;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.superiorcraft.main.Main;

public class UraniumOre extends CustomBlockLoader {
	
	public HashMap<ArmorStand, ArmorStand> link = new HashMap<ArmorStand, ArmorStand>();
	
	public UraniumOre(String name, String id) {
		super(name, id);
		//material = Material.COMMAND;
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		CustomBlockTexture t = new CustomBlockTexture();
    	
    	t.setLayerPrimary(CustomBlockTexture.STONE, Color.GRAY);
    	t.setLayerSecondary(CustomBlockTexture.LAPIS_ORE, Color.GREEN);
    	
    	link.put(e, t.placeBlock(e.getLocation().add(0, 0.125, -0.375)));
    	e.getLocation().getBlock().setType(Material.SLIME_BLOCK);
    	
		return true;
	}
	
	@Override
	public void removeBlock(BlockBreakEvent e) {
		//if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getBlock().getLocation())) {
					link.get(en).remove();
					en.remove();
					en.getWorld().getBlockAt(en.getLocation().add(-0.5, 0, -0.5)).setType(Material.AIR);
					
					/*CustomBlockTexture t = new CustomBlockTexture();
			    	
			    	t.setLayerPrimary(CustomBlockTexture.STONE, "7237230", "");
			    	t.setLayerSecondary(CustomBlockTexture.LAPIS_ORE, "6291238", ",ench:[{id:64}]");
			    	t.giveBlock(e.getPlayer(), name);*/
					for (CustomItemLoader cil : CustomItemLoader.items) {
						if (cil instanceof UraniumIngot) {
							cil.giveItem(cil, e.getPlayer());
						}
					}
				}
			}
		//}
	}
	
}
