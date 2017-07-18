package com.superiorcraft.api.blocks;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class CustomTexturedBlock extends CustomBlock {
	
	private CustomBlockTexture texture;
	private Material material = Material.STONE;
	
	private ArmorStand le;
	
	public CustomTexturedBlock(String name, String id) {
		super(name, id);
		setTexture(new CustomBlockTexture());
	}
	
	public CustomTexturedBlock(String name, String id, CustomBlockTexture t) {
		super(name, id);
		setTexture(t);
	}

	public CustomBlockTexture getTexture() {
		return texture;
	}

	public void setTexture(CustomBlockTexture texture) {
		this.texture = texture;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public ArmorStand getTextureEntity(ArmorStand s) {
		for (Entity ent : s.getNearbyEntities(0.5, 0.5, 0.5)) {
			if (ent.getCustomName().equals("CustomBlock")) {
					return (ArmorStand) ent;
			}
		}
		
		return null;
	}

	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		getTexture().placeBlock(e.getLocation().add(0, 0.125, -0.375));
		e.getLocation().getBlock().setType(material);
		le = e;
		return true;
	}
	
	@Override
	public void removeBlock(BlockBreakEvent e) {
		if (e.getPlayer() != null) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(getName()) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getBlock().getLocation())) {
					for (Entity ent : en.getNearbyEntities(0.5, 0.5, 0.5)) {
						if (ent.getCustomName().equals("CustomBlock")) {
							ent.remove();
							break;
						}
					}
					en.remove();
					en.getWorld().getBlockAt(en.getLocation().add(-0.5, 0, -0.5)).setType(Material.AIR);
					e.getPlayer().getInventory().addItem(getItem());
				}
			}
		} else {
			for (Entity en : e.getBlock().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(getName()) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getBlock().getLocation())) {
					for (Entity ent : en.getNearbyEntities(0.5, 0.5, 0.5)) {
						if (ent.getCustomName().equals("CustomBlock")) {
							ent.remove();
							break;
						}
					}
					en.remove();
					en.getWorld().getBlockAt(en.getLocation().add(-0.5, 0, -0.5)).setType(Material.AIR);
					//e.getPlayer().getInventory().addItem(getItem());
				}
			}
		}
	}
	
}
