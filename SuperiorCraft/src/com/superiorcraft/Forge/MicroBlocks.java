/*package com.superiorcraft.Forge;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.superiorcraft.main.Main;

public class MicroBlocks extends CustomBlockLoader {

	public MicroBlocks(String name, String id) {
		super(name, id);
	}
	
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		e.setVisible(true);
		e.setSmall(false);
		//e.setBodyPose(new EulerAngle(7.85, 0, 0));
		
		//WitherSkull ws = (WitherSkull) e.getLocation().getWorld().spawnEntity(e.getLocation().add(0.5, 1, 0.5), EntityType.WITHER_SKULL);
		//ws.setGravity(false);
		//ws.setDirection(new Vector(20, 20, 20));
		
		//FallingBlock fb = (FallingBlock) e.getLocation().getWorld().spawnFallingBlock(e.getLocation().add(0.5, 1, 0.5), Material.STONE_SLAB2, (byte) 0x0);
		//fb.setGravity(false);
		//fb.setInvulnerable(true);
		
		//ws.setPassenger(fb);
		//e.setPassenger(fb);
		//e.setCustomName("microblock");
		//e.setHelmet(new ItemStack(Material.STONE_SLAB2));
		
		return true;
	}
	
	public String getPlayerDirection(Player playerSelf){
        String dir = "";
        float y = playerSelf.getLocation().getYaw();
        if( y < 0 ){y += 360;}
        y %= 360;
        int i = (int)((y+8) / 22.5);
        if(i == 0){dir = "west";}
        else if(i == 1){dir = "west northwest";}
        else if(i == 2){dir = "northwest";}
        else if(i == 3){dir = "north northwest";}
        else if(i == 4){dir = "north";}
        else if(i == 5){dir = "north northeast";}
        else if(i == 6){dir = "northeast";}
        else if(i == 7){dir = "east northeast";}
        else if(i == 8){dir = "east";}
        else if(i == 9){dir = "east southeast";}
        else if(i == 10){dir = "southeast";}
        else if(i == 11){dir = "south southeast";}
        else if(i == 12){dir = "south";}
        else if(i == 13){dir = "south southwest";}
        else if(i == 14){dir = "southwest";}
        else if(i == 15){dir = "west southwest";}
        else {dir = "west";}
        return dir;
   }
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getPlayer().isSneaking()) {
			for (Entity en : e.getPlayer().getNearbyEntities(1, 1, 1)) {
				System.out.println(en.getLocation().distance(e.getPlayer().getTargetBlock((HashSet<Byte>) null, 7).getLocation()));
				if (en.getName().equals(this.name)) {
					System.out.println(getPlayerDirection(e.getPlayer()));
					if (getPlayerDirection(e.getPlayer()).equals("north")) {
						if (en.getPassenger() != null) {en.getPassenger().remove();}
						//en.teleport(e.getPlayer());
						
						//en.getPassenger().teleport(en.getLocation().add(-0.1, 0, 0));
						en.teleport(en.getLocation().add(-0.1, 0, 0));
						
						e.setCancelled(true);
					}
				}
			}
		}
		else if (e.getAction() == Action.RIGHT_CLICK_AIR && !e.getPlayer().isSneaking()) {
			for (Entity en : e.getPlayer().getNearbyEntities(7, 7, 7)) {
				System.out.println(en.getLocation().distance(e.getPlayer().getTargetBlock((HashSet<Byte>) null, 7).getLocation()));
				if (en.getLocation().distance(e.getPlayer().getTargetBlock((HashSet<Byte>) null, 7).getLocation()) < 2) {
					if (en.getName().equals(this.name)) {
						
						if (en.getPassenger() != null) {
							en.getPassenger().remove();
						}
						try {
							FallingBlock fb = (FallingBlock) en.getLocation().getWorld().spawnFallingBlock(en.getLocation().add(0.5, 1, 0.5), e.getPlayer().getItemInHand().getType(), e.getPlayer().getItemInHand().getData().getData());
							fb.setGravity(false);
						
							fb.setInvulnerable(true);
						
							//ws.setPassenger(fb);
							en.setPassenger(fb);
						}
						catch (Exception ex) {
							en.remove();
							
							ItemStack block = new ItemStack(Material.MONSTER_EGG, 1);
							
							ItemMeta bmeta = block.getItemMeta();
							
							bmeta.setDisplayName(this.name);
							
							block.setItemMeta(bmeta);
							
							e.getPlayer().getInventory().addItem(block);
						}
					}
				}
			}
		}
		if (e.getAction() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem() != null && e.getItem().getType() != null && e.getItem().getType().equals(Material.MONSTER_EGG) && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().getDisplayName() != null && e.getItem().getItemMeta().getDisplayName().equals(name)) {
			//e.setCancelled(true);
			
			if (e.getItem().getAmount() - 1 != 0) {
				ItemStack it = e.getItem();
				it.setAmount(e.getItem().getAmount() - 1);
				e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().first(e.getItem()), it);
			}
			
			else {
				e.getPlayer().getInventory().clear(e.getPlayer().getInventory().first(e.getItem()));
			}
			
			ArmorStand block = (ArmorStand) e.getClickedBlock().getLocation().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5, 1, 0.5), EntityType.ARMOR_STAND);
			block.setSmall(true);
			block.setGravity(false);
			block.setCustomName(name);
			block.setMarker(true);
			block.setVisible(false);
			if (placeBlock(block, e.getPlayer()) == false) {
				block.remove();
				//e.getPlayer().sendMessage("a");
				
				ItemStack it = new ItemStack(Material.MONSTER_EGG, 1);
				
				ItemMeta bmeta = it.getItemMeta();
				
				bmeta.setDisplayName(name);
				
				it.setItemMeta(bmeta);
				
				e.getPlayer().getInventory().addItem(it);
			}
		}
		
		removeBlock(e);
		onInteract(e);
	}
	
}*/
