package com.superiorcraft.api.items.weapons.ranged;

import org.bukkit.entity.Player;

import com.superiorcraft.api.items.weapons.RangedWeapon;

public abstract class Sniper extends RangedWeapon {

	/*@Override
	public String getName() {
		ret
	}*/

	@Override
	public double getDamage() {
		return 0;
	}
	
	@Override
	public double getDamageMultiplier() {
		return 10;
	}

}
