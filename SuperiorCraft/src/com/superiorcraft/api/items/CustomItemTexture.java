package com.superiorcraft.api.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemTexture {
	
	public static final int WOOD_DUST = 2;
	public static final int WEAPON1 = 3;
	
	private ItemStack item;
	
	public CustomItemTexture(int texture) {
		item = new ItemStack(Material.DIAMOND_SWORD);
		item.setDurability((short) 2);
    	ItemMeta im = item.getItemMeta();
    	im.setUnbreakable(true);
    	item.setItemMeta(im);
	}
	
	public void setItem(ItemStack item) {
		this.item = item;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
}
