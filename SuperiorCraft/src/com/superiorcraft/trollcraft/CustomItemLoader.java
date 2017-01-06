package com.superiorcraft.trollcraft;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.superiorcraft.main.Main;

public class CustomItemLoader implements Listener, CommandExecutor {
	
	public String id;
	public ItemStack item;
	
	public CustomItemLoader(ItemStack item, String id) {
		super();
		
		this.id = id.replace('&', '§');
		this.item = item;
	}
	
	public void load() {
		// Ghost Block
		
		ItemStack pcrys = new ItemStack(Material.DIAMOND);
    	ItemMeta pcrysm = pcrys.getItemMeta();
    	pcrysm.setDisplayName("&b&lPower Crystal".replace('&', '§'));
    	ArrayList<String> pcrysl = new ArrayList<String>();
    	pcrysl.add("&b&l0 / 100,000 RF".replace('&', '§'));
       	pcrysm.setLore(pcrysl);
       	pcrys.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 100);
    	pcrys.setItemMeta(pcrysm);
		
		CustomItemLoader pc = new PowerCrystal(pcrys, "trollcraft:power_crystal");
		Main.plugin.getServer().getPluginManager().registerEvents(pc, Main.plugin);
	}
	
	public void giveItem(String name, Player player) {
		ItemStack block = new ItemStack(Material.MONSTER_EGG, 64);
			
		ItemMeta bmeta = block.getItemMeta();
			
		bmeta.setDisplayName("&f&l".replace('&', '§') + name.replace('_', ' '));
			
		block.setItemMeta(bmeta);
			
		player.getInventory().addItem(block);
	}
	
	@Override
    public boolean onCommand(CommandSender sender,
            Command command,
            String label,
            String[] args) {
		
		if (command.getName().equalsIgnoreCase("getitem")) {
        	Player player = (Player) sender;
        	
        	if (id.equals(args[0])) {
        		
        		player.getInventory().addItem(item);
			}
			
			return true;
		}
		
		return false;
	}
	
}
