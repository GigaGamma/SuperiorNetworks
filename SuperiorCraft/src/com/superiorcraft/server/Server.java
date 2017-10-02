package com.superiorcraft.server;

import org.bukkit.Location;

public class Server {
	
	private String name;
	private Location location;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
