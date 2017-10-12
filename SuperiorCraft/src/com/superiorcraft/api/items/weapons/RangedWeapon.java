package com.superiorcraft.api.items.weapons;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public abstract class RangedWeapon implements Weapon {
	
	protected static ArrayList<RangedWeapon> weapons = new ArrayList<RangedWeapon>();
	private Class<? extends Projectile> projectile = Arrow.class;
	private double damage;
	
	public RangedWeapon() {
		weapons.add(this);
	}
	
	@Override
	public void useSpecial(Player player) {
		player.launchProjectile(getProjectile()).setCustomName(ChatColor.translateAlternateColorCodes('&', getName()));
	}
	
	public Class<? extends Projectile> getProjectile() {
		return projectile;
	}

	public void setProjectile(Class<? extends Projectile> projectile) {
		this.projectile = projectile;
	}

	public double getDamageMultiplier() {
		return damage;
	}

	public void setDamageMultiplier(double damage) {
		this.damage = damage;
	}
	
	@EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager().getCustomName().equals(ChatColor.translateAlternateColorCodes('&', getName())) && e.getEntity() instanceof LivingEntity) {
			e.setDamage(e.getDamage() + getDamageMultiplier());
		}
	}

}
