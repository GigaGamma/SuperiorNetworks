package com.superiorcraft.server;

import java.util.ArrayList;

import org.bukkit.World;

public class ServerSelector {
	
	private static ArrayList<Server> servers = new ArrayList<Server>();

	public static ArrayList<Server> getServers() {
		return servers;
	}
	
}
