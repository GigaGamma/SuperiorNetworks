package com.superiorcraft.api.items.weapons.ranged;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.superiorcraft.api.util.item.ItemConstruct;

public class ShoeShiner extends Sniper {

	@Override
	public String getName() {
		return "&rShoe Shiner";
	}

	@Override
	public ItemStack getItem() {
		/*item.setDurability((short) 2);
    	ItemMeta im = item.getItemMeta();
    	im.setUnbreakable(true);
    	item.setItemMeta(im);*/
		return new ItemConstruct(Material.DIAMOND_SWORD).getMeta().setUnbreakable(true).setData((short) 2).getItem();
	}

}
