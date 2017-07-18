package com.superiorcraft.api;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.superiorcraft.api.blocks.ColorableBlock;
import com.superiorcraft.api.blocks.CustomBlockInstance;
import com.superiorcraft.api.blocks.CustomBlockTexture;
import com.superiorcraft.api.util.Colors;

public class ElevatorInstance extends CustomBlockInstance implements ColorableBlock {
	
	private Color color = Color.WHITE;
	
	public ElevatorInstance(ArmorStand b, ArmorStand t) {
		super(b, t);
		setColor(CustomBlockTexture.extractTextureFromEntity(t).getSecondary().getColor());
	}
	
	public ElevatorInstance(ArmorStand b, ArmorStand t, CustomBlockTexture bt) {
		super(b, t, bt);
		setColor(Colors.BAMBLUE);
		//System.out.println("A");
	}
	
	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color c) {
		Location l = getTextureEntity().getLocation();
		getTextureEntity().remove();
		
		getTexture().setLayerPrimary(CustomBlockTexture.DIAMOND_BLOCK, c);
		getTexture().setLayerSecondary(CustomBlockTexture.DAYLIGHT_BOTTOM, c);
		setTextureEntity(getTexture().placeBlock(l));
		
		color = c;
	}
	
}
