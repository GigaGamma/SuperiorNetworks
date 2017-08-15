package com.superiorcraft.anticheat;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.util.ListUtil;
import com.superiorcraft.api.util.PlayerData;
import com.superiorcraft.api.util.ServerUtil;
import com.superiorcraft.nms.JsonMessage;
import com.superiorcraft.nms.NMSAdapter;

// AKA SuperiorSecure

public class AntiCheat implements Listener {
	
	private static FileConfiguration config;
	public static HashMap<Player, Integer> ftime = new HashMap<Player, Integer>();
	public static ArrayList<PlayerData> data = new ArrayList<PlayerData>();
	
	public AntiCheat() {
		config = YamlConfiguration.loadConfiguration(new File(SuperiorCraft.plugin.getDataFolder(), "anticheat.yml"));
		if (config.getString("enabled").equalsIgnoreCase("true")) {
			JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage("[SuperiorCraft > SuperiorSecure] AntiCheat is now activated", "green", "If you are not a developer, you can ignore this", "light_purple", "")});
			for (Player p : ServerUtil.getPlayers()) {
				AntiCheat.data.add(new PlayerData(p));
			}
		} else {
			JsonMessage.broadcastJsonMessages(new JsonMessage[] {new JsonMessage("[SuperiorCraft > SuperiorSecure] SuperiorSecure is either not enabled or it could not find the anticheat.yml", "red", "If you are not a developer, you can ignore this", "light_purple", "")});
		}
	}
	
	public static void kickPlayer(Player player, Reason reason, String message) {
		player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&4[SuperiorCraft > SuperiorSecure] You were kicked for " + reason.getReadableName() + "\n" + message));
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (!e.getPlayer().isOnGround()) {
			Location moved = e.getTo().clone().subtract(e.getFrom());
			Block b = e.getPlayer().getWorld().getHighestBlockAt(e.getPlayer().getLocation());
			//e.getPlayer().sendMessage(String.valueOf(e.getPlayer().getLocation().getBlockY() - b.getY()));
			if (!(moved.getY() < -0.1)) {
				if (!e.getPlayer().hasPermission("superiorcraft.fly") && !e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && !e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
					if (ftime.containsKey(e.getPlayer())) {
						int ov = ftime.get(e.getPlayer());
						ftime.remove(e.getPlayer());
						ftime.put(e.getPlayer(), ov + 1);
						//e.getPlayer().sendMessage(String.valueOf(ov));
						if (ov > 20) {
							e.setCancelled(true);
							e.getPlayer().teleport(b.getLocation().add(0, 1, 0));
							kickPlayer(e.getPlayer(), Reason.FLYING, (String) ListUtil.getRandomItem(AntiCheat.config.getStringList("messages.flight")));
						}
					} else {
						ftime.put(e.getPlayer(), 1);
					}
				}
			}
		}
		for (PlayerData d : AntiCheat.data) {
			if (d.getPlayer().equals(e.getPlayer())) {
				d.addBlock(e.getTo().getBlock());
				//System.out.println(d.getBlocks().get(d.getBlocks().size() - 2).getLocation().distance(d.getBlocks().get(d.getBlocks().size() - 1).getLocation()));
				if (d.getPlayer().isOnGround() && d.getBlocks().size() > 3 && d.getBlocks().get(d.getBlocks().size() - 2).getLocation().distance(d.getBlocks().get(d.getBlocks().size() - 1).getLocation()) > 2 && !e.getPlayer().isOp()) {
					AntiCheat.kickPlayer(d.getPlayer(), Reason.SPEED_HACKING, (String) ListUtil.getRandomItem(AntiCheat.config.getStringList("messages.speed")));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		ftime.remove(e.getEntity());
	}
	
}
