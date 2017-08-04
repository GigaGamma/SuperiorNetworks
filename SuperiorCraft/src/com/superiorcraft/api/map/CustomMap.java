package com.superiorcraft.api.map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class CustomMap implements Listener {
	
	@EventHandler
	public void onMapInit(MapInitializeEvent e) {
		//e.getMap().setUnlimitedTracking(true);
		for (MapRenderer r : e.getMap().getRenderers()) {
			e.getMap().removeRenderer(r);
		}
		e.getMap().addRenderer(new GameManRenderer());
		//e.getMap().addRenderer(new MapRendererMa);
	}
	
}
