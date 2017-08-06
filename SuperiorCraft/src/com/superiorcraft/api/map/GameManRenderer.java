package com.superiorcraft.api.map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapFont;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

public class GameManRenderer extends MapRenderer {
	
	public static int i = 10;
	
	@Override
	public void render(MapView view, MapCanvas canvas, Player player) {
		for (int i = GameManRenderer.i; i < 100; i++) {
			canvas.setPixel(10, 10, (byte) 10); 
			GameManRenderer.i += 30;
		}
		
		//player.sendMessage("You've unlocked one of our greatest secrets...");
	}

}
