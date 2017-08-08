package com.superiorcraft.realism;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BlockBreakRule {
	
	public static ArrayList<BlockBreakRule> rules = new ArrayList<BlockBreakRule>();
	
	private Material material;
	private ToolPower level;
	
	public BlockBreakRule(Material material, ToolPower level) {
		setMaterial(material);
		setLevel(level);
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public ToolPower getLevel() {
		return level;
	}

	public void setLevel(ToolPower level) {
		this.level = level;
	}
	
}
