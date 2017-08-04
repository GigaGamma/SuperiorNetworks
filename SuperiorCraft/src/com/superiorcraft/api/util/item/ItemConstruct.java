package com.superiorcraft.api.util.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class ItemConstruct extends ItemStack {
	
	private ItemMetaConstruct imc;
	
	public ItemConstruct(Material mat) {
		super(mat);
		setMeta(new ItemMetaConstruct(this, "&r".replace('&', '§')));
	}
	
	public ItemConstruct setMeta(ItemMetaConstruct meta) {
		imc = meta;
		setItemMeta(imc.getMeta());
		
		return this;
	}
	
	public ItemMetaConstruct getMeta() {
		return imc;
	}
	
	public ItemStack getItemStack() {
		return (ItemStack) this;
	}
	
}
