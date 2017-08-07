package com.superiorcraft.city;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.superiorcraft.Main;

public class HoverBike implements CommandExecutor, Listener {
	
	public static Pig create(Location l) {
		Pig hov = (Pig) l.getWorld().spawnEntity(l, EntityType.PIG);
		
		hov.setSaddle(true);
		hov.setCustomName("HoverBike");
		hov.addScoreboardTag("speed:10");
		hov.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 30, true, false));
		hov.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, true, false));
		//hov.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, true, false));
		hov.setAI(false);
		hov.setGravity(true);
		hov.setSilent(true);
		
		hov.setMaxHealth(100);
		hov.setHealth(100);
		
		return hov;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("hbike")) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				create(player.getLocation()).setPassenger(player);
			}
			else if (args[0].equals("destroy")) {
				for (Entity ent : player.getNearbyEntities(0.2, 0.2, 0.2)) {
					if (ent.getCustomName() != null && ent.getCustomName().equals("HoverBike")) {
						ent.remove();
						return true;
					}
				}
			}
				
			return true;
		}
		
		return false;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
		for (Entity ent : e.getPlayer().getNearbyEntities(0.2, 0.2, 0.2)) {
			if (ent.getCustomName() != null && ent.getCustomName().equals("HoverBike")) {
				if (e.getMessage().startsWith("!")) {
					int value = Integer.valueOf(e.getMessage().replaceFirst("!", ""));
					for (String tag : ent.getScoreboardTags()) {
						if (tag.startsWith("speed:") && value <= 100 && value >= 0) {
							e.getPlayer().sendMessage(ChatColor.GRAY + "Speed being set to " + e.getMessage().replace("!", "") + " BPH");
							((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
							Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
								
								private boolean active = true;
								
								@Override
								public void run() {
									if (active) {
										for (String tag : ent.getScoreboardTags()) {
											if (Integer.valueOf(tag.split(":")[1]) > value) {
												if ((Integer.valueOf(tag.split(":")[1]) - 1) % 2 == 0)
													e.getPlayer().sendMessage(ChatColor.GRAY + String.valueOf(Integer.valueOf(tag.split(":")[1]) - 1) + " BPH");
												((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
												((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, Integer.valueOf(tag.split(":")[1]) - 1, true, false));
												ent.addScoreboardTag("speed:" + String.valueOf(Integer.valueOf(tag.split(":")[1]) - 1));
												ent.getScoreboardTags().remove(tag);
											}
											else if (Integer.valueOf(tag.split(":")[1]) < value) {
												if ((Integer.valueOf(tag.split(":")[1]) + 1) % 2 == 0)
													e.getPlayer().sendMessage(ChatColor.GRAY + String.valueOf(Integer.valueOf(tag.split(":")[1]) + 1) + " BPH");
												((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
												((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, Integer.valueOf(tag.split(":")[1]) + 1, true, false));
												ent.addScoreboardTag("speed:" + String.valueOf(Integer.valueOf(tag.split(":")[1]) + 1));
												ent.getScoreboardTags().remove(tag);
											}
											if (Integer.valueOf(tag.split(":")[1]) == value) {
												if (Integer.valueOf(tag.split(":")[1]) == 0) {
													((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
													((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255, true, false));
												}
												active = false;
											}
										}
									}
								}
								
							}, 0, 1);
							//e.getPlayer().setTotalExperience(0);
							//ent.addScoreboardTag("speed:" + String.valueOf(value));
							//ent.getScoreboardTags().remove(tag);
						}
						else if (tag.startsWith("speed:") && (value > 100 || value < 0)) {
							e.getPlayer().sendMessage(ChatColor.RED + "Speed issues... Don't want to get pulled over (0-100 BPH)");
						}
					}
					
					e.setCancelled(true);
				}
				else if (e.getMessage().startsWith("#")) {
					if (e.getMessage().replaceFirst("#", "").equalsIgnoreCase("h")) {
						e.getPlayer().chat("!50");
					}
					else if (e.getMessage().replaceFirst("#", "").equalsIgnoreCase("p")) {
						e.getPlayer().chat("!5");
					}
					e.setCancelled(true);
				}
			}
		}
    }
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		for (Entity ent : e.getPlayer().getNearbyEntities(0.2, 0.2, 0.2)) {
			if (ent.getCustomName() != null && ent.getCustomName().equals("HoverBike")) {
				Location l = ent.getLocation();
				if (e.getAction() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().getType() != null && e.getItem().getType() == Material.CARROT_STICK) {
					boolean hadSlow = ((Pig) ent).hasPotionEffect(PotionEffectType.SLOW);
					((Pig) ent).removePotionEffect(PotionEffectType.SLOW);
					//System.out.println(e.getPlayer().getLocation().getDirection().getY());
					if (e.getPlayer().getLocation().getDirection().getY() >= 0.2) {
						for (String tag : ent.getScoreboardTags()) {
							if (tag.startsWith("speed:") && Integer.valueOf(tag.split(":")[1]) + 1 <= 100) {
								if ((Integer.valueOf(tag.split(":")[1]) + 1) % 2 == 0)
									e.getPlayer().sendMessage(ChatColor.GRAY + String.valueOf(Integer.valueOf(tag.split(":")[1]) + 1) + " BPH");
								((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
								((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, Integer.valueOf(tag.split(":")[1]) + 1, true, false));
								((Pig) ent).setVelocity(((Pig) ent).getVelocity().multiply(4));
								if (hadSlow) {
									((Pig) ent).addScoreboardTag("stopcooldown");
								}
								//e.getPlayer().setTotalExperience(0);
								ent.addScoreboardTag("speed:" + String.valueOf(Integer.valueOf(tag.split(":")[1]) + 1));
								ent.getScoreboardTags().remove(tag);
								e.setCancelled(true);
							}
						}
					}
					else if (e.getPlayer().getLocation().getDirection().getY() < -0.2) {
						for (String tag : ent.getScoreboardTags()) {
							if (tag.startsWith("speed:") && Integer.valueOf(tag.split(":")[1]) - 1 >= 0) {
								if ((Integer.valueOf(tag.split(":")[1]) - 1) % 2 == 0)
									e.getPlayer().sendMessage(ChatColor.GRAY + String.valueOf(Integer.valueOf(tag.split(":")[1]) - 1) + " BPH");
								((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
								((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, Integer.valueOf(tag.split(":")[1]) - 1, true, false));
								ent.addScoreboardTag("speed:" + String.valueOf(Integer.valueOf(tag.split(":")[1]) - 1));
								ent.getScoreboardTags().remove(tag);
								e.setCancelled(true);
							}
							if (tag.startsWith("speed:") && Integer.valueOf(tag.split(":")[1]) - 1 == 0) {
								((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
								((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255, true, false));
								e.setCancelled(true);
							}
						}
					}
				}
				
				else if ((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && e.getItem().getType() == Material.CARROT_STICK && !e.getPlayer().isSneaking()) {
					for (String tag : ent.getScoreboardTags()) {
						if (tag.startsWith("speed:")) {
							e.getPlayer().sendMessage(ChatColor.GRAY + "Hold on to your hats!");
							((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
							//((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, Integer.valueOf(tag.split(":")[1]) - 1, true, false));
							//ent.addScoreboardTag("speed:" + String.valueOf(0));
							Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
								
								private boolean active = true;
								
								@Override
								public void run() {
									if (active) {
										for (String tag : ent.getScoreboardTags()) {
											if (Integer.valueOf(tag.split(":")[1]) - 1 >= 0) {
												if ((Integer.valueOf(tag.split(":")[1]) - 1) % 2 == 0)
													e.getPlayer().sendMessage(ChatColor.GRAY + String.valueOf(Integer.valueOf(tag.split(":")[1]) - 1) + " BPH");
												((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
												((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, Integer.valueOf(tag.split(":")[1]) - 1, true, false));
												ent.addScoreboardTag("speed:" + String.valueOf(Integer.valueOf(tag.split(":")[1]) - 1));
												ent.getScoreboardTags().remove(tag);
											}
											else {
												((Pig) ent).removePotionEffect(PotionEffectType.SPEED);
												((Pig) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255, true, false));
												active = false;
											}
										}
									}
								}
								
							}, 0, 1);
							e.setCancelled(true);
						}
					}
				}
				else if (e.getAction() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().getType() != null && e.getItem().getType() == Material.STICK) {
					for (String tag : ent.getScoreboardTags()) {
						if (tag.equals("speed:50")) {
							e.getPlayer().chat("#p");
						}
						else {
							e.getPlayer().chat("#h");
						}
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
}
