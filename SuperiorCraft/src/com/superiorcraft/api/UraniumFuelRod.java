package com.superiorcraft.api;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class UraniumFuelRod extends CustomBlock {
	
	public UraniumFuelRod(String name, String id) {
		super(name, id);
		//material = Material.COMMAND;
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		CustomBlockTexture t = new CustomBlockTexture();
    	
    	t.setLayerPrimary(CustomBlockTexture.IRON_BLOCK, Color.fromRGB(0, 20, 0));
    	t.setLayerSecondary(CustomBlockTexture.DAYLIGHT_TOP, Color.GREEN, true);
    	
    	t.placeBlock(e.getLocation().add(0, 0.125, -0.375));
    	e.getLocation().getBlock().setType(Material.SLIME_BLOCK);
    	
		return true;
	}
	
	@Override
	public void removeBlock(BlockBreakEvent e) {
		//if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getBlock().getLocation())) {
					for (Entity ent : en.getNearbyEntities(0.5, 0.5, 0.5)) {
						if (ent.getCustomName().equals("CustomBlock")) {
							ent.remove();
							break;
						}
					}
					en.remove();
					en.getWorld().getBlockAt(en.getLocation().add(-0.5, 0, -0.5)).setType(Material.AIR);
					
					/*CustomBlockTexture t = new CustomBlockTexture();
			    	
			    	t.setLayerPrimary(CustomBlockTexture.STONE, "7237230", "");
			    	t.setLayerSecondary(CustomBlockTexture.LAPIS_ORE, "6291238", ",ench:[{id:64}]");
			    	t.giveBlock(e.getPlayer(), name);*/
					for (CustomBlock cbl : CustomBlock.blocks) {
						if (cbl instanceof UraniumFuelRod) {
							cbl.giveItem(cbl, e.getPlayer());
						}
					}
				}
			}
		//}
	}
	
}
