package com.superiorcraft.api.util;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.superiorcraft.api.ConfigTranslater;
import com.superiorcraft.api.util.ranks.RankAdapter;
import com.superiorcraft.nms.NMSAdapter;

public class PlayerData implements Serializable {

	private static final long serialVersionUID = 2142020039529917194L;
	
	private Player player;
	private Rank rank = null;
	/*private ArrayList<Block> blocks = new ArrayList<Block>();*/
	
	public PlayerData(Player player) {
		this.player = player;
		//System.out.println(ConfigTranslater.getRank(player));
		if (ConfigTranslater.getRank(player) != null) {
			this.rank = RankAdapter.valueOf(ConfigTranslater.getRank(player).split("\\.")[ConfigTranslater.getRank(player).split("\\.").length - 1].toUpperCase()).rank();	
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/*public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public void addBlock(Block block) {
		blocks.add(block);
	}*/
	
	public static void setTagName(Player p, String name) {
		try {
			NMSAdapter.getConnection(p).getClass().getDeclaredField("name").set(NMSAdapter.getConnection(p).getClass(), name);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
}
