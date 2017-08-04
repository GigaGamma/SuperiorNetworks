package com.superiorcraft.api.blocks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomPanelTexture {
	
	public static final int WOOD_PANEL = 1;
	public static final int IRON_PANEL = 2;
	
	private int texture;
	private ItemStack textureItem;
	
	public CustomPanelTexture(int texture) {
		setTexture(texture);
		ItemStack textureItem = new ItemStack(Material.DIAMOND_HOE);
		textureItem.setDurability((short) texture);
		ItemMeta am = textureItem.getItemMeta();
		am.setUnbreakable(true);
		am.setDisplayName("&rPanel".replace('&', '§'));
		am.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		am.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		textureItem.setItemMeta(am);
		
		setTextureItem(textureItem);
	}

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public ItemStack getTextureItem() {
		return textureItem;
	}

	public void setTextureItem(ItemStack textureItem) {
		this.textureItem = textureItem;
	}
	
}
