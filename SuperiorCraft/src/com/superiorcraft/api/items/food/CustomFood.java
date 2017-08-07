package com.superiorcraft.api.items.food;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.superiorcraft.api.items.CustomItem;

public abstract class CustomFood extends CustomItem implements FoodItem {

	public CustomFood(ItemStack item, String id) {
		super(item, id);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem() != null && e.getItem().getType() != null && e.getItem().getType() == getItem().getType() && e.getItem().getDurability() == getItem().getDurability() && e.getPlayer().getFoodLevel() < 20) {
			if (e.getItem().getAmount() - 1 != 0) {
				ItemStack it = e.getItem();
				it.setAmount(e.getItem().getAmount() - 1);
				e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().first(e.getItem()), it);
			}
			
			else {
				e.getPlayer().getInventory().clear(e.getPlayer().getInventory().first(e.getItem()));
			}
			e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + getNutritionLevel() <= 20 ? e.getPlayer().getFoodLevel() + getNutritionLevel() : 20);
			e.getPlayer().setSaturation(e.getPlayer().getSaturation() + getNutritionLevel() <= 20 ? e.getPlayer().getSaturation() + getSaturationLevel() : 20);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_BURP, 100, 1);
			onEat(e.getPlayer());
			e.setCancelled(true);
		}
	}

}
