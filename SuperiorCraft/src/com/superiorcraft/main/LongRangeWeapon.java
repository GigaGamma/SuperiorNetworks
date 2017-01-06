package com.superiorcraft.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

public class LongRangeWeapon implements Listener {
	
	private HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	
	private Material wep;
	private String name;
	private Material ammo;
	private String desc;
	private String type;
	private double damage;
	private int cooltime;
	private boolean isCrit = false;
	private int knockback = 0;
	private boolean silent = false;
	private int shots = 1;
	private String proj = "Arrow";
	private PotionEffect effect = null;
	
	// Material.FIREWORK_CHARGE
	
	public LongRangeWeapon(Material wep, String name, Material ammo, String desc, String type, double damage, int cooltime) {
		this.wep = wep;
		this.name = name;
		this.ammo = ammo;
		this.desc = desc;
		this.type = type;
		this.damage = damage;
		this.cooltime = cooltime;
		
		ItemStack item = new ItemStack(wep);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BOLD + desc);
		lore.add(ChatColor.WHITE + type);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		addWep(item);
	}
	
	public LongRangeWeapon(Material wep, String name, Material ammo, String desc, String type, double damage, int cooltime, boolean isCrit) {
		this.wep = wep;
		this.name = name;
		this.ammo = ammo;
		this.desc = desc;
		this.type = type;
		this.damage = damage;
		this.cooltime = cooltime;
		this.isCrit = isCrit;
		
		ItemStack item = new ItemStack(wep);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BOLD + desc);
		lore.add(ChatColor.WHITE + type);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		addWep(item);
	}
	
	public LongRangeWeapon(Material wep, String name, Material ammo, String desc, String type, double damage, int cooltime, boolean isCrit, int knockback) {
		this.wep = wep;
		this.name = name;
		this.ammo = ammo;
		this.desc = desc;
		this.type = type;
		this.damage = damage;
		this.cooltime = cooltime;
		this.isCrit = isCrit;
		this.knockback = knockback;
		
		ItemStack item = new ItemStack(wep);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BOLD + desc);
		lore.add(ChatColor.WHITE + type);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		addWep(item);
	}
	
	public LongRangeWeapon(Material wep, String name, Material ammo, String desc, String type, double damage, int cooltime, boolean isCrit, int knockback, boolean silent) {
		this.wep = wep;
		this.name = name;
		this.ammo = ammo;
		this.desc = desc;
		this.type = type;
		this.damage = damage;
		this.cooltime = cooltime;
		this.isCrit = isCrit;
		this.knockback = knockback;
		this.silent = silent;
		
		ItemStack item = new ItemStack(wep);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BOLD + desc);
		lore.add(ChatColor.WHITE + type);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		addWep(item);
	}
	
	public LongRangeWeapon(Material wep, String name, Material ammo, String desc, String type, double damage, int cooltime, boolean isCrit, int knockback, boolean silent, int shots) {
		this.wep = wep;
		this.name = name;
		this.ammo = ammo;
		this.desc = desc;
		this.type = type;
		this.damage = damage;
		this.cooltime = cooltime;
		this.isCrit = isCrit;
		this.knockback = knockback;
		this.silent = silent;
		this.shots = shots;
		
		ItemStack item = new ItemStack(wep);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BOLD + desc);
		lore.add(ChatColor.WHITE + type);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		addWep(item);
	}
	
	public LongRangeWeapon(Material wep, String name, Material ammo, String desc, String type, double damage, int cooltime, boolean isCrit, int knockback, boolean silent, int shots, String proj) {
		this.wep = wep;
		this.name = name;
		this.ammo = ammo;
		this.desc = desc;
		this.type = type;
		this.damage = damage;
		this.cooltime = cooltime;
		this.isCrit = isCrit;
		this.knockback = knockback;
		this.silent = silent;
		this.shots = shots;
		this.proj = proj;
		
		ItemStack item = new ItemStack(wep);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BOLD + desc);
		lore.add(ChatColor.WHITE + type);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		addWep(item);
	}
	
	public LongRangeWeapon(Material wep, String name, Material ammo, String desc, String type, double damage, int cooltime, boolean isCrit, int knockback, boolean silent, int shots, String proj, PotionEffect effect) {
		this.wep = wep;
		this.name = name;
		this.ammo = ammo;
		this.desc = desc;
		this.type = type;
		this.damage = damage;
		this.cooltime = cooltime;
		this.isCrit = isCrit;
		this.knockback = knockback;
		this.silent = silent;
		this.shots = shots;
		this.proj = proj;
		this.effect = effect;
		
		ItemStack item = new ItemStack(wep);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BOLD + desc);
		lore.add(ChatColor.WHITE + type);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		addWep(item);
	}
	
	public void addWep(ItemStack item) {
		if (this.type.equalsIgnoreCase("side")) {
			Main.wm2.inv.addItem(item);
		}
		else {
			if (type.equalsIgnoreCase("assault")) {
				for (int i = 1; i < 9; i++) {
					if (Main.wm1.inv.getItem(i) == null) {
						Main.wm1.inv.setItem(i, item);
						return;
					}
				}
			}
			else if (type.equalsIgnoreCase("sniper")) {
				for (int i = 1; i < 9; i++) {
					if (Main.wm1.inv.getItem(i + 9) == null) {
						Main.wm1.inv.setItem(i + 9, item);
						return;
					}
				}
			}
			else if (type.equalsIgnoreCase("shotgun")) {
				for (int i = 1; i < 9; i++) {
					if (Main.wm1.inv.getItem(i + 9 * 2) == null) {
						Main.wm1.inv.setItem(i + 9 * 2, item);
						return;
					}
				}
			}
			else if (type.equalsIgnoreCase("special")) {
				for (int i = 1; i < 9; i++) {
					if (Main.wm1.inv.getItem(i + 9 * 3) == null) {
						Main.wm1.inv.setItem(i + 9 * 3, item);
						return;
					}
				}
			}
			else {
				Main.wm1.inv.addItem(item);
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == wep && e.getItem().getItemMeta().getDisplayName() != null && e.getItem().getItemMeta().getDisplayName().contains(name) && !cooldown.containsKey(e.getPlayer())) {
    		//Material ammo = ;
    		if (e.getPlayer().getInventory().contains(ammo) && !Main.inStealthMode.contains(e.getPlayer())) {
    			for (int i = 0; i < shots; i++) {
    				if (e.getPlayer().getInventory().contains(ammo)) {
    					if (proj == "Arrow") {
    						Arrow p = e.getPlayer().launchProjectile(Arrow.class);
    						p.setCritical(isCrit);
    						p.setKnockbackStrength(knockback);
    						p.setSilent(silent);
    						p.setCustomName(name);
    					}
    					
    					else if (proj == "Rocket") {
    						Fireball p = e.getPlayer().launchProjectile(Fireball.class);
    						//ShulkerBullet p = e.getPlayer().launchProjectile(ShulkerBullet.class);
    						p.setYield(0);
    						p.setIsIncendiary(false);
    						p.setSilent(silent);
    						p.setCustomName(name);
    					}
    					e.getPlayer().getInventory().removeItem(new ItemStack[] {new ItemStack(ammo, 1) });
    					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
    						if (type.equalsIgnoreCase("sniper")) {
    							p.playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
    						}
    						else if (type.equalsIgnoreCase("side")) {
    							//p.playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
    						}
    					}
    				}
    			}
    			if (e.getPlayer().getInventory().contains(ammo)) {
    				int x = 0;
    				do {
    					x += 1;
    				} while (e.getPlayer().getInventory().contains(ammo, x));
    				x -= 1;
    				//e.getPlayer().sendMessage(ChatColor.GRAY + "You have " + Integer.toString(x) + " ammo left");
    				ItemMeta meta = e.getItem().getItemMeta();
    				meta.setDisplayName(ChatColor.BOLD + name);
    				ArrayList<String> lore = new ArrayList<String>();
    				lore.add(ChatColor.BOLD + desc);
    				lore.add(ChatColor.WHITE + type);
    				meta.setLore(lore);
    				//meta.setDisplayName(ChatColor.BOLD + "Shoe Shiner - " + Integer.toString(x) + " ammo");
    				e.getItem().setItemMeta(meta);
    				cooldown.put(e.getPlayer(), 1);
    				Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
    					@Override
    					public void run() {
    						for (Player p : Bukkit.getServer().getOnlinePlayers()) {
    							if (cooltime > 1) {
    								p.playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
    							}
    						}
    						cooldown.remove(e.getPlayer());

    					}
    				}, cooltime * 20);
    			}

    			else {
    				e.getPlayer().sendMessage(ChatColor.RED + "You are out of ammo");
    				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_SLIME_SQUISH, 1, 1);
    				//ItemMeta meta = e.getItem().getItemMeta();
					//meta.setDisplayName(ChatColor.BOLD + "Shoe Shiner - No Ammo");
					//e.getItem().setItemMeta(meta);
    			}
    		}
    	}
	}
	
	@EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
    	if ((e.getDamager() instanceof Arrow) && (((Arrow) e.getDamager()).getShooter() instanceof Player) && e.getDamager().getCustomName() == name) {
    		e.setDamage(e.getDamage() + damage);
    		if (effect != null) {

    			((LivingEntity) e.getEntity()).addPotionEffect(effect);
    		}
    	}
    	if ((e.getDamager() instanceof Fireball) && (((Fireball) e.getDamager()).getShooter() instanceof Player) && e.getDamager().getCustomName() == name) {
    		e.setDamage(e.getDamage() + damage);
    	}

    }
}
