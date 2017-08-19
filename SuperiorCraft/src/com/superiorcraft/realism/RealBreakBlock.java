package com.superiorcraft.realism;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.items.CustomTool;

public class RealBreakBlock implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
		
		for (BlockBreakRule r : BlockBreakRule.rules) {
			if (r.getMaterial().equals(e.getClickedBlock().getType()) && (e.getPlayer().getEquipment().getItemInMainHand() == null || !r.getLevel().canBreakWith(e.getPlayer().getEquipment().getItemInMainHand()))) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "[Info] You need a tool that is made of " + r.getLevel().name() + " or a material that is stronger than " + r.getLevel().name() + " to break this block!");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockbreak(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		
		for (BlockBreakRule r : BlockBreakRule.rules) {
			if (r.getMaterial().equals(e.getBlock().getType()) && (e.getPlayer().getEquipment().getItemInMainHand() == null || !r.getLevel().canBreakWith(e.getPlayer().getEquipment().getItemInMainHand()))) {
				e.setCancelled(true);
				return;
			}
		}
		
		for (CustomItem it : CustomItem.items) {
			if (it instanceof CustomTool) {
				if (e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getType() != null && e.getPlayer().getItemInHand().hasItemMeta() && e.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && it.getItem().getItemMeta().getDisplayName().equals(e.getPlayer().getItemInHand().getItemMeta().getDisplayName())) {
					if (!e.getPlayer().getItemInHand().getItemMeta().isUnbreakable()) {
						//e.getPlayer().sendMessage(e.getPlayer().getItemInHand().getType().name());
						int maxDamage = e.getPlayer().getItemInHand().getType().getMaxDurability(); // btw, this value is 0 for items that don't have durability bar
						int damage = e.getPlayer().getItemInHand().getDurability() + ((CustomTool) it).getDamagePerUse();

						if(damage > maxDamage) // calculated damage exceeds max damage
						{
							e.getPlayer().getInventory().remove(e.getPlayer().getItemInHand());
						}
						else
						{
							e.getPlayer().getItemInHand().setDurability((short) damage);
						}
					}
				}
			}
		}
	}
	
}
