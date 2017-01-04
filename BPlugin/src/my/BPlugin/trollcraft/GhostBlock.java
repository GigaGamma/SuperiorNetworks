package my.BPlugin.trollcraft;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GhostBlock extends CustomBlockLoader implements Listener {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean placeBlock(ArmorStand e, Player p) {
		if (!(e.getWorld().getBlockAt(e.getLocation().add(0, -1, 0)).getType().equals(Material.AIR))) {
			e.getWorld().getBlockAt(e.getLocation()).setType(e.getWorld().getBlockAt(e.getLocation().add(0, -1, 0)).getType());
			e.getWorld().getBlockAt(e.getLocation()).setData(e.getWorld().getBlockAt(e.getLocation().add(0, -1, 0)).getData());
			//p.sendMessage(e.getWorld().getBlockAt(e.getLocation().add(0, -1, 0)).getType().toString());
			return true;
		}
		else {
			return false;
		}
	}
	
	public GhostBlock(String name, String id) {
		super(name, id);
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
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		for (Entity en : e.getPlayer().getWorld().getEntities()) {
			if (en.getCustomName() != null && en.getCustomName().equals(name) && en.getLocation().distance(e.getTo()) <= 1) {
				Location l = en.getLocation();
				//e.getPlayer().sendMessage(getPlayerDirection(e.getPlayer()));
				if (getPlayerDirection(e.getPlayer()).equals("north")) {
					l.add(-1.2, 0, 0);	
				}
				else if (getPlayerDirection(e.getPlayer()).equals("south")) {
					l.add(1.2, 0, 0);	
				}
				else if (getPlayerDirection(e.getPlayer()).equals("east")) {
					l.add(0, 0, -1.2);	
				}
				else if (getPlayerDirection(e.getPlayer()).equals("west")) {
					l.add(0, 0, 1.2);	
				}
				else {
					l = e.getPlayer().getLocation();
				}
				l.setDirection(e.getPlayer().getLocation().getDirection());
				e.setTo(l);
			}
		}
	}
	
}
