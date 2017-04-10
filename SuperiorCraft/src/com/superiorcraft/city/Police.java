package com.superiorcraft.city;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.superiorcraft.commands.CommandConstruct;

import net.minecraft.server.v1_11_R1.EntityEvoker.e;

public class Police implements CommandExecutor, Listener {
	
	public String[] police_target = {"target", "player"};
	
	private static Player getPlayer(String player) {
		for(Player ps : Bukkit.getOnlinePlayers()){
			if (ps.getName().equalsIgnoreCase(player)) {
				return ps;
			}
		}
		
		return null;
	}
	
	public void create(Location l, Player p) {
		PigZombie police = (PigZombie) l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
		Pig hov = (Pig) l.getWorld().spawnEntity(l, EntityType.PIG);
		
		police.setTarget(p);
		police.setMaxHealth(80);
		police.setHealth(80);
		
		ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
		helmMeta.setColor(Color.BLACK);
		helmMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Helmet");
		helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		
		helm.setItemMeta(helmMeta);
		
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
		chestMeta.setColor(Color.BLACK);
		chestMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Vest");
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);

		chest.setItemMeta(chestMeta);
		
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta legMeta = (LeatherArmorMeta) leg.getItemMeta();
		legMeta.setColor(Color.BLACK);
		legMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Leggings");
		legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		
		leg.setItemMeta(legMeta);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(Color.BLACK);
		bootsMeta.setDisplayName(ChatColor.BOLD + "Bullet Proof Boots");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		bootsMeta.addEnchant(Enchantment.PROTECTION_FALL, 15, true);
		
		boots.setItemMeta(bootsMeta);
		
		EntityEquipment ee = police.getEquipment();
		
		ee.setHelmet(helm);
		ee.setChestplate(chest);
		ee.setLeggings(leg);
		ee.setBoots(boots);
		
		ee.setItemInOffHand(new ItemStack(Material.CARROT_STICK));
		
		police.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, false));
		hov.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4, true, false));
		
		hov.setTarget(p);
		hov.setPassenger(police);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("police")) {
			Player player = (Player) sender;
			
			if (CommandConstruct.match(args, police_target)) {			
				create(player.getLocation().add(-10, 2, -10), getPlayer(args[1]));
			}
			
			return true;
		}
		
		return false;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		
	}
	
}
