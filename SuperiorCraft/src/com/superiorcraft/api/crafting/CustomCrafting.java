package com.superiorcraft.api.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BookMeta;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.Registry;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.recipes.InOutRecipe;
import com.superiorcraft.api.recipes.UraniumFuelRodRecipe;
import com.superiorcraft.api.recipes.food.SaladRecipe;
import com.superiorcraft.api.recipes.food.SandwichRecipe;
import com.superiorcraft.api.util.Button;
import com.superiorcraft.api.util.Menu;
import com.superiorcraft.api.util.ServerUtil;
import com.superiorcraft.api.util.item.ItemConstruct;

public class CustomCrafting implements Listener {
	
	public String name;
	
	public ItemStack[] makeCrafter = new ItemStack[9];
	
	public static ArrayList<CustomCraftingRecipe> recipes = new ArrayList<CustomCraftingRecipe>();
	
	public CustomCrafting(String name) {
		this.name = name;
		
		//System.out.println(makeCrafter[0]);
		
		makeCrafter[4] = new ItemStack(Material.IRON_INGOT);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().getType().equals(Material.KNOWLEDGE_BOOK)) {
			//e.getPlayer().sendMessage("HE");
			
			e.getPlayer().openInventory(getKnowledgeMain().inv);
			e.setCancelled(true);
		}
	}
	
	public Menu getKnowledgeMain() {
		Menu m = new Menu(ChatColor.GOLD + "Book of Knowledge", 45);
		Button crafting = new Button(new ItemConstruct(Material.WORKBENCH).getMeta().setName("Recipes").getItem()) {
			
			@Override
			public void onClick(Player p, Inventory i) {
				p.openInventory(getCraftMainMenu().inv);
			}
			
		};
		m.addButton(crafting);
		m.addItem(crafting.getItem());
		Registry.registerListener(m);
		return m;
	}
	
	public Menu getCraftMainMenu() {
		Menu m = new Menu(ChatColor.GOLD + "Crafting", 45);
		Button back = new Button(new ItemConstruct(Material.WORKBENCH).getMeta().setName("Back").getItem()) {
			
			@Override
			public void onClick(Player p, Inventory i) {
				p.openInventory(getKnowledgeMain().inv);
			}
			
		};
		m.addButton(back);
		m.addItem(back.getItem(), 0);
		for (int i = 1; i < 9; i++) {
			m.addItem(new ItemConstruct(Material.STAINED_GLASS_PANE).getMeta().setName("").setData((byte) 7).getItem(), i);
		}
		ArrayList<String> ncons = new ArrayList<String>();
		for (CustomCraftingRecipe r : recipes) {
			if (r.ncon != "container.dropper" && !ncons.contains(r.ncon)) {
				Button crafting = new Button(new ItemConstruct(Material.WORKBENCH).getMeta().setName(r.ncon).getItem()) {
					
					@Override
					public void onClick(Player p, Inventory i) {
						p.openInventory(getCraftCatMenu(r.ncon).inv);
					}
					
				};
				m.addButton(crafting);
				ncons.add(r.ncon);
				m.addItem(crafting.getItem());
			}
		}
		return m;
	}
	
	public Menu getCraftCatMenu(String cat) {
		Menu m = new Menu(ChatColor.GOLD + "Crafting Guide", 45);
		Button crafting = new Button(new ItemConstruct(Material.WORKBENCH).getMeta().setName("Back").getItem()) {
			
			@Override
			public void onClick(Player p, Inventory i) {
				p.openInventory(getCraftMainMenu().inv);
			}
			
		};
		m.addButton(crafting);
		m.addItem(crafting.getItem(), 0);
		for (int i = 1; i < 9; i++) {
			m.addItem(new ItemConstruct(Material.STAINED_GLASS_PANE).getMeta().setName("").setData((byte) 7).getItem(), i);
		}
		for (CustomCraftingRecipe r : recipes) {
			if (r.ncon == cat) {
				m.addItem(r.out);
			}
		}
		//return m;
		return new CraftingGui(cat);
	}
	
	public void load() {
		Registry.addRecipe(new CustomCrafter(makeCrafter));
		Registry.addRecipe(new FoodCrafter(FoodCrafter.recipe));
		
		Registry.addRecipe(new InOutRecipe(new ItemStack[] {new ItemStack(Material.WORKBENCH), new ItemStack(Material.BOOK)}, new ItemConstruct(Material.KNOWLEDGE_BOOK).getMeta().setName(ChatColor.LIGHT_PURPLE + "Book of Knowledge").getItem(), CustomCrafter.name));
		
		Registry.addRecipe(new UraniumFuelRodRecipe(UraniumFuelRodRecipe.recipe));
		Registry.addRecipe(new SaladRecipe(SaladRecipe.recipe, CustomItem.getItem("salad")));
		Registry.addRecipe(new SaladRecipe(SandwichRecipe.recipe, CustomItem.getItem("sandwich")));
		Registry.addRecipe(new InOutRecipe(new ItemStack[] {new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.APPLE), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.GOLD_BLOCK)}, new ItemConstruct(Material.GOLDEN_APPLE).getMeta().setName(ChatColor.LIGHT_PURPLE + "Notch Apple").setData((short) 1).getItem(), FoodCrafter.name));
		Registry.addRecipe(new InOutRecipe(new ItemStack[] {null, null, null, null, new ItemConstruct(Material.INK_SACK).getMeta().setData((short) 3).getItem()}, CustomItem.getItem("chocolate"), FoodCrafter.name));
		Registry.addRecipe(new InOutRecipe(new ItemStack[] {null, null, null, null, CustomItem.getItem("chocolate"), null, null, new ItemStack(Material.MILK_BUCKET)}, CustomItem.getItem("chocolate_milk"), FoodCrafter.name));
		/*List<Recipe> recipes = new ArrayList<>();
		Bukkit.recipeIterator().forEachRemaining(recipes::add);
		for (Recipe r : recipes) {
			if (r instanceof ShapelessRecipe) {
				ItemStack[] its = new ItemStack[9];
				its = ((ShapelessRecipe) r).getIngredientList().toArray(its);
				Registry.addRecipe(new InOutRecipe(its, r.getResult()));
				//System.out.println(((ShapelessRecipe) r).getIngredientList());
				//System.out.println(r.getResult());
			}
			else if (r instanceof ShapedRecipe) {
				ItemStack[] its = new ItemStack[9];
				String shape = "";
				for (int i = 0; i < ((ShapedRecipe) r).getShape().length; i++) {
					shape += ((ShapedRecipe) r).getShape()[i];
				}
				//System.out.println(shape.length());
				for (int i2 = 0; i2 < shape.length(); i2++) {
					its[i2] = ((ShapedRecipe) r).getIngredientMap().get(shape.charAt(i2));
					//System.out.print(((ShapedRecipe) r).getIngredientMap().get(shape.charAt(i2)));
				}
				Registry.addRecipe(new InOutRecipe(its, r.getResult()));
				//System.out.println(((ShapelessRecipe) r).getIngredientList())
				//System.out.println("");
				//System.out.println(r.getResult());
			}
		}*/
	}
	
	@EventHandler
	public void onCraft(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("Crafting Guide")) {
			Menu m = null;
			for (CustomCraftingRecipe r : recipes) {
				if (r.out.equals(e.getCurrentItem())) {
					m = new Menu("Crafting Guide > " + e.getCurrentItem().getItemMeta().getDisplayName(), 27).setBackground(new ItemConstruct(Material.STAINED_GLASS_PANE).getMeta().setName("Make in " + r.ncon).setData((byte) 7).getItem());
					for (int i = 0; i < 9; i++) {
						HashMap<Integer, Integer> id = new HashMap<Integer, Integer>();
						id.put(0, 2);
						id.put(1, 3);
						id.put(2, 4);
						id.put(3, 11);
						id.put(4, 12);
						id.put(5, 13);
						id.put(6, 20);
						id.put(7, 21);
						id.put(8, 22);
						if (id.containsKey(i) && r.recipe.length > i) {
							if (r.recipe[i] != null) {
								m.addItem(r.recipe[i], id.get(i));
							} else {
								m.addItem(new ItemStack(Material.AIR), id.get(i));
							}
						} else {
							m.addItem(new ItemStack(Material.AIR), id.get(i));
						}
						m.addItem(r.out, 15);
						Button crafting = new Button(new ItemConstruct(Material.WORKBENCH).getMeta().setName("Back to Recipes").getItem()) {
							
							@Override
							public void onClick(Player p, Inventory i) {
								p.openInventory(getCraftMainMenu().inv);
							}
							
						};
						m.addButton(crafting);
						m.addItem(crafting.getItem(), 0);
					}
				}
			}
			if (m != null) {
				e.getWhoClicked().openInventory(m.inv);
			}
			e.setCancelled(true);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SuperiorCraft.plugin, new Runnable() {
			@Override
			public void run() {
				for (CustomCraftingRecipe r : recipes) {
					if (r.check(e.getInventory().getContents(), e.getInventory())) {
						r.onCraft(e);
						return;
					}
				}
			}
		}, 10);
 	}
	
	@EventHandler
	public void onCraft(InventoryCloseEvent e) {
		for (CustomCraftingRecipe r : recipes) {
			if (r.check(e.getInventory().getContents(), e.getInventory())) {
				//System.out.println();
				r.onCraft(e);
				return;
			}
		}
 	}
	
	@EventHandler
	public void onCraft(InventoryMoveItemEvent e) {
		Bukkit.broadcastMessage(e.getDestination().getName());
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SuperiorCraft.plugin, new Runnable() {
			@Override
			public void run() {
				for (CustomCraftingRecipe r : recipes) {
					if (r.check(e.getDestination().getContents(), e.getDestination())) {
						//System.out.println();
						r.onCraft(e);
						return;
					}
				}
			}
		}, 10);
	}
	
	@EventHandler
	public void onCraft(PlayerDropItemEvent e) {
		//e.getPlayer().sendMessage(e.getItemDrop().getItemStack().toString());
		/*for (CustomCraftingRecipe r : recipes) {
			//if (r.ncon.equals("ground")) {
				ArrayList<Item> items = new ArrayList<Item>();
				items.add(e.getItemDrop());
				for (Entity it : e.getItemDrop().getNearbyEntities(5, 5, 5)) {
					if (it instanceof Item) {
						items.add((Item) it);
					}
				}
				e.getPlayer().sendMessage(String.valueOf(items.size()));
			//}
		}*/
	}
	
}
