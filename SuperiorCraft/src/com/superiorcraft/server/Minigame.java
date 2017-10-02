package com.superiorcraft.server;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.superiorcraft.SuperiorCraft;

public class Minigame extends Server {
	
	private int minPlayers = 1;
	private int maxPlayers = 16;
	private ArrayList<Player> players = new ArrayList<Player>();
	private boolean autoStart = true;
	
	public Minigame() {
		if (isAutoStart()) {
			new BukkitRunnable() {
				
				@Override
				public void run() {
					if (players.size() >= minPlayers) {
						start();
					}
				}
				
			}.runTaskTimer(SuperiorCraft.plugin, 0, 50);
		}
	}
	
	public void start() {
		
	}
	
	public boolean canJoin() {
		return !(players.size() >= maxPlayers);
	}
	
	public boolean join(Player player) {
		if (canJoin()) {
			player.teleport(getLocation());
			return true;
		}
		return false;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public boolean isAutoStart() {
		return autoStart;
	}

	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}
	
}
