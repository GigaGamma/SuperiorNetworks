package com.superiorcraft.Forge;

import org.bukkit.Bukkit;
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

	public UraniumOre(String name, String id) {
		super(name, id);
		material = Material.COMMAND;
	}
	
	@Override
	public void giveItem(CustomBlockLoader cbl, Player player) {
		CustomBlockTexture t = new CustomBlockTexture();
    	
    	t.setLayerPrimary(CustomBlockTexture.STONE, "7237230", "");
    	t.setLayerSecondary(CustomBlockTexture.LAPIS_ORE, "6291238", ",ench:[{id:64}]");
    	for (int i = 0; i < 64; i++) 
    		t.giveBlock(player, name);
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		CustomBlockTexture t = new CustomBlockTexture();
    	
    	t.setLayerPrimary(CustomBlockTexture.STONE, "7237230", "");
    	t.setLayerSecondary(CustomBlockTexture.LAPIS_ORE, "6291238", ",ench:[{id:64}]");
    	t.giveBlock(p, name);
    	
		return true;
	}
	
	@Override
	public void removeBlock(BlockBreakEvent e) {
		//if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getBlock().getLocation())) {
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
