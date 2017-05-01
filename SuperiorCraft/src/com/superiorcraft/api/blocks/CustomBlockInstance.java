package com.superiorcraft.api.blocks;

import org.bukkit.entity.ArmorStand;

public class CustomBlockInstance {
	
	private ArmorStand block;
	private ArmorStand textureEntity;
	private CustomBlockTexture texture;
	
	public CustomBlockInstance(ArmorStand b) {
		setBlock(b);
	}
	
	public CustomBlockInstance(ArmorStand b, ArmorStand t) {
		setBlock(b);
		setTextureEntity(t);
	}
	
	public CustomBlockInstance(ArmorStand b, ArmorStand t, CustomBlockTexture bt) {
		setBlock(b);
		setTextureEntity(t);
		setTexture(bt);
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

	public void saveData(BlockData d) {
		if (!getBlock().isDead()) {}
	}
	
	public BlockData loadData() {
		return null;
	}
	
}
