package com.superiorcraft.api.blocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtil {
	
	public static List<Block> getBlocksTouching(Material m, Location l) {
		List<Block> ub = new ArrayList<Block>();
		List<Block> b = new ArrayList<Block>();
		Block cb;
		System.out.println(l);
		if (l.getBlock() != null && l.getBlock().getType() == m) {
			ub.add(l.getBlock());
			b.add(l.getBlock());
			cb = l.getBlock();
			for (int i = 0; i < 100; i++) {
				int mx = 1;
				int my = 0;
				int mz = 0;
				
				if (cb.getLocation().add(mx, my, mz).getBlock() != null && !ub.contains(cb.getLocation().add(mx, my, mz).getBlock()) && cb.getLocation().add(mx, my, mz).getBlock().getType() == m) {
					cb = cb.getLocation().add(mx, my, mz).getBlock();
					ub.add(cb);
					b.add(cb);
				}
			}
		}
		
		return b;
	}
	
}
