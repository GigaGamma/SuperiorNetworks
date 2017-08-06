package com.superiorcraft.api.blocks;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.superiorcraft.api.util.Menu;
import com.superiorcraft.api.util.item.ItemConstruct;
import com.superiorcraft.api.util.item.ItemMetaConstruct;

public class CustomPanel implements Listener {
	
	public static ArrayList<CustomPanel> panels = new ArrayList<CustomPanel>();
	
	private String name;
	private String id;
	private CustomPanelTexture texture;
	
	public CustomPanel(String name, String id, CustomPanelTexture texture) {
		setName(name);
		setId(id);
		setTexture(texture);
		
		panels.add(this);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof ItemFrame) {
			if (((ItemFrame) e.getRightClicked()).getItem() != null && ((ItemFrame) e.getRightClicked()).getItem().getType() == Material.DIAMOND_HOE && ((ItemFrame) e.getRightClicked()).getItem().getDurability() == getTexture().getTextureItem().getDurability()) {
				if (e.getPlayer().isSneaking()) {
					e.getPlayer().openInventory(new Menu("Panel Configuration", 27) {
						
						@Override
						public void onInventoryClick(InventoryClickEvent e) {
							e.setCancelled(true);
						}
						
					}.setBackground(new ItemConstruct(Material.STAINED_GLASS_PANE).getMeta().setName("Space Filler").setData((byte) 8).getItem()).addItem(getTexture().getTextureItem(), 10).inv);
				}
				e.setCancelled(true);
			} else if (((ItemFrame) e.getRightClicked()).getItem() != null && e.getPlayer().getEquipment().getItemInMainHand() != null && e.getPlayer().getEquipment().getItemInMainHand().getType() == Material.DIAMOND_HOE && e.getPlayer().getEquipment().getItemInMainHand().getDurability() == getTexture().getTextureItem().getDurability()) {
				ItemStack it = e.getPlayer().getEquipment().getItemInMainHand().clone();
				ItemMeta itm = it.getItemMeta();
				itm.setDisplayName("");
				it.setItemMeta(itm);
				((ItemFrame) e.getRightClicked()).setItem(it);
				e.setCancelled(true);
			} else if (e.getPlayer().getEquipment().getItemInMainHand() != null && e.getPlayer().getEquipment().getItemInMainHand().getType() == Material.FLINT_AND_STEEL) {
				ItemFrame frame = ((ItemFrame) e.getRightClicked());
				if (frame.getItem() != null && frame.getItem().getType() == Material.RAW_BEEF) {
					frame.setItem(new ItemStack(Material.COOKED_BEEF));
					e.getPlayer().sendMessage("Cooked!");
				}
				e.setCancelled(true);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomPanelTexture getTexture() {
		return texture;
	}

	public void setTexture(CustomPanelTexture texture) {
		this.texture = texture;
	}
	
}
