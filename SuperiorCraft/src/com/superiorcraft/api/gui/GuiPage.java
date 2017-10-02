package com.superiorcraft.api.gui;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.util.item.ItemConstruct;

public abstract class GuiPage extends Menu implements Gui {
	
	private HashMap<PagePosition, ItemStack> items = new HashMap<PagePosition, ItemStack>();
	private int start;
	private int end;
	private int page;
	
	private GuiCategory category;
	
	public GuiPage(String name, int size, GuiCategory cat) {
		super(name, size);
		setCategory(cat);
	}
	
	public HashMap<PagePosition, ItemStack> getItems() {
		return items;
	}

	public void setItems(HashMap<PagePosition, ItemStack> items) {
		this.items = items;
	}
	
	public GuiPage addItem(ItemStack item, PagePosition position) {
		this.items.put(position, item);
		return this;
	}
	
	@Override
	@Deprecated
	public GuiPage addItem(ItemStack item) {
		return this;
	}
	
	public void setPageDelimiters(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public void loadPage(int page) {
		boolean test = false;
		for (int i = 0; i < end - start; i++) {
			for (PagePosition p : items.keySet()) {
				if (p.getPage() == page) {
					test = true;
				}
			}
		}
		
		if (!test) {
			loadPage(getPage());
			return;
		}
		for (int i = 0; i < end - start; i++) {
			this.inv.setItem(start + i, new ItemStack(Material.AIR));
		}
		for (int i = 0; i < end - start; i++) {
			for (PagePosition p : items.keySet()) {
				if (p.getPage() == page && p.getPosition() == i) {
					this.inv.setItem(start + i, items.get(p));
				}
			}
		}
		Button next = new Button(new ItemConstruct(Material.WOOL).getMeta().setName("Next").getItem()) {
			
			@Override
			public void onClick(Player p, Inventory i) {
				loadPage(page + 1);
				removeButton(this);
			}
			
		};
		addButton(next);
		addItem(next.getItem(), this.inv.getSize() - 8);
		
		Button back = new Button(new ItemConstruct(Material.WOOL).getMeta().setName("Back").getItem()) {
			
			@Override
			public void onClick(Player p, Inventory i) {
				loadPage(page - 1);
				removeButton(this);
			}
			
		};
		addButton(back);
		addItem(back.getItem(), this.inv.getSize() - 9);
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}

	public GuiCategory getCategory() {
		return category;
	}

	public void setCategory(GuiCategory category) {
		this.category = category;
	}

}
