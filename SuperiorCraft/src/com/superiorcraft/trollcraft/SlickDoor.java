package com.superiorcraft.trollcraft;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.CustomBlockLoader;
import com.superiorcraft.main.Main;

public class SlickDoor extends CustomBlockLoader implements Listener {

	public SlickDoor(String name, String id) {
		super(name, id);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		e.getWorld().getBlockAt(e.getLocation()).setType(Material.ENDER_STONE);
		
		return true;
	}
	
	@Override
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && !e.getPlayer().isSneaking()) {
			ArrayList<Entity> opened = new ArrayList<Entity>();
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getClickedBlock().getLocation())) {
					opened.add(en);
					for (Entity en2 : en.getNearbyEntities(2.5, 2.5, 2.5)) {
						if (en2.getCustomName() != null && en2.getCustomName().contains("Slick Door Frame") && !opened.contains(en2)) {
							opened.add(en2);
							Material old = e.getPlayer().getWorld().getBlockAt(en2.getLocation().add(-0.5, 0, -0.5)).getType();
							e.getPlayer().getWorld().getBlockAt(en2.getLocation().add(-0.5, 0, -0.5)).setType(Material.AIR);
							//e.getPlayer().playEffect(en2.getLocation().add(-0.5, 0, -0.5), Effect.STEP_SOUND, old);
							CustomBlockLoader.particle(en2.getLocation().add(-0.5, 0, -0.5), en2, Effect.STEP_SOUND, old);
							Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
		    					@Override
		    					public void run() {
		    						e.getPlayer().getWorld().getBlockAt(en2.getLocation().add(-0.5, 0, -0.5)).setType(old);

		    					}
		    				}, 3 * 20);
						}
					}
					Material old = e.getClickedBlock().getType();
					e.getClickedBlock().setType(Material.AIR);
					CustomBlockLoader.particle(e.getClickedBlock().getLocation(), en, Effect.STEP_SOUND, old);
					Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
    					@Override
    					public void run() {
    						e.getClickedBlock().setType(old);

    					}
    				}, 3 * 20);
					
					e.setCancelled(true);
				}
			}
		}
	}

}
