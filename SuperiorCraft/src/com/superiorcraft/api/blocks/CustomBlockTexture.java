package com.superiorcraft.api.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class CustomBlockTexture {
	
	public static final int WOOD = 8;
	public static final int STONE = 13;
	public static final int DIAMOND_BLOCK = 3;
	public static final int IRON_ORE = 20;
	public static final int LAPIS_ORE = 21;
	public static final int DAYLIGHT_BOTTOM = 15;
	public static final int DAYLIGHT_TOP = 16;
	
	private CustomLayer primary;
	private CustomLayer secondary;
	private CustomLayer third;
	
	public static CustomBlockTexture extractTextureFromEntity(ArmorStand e) {
		CustomBlockTexture t = new CustomBlockTexture();
		short tex = (short) e.getHelmet().getDurability();
		Color c = ((LeatherArmorMeta) e.getHelmet().getItemMeta()).getColor();
		boolean g = e.getHelmet().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
		t.setLayerPrimary(tex, c, g);
		
		if (e.getEquipment().getItemInMainHand() == null) {
			return t;
		}
		
		short tex2 = (short) e.getEquipment().getItemInMainHand().getDurability();
		Color c2 = ((LeatherArmorMeta) e.getEquipment().getItemInMainHand().getItemMeta()).getColor();
		boolean g2 = e.getEquipment().getItemInMainHand().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
		t.setLayerSecondary(tex2, c2, g2);
		
		return t;
	}
	
	public void setLayerPrimary(int texture, Color color) {
		primary = new CustomLayer(texture, color);
	}
	
	public void setLayerPrimary(int texture, Color color, boolean glowing) {
		primary = new CustomLayer(texture, color, glowing);
	}
	
	public void setLayerSecondary(int texture, Color color) {
		secondary = new CustomLayer(texture, color);
	}
	
	public void setLayerSecondary(int texture, Color color, boolean glowing) {
		secondary = new CustomLayer(texture, color, glowing);
	}
	
	public void setLayerThird(int texture, Color color) {
		third = new CustomLayer(texture, color);
	}
	
	public void setLayerThird(int texture, Color color, boolean glowing) {
		third = new CustomLayer(texture, color, glowing);
	}
	
	public CustomLayer getPrimary() {
		return primary;
	}

	public void setPrimary(CustomLayer primary) {
		this.primary = primary;
	}

	public CustomLayer getSecondary() {
		return secondary;
	}

	public void setSecondary(CustomLayer secondary) {
		this.secondary = secondary;
	}

	public CustomLayer getThird() {
		return third;
	}

	public void setThird(CustomLayer third) {
		this.third = third;
	}

	public ArmorStand placeBlock(Location l) {
		ArmorStand block = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		block.setSmall(true);
		block.setGravity(false);
		block.setCustomName("CustomBlock");
		block.setCustomNameVisible(false);
		block.setInvulnerable(true);
		block.setVisible(false);
		block.setMarker(true);
		
		ItemStack a = new ItemStack(Material.LEATHER_BOOTS);
		a.setDurability((short) primary.getTexture());
		LeatherArmorMeta am = (LeatherArmorMeta) a.getItemMeta();
		am.setColor(primary.getColor());
		am.setUnbreakable(true);
		if (primary.isGlowing()) {
			am.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		}
		a.setItemMeta(am);
		block.setHelmet(a);
		
		if (secondary != null) {
			ItemStack b = new ItemStack(Material.LEATHER_BOOTS);
			b.setDurability((short) secondary.getTexture());
			LeatherArmorMeta bm = (LeatherArmorMeta) b.getItemMeta();
			bm.setColor(secondary.getColor());
			bm.setUnbreakable(true);
			if (secondary.isGlowing()) {
				System.out.println("H");
				bm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
			}
			b.setItemMeta(bm);
			block.getEquipment().setItemInMainHand(b);
		}
		
		return block;
	}
	
}
