package com.superiorcraft.realism;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RealBreakBlock implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
		
		for (BlockBreakRule r : BlockBreakRule.rules) {
			if (r.getMaterial().equals(e.getClickedBlock().getType()) && (e.getPlayer().getEquipment().getItemInMainHand() == null || !e.getPlayer().getEquipment().getItemInMainHand().getType().name().contains(r.getLevel().name()))) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "[Info] You need a tool that is made of " + r.getLevel().name() + " or a material that is stronger than " + r.getLevel().name() + " to break this block!");
			}
		}
	}
	
	@EventHandler
	public void onBlockbreak(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		
		for (BlockBreakRule r : BlockBreakRule.rules) {
			if (r.getMaterial().equals(e.getBlock().getType()) && (e.getPlayer().getEquipment().getItemInMainHand() == null || !e.getPlayer().getEquipment().getItemInMainHand().getType().name().contains(r.getLevel().name()))) {
				e.setCancelled(true);
			}
		}
	}
	
}
