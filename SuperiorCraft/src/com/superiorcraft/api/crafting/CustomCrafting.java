package com.superiorcraft.api.crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.Registry;
import com.superiorcraft.api.items.CustomItem;
import com.superiorcraft.api.recipes.InOutRecipe;
import com.superiorcraft.api.recipes.UraniumFuelRodRecipe;
import com.superiorcraft.api.recipes.food.SaladRecipe;
import com.superiorcraft.api.recipes.food.SandwichRecipe;

public class CustomCrafting implements Listener {
	
	public String name;
	
	public ItemStack[] makeCrafter = new ItemStack[9];
	
	public static ArrayList<CustomCraftingRecipe> recipes = new ArrayList<CustomCraftingRecipe>();
	
	public CustomCrafting(String name) {
		this.name = name;
		
		//System.out.println(makeCrafter[0]);
		
		makeCrafter[4] = new ItemStack(Material.IRON_INGOT);
	}
	
	public void load() {
		Registry.addRecipe(new CustomCrafter(makeCrafter));
		Registry.addRecipe(new FoodCrafter(FoodCrafter.recipe));
		Registry.addRecipe(new UraniumFuelRodRecipe(UraniumFuelRodRecipe.recipe));
		Registry.addRecipe(new SaladRecipe(SaladRecipe.recipe, CustomItem.getItem("salad")));
		Registry.addRecipe(new SaladRecipe(SandwichRecipe.recipe, CustomItem.getItem("sandwich")));
		
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
		System.out.println(e.getInventory().getName());
		for (CustomCraftingRecipe r : recipes) {
			if (r.check(e.getInventory().getContents(), e.getInventory())) {
				//System.out.println();
				r.onCraft(e);
				return;
			}
		}
 	}
	
}
