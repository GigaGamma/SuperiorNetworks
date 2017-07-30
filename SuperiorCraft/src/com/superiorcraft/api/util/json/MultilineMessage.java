package com.superiorcraft.api.util.json;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MultilineMessage {
	
	private String[] lines;
	
	private String linePrefix = "";
	private String lineSuffix = "";
	private ChatColor baseColor = ChatColor.RESET;
	
	public MultilineMessage(String[] lines) {
		setLines(lines);
	}
	
	public String[] getLines() {
		return lines;
	}

	public void setLines(String[] lines) {
		this.lines = lines;
	}
	
	public String getLinePrefix() {
		return linePrefix;
	}

	public MultilineMessage setLinePrefix(String linePrefix) {
		this.linePrefix = linePrefix;
		return this;
	}

	public String getLineSuffix() {
		return lineSuffix;
	}

	public MultilineMessage setLineSuffix(String lineSuffix) {
		this.lineSuffix = lineSuffix;
		return this;
	}

	public ChatColor getBaseColor() {
		return baseColor;
	}

	public MultilineMessage setBaseColor(ChatColor baseColor) {
		this.baseColor = baseColor;
		return this;
	}

	public void sendMessage(Player player) {
		for (String line : getLines()) {
			player.sendMessage(getBaseColor() + getLinePrefix() + line + getLineSuffix());
		}
	}
	
}
