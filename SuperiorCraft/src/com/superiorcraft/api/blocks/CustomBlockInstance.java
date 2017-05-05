package com.superiorcraft.api.blocks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CustomBlockInstance implements Listener {
	
	private static ArrayList<CustomBlockInstance> block_instances = new ArrayList<CustomBlockInstance>();
	
	private ArmorStand block;
	private ArmorStand textureEntity;
	private CustomBlockTexture texture;
	
	public CustomBlockInstance(ArmorStand b) {
		setBlock(b);
		CustomBlockInstance.block_instances.add(this);
	}
	
	public CustomBlockInstance(ArmorStand b, ArmorStand t) {
		setBlock(b);
		setTextureEntity(t);
		setTexture(CustomBlockTexture.extractTextureFromEntity(t));
		CustomBlockInstance.block_instances.add(this);
	}
	
	public CustomBlockInstance(ArmorStand b, ArmorStand t, CustomBlockTexture bt) {
		setBlock(b);
		setTextureEntity(t);
		setTexture(bt);
		CustomBlockInstance.block_instances.add(this);
	}
	
	public ArmorStand getBlock() {
		return block;
	}

	public void setBlock(ArmorStand block) {
		this.block = block;
	}
	
	public ArmorStand getTextureEntity() {
		return textureEntity;
	}

	public void setTextureEntity(ArmorStand textureEntity) {
		this.textureEntity = textureEntity;
	}
	
	public CustomBlockTexture getTexture() {
		return texture;
	}

	public void setTexture(CustomBlockTexture texture) {
		this.texture = texture;
	}
	
	public static CustomBlockInstance getBlockInstance(ArmorStand e) {
		for (CustomBlockInstance cbi : CustomBlockInstance.block_instances) {
			if (cbi.block.equals(e)) {
				return cbi;
			}
		}
		
		return null;
	}
	
	public static void addBlockInstance(CustomBlockInstance c) {
		block_instances.add(c);
	}
	
	public static ArrayList<CustomBlockInstance> getBlockInstances() {
		return  block_instances;
	}
	
	public boolean isDataSavable() {
		return !getBlock().isDead();
	}

	public void saveData(BlockData d) {
		
	}
	
	public BlockData loadData() {
		return null;
	}
	
}
