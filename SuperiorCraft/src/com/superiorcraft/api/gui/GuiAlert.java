package com.superiorcraft.api.gui;

import org.bukkit.Material;

import com.superiorcraft.api.util.item.ItemConstruct;

public class GuiAlert extends Menu implements Gui {

	public GuiAlert(String name) {
		super(name, 27);
		
		setBackground(new ItemConstruct(Material.STAINED_GLASS_PANE).getMeta().setName("").setData((byte) 7).getItem());
		
		addItem(new ItemConstruct(Material.WOOL).getMeta().setName("").setData((byte) 5).getItem(), 11);
		addItem(new ItemConstruct(Material.WOOL).getMeta().setName("").setData((byte) 14).getItem(), 15);
		
		//addButton(new Button(item))
	}

	@Override
	public int getPage() {
		return 0;
	}

	@Override
	public GuiCategory getCategory() {
		return GuiCategory.ALERT;
	}
	
	public void onResponse(int response) {
		
	}

}
