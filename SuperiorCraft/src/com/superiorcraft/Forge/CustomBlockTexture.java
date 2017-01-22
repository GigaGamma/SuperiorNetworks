package com.superiorcraft.Forge;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class CustomBlockTexture {
	
	public static final int STONE = 13;
	public static final int IRON_BLOCK = 3;
	public static final int IRON_ORE = 20;
	public static final int LAPIS_ORE = 21;
	
	public String line1n = "id:minecraft:leather_boots,Count:1,Damage:(damage),tag:{Unbreakable:1,display:{color:(color)}(extra)}";
	public String line1 = "";
	
	public String line2n = "id:minecraft:leather_boots,Count:1,Damage:(damage),tag:{Unbreakable:1,display:{color:(color)}(extra)}";
	public String line2 = "";
	
	public String line3n = "id:minecraft:leather_boots,Count:1,Damage:(damage),tag:{Unbreakable:1,display:{color:(color)}(extra)}";
	public String line3 = "";
	
	public void setLayerPrimary(int damage, String hex, String extra) {
		line1 = line1n.replace("(damage)", String.valueOf(damage)).replace("(color)", hex).replace("(extra)", extra);
	}
	
	public void setLayerSecondary(int damage, String hex, String extra) {
		line2 = line2n.replace("(damage)", String.valueOf(damage)).replace("(color)", hex).replace("(extra)", extra);
	}
	
	public void setLayerThird(int damage, String hex, String extra) {
		line3 = line3n.replace("(damage)", String.valueOf(damage)).replace("(color)", hex).replace("(extra)", extra);
	}
	
	public void placeBlock(Location l) {
		//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setblock " + l.getX() + " " + l.getY() + " " + l.getZ() + " minecraft:mob_spawner 0 replace {SpawnData:{id:minecraft:armor_stand,ArmorItems:[{},{},{},{" + line1 + "}],HandItems:[{id:minecraft:leather_boots,Count:1,Damage:17,tag:{Unbreakable:1,display:{color:5719087}}},{}],Pose:{RightArm:[30f,0f,0f],LeftArm:[30f,0f,0f]}},RequiredPlayerRange:0,MaxNearbyEntities:0}");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setblock " + l.getX() + " " + l.getY() + " " + l.getZ() + " minecraft:mob_spawner 0 replace {SpawnData:{id:minecraft:armor_stand,ArmorItems:[{},{},{},{" + line1 + "}],HandItems:[{" + line2 + "},{" + line3 + "}],Pose:{RightArm:[30f,0f,0f],LeftArm:[30f,0f,0f]}},RequiredPlayerRange:0,MaxNearbyEntities:0}");
	}
	
}
