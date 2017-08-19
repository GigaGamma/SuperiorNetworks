package com.superiorcraft.api.gui;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PageHistory {
	
	public ArrayList<PageHistory> history = new ArrayList<PageHistory>();
	
	private Player player;
	private ArrayList<GuiPage> pages = new ArrayList<GuiPage>();
	
	public PageHistory(Player player) {
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<GuiPage> getPages() {
		return pages;
	}

	public void setPages(ArrayList<GuiPage> pages) {
		this.pages = pages;
	}
	
}
