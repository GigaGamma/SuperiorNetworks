package com.superiorcraft.api.blocks;

import org.bukkit.Color;

public class CustomLayer {
	
	private int texture;
	private Color color;
	private boolean glowing = false;
	
	public CustomLayer(int texture, Color color, boolean glowing) {
		this.texture = texture;
		this.color = color;
		this.glowing = glowing;
	}
	
	public CustomLayer(int texture, Color color) {
		this.texture = texture;
		this.color = color;
	}

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isGlowing() {
		return glowing;
	}

	public void setGlowing(boolean glowing) {
		this.glowing = glowing;
	}
	
}
