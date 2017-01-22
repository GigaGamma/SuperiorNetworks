package com.superiorcraft.Forge;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Flag extends CustomBlockLoader {

	public Flag(String name, String id) {
		super(name, id);
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		//e.setHelmet(new ItemStack(Material.STONE, 1));
		
		
		if (e.getWorld().getBlockAt(e.getLocation()).getType() != Material.STANDING_BANNER) {
			p.sendMessage(ChatColor.RED + "Please place down a banner first!");
			e.remove();
		}
		
		else {
			e.setCustomName("flag");
			p.sendMessage(ChatColor.GREEN + "Flag placed");
		}
		
		return true;
	}
	
	@Override
	public void removeBlock(BlockBreakEvent e) {
			for (Entity en : e.getPlayer().getWorld().getEntities()) {
				if (en.getCustomName() != null && en.getCustomName().equals("flag") && en.getLocation().add(-0.5, 0, -0.5).equals(e.getBlock().getLocation())) {
					en.remove();
					e.getPlayer().sendMessage(ChatColor.GRAY + "Flag removed");
				}
		}
	}

}
