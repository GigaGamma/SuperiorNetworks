package com.superiorcraft.api.blocks;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
	
	public ArmorStand getLe() {
		return le;
	}

	public void setLe(ArmorStand le) {
		this.le = le;
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
		e.getLocation().getBlock().setType(getMaterial());
		if (getMaterial().equals(Material.MOB_SPAWNER)) {
			CreatureSpawner cs = (CreatureSpawner) e.getLocation().getBlock().getState();
			cs.setSpawnedType(EntityType.DROPPED_ITEM);
			cs.update();
		}
		setLe(e);
		return true;
	}
	
	@Override
	public void removeBlock(BlockBreakEvent e) {
			if (e.getPlayer() == null) {return;}
			for (Entity en : e.getPlayer().getNearbyEntities(10, 10, 10)) {
				System.out.println("Removal has Begun");
				if (en.getCustomName() != null && en.getCustomName().equals(getName()) && /*en.getLocation().add(-0.5, 0, -0.5).equals(e.getBlock().getLocation())*/ en.getLocation().distance(e.getBlock().getLocation()) < 2) {
					for (Entity ent : en.getNearbyEntities(0.5, 0.5, 0.5)) {
						if (ent.getCustomName().equals("CustomBlock")) {
							ent.remove();
							break;
						}
					}
					en.remove();
					//en.getWorld().getBlockAt(en.getLocation().add(-0.5, 0, -0.5)).setType(Material.AIR);
					if (e.getPlayer() != null && e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
						e.getPlayer().getInventory().addItem(getItem());
					} else {
						e.getBlock().getWorld().dropItemNaturally(en.getLocation().add(-0.5, 0, -0.5), getItem());
					}
					
					return;
				}
			}
	}
	
}
