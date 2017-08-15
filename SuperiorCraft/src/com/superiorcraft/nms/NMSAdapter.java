package com.superiorcraft.nms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class NMSAdapter {
	
	public static String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replace('.', ',').split(",")[3];
	}
	
	public static Class<?> getClass(String path) {
		try {
			return Class.forName("net.minecraft.server." + NMSAdapter.getVersion() + "." + path);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Class<?> getCraftBukkitClass(String path) {
		try {
			return Class.forName("org.bukkit.craftbukkit." + NMSAdapter.getVersion() + "." + path);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object getServer() {
		try {
			Object bs = getClass("MinecraftServer").getDeclaredMethod("getServer").invoke(getClass("MinecraftServer"));
			return bs;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Object getWorld(World world) {
		try {
			Object bs = getClass("WorldServer").getDeclaredMethod("getHandle").invoke(world);
			return bs;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Object getConnection(Player player) {
		try {
			Method gh = null;
			try {
				gh = player.getClass().getMethod("getHandle");
			} catch (NoSuchMethodException | SecurityException e1) {e1.printStackTrace();}
			//System.out.println();
			return gh.invoke(player).getClass().getField("playerConnection").get(gh.invoke(player));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
				| InvocationTargetException e) {e.printStackTrace();}
		return null;
	}
	
	public static void sendPacket(Player player, Object packet) {
		Method sendPacket;
		try {
			sendPacket = getClass("PlayerConnection").getMethod("sendPacket", NMSAdapter.getClass("Packet"));
			sendPacket.invoke(NMSAdapter.getConnection(player), packet);
		} catch (Exception e) {e.printStackTrace();}
	}
	
}
