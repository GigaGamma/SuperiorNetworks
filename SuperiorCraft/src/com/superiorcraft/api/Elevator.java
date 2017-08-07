package com.superiorcraft.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.material.Dye;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.superiorcraft.Main;
import com.superiorcraft.api.blocks.ColorableBlock;
import com.superiorcraft.api.blocks.CustomBlockInstance;
import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.blocks.CustomTexturedBlock;

public class Elevator extends CustomTexturedBlock {
	
	public Elevator(String name, String id) {
		super(name, id);
		
		getTexture().setLayerPrimary(CustomBlockTexture.DIAMOND_BLOCK, Color.WHITE);
		getTexture().setLayerSecondary(CustomBlockTexture.DAYLIGHT_BOTTOM, Color.WHITE);
		//setMaterial(Material.SLIME_BLOCK);
		setMaterial(Material.GLASS);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onInteract(PlayerInteractEvent e, Entity en) {
		ElevatorInstance ei = ((ElevatorInstance) CustomBlockInstance.getBlockInstance((ArmorStand) en));
		if (e.getItem() != null && e.getItem().getType() == Material.INK_SACK) {
			ei.setColor(ei.getColor().mixColors(DyeColor.getByDyeData(e.getItem().getData().getData()).getFireworkColor()));
		}
	}
	
	public void registerInstances() {
		for (World w : Bukkit.getServer().getWorlds()) {
			for (ArmorStand e : w.getEntitiesByClass(ArmorStand.class)) {
				if (e.getCustomName() != null && e.getCustomName().equals(getName())) {
					ArmorStand te = null;
					for (Entity en : e.getNearbyEntities(0.5, 0.5, 0.5)) {
						if (en.getCustomName() != null && en.getCustomName().equals("CustomBlock")) {
							te = (ArmorStand) en;
						}
					}
					//CustomBlockTexture t =
					if (te != null) {
						CustomBlockInstance.addBlockInstance(new ElevatorInstance(e, te));
					}
					System.out.println("Block Instance Init: " + this.getName() + ":" + e.getUniqueId());
				}
			}
		}
	}
	
	@Override
	public CustomBlockInstance getInstance(ArmorStand s) {
		ElevatorInstance ei = new ElevatorInstance(s, getTextureEntity(s), getTexture());
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				if (s.getCustomName() != null && s.getCustomName().equals(getName()) && s.getLocation().add(-0.5, 0, -0.5).getBlock().getType() == Material.AIR) {
					removeBlock(new BlockBreakEvent(s.getLocation().add(-0.5, 0, -0.5).getBlock(), null));
					//System.out.println("A");
				}
			}
		}, 0, 0);
		Main.plugin.getServer().getPluginManager().registerEvents(ei, Main.plugin);
		return ei;
	}
	
	public List<Entity> getConnectedElevator(Entity e, Player p) {
		ArrayList<Entity> ents = new ArrayList<Entity>();
		
		for (Entity en : p.getWorld().getEntities()) {
			if (en != e && en.getCustomName() != null && en.getCustomName().equals(getName()) && en.getLocation().getX() == e.getLocation().getX() && en.getLocation().getZ() == e.getLocation().getZ()) {
				ents.add(en);
			}
		}
		
		return ents;
	}
	
	public Entity getElevatorUp(Entity e, Player p) {
		double bd = Double.MAX_VALUE;
		Entity be = null;
		
		for (Entity en : getConnectedElevator(e, p)) {
			if (e.getLocation().getY() < en.getLocation().getY() && e.getLocation().distance(en.getLocation()) < bd) {
				bd = e.getLocation().distance(en.getLocation());
				be = en;
			}
		}
		
		return be;
	}
	
	public Entity getElevatorDown(Entity e, Player p) {
		double bd = Double.MAX_VALUE;
		Entity be = null;
		
		for (Entity en : getConnectedElevator(e, p)) {
			if (e.getLocation().getY() > en.getLocation().getY() && e.getLocation().distance(en.getLocation()) < bd) {
				bd = e.getLocation().distance(en.getLocation());
				be = en;
			}
		}
		
		return be;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		for (Entity en : e.getPlayer().getWorld().getEntities()) {
			if (en.getCustomName() != null && en.getCustomName().equals(getName()) && en.getLocation().distance(e.getTo()) <= 1.5 && en.getLocation().getY() < e.getTo().getY()) {
				if (e.getTo().getY() - en.getLocation().getY() >= 1.2) {
					if (getElevatorUp(en, e.getPlayer()) != null) {e.setTo(getElevatorUp(en, e.getPlayer()).getLocation().add(0, 1, 0));}
				}
			}
		}
	}
	
	@EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent e) {
		// Sneaking = !sneaking because of badly timed events
		
		for (Entity en : e.getPlayer().getWorld().getEntities()) {
			if (en.getCustomName() != null && en.getCustomName().equals(getName()) && en.getLocation().distance(e.getPlayer().getLocation()) <= 1.5 && en.getLocation().getY() < e.getPlayer().getLocation().getY()) {
				if (!e.getPlayer().isSneaking()) {
					//e.getPlayer().sendMessage("down");
					if (getElevatorDown(en, e.getPlayer()) != null) {e.getPlayer().teleport(getElevatorDown(en, e.getPlayer()).getLocation().add(0, 1, 0));}
				}
			}
		}
	}

}
