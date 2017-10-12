package com.superiorcraft.server;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.Registry;

public class Minigame extends Server implements Listener {
	
	protected static ArrayList<Minigame> minigames = new ArrayList<Minigame>();
	
	private int minPlayers = 1;
	private int maxPlayers = 16;
	private ArrayList<Player> players = new ArrayList<Player>();
	private boolean autoStart = true;
	
	public Minigame() {
		if (getLocation() != null) {
			Bukkit.getServer().createWorld(new WorldCreator(getLocation().getWorld().getName()));
		}
		
		if (isAutoStart()) {
			new BukkitRunnable() {
				
				@Override
				public void run() {
					if (players.size() >= minPlayers) {
						System.out.println("AutoJoin Timer");
						start();
						cancel();
					}
				}
				
			}.runTaskTimer(SuperiorCraft.plugin, 10, 50);
		}
		
		Registry.registerListener(this);
		minigames.add(this);
	}
	
	public void start() {
		
	}
	
	public boolean canJoin() {
		return players.size() <= maxPlayers;
	}
	
	public boolean join(Player player) {
		if (canJoin()) {
			getPlayers().add(player);
			player.teleport(getLocation());
			return true;
		} else {
			player.sendMessage("Cannot join minigame");
		}
		return false;
	}
	
	public void leave(Player player) {
		if (getPlayers().contains(player)) {
			getPlayers().remove(player);
		}
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
