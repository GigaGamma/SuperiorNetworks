package com.superiorcraft.api.items.weapons;

import com.superiorcraft.api.crafting.CustomCrafting;
import com.superiorcraft.api.crafting.CustomCraftingRecipe;
import com.superiorcraft.api.gui.GuiCategory;
import com.superiorcraft.api.gui.GuiPage;
import com.superiorcraft.api.gui.PagePosition;
import com.superiorcraft.api.util.item.ItemConstruct;

public class RangedWeaponList extends GuiPage {

	public RangedWeaponList() {
		super("Ranged Weapons", 45, GuiCategory.PAGE_LIST);
		
		setPageDelimiters(9, 36);
		
		int i = 0;
		int page = 0;
		for (RangedWeapon r : RangedWeapon.weapons) {
			if (i == 27) {
				page++;
				i = 0;
			}
			addItem(r.getItem(), new PagePosition(page, i));
			i++;
		}
		
		loadPage(0);
	}

}
