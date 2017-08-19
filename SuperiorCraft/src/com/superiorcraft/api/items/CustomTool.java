package com.superiorcraft.api.items;

import org.bukkit.inventory.ItemStack;

import com.superiorcraft.realism.ToolPower;

public class CustomTool extends CustomItem {
	
	private ToolPower power;
	private int damagePerUse;
	
	public CustomTool(ItemStack item, String id, ToolPower power, int damagePerUse) {
		super(item, id);
		setPower(power);
		setDamagePerUse(damagePerUse);
	}

	public ToolPower getPower() {
		return power;
	}

	public void setPower(ToolPower power) {
		this.power = power;
	}

	public int getDamagePerUse() {
		return damagePerUse;
	}

	public void setDamagePerUse(int damagePerUse) {
		this.damagePerUse = damagePerUse;
	}

}
