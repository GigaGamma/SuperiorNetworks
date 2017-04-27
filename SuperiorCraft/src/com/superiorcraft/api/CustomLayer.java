package com.superiorcraft.api;

import org.bukkit.Color;

public class CustomLayer {
	
	public int texture;
	public Color color;
	public boolean glowing = false;
	
	public CustomLayer(int texture, Color color, boolean glowing) {
		this.texture = texture;
		this.color = color;
		this.glowing = glowing;
	}
	
	public CustomLayer(int texture, Color color) {
		this.texture = texture;
		this.color = color;
	}
	
}
