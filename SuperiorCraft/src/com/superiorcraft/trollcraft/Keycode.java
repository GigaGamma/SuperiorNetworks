package com.superiorcraft.trollcraft;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.blocks.CustomBlock;
import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.gui.Menu;

public class Keycode extends CustomBlock implements Listener {

	HashMap<Player, Entity> plu = new HashMap<Player, Entity>();
	HashMap<Player, Entity> plr = new HashMap<Player, Entity>();
	ArrayList<Player> plrs = new ArrayList<Player>();
	
	public Keycode(String name, String id) {
		super(name, id);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
    	Location l = new Location(e.getWorld(), e.getLocation().getBlockX(), e.getLocation().getBlockY(), e.getLocation().getBlockZ());
		l.getWorld().getBlockAt(l).setType(Material.IRON_BLOCK);
    	
		e.addScoreboardTag("c0000");
		
		return true;
	}
	
	public void onInteract(PlayerInteractEvent e, Entity ent) {
		//e.getPlayer().sendMessage("A");
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals(getName()) && en.getLocation().add(-0.5, 0, -0.5).equals(e.getClickedBlock().getLocation())) {
					
					Menu m;
					
					if (e.getPlayer().getInventory().getItemInHand().getType() == Material.TRIPWIRE_HOOK) {
						m = new Menu("Keycode2", 27);
					}
					else {
						m = new Menu("Keycode", 27);
					}
					
					ItemStack code = new ItemStack(Material.PAPER, 1);
					
					ItemMeta codem = code.getItemMeta();
					codem.setDisplayName("&f&l".replace('&', '§'));
					
					code.setItemMeta(codem);
					
					m.inv.setItem(15, code);
					
					ItemStack res = new ItemStack(Material.BUCKET, 1);
					
					ItemMeta resm = res.getItemMeta();
					resm.setDisplayName("&f&lReset".replace('&', '§'));
					
					res.setItemMeta(resm);
					
					m.inv.setItem(13, res);
					
					for (int i = 0; i < 27; i++) {
						ItemStack it = new ItemStack(Material.IRON_BLOCK, 1);
						
						
						HashMap<Integer, Integer> id = new HashMap<Integer, Integer>();
						id.put(0, 1);
						id.put(1, 2);
						id.put(2, 3);
						id.put(9, 4);
						id.put(10, 5);
						id.put(11, 6);
						id.put(18, 7);
						id.put(19, 8);
						id.put(20, 9);
						id.put(12, 0);
						if (id.containsKey(i)) {
							ItemMeta itm = it.getItemMeta();
							itm.setDisplayName(("&f&l" + String.valueOf(id.get(i))).replace('&', '§'));
							
							it.setItemMeta(itm);
							m.inv.setItem(i, it);
						}
					}
					
					
					if (plu.containsKey(e.getPlayer())) {plu.remove(e.getPlayer());}
					if (plr.containsKey(e.getPlayer())) {plr.remove(e.getPlayer());}
					if (plrs.contains(e.getPlayer())) {plrs.remove(e.getPlayer());}
					
					if (e.getPlayer().getInventory().getItemInHand().getType() == Material.TRIPWIRE_HOOK) {
						m.show(e.getPlayer());
						e.getPlayer().sendMessage(ChatColor.GREEN + "Please enter the keycode to then reset");
						plr.put(e.getPlayer(), en);
					}
					else {
						m.show(e.getPlayer());
						e.getPlayer().sendMessage(ChatColor.GREEN + "Please enter the keycode");
						plu.put(e.getPlayer(), en);
					}
					
					e.setCancelled(true);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equals("Keycode")) {
			//e.getWhoClicked().sendMessage(e.getCurrentItem().getItemMeta().getDisplayName());
			
			String kcode = "0";
			
			for (String t : plu.get(e.getWhoClicked()).getScoreboardTags()) {
				
				if (t.startsWith("c") && !t.equals("cblock")) {
					kcode = t.replace("c", "");
				}
			}
			
			//e.getWhoClicked().sendMessage(kcode);
			
			if (e.getCurrentItem().getItemMeta().getDisplayName() != e.getInventory().getItem(15).getItemMeta().getDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName() != e.getInventory().getItem(13).getItemMeta().getDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName() != null && Integer.valueOf(e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", "")) != null) {
				ItemStack code = new ItemStack(Material.PAPER, 1);
				ItemMeta codem = code.getItemMeta();
				
				codem.setDisplayName(("&f&l" + e.getInventory().getItem(15).getItemMeta().getDisplayName().replace("§f§l", "") + e.getCurrentItem().getItemMeta().getDisplayName()).replace('&', '§'));
			
				code.setItemMeta(codem);
				
				e.getInventory().setItem(15, code);
			}
			
			else if (e.getCurrentItem().getItemMeta().getDisplayName() == e.getInventory().getItem(15).getItemMeta().getDisplayName()) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", "").length() != 0 && e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", "").equals(kcode)) {
					e.getWhoClicked().closeInventory();
					e.getWhoClicked().sendMessage(ChatColor.GREEN + "Keycode correct!");
					
					Location l = plu.get(e.getWhoClicked()).getLocation();
					
					l.add(-0.5, 0, -0.5);
					l.add(0, -2, 0);
					
					plu.get(e.getWhoClicked()).getWorld().getBlockAt(l).setData((byte) 15, true);
				}
			}
			
			else if (e.getCurrentItem().getItemMeta().getDisplayName() == e.getInventory().getItem(13).getItemMeta().getDisplayName()) {
				ItemStack code = new ItemStack(Material.PAPER, 1);
				ItemMeta codem = code.getItemMeta();
				
				codem.setDisplayName("&f&l".replace('&', '§'));
			
				code.setItemMeta(codem);
				
				e.getInventory().setItem(15, code);
			}
				
			
			
			e.setCancelled(true);
		}
		
		else if (e.getInventory().getName().equals("Keycode2")) {
			//e.getWhoClicked().sendMessage(e.getCurrentItem().getItemMeta().getDisplayName());
			
			String kcode = "0";
			
			for (String t : plr.get(e.getWhoClicked()).getScoreboardTags()) {
				
				if (t.startsWith("c")) {
					kcode = t.replace("c", "");
				}
			}
			
			//e.getWhoClicked().sendMessage(kcode);
			
			if (e.getCurrentItem().getItemMeta().getDisplayName() != e.getInventory().getItem(15).getItemMeta().getDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName() != e.getInventory().getItem(13).getItemMeta().getDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName() != null && Integer.valueOf(e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", "")) != null) {
				ItemStack code = new ItemStack(Material.PAPER, 1);
				ItemMeta codem = code.getItemMeta();
				
				codem.setDisplayName(("&f&l" + e.getInventory().getItem(15).getItemMeta().getDisplayName().replace("§f§l", "") + e.getCurrentItem().getItemMeta().getDisplayName()).replace('&', '§'));
			
				code.setItemMeta(codem);
				
				e.getInventory().setItem(15, code);
			}
			
			else if (e.getCurrentItem().getItemMeta().getDisplayName() == e.getInventory().getItem(15).getItemMeta().getDisplayName()) {
				if (!plrs.contains(e.getWhoClicked()) && e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", "").length() != 0 && e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", "").equals(kcode)) {
					ItemStack code = new ItemStack(Material.PAPER, 1);
					ItemMeta codem = code.getItemMeta();
					
					codem.setDisplayName("&f&l".replace('&', '§'));
				
					code.setItemMeta(codem);
					
					e.getInventory().setItem(15, code);
					plrs.add((Player) e.getWhoClicked());
					e.getWhoClicked().sendMessage(ChatColor.GREEN + "Please enter a new code");
				}
				else if (plrs.contains(e.getWhoClicked())) {
					e.getWhoClicked().sendMessage(ChatColor.GREEN + "Done");
					plr.get(e.getWhoClicked()).getScoreboardTags().clear();
					plr.get(e.getWhoClicked()).addScoreboardTag("c" + e.getCurrentItem().getItemMeta().getDisplayName().replace("§f§l", ""));
					e.getWhoClicked().closeInventory();
				}
			}
			
			else if (e.getCurrentItem().getItemMeta().getDisplayName() == e.getInventory().getItem(13).getItemMeta().getDisplayName()) {
				ItemStack code = new ItemStack(Material.PAPER, 1);
				ItemMeta codem = code.getItemMeta();
				
				codem.setDisplayName("&f&l".replace('&', '§'));
			
				code.setItemMeta(codem);
				
				e.getInventory().setItem(15, code);
			}
				
			
			
			e.setCancelled(true);
		}
	}
	
	/*public void onBlockRedstoneEvent(BlockRedstoneEvent e) {
		e.setNewCurrent(e.getOldCurrent());
	}*/
}
