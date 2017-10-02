package com.superiorcraft.api.crafting;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.gui.Button;
import com.superiorcraft.api.gui.GuiCategory;
import com.superiorcraft.api.gui.GuiPage;
import com.superiorcraft.api.gui.PagePosition;
import com.superiorcraft.api.util.item.ItemConstruct;

public class CraftingGui extends GuiPage {
	
	private String machine;
	
	public CraftingGui(String machine) {
		super("Crafting Guide > " + machine, 45, GuiCategory.PAGE_LIST);
		setMachine(machine);
		setPageDelimiters(9, 36);
		
		Button crafting = new Button(new ItemConstruct(Material.WORKBENCH).getMeta().setName("Back").getItem()) {
			
			@Override
			public void onClick(Player p, Inventory i) {
				///p.openInventory(getCraftMainMenu().inv);
			}
			
		};
		addButton(crafting);
		addItem(crafting.getItem(), 0);
		for (int i = 1; i < 9; i++) {
			addItem(new ItemConstruct(Material.STAINED_GLASS_PANE).getMeta().setName("").setData((byte) 7).getItem(), i);
		}
		int i = 0;
		int page = 0;
		for (CustomCraftingRecipe r : CustomCrafting.recipes) {
			if (r.ncon == machine) {
				if (i == 27) {
					page++;
					i = 0;
				}
				addItem(r.out, new PagePosition(page, i));
				i++;
			}
		}
		
		loadPage(0);
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

}
