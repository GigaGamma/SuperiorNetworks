package com.superiorcraft.Forge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.superiorcraft.main.Main;
import com.superiorcraft.trollcraft.BlockBreaker;
import com.superiorcraft.trollcraft.GhostBlock;
import com.superiorcraft.trollcraft.Keycode;
import com.superiorcraft.trollcraft.SlickDoor;
import com.superiorcraft.trollcraft.SlickDoorFrame;

public class CustomBlockLoader implements Listener, CommandExecutor {
	
	public String name;
	public String id;
	
	public CustomBlockLoader(String name, String id) {
		super();
		
		this.name = name.replace('&', '§');
		this.id = id;
	}
	
	public void load() {
		// Ghost Block
		
		CustomBlockLoader gb = new GhostBlock("&f&lGhost Block", "trollcraft:ghost_block");
    	Main.plugin.getServer().getPluginManager().registerEvents(gb, Main.plugin);
    	
		// Slick Door
		
		CustomBlockLoader sd = new SlickDoor("&f&lSlick Door", "trollcraft:slick_door");
    	Main.plugin.getServer().getPluginManager().registerEvents(sd, Main.plugin);
    	
		// Slick Door Frame
		
		CustomBlockLoader sdf = new SlickDoorFrame("&f&lSlick Door Frame", "trollcraft:slick_door_frame");
    	Main.plugin.getServer().getPluginManager().registerEvents(sdf, Main.plugin);
    	
		// Block Breaker
    	
    	Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
    		@Override
    		public void run() {
    			for (World world : Main.plugin.getServer().getWorlds()) {
    				for (Entity en : world.getEntities()) {
    					if (en.getCustomName() != null && en.getCustomName().contains("Block Breaker")) {
    						Location l = en.getLocation();
    						l.add(-0.5, 0, -0.5);
    						
    						if (en.getWorld().getBlockAt(l).isBlockPowered()) {
    							l.add(0, -1, 0);
    							en.getWorld().getBlockAt(l).breakNaturally();
    						}
    					}
    				}
    			}
    		}
    	}, 0L, 2L);
		
		CustomBlockLoader bb = new BlockBreaker("&f&lBlock Breaker", "trollcraft:block_breaker");
    	Main.plugin.getServer().getPluginManager().registerEvents(bb, Main.plugin);
    	
    	// Slick Door Frame
		
    	CustomBlockLoader kc = new Keycode("&f&lKey Pad", "trollcraft:key_pad");
    	Main.plugin.getServer().getPluginManager().registerEvents(kc, Main.plugin);
	}
	
	public boolean placeBlock(ArmorStand e, Player p) {
		//e.setHelmet(new ItemStack(Material.STONE, 1));
		
		e.getWorld().getBlockAt(e.getLocation()).setType(Material.STONE);
		
		return true;
	}
	
	public void removeBlock(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getClickedBlock().getLocation())) {
					en.remove();
					en.getWorld().getBlockAt(en.getLocation().add(-0.5, 0, -0.5)).setType(Material.AIR);
					
					ItemStack block = new ItemStack(Material.MONSTER_EGG, 1);
					
					ItemMeta bmeta = block.getItemMeta();
					
					bmeta.setDisplayName(name);
					
					block.setItemMeta(bmeta);
					
					e.getPlayer().getInventory().addItem(block);
				}
			}
		}
	}
	
	public void onInteract(PlayerInteractEvent e) {
		
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem() != null && e.getItem().getType() != null && e.getItem().getType().equals(Material.MONSTER_EGG) && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().getDisplayName() != null && e.getItem().getItemMeta().getDisplayName().equals(name)) {
			//e.setCancelled(true);
			
			if (e.getItem().getAmount() - 1 != 0) {
				ItemStack it = e.getItem();
				it.setAmount(e.getItem().getAmount() - 1);
				e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().first(e.getItem()), it);
			}
			
			else {
				e.getPlayer().getInventory().clear(e.getPlayer().getInventory().first(e.getItem()));
			}
			
			ArmorStand block = (ArmorStand) e.getClickedBlock().getLocation().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5, 1, 0.5), EntityType.ARMOR_STAND);
			block.setSmall(true);
			block.setGravity(false);
			block.setCustomName(name);
			block.setMarker(true);
			block.setVisible(false);
			if (placeBlock(block, e.getPlayer()) == false) {
				block.remove();
				//e.getPlayer().sendMessage("a");
				
				ItemStack it = new ItemStack(Material.MONSTER_EGG, 1);
				
				ItemMeta bmeta = it.getItemMeta();
				
				bmeta.setDisplayName(name);
				
				it.setItemMeta(bmeta);
				
				e.getPlayer().getInventory().addItem(it);
			}
		}
		
		removeBlock(e);
		onInteract(e);
		
		/*else if (e.getPlayer().isSneaking()) {
			for (Entity en : e.getPlayer().getNearbyEntities(0, 0, 0)) {
				if (en.getCustomName().equals()) {
					en.remove();
					ItemStack wire = new ItemStack(Material.MONSTER_EGG, 64);
					
					ItemMeta wmeta = wire.getItemMeta();
					
					wmeta.setDisplayName("&4&lWire".replace('&', '§'));
					
					wire.setItemMeta(wmeta);
					
					e.getPlayer().getInventory().addItem(wire);
				}
			} 
		}*/
	}
	
	public void giveItem(String name, Player player) {
		ItemStack block = new ItemStack(Material.MONSTER_EGG, 64);
			
		ItemMeta bmeta = block.getItemMeta();
			
		bmeta.setDisplayName("&f&l".replace('&', '§') + name.replace('_', ' '));
			
		block.setItemMeta(bmeta);
			
		player.getInventory().addItem(block);
	}
	
	@Override
    public boolean onCommand(CommandSender sender,
            Command command,
            String label,
            String[] args) {
		
		if (command.getName().equalsIgnoreCase("getblock")) {
        	Player player = (Player) sender;
        	
			giveItem(args[0], player);
			
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static void particle(Location l, Entity e, Effect ef, Material param) {
		for (Player p : e.getWorld().getPlayers()) {
			p.playEffect(l, ef, param);
		}
	}
	
	public static ArrayList<Entity> getBlockByName(String name, Entity e) {
		ArrayList<Entity> r = new ArrayList<Entity>();
		
		for (Entity en : e.getWorld().getEntities()) {
			if (en.getCustomName() != null && en.getCustomName().equals(name)) {
				r.add(en);
			}
		}
		
		return r;
	}
	
}
