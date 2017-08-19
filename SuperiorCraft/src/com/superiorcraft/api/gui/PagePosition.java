package com.superiorcraft.api.gui;

public class PagePosition {
	
	private int page;
	private int position;
	
	public PagePosition(int page, int position) {
		setPage(page);
		setPosition(position);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
}
